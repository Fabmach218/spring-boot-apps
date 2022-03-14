package fia.usmp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import fia.usmp.model.Especialidad;

@Component
public class EspecialidadDAO implements IEspecialidadDAO{

	
	private static final Logger log = LoggerFactory.getLogger(EspecialidadDAO.class);
	private JdbcTemplate jdbcTemplate;
	
	public EspecialidadDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Especialidad> getEspecialidadesEmpleado(String dni) {
		
		String sql = "SELECT E.CATEGORIA, C.NOMCAT, (CASE WHEN E.NOTAS IS NOT NULL THEN E.NOTAS ELSE '-' END) NOTAS FROM ESPECIALIDAD E, CATEGORIA C WHERE E.EMPLEADO = '" + dni + "' AND E.CATEGORIA = C.CODCAT";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Especialidad>>() {

			@Override
			public List<Especialidad> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<Especialidad> lista = new ArrayList<Especialidad>();
				Especialidad e = null;
				
				try {
					
					while(rs.next()) {
						e = new Especialidad(rs.getString(1), rs.getString(2), rs.getString(3));
						lista.add(e);
					}
					
					
				}catch(Exception ex) {
					
				}
				
				return lista;
				
			}
			
		});
		
		
	}

	@Override
	public Especialidad getEspecialidadByCod(String dni, String cod) {
		
		String sql = "SELECT C.CODCAT, C.NOMCAT, E.NOTAS FROM CATEGORIA C, ESPECIALIDAD E WHERE C.CODCAT = E.CATEGORIA AND CODCAT = '" + cod + "' AND EMPLEADO = '" + dni + "'";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Especialidad>() {

			@Override
			public Especialidad extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Especialidad e = null;
				
				try {
					
					while(rs.next()) {
						e = new Especialidad(rs.getString(1), rs.getString(2), rs.getString(3));
					}
					
					
				}catch(Exception ex) {
					
				}
				
				return e;
				
			}
			
		});
		
	}
	
	@Override
	public boolean existeEspecialidad(String dni, String nomEsp) {
		
		String sql = "SELECT E.CATEGORIA, C.NOMCAT, (CASE WHEN E.NOTAS IS NOT NULL THEN E.NOTAS ELSE '-' END) NOTAS FROM ESPECIALIDAD E, CATEGORIA C WHERE E.EMPLEADO = '" + dni + "' AND E.CATEGORIA = C.CODCAT AND C.NOMCAT = '" + nomEsp + "'";
		
		Especialidad e = jdbcTemplate.query(sql, new ResultSetExtractor<Especialidad>() {

			@Override
			public Especialidad extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Especialidad e = null;
				
				try {
					
					while(rs.next()) {
						e = new Especialidad(rs.getString(1), rs.getString(2), rs.getString(3));
					}
					
					
				}catch(Exception ex) {
					
				}
				
				return e;
				
			}
			
		});
		
		if(e == null) {
			return false;
		}else {
			return true;
		}
		
		
	}
	
	@Override
	public void ingresarEspecialidad(String dni, Especialidad e) {
		
		String sql = "INSERT INTO ESPECIALIDAD VALUES ('" + dni + "',(SELECT CODCAT FROM CATEGORIA WHERE NOMCAT = '" + e.getNom() + "'),'" + e.getNotas() + "')";
		
		try {
			jdbcTemplate.update(sql);
		}catch(Exception ex) {
			
		}
		
	}

	@Override
	public void editarEspecialidad(String dni, Especialidad e) {
		
		String sql = "UPDATE ESPECIALIDAD SET\n"
				+ "NOTAS = '" + e.getNotas() + "'"
				+ "WHERE EMPLEADO = '" + dni + "' AND CATEGORIA = (SELECT CODCAT FROM CATEGORIA WHERE NOMCAT = '" + e.getNom() + "')";
		
		try {
			jdbcTemplate.update(sql);
		}catch(Exception ex) {
			
		}
		
	}

	@Override
	public void eliminarEspecialidad(String dni, String nomEsp) {
		
		String sql = "DELETE FROM ESPECIALIDAD WHERE EMPLEADO = '" + dni + "' AND CATEGORIA = (SELECT CODCAT FROM CATEGORIA WHERE NOMCAT = '" + nomEsp + "')";
		
		try {
			jdbcTemplate.update(sql);
		}catch(Exception ex) {
			
		}
		
	}

}
