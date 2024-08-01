package org.ma5d.javamock.dao;

import com.zaxxer.hikari.HikariDataSource;
import org.ma5d.javamock.dto.SaveParam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JavaMockDao {

    public boolean saveConfig(HikariDataSource dataSource, SaveParam saveParam) {
        // Step 3: Use the connection from the pool
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM users"; // Replace with your actual query
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");

                    System.out.println("ID: " + id + ", Username: " + username + ", Password: " + password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Step 4: Close the DataSource when done
            dataSource.close();
        }
        return true;
    }
}
