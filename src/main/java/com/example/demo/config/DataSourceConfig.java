package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
public class DataSourceConfig {

    // Primary DataSource Configuration with JPA
    @Primary
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
        EntityManagerFactoryBuilder builder,
        @Qualifier("primaryDataSource") DataSource dataSource) {
        return builder
            .dataSource(dataSource)
            .packages("com.example.demo.model")  // base package for primary datasource entities
            .persistenceUnit("primary")
            .build();
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
        @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    // Secondary DataSource 1 Configuration for JdbcTemplate
    @Bean(name = "secondary1DataSource")
    @ConfigurationProperties(prefix = "spring.ds1")
    public DataSource secondary1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondary1JdbcTemplate")
    public JdbcTemplate secondary1JdbcTemplate(@Qualifier("secondary1DataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    // Secondary DataSource 2 Configuration for JdbcTemplate
    @Bean(name = "secondary2DataSource")
    @ConfigurationProperties(prefix = "spring.ds2")
    public DataSource secondary2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondary2JdbcTemplate")
    public JdbcTemplate secondary2JdbcTemplate(@Qualifier("secondary2DataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }
}

