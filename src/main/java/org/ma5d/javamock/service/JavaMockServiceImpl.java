package org.ma5d.javamock.service;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.Resource;
import org.ma5d.javamock.dto.SaveParam;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public class JavaMockServiceImpl {

    @Resource
    HikariDataSource dataSource;

    public boolean saveConfig(SaveParam saveParam) throws SQLException {
        Connection connection = dataSource.getConnection();
        //language=SQLite
        String query = "insert into java_mock(adomain, path_param, response) values(?, ?, ?)";
        boolean execute = connection.prepareStatement(query, saveParam.toStringArray()).execute();
        dataSource.close();
        return execute;
    }
}
