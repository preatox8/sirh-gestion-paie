package dev.paie.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DataSourceMySQLConfig {
	
	@Bean
	public DataSource datasource() {
	return new EmbeddedDatabaseBuilder()
	.setType(EmbeddedDatabaseType.H2)
	.build();
	}
}