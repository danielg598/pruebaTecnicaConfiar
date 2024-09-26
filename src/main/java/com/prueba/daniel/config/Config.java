package com.prueba.daniel.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class Config {
	
	@Bean(name="configDataSource")
	@ConfigurationProperties(prefix="config.one")
	DataSource ds() {
		DriverManagerDataSource dm = new DriverManagerDataSource();
		dm.setDriverClassName("org.postgresql.Driver");
		dm.setUrl("jdbc:postgresql://10.200.0.140:5432/mi_db");
		dm.setUsername("admin");
		dm.setPassword("admin123");
		return dm;
	}
	
	@Bean(name="jdbcConfig")
	JdbcTemplate jdbc (@Qualifier("configDataSource") DataSource ds) {
		return new JdbcTemplate(ds);
	}

}
