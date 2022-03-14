package fia.usmp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import fia.usmp.model.Usuario;

@Component
public class UsuarioDAO implements IUsuarioDAO{

	private static final Logger log = LoggerFactory.getLogger(UsuarioDAO.class);
	private JdbcTemplate jdbcTemplate;
	
	public UsuarioDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void iniciarSesion(Usuario u) {
		
		String sql = "UPDATE USUARIO SET LOG = 'S' WHERE NOMUSU = '" + u.getNom() + "'";	
		
		try {
			jdbcTemplate.update(sql);
		}catch(Exception e) {
			
		}
		
	}

	@Override
	public void cerrarSesion(Usuario u) {

		String sql = "UPDATE USUARIO SET LOG = 'N' WHERE NOMUSU = '" + u.getNom() + "'";	
		
		try {
			jdbcTemplate.update(sql);
		}catch(Exception e) {
			
		}
		
	}

	@Override
	public boolean existeSesionActiva() {
		
		String sql = "SELECT COUNT(*) FROM USUARIO WHERE LOG = 'S'";
		
		int c = jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Integer c = null;
				
				try {
					
					while(rs.next()) {
						
						c = rs.getInt(1);
						
					}
					
				}catch (Exception e) {
					
				}
				
				return c;
				
			}
			
			
		});
		
		if(c == 1) {
			return true;
		}else {
			return false;
		}
		
		
	}

	@Override
	public Usuario getUsuarioActivo() {
		
		String sql = "SELECT * FROM USUARIO WHERE LOG = 'S'";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Usuario>() {

			@Override
			public Usuario extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Usuario u = null;
				
				try {
					
					while(rs.next()) {
						
						u = new Usuario(rs.getString(1), rs.getString(2), rs.getString(3).charAt(0));
						
					}
					
				}catch (Exception e) {
					
				}
				
				return u;
				
			}
			
			
		});
		
	}

	@Override
	public boolean existeUsuario(String nom) {
		
		String sql = "SELECT NOMUSU FROM USUARIO WHERE NOMUSU = '" + nom + "'";
		String n = "";
		
		try {
			
			n = jdbcTemplate.query(sql, new ResultSetExtractor<String>() {

				@Override
				public String extractData(ResultSet rs) throws SQLException, DataAccessException {
					
					String n = "";
					
					try {
						
						while(rs.next()) {
							
							n = rs.getString(1);
							
						}
						
					}catch (Exception e) {
						
					}
					
					return n;
					
				}
				
				
			});
			
			
		}catch (Exception e){
			
		}
		
		if(nom.equals(n)) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public boolean validarCredenciales(String nom, String password) {
		
		String sql = "SELECT NOMUSU, CONTRASENA FROM USUARIO WHERE NOMUSU = '" + nom + "'";
		String n1 = "", p1 = "";
		
		try {
			
			n1 = jdbcTemplate.query(sql, new ResultSetExtractor<String>() {

				@Override
				public String extractData(ResultSet rs) throws SQLException, DataAccessException {
					
					String n1 = "";
					
					try {
						
						while(rs.next()) {
							
							n1 = rs.getString(1);
							
						}
						
					}catch (Exception e) {
						
					}
					
					return n1;
					
				}
				
				
			});
			
			p1 = jdbcTemplate.query(sql, new ResultSetExtractor<String>() {

				@Override
				public String extractData(ResultSet rs) throws SQLException, DataAccessException {
					
					String p1 = "";
					
					try {
						
						while(rs.next()) {
							
							p1 = rs.getString(2);
							
						}
						
					}catch (Exception e) {
						
					}
					
					return p1;
					
				}
				
				
			});
			
			
		}catch (Exception e){
			
		}
		
		if(nom.equals(n1) && password.equals(p1)) {
			return true;
		}else {
			return false;
		}
		
		
		
	}

}
