package com.example.backend.database.repositories;

import com.example.backend.database.models.UserFile;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class UserFileRepository extends DataRepository<UserFile, Long> {

    @PersistenceContext(name = "default")
    private EntityManager em;

    public UserFileRepository() {
        super(UserFile.class);
    }

    public UserFile findByFilename(String filename) {
        return em.createQuery("SELECT uf FROM UserFile uf WHERE uf.name = :filename", UserFile.class)
                .setParameter("filename", filename)
                .getSingleResult();
    }

}
