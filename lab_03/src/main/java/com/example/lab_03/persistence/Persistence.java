package com.example.lab_03.persistence;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.sql.*;

@ApplicationScoped
public class Persistence {

    private Connection connection;

    @PostConstruct
    private void init() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
            connection.setAutoCommit(false);

            System.out.println("Database initialized successfully");
        } catch (Exception e) {
            System.out.println("Error initializing database");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet executeQuery(String sql, Statement statement) {
        try {
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            connection.commit();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeQueryWithoutReturn(String sql, Statement statement) {
        try {
            System.out.println(sql);
            statement.executeQuery(sql);

            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
