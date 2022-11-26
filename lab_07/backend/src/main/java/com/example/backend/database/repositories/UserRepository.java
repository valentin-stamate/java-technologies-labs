package com.example.backend.database.repositories;


import com.example.backend.database.models.User;
import com.example.backend.database.models.Document;
import com.example.backend.database.repositories.generic.DataRepository;
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

    public List<Document> getFiles() {
        try {
            return em.createQuery("SELECT c FROM Document c WHERE c.user = :user", Document.class)
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
