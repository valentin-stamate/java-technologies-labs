package com.example.lab_03.persistence.dao;

import com.example.lab_03.models.Team;
import com.example.lab_03.persistence.Persistence;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Named(value = "teamModel")
public class TeamDao {

    private static String COL_ID = "id";
    private static String COL_NAME = "name";
    private static String COL_FOUNDING_DATE = "founding_date";
    private static String COL_CITY = "city";

    @Inject
    private Persistence persistence;

    private static String MODEL_NAME = "teams";

    public List<Team> getTeams() throws SQLException {
        String sql = String.format("SELECT * FROM %s", MODEL_NAME);

        Connection connection = persistence.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet =  persistence.executeQuery(sql, statement);

        List<Team> teamList = new ArrayList<>();

        while (resultSet.next()) {
            teamList.add(new Team(
                    resultSet.getInt(COL_ID),
                    resultSet.getString(COL_NAME),
                    resultSet.getDate(COL_FOUNDING_DATE),
                    resultSet.getString(COL_CITY)
            ));
        }

        resultSet.close();
        statement.close();
        connection.close();
        return teamList;
    }

    public Team getTeam(int id) throws SQLException {
        String sql = String.format("SELECT * FROM %s WHERE id = %d", MODEL_NAME, id);

        Connection connection = persistence.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet =  persistence.executeQuery(sql, statement);

        Team team = null;

        if (resultSet.next()) {
            team = new Team(
                resultSet.getInt(COL_ID),
                resultSet.getString(COL_NAME),
                resultSet.getDate(COL_FOUNDING_DATE),
                resultSet.getString(COL_CITY)
            );
        }

        resultSet.close();
        statement.close();
        connection.close();

        return team;
    }

    public void addTeam(Team team) throws SQLException {
        String sql = String.format(
                "INSERT INTO %s (%s, %s, %s) VALUES ('%s', '%s', '%s')",
                MODEL_NAME,
                COL_NAME, COL_FOUNDING_DATE, COL_CITY,
                team.getName(), new Timestamp(team.getFoundDate().getTime()), team.getCountry());

        Connection connection = persistence.getConnection();
        Statement statement = connection.createStatement();
        persistence.executeQueryWithoutReturn(sql, statement);

        statement.close();
    }

    public void editTeam(Team team) throws SQLException {
        String sql = String.format(
                "UPDATE %s SET %s = '%s', %s = '%s', %s = '%s' WHERE %s = %d",
                MODEL_NAME,
                COL_NAME, team.getName(),
                COL_FOUNDING_DATE, new Timestamp(team.getFoundDate().getTime()),
                COL_CITY, team.getCountry(),
                COL_ID, team.getId()
        );

        Connection connection = persistence.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet =  persistence.executeQuery(sql, statement);

        resultSet.close();
        statement.close();
        connection.close();
    }

}
