package com.example.backend.database.repositories;


import com.example.backend.database.models.User;
import com.example.backend.database.models.UserFile;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserRepository extends DataRepository<User, Long> {

    @PersistenceContext(name = "default")
    private EntityManager em;

    public UserRepository() {
        super(User.class);
    }

    public List<UserFile> getFiles() {
        try {
            return em.createQuery("SELECT c FROM UserFile c WHERE c.user = :user", UserFile.class)
                    .setParameter("user", this)
                    .getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }

    public User findByUsername(String username) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
