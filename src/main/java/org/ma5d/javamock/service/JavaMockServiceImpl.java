package org.ma5d.javamock.service;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.Resource;
import org.ma5d.javamock.dao.JavaMockDao;
import org.ma5d.javamock.dto.SaveParam;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class JavaMockServiceImpl {

    @Resource
    HikariDataSource dataSource;
    @Resource
    JavaMockDao javaMockDao;

    public boolean saveConfig(SaveParam saveParam) throws SQLException {
        return javaMockDao.saveConfig(dataSource, saveParam);
    }
}
