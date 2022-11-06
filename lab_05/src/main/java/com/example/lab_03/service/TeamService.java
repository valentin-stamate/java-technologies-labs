package com.example.lab_03.service;

import com.example.lab_03.models.Team;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
@Named("teamService")
public class TeamService {

    @PersistenceContext(name = "default")
    private EntityManager entityManager;

    public Team findById(int id) {
        return entityManager.find(Team.class, id);
    }

    public List<Team> getAll() {
        return entityManager.createQuery("SELECT t FROM teams t").getResultList();
    }

    public void addTeam(Team team) {
        entityManager.persist(team);
    }

    public void editTeam(Team team) {
        entityManager.persist(team);
    }

}
