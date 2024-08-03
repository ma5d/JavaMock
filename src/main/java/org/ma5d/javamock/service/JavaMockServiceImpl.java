package org.ma5d.javamock.service;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.Resource;
import org.ma5d.javamock.dto.SaveParam;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JavaMockServiceImpl {

    @Resource
    HikariDataSource dataSource;

    public boolean saveConfig(SaveParam saveParam) throws SQLException {
        Connection connection = dataSource.getConnection();
        // language=SQLite
        String query = "insert into java_mock(time_stamp, adomain, path_param, response) values(?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query, saveParam.toStringArray());
        stmt.setString(1, saveParam.getTimeStamp());
        stmt.setString(2, saveParam.getDomainCom());
        stmt.setString(3, saveParam.getPathWithParam());
        stmt.setString(4, saveParam.getResponse());
        return stmt.execute();
    }

    public List<SaveParam> queryAllLines() throws SQLException {
        Connection connection = dataSource.getConnection();
        // language=SQLite
        String query = "select time_stamp, adomain, path_param, response from java_mock";
        ResultSet resultSet = connection.prepareStatement(query).executeQuery();
        List<SaveParam> saveParams = new ArrayList<>();
        while (resultSet.next()) {
            SaveParam saveParam = new SaveParam();
            saveParam.setTimeStamp(resultSet.getString("time_stamp"));
            saveParam.setDomainCom(resultSet.getString("adomain"));
            saveParam.setPathWithParam(resultSet.getString("path_param"));
            saveParam.setResponse(resultSet.getString("response"));
            saveParams.add(saveParam);
        }
        connection.close();
        return saveParams;
    }

    public SaveParam queryLine(String timeStamp) throws SQLException {
        Connection connection = dataSource.getConnection();
        // language=SQLite
        String query = "select time_stamp, adomain, path_param, response from java_mock where time_stamp = ?";
        ResultSet resultSet = connection.prepareStatement(query, new String[]{timeStamp}).executeQuery();
        SaveParam saveParam = new SaveParam();
        while (resultSet.next()) {
            saveParam.setTimeStamp(resultSet.getString("time_stamp"));
            saveParam.setDomainCom(resultSet.getString("adomain"));
            saveParam.setPathWithParam(resultSet.getString("path_param"));
            saveParam.setResponse(resultSet.getString("response"));
        }
        connection.close();
        return saveParam;
    }
}
