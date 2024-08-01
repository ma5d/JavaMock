package org.ma5d.javamock.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaMockConfig {

    @Bean
    public HikariDataSource dataSource(){
        // Step 1: Configure HikariCP
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlite:./JavaMock.db");
        config.setDriverClassName("org.sqlite.JDBC");

        // Optional Hikari in-memory settings for SQLite
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(30000);
        config.setConnectionTimeout(30000);
        config.setLeakDetectionThreshold(2000);

        // Step 2: Initialize HikariCP DataSource
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }
}
