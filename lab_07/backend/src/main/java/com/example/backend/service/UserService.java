package com.example.backend.service;

import com.example.backend.classes.ResponseMessage;
import com.example.backend.database.models.User;
import com.example.backend.database.models.UserFile;
import com.example.backend.database.repositories.UserFileRepository;
import com.example.backend.database.repositories.UserRepository;
import com.example.backend.database.repositories.UserType;
import com.example.backend.service.exception.ServiceException;
import com.example.backend.service.jwt.JwtService;
import com.example.backend.service.jwt.UserJwtPayloadService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.Map;

@ApplicationScoped
public class UserService implements Serializable {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserFileRepository userFileRepository;

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

    private void uploadFile(String username, String filename, byte[] buffer) {
        User existingUser = userRepository.findByUsername(username);
        UserFile userFile = new UserFile(filename, existingUser.getUsername(), buffer, existingUser);

        existingUser.addFile(userFile);

        userFileRepository.persist(userFile);
        userRepository.persist(existingUser);
    }

    private void removeFile(String username, String filename) {
        User existingUser = userRepository.findByUsername(username);
        UserFile existingUserFile = userFileRepository.findByFilename(filename);

        existingUser.removeFile(existingUserFile);
    }

}
