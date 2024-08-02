package org.ma5d.javamock.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class JavaMockConfig {

    @Bean
    public HikariDataSource dataSource() throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlite:./JavaMock.db");
        config.setDriverClassName("org.sqlite.JDBC");

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(30000);
        config.setConnectionTimeout(30000);
        config.setLeakDetectionThreshold(2000);

        HikariDataSource dataSource = new HikariDataSource(config);
        Connection connection = dataSource.getConnection();
        //language=SQLite
        String sql = "create table if not exists " +
                "java_mock(" +
                    "time_stamp varchar(50) /*时间戳*/, " +
                    "adomain varchar(20) /*域名*/, " +
                    "path_param varchar(200) /*路径与参数*/, " +
                    "response varchar(2000) /*返回值*/" +
                ")";
        connection.prepareStatement(sql).execute();
        connection.close();
        return dataSource;
    }
}
