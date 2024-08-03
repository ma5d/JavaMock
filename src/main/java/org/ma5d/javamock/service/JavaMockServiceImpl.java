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
@SuppressWarnings("UnusedReturnValue")
public class JavaMockServiceImpl {

    @Resource
    HikariDataSource dataSource;


    public boolean saveConfig(SaveParam saveParam) throws SQLException {
        Connection connection = dataSource.getConnection();
        // language=SQLite
        String query = "replace into java_mock(time_stamp, adomain, path_param, response) values(?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, saveParam.getTimeStamp());
        stmt.setString(2, saveParam.getDomainCom());
        stmt.setString(3, saveParam.getPathWithParam());
        stmt.setString(4, saveParam.getResponse());
        boolean execute = stmt.execute();
        connection.close();
        return execute;
    }

    public List<SaveParam> queryAllLines() throws SQLException {
        Connection connection = dataSource.getConnection();
        // language=SQLite
        String query = "select time_stamp, adomain, path_param, response from java_mock";
        ResultSet resultSet = connection.prepareStatement(query).executeQuery();
        List<SaveParam> saveParams = new ArrayList<>();
        while (resultSet.next()) {
            SaveParam saveParam = new SaveParam(resultSet);
            saveParams.add(saveParam);
        }
        connection.close();
        return saveParams;
    }

    public SaveParam queryLine(String timeStamp) throws SQLException {
        Connection connection = dataSource.getConnection();
        // language=SQLite
        String query = "select time_stamp, adomain, path_param, response from java_mock where time_stamp = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, timeStamp);
        ResultSet resultSet = preparedStatement.executeQuery();
        SaveParam saveParam = new SaveParam();
        while (resultSet.next()) {
            saveParam = new SaveParam(resultSet);
        }
        connection.close();
        return saveParam;
    }

    public SaveParam getSaveParamByPathWithParam(String pathWithParam) throws SQLException {
        Connection connection = dataSource.getConnection();
        // language=SQLite
        String query = "select time_stamp, adomain, path_param, response from java_mock where path_param = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, pathWithParam);
        ResultSet resultSet = preparedStatement.executeQuery();
        SaveParam saveParam = new SaveParam();
        while (resultSet.next()) {
            saveParam = new SaveParam(resultSet);
        }
        connection.close();
        return saveParam;
    }

    public boolean deleteByTimeStamp(String id) throws SQLException {
        Connection connection = dataSource.getConnection();
        // language=SQLite
        String query = "delete from java_mock where time_stamp = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, id);
        boolean execute = preparedStatement.execute();
        connection.close();
        return execute;
    }
}
