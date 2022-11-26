package com.example.backend.service;

import com.example.backend.classes.ResponseMessage;
import com.example.backend.database.models.User;
import com.example.backend.database.models.Document;
import com.example.backend.database.repositories.DocumentRepository;
import com.example.backend.database.repositories.UserRepository;
import com.example.backend.database.repositories.UserType;
import com.example.backend.service.exception.ServiceException;
import com.example.backend.service.jwt.JwtService;
import com.example.backend.service.jwt.UserJwtPayloadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService implements Serializable {

    @Inject
    private UserRepository userRepository;

    @Inject
    private DocumentRepository documentRepository;

    /* ------------================= VISITOR =================------------ */

    public String loginUser(String username, String password) throws ServiceException {
        User existingUser = userRepository.findByUsername(username);

        if (existingUser == null) {
            throw new ServiceException(ResponseMessage.USER_NOT_FOUND);
        }

        if (!existingUser.getPassword().equals(password)) {
            throw new ServiceException(ResponseMessage.INVALID_CREDENTIALS);
        }

        Map<String, Object> payload = UserJwtPayloadService.payloadFromUser(existingUser);
        String token = JwtService.sign(payload);

        if (token == null) {
            throw new ServiceException(ResponseMessage.ERROR_GENERATING_TOKEN);
        }

        return token;
    }

    public String signupUser(String username, String password, UserType userType) throws ServiceException {
        User existingUser = userRepository.findByUsername(username);

        if (existingUser != null) {
            throw new ServiceException(ResponseMessage.USER_ALREADY_EXISTS);
        }

        User newUser = new User(username, password, userType);

        userRepository.persist(newUser);

        Map<String, Object> payload = UserJwtPayloadService.payloadFromUser(newUser);
        String token = JwtService.sign(payload);

        if (token == null) {
            throw new ServiceException(ResponseMessage.ERROR_GENERATING_TOKEN);
        }

        return token;
    }

    /* ------------================= ADMIN =================------------ */

    /* ------------================= AUTHOR =================------------ */

    public List getFiles(String username) throws ServiceException {
        User user = userRepository.findByUsername(username);
        return documentRepository.getUserFilesWithoutBuffer(user);
    }

    public void uploadFile(String username, String filename, InputStream file) throws ServiceException {
        User existingUser = userRepository.findByUsername(username);

        byte[] buffer;

        try {
            buffer = file.readAllBytes();
        } catch (Exception e) {
            throw new ServiceException(ResponseMessage.CANNOT_READ_FILE);
        }

        Document document = new Document(filename, existingUser.getUsername(), buffer, buffer.length, existingUser);

        existingUser.addFile(document);

        documentRepository.persist(document);
        userRepository.update(existingUser);
    }

    public InputStream getDocumentBuffer(String username, String documentId) throws ServiceException {
        User existingUser = userRepository.findByUsername(username);
        List<Document> documentList = existingUser.getFiles().stream()
                .filter(doc -> doc.getDocumentId().equals(documentId))
                .collect(Collectors.toList());

        if (documentList.size() == 0) {
            throw new ServiceException(ResponseMessage.FILE_NOT_FOUND);
        }

        Document document = documentList.get(0);

        return new ByteArrayInputStream(document.getFile());

    }

    public void removeFile(String username, String fileIdentifier) throws ServiceException {
        User existingUser = userRepository.findByUsername(username);
        Document existingDocument = documentRepository.findByFileIdentifier(fileIdentifier);

        if (existingDocument == null) {
            throw new ServiceException(ResponseMessage.FILE_NOT_FOUND);
        }

        if (!existingDocument.getUser().getId().equals(existingUser.getId())) {
            throw new ServiceException(ResponseMessage.FILE_NOT_OWN_BY_USER);
        }

        documentRepository.remove(existingDocument);
    }

    /* ------------================= REVIEWER =================------------ */

}
