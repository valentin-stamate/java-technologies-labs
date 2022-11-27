package com.example.backend.database.repositories;

import com.example.backend.database.models.Document;
import com.example.backend.database.models.User;
import com.example.backend.database.repositories.generic.DataRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class DocumentRepository extends DataRepository<Document, Long> {

    @PersistenceContext(name = "default")
    private EntityManager em;

    public DocumentRepository() {
        super(Document.class);
    }

    public Document findByFilename(String filename) {
        try {
            return em.createQuery("SELECT d FROM Document d WHERE d.name = :filename", Document.class)
                    .setParameter("filename", filename)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Document findByFileIdentifier(String fileIdentifier) {
        try {
            return em.createQuery("SELECT d FROM Document d WHERE d.documentId = :fileId", Document.class)
                    .setParameter("fileId", fileIdentifier)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Document> getUserFilesWithoutBuffer(User user) {
        List<Object[]> list = em.createQuery("SELECT d.id, d.documentId, d.name, d.size, d.author FROM Document d WHERE d.user = :user", Object[].class)
                .setParameter("user", user)
                .getResultList();

        return list.stream().map(o -> {
            Document d = new Document();
            d.setId((Long) o[0]);
            d.setDocumentId((String) o[1]);
            d.setName((String) o[2]);
            d.setSize((Long) o[3]);
            d.setAuthor((String) o[4]);

            return d;
        }).collect(Collectors.toList());
    }

    public List<Document> getAllFilesWithoutBuffer() {
        List<Object[]> list = em.createQuery("SELECT d.id, d.documentId, d.name, d.size, d.author FROM Document d", Object[].class)
                .getResultList();

        return list.stream().map(o -> {
            Document d = new Document();
            d.setId((Long) o[0]);
            d.setDocumentId((String) o[1]);
            d.setName((String) o[2]);
            d.setSize((Long) o[3]);
            d.setAuthor((String) o[4]);

            return d;
        }).collect(Collectors.toList());
    }

    public List<Document> getUserFiles(User user) {
        return em.createQuery("SELECT d FROM Document d WHERE d.user = :user", Document.class)
                .setParameter("user", user)
                .getResultList();
    }

}