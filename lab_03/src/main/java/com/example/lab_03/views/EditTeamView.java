package com.example.lab_03.views;

import com.example.lab_03.models.Team;
import com.example.lab_03.persistence.dao.TeamDao;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.*;

@ViewScoped
@Named
public class EditTeamView implements Serializable {

    @Inject
    private TeamDao teamDao;

    private int id;
    private String name;
    private Date foundingDate;
    private String country;
    private List<String> countries = Arrays.asList("Romania", "Italia", "Spain");

    @PostConstruct
    public void init() {
        Map<String, String> parameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int teamId = Integer.parseInt(parameters.get("id"));
        this.id = teamId;

        try {
            Team team = teamDao.getTeam(teamId);

            name = team.getName();
            foundingDate = team.getFoundDate();
            country = team.getCountry();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onEditTeam() throws SQLException {
        teamDao.editTeam(new Team(this.id, this.name, this.foundingDate, this.country));
    }

    public void onAddTeam() throws SQLException {
        teamDao.addTeam(new Team(this.id, this.name, this.foundingDate, this.country));
    }

    public void onTeamChange() { }

    public List<String> getCountries() {
        return countries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(Date foundingDate) {
        this.foundingDate = foundingDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
