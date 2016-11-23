package com.javaaround.rowmapper;
import com.javaaround.Domain;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class DomainRowMapper implements RowMapper<Domain> {

	@Override
	public Domain mapRow(ResultSet rs, int rowNum) throws SQLException {
		Domain domain = new Domain();
		domain.setId(rs.getInt("id"));
		domain.setDomain(rs.getString("name"));
		return domain;
	}

}