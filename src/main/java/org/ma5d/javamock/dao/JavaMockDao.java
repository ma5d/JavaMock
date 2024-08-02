package org.ma5d.javamock.dao;

import com.zaxxer.hikari.HikariDataSource;
import org.ma5d.javamock.dto.SaveParam;

import java.sql.Connection;
import java.sql.SQLException;

public class JavaMockDao {

    public boolean saveConfig(HikariDataSource dataSource, SaveParam saveParam) throws SQLException {
        Connection connection = dataSource.getConnection();
        String query = "insert into java_mock(adomain, path_param, response) values(?, ?, ?)";
        boolean execute = connection.prepareStatement(query, saveParam.toStringArray()).execute();
        dataSource.close();
        return execute;
    }
}
