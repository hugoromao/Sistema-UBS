package com.osouza.teste.utils;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

@Component
@Configuration
public class FlywayMigrationWrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final Flyway flyway;

    public FlywayMigrationWrapper(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password
    ) {
        this.flyway = Flyway.configure()
                .cleanDisabled(false)
                .dataSource(buildDatasource(url, username, password)).load();
    }

    private DataSource buildDatasource(String url, String username, String password) {
        return DataSourceBuilder.create().url(url).username(username).password(password).build();
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // Executa as migrations do Flyway após a criação das tabelas pelo Hibernate
        this.flyway.migrate();
    }
}