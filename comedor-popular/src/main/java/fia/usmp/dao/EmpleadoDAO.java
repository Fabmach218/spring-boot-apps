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

import fia.usmp.model.Empleado;

@Component
public class EmpleadoDAO implements IEmpleadoDAO{

	private static final Logger log = LoggerFactory.getLogger(EmpleadoDAO.class);
	private JdbcTemplate jdbcTemplate;
	
	public EmpleadoDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Empleado> getEmpleados() {
		
		String sql = "SELECT\n"
				+ "P.NUMDNI,\n"
				+ "P.APEPAT,\n"
				+ "P.APEMAT,\n"
				+ "P.NOMBRE,\n"
				+ "C.NOMCAR,\n"
				+ "E.SUELDO,\n"
				+ "E.FECINGRESO,\n"
				+ "TRUNC((TO_NUMBER(TO_CHAR(SYSDATE,'YYYYMMDD')) - TO_NUMBER(TO_CHAR(P.FECNAC, 'YYYYMMDD'))) / 10000) EDAD,\n"
				+ "P.FECNAC,\n"
				+ "(CASE WHEN P.SEXO = 'M' THEN 'Masculino' ELSE 'Femenino' END) SEXO,\n"
				+ "P.NUMCEL\n"
				+ "FROM PERSONA P, EMPLEADO E, CARGO C WHERE P.NUMDNI = E.DNIEMP AND E.CARGO = C.CODCAR\n"
				+ "ORDER BY P.APEPAT";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Empleado>>() {

			@Override
			public List<Empleado> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<Empleado> lista = new ArrayList<Empleado>();
				Empleado e;
				
				try {
					
					while(rs.next()) {
						
						e = new Empleado(rs.getString(1),
										 rs.getString(2),
										 rs.getString(3),
										 rs.getString(4),
										 rs.getString(5),
										 rs.getDouble(6),
										 rs.getDate(7),
										 rs.getInt(8),
										 rs.getDate(9),
										 rs.getString(10),
										 rs.getString(11));
						
						lista.add(e);
						
					}
					
				}catch (Exception ex) {
					
				}
				
				return lista;
				
			}
			
		});
		
		
	}

	@Override
	public List<Empleado> filtrarEmpleados(String nom, String sexo, String cargo){
		
		String sql = "SELECT\n"
				+ "P.NUMDNI,\n"
				+ "P.APEPAT,\n"
				+ "P.APEMAT,\n"
				+ "P.NOMBRE,\n"
				+ "C.NOMCAR,\n"
				+ "E.SUELDO,\n"
				+ "E.FECINGRESO,\n"
				+ "TRUNC((TO_NUMBER(TO_CHAR(SYSDATE,'YYYYMMDD')) - TO_NUMBER(TO_CHAR(P.FECNAC, 'YYYYMMDD'))) / 10000) EDAD,\n"
				+ "P.FECNAC,\n"
				+ "(CASE WHEN P.SEXO = 'M' THEN 'Masculino' ELSE 'Femenino' END) SEXO,\n"
				+ "P.NUMCEL\n"
				+ "FROM PERSONA P, EMPLEADO E, CARGO C WHERE P.NUMDNI = E.DNIEMP AND E.CARGO = C.CODCAR";
				
		if(!nom.equals("")) {
			sql += " AND (UPPER(P.APEPAT) LIKE '%" + nom.toUpperCase() + "%' OR UPPER(P.APEMAT) LIKE '%" + nom.toUpperCase() + "%' OR UPPER(P.NOMBRE) LIKE '%" + nom.toUpperCase() + "%')";
		}
		
		if(!sexo.equals("")) {
			sql += " AND P.SEXO = '" + sexo.charAt(0) + "'";
		}
		
		if(!cargo.equals("")) {
			sql += " AND C.NOMCAR = '" + cargo + "'";
		}
		
		sql += "\nORDER BY P.APEPAT";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Empleado>>() {

			@Override
			public List<Empleado> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<Empleado> lista = new ArrayList<Empleado>();
				Empleado e;
				
				try {
					
					while(rs.next()) {
						
						e = new Empleado(rs.getString(1),
										 rs.getString(2),
										 rs.getString(3),
										 rs.getString(4),
										 rs.getString(5),
										 rs.getDouble(6),
										 rs.getDate(7),
										 rs.getInt(8),
										 rs.getDate(9),
										 rs.getString(10),
										 rs.getString(11));
						
						lista.add(e);
						
					}
					
				}catch (Exception ex) {
					
				}
				
				return lista;
				
			}
			
		});
		
	}
	
	@Override
	public List<String> getCargos(){
		
		String sql = "SELECT NOMCAR FROM CARGO ORDER BY 1";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<String> lista = new ArrayList<String>();
				
				try {
					
					while(rs.next()) {
						lista.add(rs.getString(1));
					}
					
				}catch(Exception e) {
					
				}
				
				return lista;
				
			}
			
		});
		
	}
	
	
	@Override
	public Empleado getEmpleadoByDni(String dni) {
		
		String sql = "SELECT\n"
				+ "P.NUMDNI,\n"
				+ "P.APEPAT,\n"
				+ "P.APEMAT,\n"
				+ "P.NOMBRE,\n"
				+ "C.NOMCAR,\n"
				+ "E.SUELDO,\n"
				+ "E.FECINGRESO,\n"
				+ "TRUNC((TO_NUMBER(TO_CHAR(SYSDATE,'YYYYMMDD')) - TO_NUMBER(TO_CHAR(P.FECNAC, 'YYYYMMDD'))) / 10000) EDAD,\n"
				+ "P.FECNAC,\n"
				+ "(CASE WHEN P.SEXO = 'M' THEN 'Masculino' ELSE 'Femenino' END) SEXO,\n"
				+ "P.NUMCEL\n"
				+ "FROM PERSONA P, EMPLEADO E, CARGO C WHERE P.NUMDNI = E.DNIEMP AND E.CARGO = C.CODCAR AND P.NUMDNI = '" + dni + "'\n"
				+ "ORDER BY P.APEPAT";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Empleado>() {

			@Override
			public Empleado extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Empleado e = null;
				
				try {
					
					while(rs.next()) {
						
						e = new Empleado(rs.getString(1),
										 rs.getString(2),
										 rs.getString(3),
										 rs.getString(4),
										 rs.getString(5),
										 rs.getDouble(6),
										 rs.getDate(7),
										 rs.getInt(8),
										 rs.getDate(9),
										 rs.getString(10),
										 rs.getString(11));
					}
					
				}catch (Exception ex) {
					
				}
				
				return e;
				
			}
			
		});
		
		
	}

	@Override
	public boolean existeEmpleado(String dni) {
		
		String sql = "SELECT DNIEMP FROM EMPLEADO WHERE DNIEMP = '" + dni + "'";
		
		String dniEmp = jdbcTemplate.query(sql, new ResultSetExtractor<String>() {

			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				String dniEmp = "";
				
				try {
					
					while(rs.next()) {
						dniEmp = rs.getString(1);
					}
					
				}catch(Exception ex) {
					
				}
				
				return dniEmp;
				
			}
			
		});
		
		Empleado e = getEmpleadoByDni(dniEmp);
		
		if(e == null) {
			return false;
		}else {
			return true;
		}
		
	}

	@Override
	public void ingresarEmpleado(Empleado e) {
		
		String sql1 = "INSERT INTO PERSONA VALUES ("
				+ "'" + e.getDni() + "',"
				+ "'" + e.getNombres() + "',"
				+ "'" + e.getApe_pat() + "',"
				+ "'" + e.getApe_mat() + "',"
				+ "TO_DATE('" + e.getFecha_nacimiento().toString() + "','yyyy-mm-dd'),"
				+ "'" + e.getSexo().charAt(0) + "',"
				+ "'" + e.getNum_celular() + "')";
		String sql2 = "INSERT INTO EMPLEADO (DNIEMP, SUELDO, CARGO) VALUES ("
				+ "'" + e.getDni() + "',"
				+ "'" + (e.getSueldo() + "").substring(0, 4) + "," + (e.getSueldo() + "").substring(5) + "',"
				+ "(SELECT CODCAR FROM CARGO WHERE NOMCAR = '" + e.getCargo() + "'))";
		
		try {
			jdbcTemplate.update(sql1);
			jdbcTemplate.update(sql2);
		}catch(Exception ex) {
			
		}
		
	}

	@Override
	public void editarEmpleado(Empleado e) {
		
		String sql1 = "UPDATE PERSONA SET "
				+ "NOMBRE = '" + e.getNombres() + "', "
				+ "APEPAT = '" + e.getApe_pat() + "', "
				+ "APEMAT = '" + e.getApe_mat() + "', "
				+ "FECNAC = TO_DATE('" + e.getFecha_nacimiento().toString() + "','yyyy-mm-dd'), "
				+ "SEXO = '" + e.getSexo().charAt(0) + "', "
				+ "NUMCEL = '" + e.getNum_celular() + "'\n"
				+ "WHERE NUMDNI = '" + e.getDni() + "'";
		
		String sql2 = "UPDATE EMPLEADO SET "
				+ "SUELDO = '" + (e.getSueldo() + "").substring(0, 4) + "," + (e.getSueldo() + "").substring(5) + "', " 
				+ "CARGO = (SELECT CODCAR FROM CARGO WHERE NOMCAR = '" + e.getCargo() + "')\n"
				+ "WHERE DNIEMP = '" + e.getDni() + "'";
		
		try {
			jdbcTemplate.update(sql1);
			jdbcTemplate.update(sql2);
		}catch(Exception ex) {
			
		}
		
	}

	@Override
	public void eliminarEmpleado(String dni) {
		
		String sql1 = "DELETE FROM ESPECIALIDAD WHERE EMPLEADO = '" + dni + "'";
		String sql2 = "DELETE FROM EMPLEADO WHERE DNIEMP = '" + dni + "'";
		String sql3 = "DELETE FROM PERSONA WHERE NUMDNI = '" + dni + "'";
		
		try {
			jdbcTemplate.update(sql1);
			jdbcTemplate.update(sql2);
			jdbcTemplate.update(sql3);
		}catch(Exception ex) {
			
		}
		
	}

}
