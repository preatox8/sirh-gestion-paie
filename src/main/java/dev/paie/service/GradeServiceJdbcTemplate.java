package dev.paie.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dev.paie.entite.Grade;

@Service
public class GradeServiceJdbcTemplate implements GradeService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public GradeServiceJdbcTemplate(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void sauvegarder(Grade nouveauGrade) {
		String sqlInsert = "INSERT INTO grade (CODE,NBHEURESBASE, TAUXBASE) VALUES(?,?,?)";
		jdbcTemplate.update(sqlInsert, nouveauGrade.getCode(), nouveauGrade.getNbHeuresBase(),nouveauGrade.getTauxBase());
	}

	public void mettreAJour(Grade grade) {
		String sqlUpdate = "UPDATE grade SET CODE = ? , NBHEURESBASE = ? , TAUXBASE = ? WHERE ID = ?";
		jdbcTemplate.update(sqlUpdate, grade.getCode(), grade.getNbHeuresBase(), grade.getTauxBase(), grade.getId());
	}

	public List<Grade> lister() {
		String vSQL = "SELECT * FROM grade";
		return jdbcTemplate.query(vSQL, new GradeMapper());
	}

	public class GradeMapper implements RowMapper<Grade> {
		// cette méthode est invoquée pour chaque ligne de résultat SQL
		public Grade mapRow(ResultSet rs, int rowNum) throws SQLException {
			Grade p = new Grade();
			p.setCode(rs.getString("CODE"));
			p.setNbHeuresBase(new BigDecimal(rs.getString("NBHEURESBASE")));
			p.setTauxBase(new BigDecimal(rs.getString("TAUXBASE")));
			return p;
		}
	}

}
