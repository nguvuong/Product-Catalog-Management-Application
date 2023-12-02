package ca.sheridan.nguvuong.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class ProductsDatabaseConfiguration {
	//Used in the DatabaseAccess class to submit JDBC Query Strings
	@Bean
	public NamedParameterJdbcTemplate
	namedParameterJdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
}

