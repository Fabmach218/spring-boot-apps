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

import fia.usmp.model.Estudiante;

@Component
public class EstudianteDAO implements IEstudianteDAO{
	
	private static final Logger log = LoggerFactory.getLogger(EstudianteDAO.class);
	private JdbcTemplate jdbcTemplate;

	private String filtro;
	
	@Override
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	public EstudianteDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Estudiante> getEstudiantes() {
		
		String sql = "SELECT * FROM ALUMNOS";
		
		if(filtro != null) {
			sql += " WHERE " + filtro;
		}
		
		sql += " ORDER BY CODIGO";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Estudiante>>() {

			@Override
			public List<Estudiante> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<Estudiante> lista = new ArrayList<Estudiante>();
				Estudiante e;
				
				try {
					
					while(rs.next()) {
						
						e = new Estudiante();
						
						e.setCod(rs.getString(1));
						e.setNom(rs.getString(2));
						e.setSeccion(rs.getString(3));
						e.setCorreo(rs.getString(4));
						e.setDis(rs.getString(5));
						e.setProv(rs.getString(6));
						e.setDep(rs.getString(7));
						e.setDni(rs.getString(8));
						e.setModing(rs.getString(9));
						e.setSeming(rs.getString(10));
						
						lista.add(e);
						
					}
					
				}catch (Exception ex) {
					lista.add(new Estudiante("", "", "", "", "", "", "", "", "", ""));
				}
				
				return lista;
				
			}
			
			
		});
		
	}

	@Override
	public int getCantidad() {
		
		String sql = "SELECT COUNT(*) FROM ALUMNOS";
		
		if(filtro != null) {
			sql += " WHERE " + filtro;
		}
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Integer cant = null;
				
				try {
					
					while(rs.next()) {
						cant = rs.getInt(1);
					}
					
				}catch (Exception e) {
					
				}
				
				return cant;
				
			}
		});
	}
	
	@Override
	public String getPK() {
		
		String sql = "SELECT MAX(CODIGO) FROM ALUMNOS";
		
		String pk = jdbcTemplate.query(sql, new ResultSetExtractor<String>() {
			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				String pk = null;
				
				try {
					
					while(rs.next()) {
						pk = rs.getString(1);
					}
					
				}catch (Exception e) {
					
				}
				
				return pk;
				
			}
		});
		
		int num = Integer.parseInt(pk.substring(1, pk.length()));
		num = num + 1;
		String cadNum = num + "";
		
		while(cadNum.length() < 7) {
			cadNum = "0" + cadNum;
		}
		
		pk = "E" + cadNum;
		
		return pk;
		
	}

	@Override
	public void registrarEstudiante(Estudiante e) {
		
		try {
			
			String sql = "INSERT INTO ALUMNOS VALUES(?,?,?,?,?,?,?,?,?,?)";
			
			Object[] arg = {e.getCod(), e.getNom(), e.getSeccion(), e.getCorreo(), e.getDis(), e.getProv(), e.getDep(), e.getDni(), e.getModing(), e.getSeming()};
			
			jdbcTemplate.update(sql, arg);
			
		}catch (Exception ex) {
			
		}
		
	}
	
	@Override
	public List<Estudiante> buscarEstudiantes(String nom){
		
		String sql = "SELECT * FROM ALUMNOS WHERE ESTUDIANTE LIKE '%" + nom.toUpperCase() + "%'";
		
		sql += " ORDER BY CODIGO";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Estudiante>>() {

			@Override
			public List<Estudiante> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<Estudiante> lista = new ArrayList<Estudiante>();
				Estudiante e;
				
				try {
					
					while(rs.next()) {
						
						e = new Estudiante();
						
						e.setCod(rs.getString(1));
						e.setNom(rs.getString(2));
						e.setSeccion(rs.getString(3));
						e.setCorreo(rs.getString(4));
						e.setDis(rs.getString(5));
						e.setProv(rs.getString(6));
						e.setDep(rs.getString(7));
						e.setDni(rs.getString(8));
						e.setModing(rs.getString(9));
						e.setSeming(rs.getString(10));
						
						lista.add(e);
						
					}
					
				}catch (Exception ex) {
					lista.add(new Estudiante("", "", "", "", "", "", "", "", "", ""));
				}
				
				return lista;
				
			}
			
			
		});
		
	}
	
	@Override
	public int getCantidadResultadosBusqueda(String nom) {
		
		String sql = "SELECT COUNT(*) FROM ALUMNOS WHERE ESTUDIANTE LIKE '%" + nom.toUpperCase() + "%'";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Integer cant = null;
				
				try {
					
					while(rs.next()) {
						cant = rs.getInt(1);
					}
					
				}catch (Exception e) {
					
				}
				
				return cant;
				
			}
		});
		
		
	}
	
	@Override
	public Estudiante getEstudianteByCod(String cod) {
		
		Estudiante e = null;
			
		String sql = "SELECT * FROM ALUMNOS WHERE CODIGO = '" + cod + "'";
			
		return jdbcTemplate.query(sql, new ResultSetExtractor<Estudiante>() {
			
			@Override
			public Estudiante extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Estudiante e = null;
				
				try {
					
					while(rs.next()) {
						e = new Estudiante();
						e.setCod(rs.getString(1));
						e.setNom(rs.getString(2));
						e.setSeccion(rs.getString(3));
						e.setCorreo(rs.getString(4));
						e.setDis(rs.getString(5));
						e.setProv(rs.getString(6));
						e.setDep(rs.getString(7));
						e.setDni(rs.getString(8));
						e.setModing(rs.getString(9));
						e.setSeming(rs.getString(10));
					}
					
				}catch (Exception ex) {
					
				}
				
				return e;
				
			}
		});
		
		
	}
	
	@Override
	public void actualizarEstudiante(Estudiante e) {
		
		try {
			
			String sql = "UPDATE ALUMNOS SET "
					+ "ESTUDIANTE = '" + e.getNom() + "', "
					+ "SECCIÓN = '" + e.getSeccion() + "', "
					+ "CORREO_USMP = '" + e.getCorreo() + "', "
					+ "DISTRITO = '" + e.getDis() + "', "
					+ "PROVINCIA = '" + e.getProv() + "', "
					+ "DEPARTAMENTO = '" + e.getDep() + "', "
					+ "DNI = '" + e.getDni() + "', "
					+ "MOD_INGRESO = '" + e.getModing() + "', "
					+ "SEM_INGRESO = '" + e.getSeming() + "' "
					+ "WHERE CODIGO = '" + e.getCod() + "'";
			
			jdbcTemplate.update(sql);
			
		}catch (Exception ex) {
			
		}
		
	}

	@Override
	public void eliminarEstudiante(String cod) {
		
		try {
			
			String sql = "DELETE FROM ALUMNOS WHERE CODIGO = '" + cod + "'";
			
			jdbcTemplate.update(sql);
			
		}catch (Exception e) {
			
		}
		
		
		
	}
	
	
	
	
}
