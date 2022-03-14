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

import fia.usmp.model.Cliente;
import fia.usmp.model.Empleado;

@Component
public class ClienteDAO implements IClienteDAO{

	private static final Logger log = LoggerFactory.getLogger(ClienteDAO.class);
	private JdbcTemplate jdbcTemplate;
	
	public ClienteDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Cliente> getClientes() {
		
		String sql = "SELECT\n"
				+ "P.NUMDNI,\n"
				+ "P.APEPAT,\n"
				+ "P.APEMAT,\n"
				+ "P.NOMBRE,\n"
				+ "(CASE WHEN C.DIRCLI IS NULL THEN '-' ELSE C.DIRCLI END) DIR_CLI,\n"
				+ "D.NOMDIS,\n"
				+ "C.FEC_REGISTRO,\n"
				+ "TRUNC((TO_NUMBER(TO_CHAR(SYSDATE,'YYYYMMDD')) - TO_NUMBER(TO_CHAR(P.FECNAC, 'YYYYMMDD'))) / 10000) EDAD,\n"
				+ "P.FECNAC,\n"
				+ "(CASE WHEN P.SEXO = 'M' THEN 'Masculino' ELSE 'Femenino' END) SEXO,\n"
				+ "(CASE WHEN P.NUMCEL IS NULL THEN '-' ELSE P.NUMCEL END) NUM_CEL\n"
				+ "FROM PERSONA P, CLIENTE C, DISTRITO D WHERE P.NUMDNI = C.DNICLI AND C.DISTRITO = D.CODDIS\n"
				+ "ORDER BY P.APEPAT";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Cliente>>() {

			@Override
			public List<Cliente> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<Cliente> lista = new ArrayList<Cliente>();
				Cliente c;
				
				try {
					
					while(rs.next()) {
						
						c = new Cliente(rs.getString(1),
										 rs.getString(2),
										 rs.getString(3),
										 rs.getString(4),
										 rs.getString(5),
										 rs.getString(6),
										 rs.getDate(7),
										 rs.getInt(8),
										 rs.getDate(9),
										 rs.getString(10),
										 rs.getString(11));
						
						lista.add(c);
						
					}
					
				}catch (Exception e) {
					
				}
				
				
				return lista;
				
				
			}
			
		});
		
	}

	@Override
	public List<Cliente> filtrarClientes(String nom, String sexo, String dis){
		
		String sql = "SELECT\n"
				+ "P.NUMDNI,\n"
				+ "P.APEPAT,\n"
				+ "P.APEMAT,\n"
				+ "P.NOMBRE,\n"
				+ "(CASE WHEN C.DIRCLI IS NULL THEN '-' ELSE C.DIRCLI END) DIR_CLI,\n"
				+ "D.NOMDIS,\n"
				+ "C.FEC_REGISTRO,\n"
				+ "TRUNC((TO_NUMBER(TO_CHAR(SYSDATE,'YYYYMMDD')) - TO_NUMBER(TO_CHAR(P.FECNAC, 'YYYYMMDD'))) / 10000) EDAD,\n"
				+ "P.FECNAC,\n"
				+ "(CASE WHEN P.SEXO = 'M' THEN 'Masculino' ELSE 'Femenino' END) SEXO,\n"
				+ "(CASE WHEN P.NUMCEL IS NULL THEN '-' ELSE P.NUMCEL END) NUM_CEL\n"
				+ "FROM PERSONA P, CLIENTE C, DISTRITO D WHERE P.NUMDNI = C.DNICLI AND C.DISTRITO = D.CODDIS";
		
		if(!nom.equals("")) {
			sql += " AND (UPPER(P.APEPAT) LIKE '%" + nom.toUpperCase() + "%' OR UPPER(P.APEMAT) LIKE '%" + nom.toUpperCase() + "%' OR UPPER(P.NOMBRE) LIKE '%" + nom.toUpperCase() + "%')";
		}
		
		if(!sexo.equals("")) {
			sql += " AND P.SEXO = '" + sexo.charAt(0) + "'";
		}
		
		if(!dis.equals("")) {
			sql += " AND D.NOMDIS = '" + dis + "'";
		}
		
		sql += "\nORDER BY P.APEPAT";
		
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Cliente>>() {

			@Override
			public List<Cliente> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<Cliente> lista = new ArrayList<Cliente>();
				Cliente c;
				
				try {
					
					while(rs.next()) {
						
						c = new Cliente(rs.getString(1),
										 rs.getString(2),
										 rs.getString(3),
										 rs.getString(4),
										 rs.getString(5),
										 rs.getString(6),
										 rs.getDate(7),
										 rs.getInt(8),
										 rs.getDate(9),
										 rs.getString(10),
										 rs.getString(11));
						
						lista.add(c);
						
					}
					
				}catch (Exception e) {
					
				}
				
				
				return lista;
				
				
			}
			
		});
		
		
	}
	
	
	
	@Override
	public Cliente getClienteByDni(String dni) {
		
		String sql = "SELECT\n"
				+ "P.NUMDNI,\n"
				+ "P.APEPAT,\n"
				+ "P.APEMAT,\n"
				+ "P.NOMBRE,\n"
				+ "(CASE WHEN C.DIRCLI IS NULL THEN '-' ELSE C.DIRCLI END) DIR_CLI,\n"
				+ "D.NOMDIS,\n"
				+ "C.FEC_REGISTRO,\n"
				+ "TRUNC((TO_NUMBER(TO_CHAR(SYSDATE,'YYYYMMDD')) - TO_NUMBER(TO_CHAR(P.FECNAC, 'YYYYMMDD'))) / 10000) EDAD,\n"
				+ "P.FECNAC,\n"
				+ "(CASE WHEN P.SEXO = 'M' THEN 'Masculino' ELSE 'Femenino' END) SEXO,\n"
				+ "(CASE WHEN P.NUMCEL IS NULL THEN '-' ELSE P.NUMCEL END) NUM_CEL\n"
				+ "FROM PERSONA P, CLIENTE C, DISTRITO D WHERE P.NUMDNI = C.DNICLI AND C.DISTRITO = D.CODDIS AND P.NUMDNI = '" + dni + "'\n"
				+ "ORDER BY P.APEPAT";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Cliente>() {

			@Override
			public Cliente extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Cliente c = null;
				
				try {
					
					while(rs.next()) {
						
						c = new Cliente(rs.getString(1),
										 rs.getString(2),
										 rs.getString(3),
										 rs.getString(4),
										 rs.getString(5),
										 rs.getString(6),
										 rs.getDate(7),
										 rs.getInt(8),
										 rs.getDate(9),
										 rs.getString(10),
										 rs.getString(11));
					}
					
				}catch (Exception e) {
					
				}
				
				return c;
				
			}
			
		});
		
	}

	@Override
	public List<String> getDistritos() {
		
		String sql = "SELECT NOMDIS FROM DISTRITO ORDER BY 1";
		
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
	public List<Cliente> getClientesPorMenu(int codMenu) {
		
		String sql = "SELECT CLIENTE FROM REGISTRO_CLIENTES WHERE MENU = '" + codMenu + "' ORDER BY 1";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Cliente>>() {

			@Override
			public List<Cliente> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<Cliente> lista = new ArrayList<Cliente>();
				Cliente c;
				
				try {
					
					while(rs.next()) {
						lista.add(getClienteByDni(rs.getString(1)));
					}
					
					
				}catch(Exception e) {
					
				}
				
				return lista;
				
			}
			
		});
		
	}

	@Override
	public boolean existeCliente(String dni) {
		
		String sql = "SELECT DNICLI FROM CLIENTE WHERE DNICLI = '" + dni + "'";
		
		String dniCli = jdbcTemplate.query(sql, new ResultSetExtractor<String>() {

			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				String dniCli = "";
				
				try {
					
					while(rs.next()) {
						dniCli = rs.getString(1);
					}
					
				}catch(Exception ex) {
					
				}
				
				return dniCli;
				
			}
			
		});
		
		Cliente c = getClienteByDni(dniCli);
		
		if(c == null) {
			return false;
		}else {
			return true;
		}
		
		
	}

	@Override
	public void ingresarCliente(Cliente c) {
		
		String sql1 = "INSERT INTO PERSONA VALUES ("
				+ "'" + c.getDni() + "',"
				+ "'" + c.getNombres() + "',"
				+ "'" + c.getApe_pat() + "',"
				+ "'" + c.getApe_mat() + "',"
				+ "TO_DATE('" + c.getFecha_nacimiento().toString() + "','yyyy-mm-dd'),"
				+ "'" + c.getSexo().charAt(0) + "',";
		
		if(c.getNum_celular().equals("")) {
			sql1 += "NULL";
		}else {
			sql1 += "'" + c.getNum_celular() + "'";
		}
		
				sql1 += ")";
				
		String sql2 = "INSERT INTO CLIENTE (DNICLI, DIRCLI, DISTRITO) VALUES ("
				
				+ "'" + c.getDni() + "',";
				
		if(c.getDireccion().equals("")) {
			sql2 += "NULL";
		}else {
			sql2 += "'" + c.getDireccion() + "'";
		}
		
				sql2 += ",(SELECT CODDIS FROM DISTRITO WHERE NOMDIS = '" + c.getDistrito() + "'))";
		
		try {
			jdbcTemplate.update(sql1);
			jdbcTemplate.update(sql2);
		}catch(Exception ex) {
			
		}
		
		
		
	}

	@Override
	public void editarCliente(Cliente c) {
		
		String sql1 = "UPDATE PERSONA SET "
				+ "NOMBRE = '" + c.getNombres() + "', "
				+ "APEPAT = '" + c.getApe_pat() + "', "
				+ "APEMAT = '" + c.getApe_mat() + "', "
				+ "FECNAC = TO_DATE('" + c.getFecha_nacimiento().toString() + "','yyyy-mm-dd'), "
				+ "SEXO = '" + c.getSexo().charAt(0) + "'";
				
		if(!c.getNum_celular().equals("")) {
			sql1 += ", NUMCEL = '" + c.getNum_celular() + "'";
		}
		
				sql1 += "\n"
				+ "WHERE NUMDNI = '" + c.getDni() + "'";
		
		String sql2 = "UPDATE CLIENTE SET";
		
		if(!c.getDireccion().equals("")) {
			sql2 += " DIRCLI = '" + c.getDireccion() + "',";
		}
		
		sql2 += " DISTRITO = (SELECT CODDIS FROM DISTRITO WHERE NOMDIS = '" + c.getDistrito() + "')\n"
				+ "WHERE DNICLI = '" + c.getDni() + "'";
		
		try {
			jdbcTemplate.update(sql1);
			jdbcTemplate.update(sql2);
		}catch(Exception ex) {
			
		}
		
		
	}

	@Override
	public void eliminarCliente(String dni) {
		
		String sql1 = "DELETE FROM REGISTRO_CLIENTES WHERE CLIENTE = '" + dni + "'";
		String sql2 = "DELETE FROM CLIENTE WHERE DNICLI = '" + dni + "'";
		String sql3 = "DELETE FROM PERSONA WHERE NUMDNI = '" + dni + "'";
		
		try {
			jdbcTemplate.update(sql1);
			jdbcTemplate.update(sql2);
			jdbcTemplate.update(sql3);
		}catch(Exception ex) {
			
		}
		
	}

}
