package com.example.lab_03.persistence;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import java.sql.*;

@ApplicationScoped
public class Persistence {

    @Resource(name = "jdbc/__default")
    private DataSource dataSource;

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public ResultSet executeQuery(String sql, Statement statement) {
        try {
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeQueryWithoutReturn(String sql, Statement statement) {
        try {
            System.out.println(sql);
            statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
