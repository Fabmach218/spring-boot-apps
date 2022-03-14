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

import fia.usmp.model.Menu;
import fia.usmp.model.Plato;

@Component
public class PlatoDAO implements IPlatoDAO{

	private static final Logger log = LoggerFactory.getLogger(PlatoDAO.class);
	private JdbcTemplate jdbcTemplate;
	
	public PlatoDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Plato> getPlatos() {
		
		String sql = "SELECT CODPLA, NOMPLA,\n"
				+ "CASE WHEN TIPOPLA = 'E' THEN 'Entrada' \n"
				+ "     WHEN TIPOPLA = 'M' THEN 'Plato principal'\n"
				+ "     WHEN TIPOPLA = 'P' THEN 'Postre'\n"
				+ "     ELSE 'Refresco'\n"
				+ "END TIPO, C.NOMCAT, P.PREP\n"
				+ "FROM PLATO P, CATEGORIA C\n"
				+ "WHERE P.CATEGORIA = C.CODCAT\n"
				+ "ORDER BY CODPLA";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Plato>>() {

			@Override
			public List<Plato> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<Plato> lista = new ArrayList<Plato>();
				Plato p;
				
				try {
					
					while(rs.next()) {
						
						p = new Plato(
								rs.getString(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
								rs.getString(5));
						
						lista.add(p);
						
					}
					
				}catch (Exception ex) {
					lista.add(null);
				}
				
				return lista;
				
			}
			
			
		});
		
	}

	@Override
	public List<Plato> filtrarPlatos(String nom, String tipo, String cat) {
		
		String sql = "SELECT CODPLA, NOMPLA,\n"
				+ "CASE WHEN TIPOPLA = 'E' THEN 'Entrada' \n"
				+ "     WHEN TIPOPLA = 'M' THEN 'Plato principal'\n"
				+ "     WHEN TIPOPLA = 'P' THEN 'Postre'\n"
				+ "     ELSE 'Refresco'\n"
				+ "END TIPO, C.NOMCAT, P.PREP\n"
				+ "FROM PLATO P, CATEGORIA C\n"
				+ "WHERE P.CATEGORIA = C.CODCAT";
		
		if(!nom.equals("")) {
			sql += " AND UPPER(P.NOMPLA) LIKE '%" + nom.toUpperCase() + "%'";
		}
		
		if(!tipo.equals("")) {
			
			sql += " AND P.TIPOPLA = '";
			
			if(tipo.equals("Plato principal")) {
				sql += "M";
			}else {
				sql += tipo.charAt(0);
			}
			
			sql += "'";
			
		}
		
		if(!cat.equals("")) {
			
			sql += " AND C.CODCAT = (SELECT CODCAT FROM CATEGORIA WHERE NOMCAT = '" + cat + "')";
			
		}
		
		sql += "\nORDER BY 1";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Plato>>() {

			@Override
			public List<Plato> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<Plato> lista = new ArrayList<Plato>();
				Plato p;
				
				try {
					
					while(rs.next()) {
						
						p = new Plato(
								rs.getString(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
								rs.getString(5));
						
						lista.add(p);
						
					}
					
				}catch (Exception ex) {
					lista.add(null);
				}
				
				return lista;
				
			}
			
			
		});
		
	}
	

	@Override
	public Plato getPlatoByCod(String cod) {
		
		String sql = "SELECT CODPLA, NOMPLA,\n"
				+ "CASE WHEN TIPOPLA = 'E' THEN 'Entrada' \n"
				+ "     WHEN TIPOPLA = 'M' THEN 'Plato principal'\n"
				+ "     WHEN TIPOPLA = 'P' THEN 'Postre'\n"
				+ "     ELSE 'Refresco'\n"
				+ "END TIPO, C.NOMCAT, P.PREP\n"
				+ "FROM PLATO P, CATEGORIA C\n"
				+ "WHERE P.CATEGORIA = C.CODCAT AND P.CODPLA = '" + cod + "'";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Plato>() {

			@Override
			public Plato extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Plato p = null;
				
				try {
					
					while(rs.next()) {
						
						p = new Plato(
								rs.getString(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
								rs.getString(5));
						
					}
					
				}catch (Exception ex) {
					
				}
				
				return p;
				
			}
			
			
		});
		
	}
	
	@Override
	public List<String> getCategorias(){
		
		String sql = "SELECT NOMCAT FROM CATEGORIA\n"
				+ "ORDER BY 1";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<String> lista = new ArrayList<String>();
				
				try {
					
					while(rs.next()) {
						
						lista.add(rs.getString(1));
						
					}
					
				}catch (Exception e) {
					
				}
				
				return lista;
				
			}
			
		});
		
	}
	
	@Override
	public List<String> getTipos(){
		
		String sql = "SELECT DISTINCT\n"
				+ "CASE WHEN TIPOPLA = 'E' THEN 'Entrada'\n"
				+ "     WHEN TIPOPLA = 'M' THEN 'Plato principal'\n"
				+ "     WHEN TIPOPLA = 'P' THEN 'Postre'\n"
				+ "     ELSE 'Refresco'\n"
				+ "END TIPO\n"
				+ "FROM PLATO\n"
				+ "ORDER BY 1";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<String> lista = new ArrayList<String>();
				
				try {
					
					while(rs.next()) {
						
						lista.add(rs.getString(1));
						
					}
					
				}catch (Exception e) {
					
				}
				
				return lista;
				
			}
			
		});
		
	}

	@Override
	public String getPK() {
		
		String sqlMin = "SELECT MIN(CODPLA) FROM PLATO";
		String sqlMax = "SELECT MAX(CODPLA) FROM PLATO";
		
		String min = jdbcTemplate.query(sqlMin, new ResultSetExtractor<String>(){

			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				String min = null;
				
				try {
					
					while(rs.next()) {
						min = rs.getString(1);
					}
					
				}catch (Exception e){
					
				}
				
				return min;
			}
			
			
		});
		
		String max = jdbcTemplate.query(sqlMax, new ResultSetExtractor<String>(){

			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				String max = null;
				
				try {
					
					while(rs.next()) {
						max = rs.getString(1);
					}
					
				}catch (Exception e){
					
				}
				
				return max;
			}
			
			
		});
		
		String sqlListaPKs = "SELECT CODMENU FROM MENU ORDER BY 1";
		List<String> listaPKs = jdbcTemplate.query(sqlListaPKs, new ResultSetExtractor<List<String>>(){

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<String> lista = new ArrayList<String>();
				
				try {
					
					while(rs.next()) {
						lista.add(rs.getString(1));
					}
					
				}catch (Exception e){
					
				}
				
				return lista;
			}
			
			
		});
		
		int flag = 1; //Están todas las PKs
		
		int minNum = Integer.parseInt(min);
		int maxNum = Integer.parseInt(max);
		
		for (int i = 0; i < listaPKs.size(); i++) {
			
			if(i > 0) {
				
				if(listaPKs.get(i).compareTo(listaPKs.get(i - 1)) != 1) {
					flag = 0; //No están todas las PKs
				}
				
			}
				
		}
		
		int pk = 0;
		
		if(flag == 0) {
			
			for (int i = 0; i < listaPKs.size(); i++) {
				
				if(Integer.parseInt(listaPKs.get(i)) != minNum + i) { //Se busca en orden ascendente la PK que falta
					pk = minNum + i;
					break; //Se encuentra y se saca esa
				}
			
			}
			
		}else {
			pk = maxNum + 1;
		}
		
		String pkString = pk + "";
		
		while(pkString.length() < 3) {
			pkString = "0" + pkString;
		}
		
		return pkString;
		
	}

	@Override
	public boolean existePK(String cod) {
		
		String sql = "SELECT CODPLA FROM PLATO WHERE CODPLA = '" + cod + "'";
		
		String pk = jdbcTemplate.query(sql, new ResultSetExtractor<String>(){

			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				String pk = "";
				
				try {
					
					while(rs.next()) {
						pk = rs.getString(1);
					}
					
				}catch (Exception e){
					
				}
				
				return pk;
			}
			
			
		});
		
		if(pk.equals("")) {
			return false;
		}else {
			return true;
		}
		
		
		
	}

	@Override
	public void ingresarPlato(Plato p) {
		
		String sql = "INSERT INTO PLATO (CODPLA, NOMPLA, TIPOPLA, CATEGORIA)\n"
				+ "(SELECT DISTINCT '" + p.getCod() + "', '" + p.getNombre() + "', '";
		
		if(p.getTipo().equals("Plato principal")) {
			sql += "M";
		}else {
			sql += p.getTipo().charAt(0);
		}
		
		sql += "', C.CODCAT\n"
				+ "FROM PLATO P, CATEGORIA C\n"
				+ "WHERE P.CATEGORIA = C.CODCAT AND C.CODCAT = (SELECT CODCAT FROM CATEGORIA WHERE NOMCAT = '" + p.getCategoria() + "'))";
		
		try {
			jdbcTemplate.update(sql);
		}catch (Exception e) {
			
		}
		
	}

	@Override
	public void editarPlato(Plato p) {
		
		String sql = "UPDATE PLATO SET\n"
				+ "NOMPLA = '" + p.getNombre() + "',\n"
				+ "TIPOPLA = '";
		
		if(p.getTipo().equals("Plato principal")) {
			sql += "M";
		}else {
			sql += p.getTipo().charAt(0);
		}
		
		sql += "',\n"
			+ "CATEGORIA = (SELECT CODCAT FROM CATEGORIA WHERE NOMCAT = '" + p.getCategoria() + "')\n"
			+ "WHERE CODPLA = '" + p.getCod() + "'";
		
		try {
			jdbcTemplate.update(sql);
		}catch (Exception e) {
			
		}
		
	}
	
	@Override
	public void editarPreparacion(String cod, String prep) {
		
		String sql = "UPDATE PLATO SET PREP = '" + prep + "' WHERE CODPLA = '" + cod + "'";
		
		try {
			jdbcTemplate.update(sql);
		}catch (Exception e) {
			
		}
		
	}

	@Override
	public void eliminarPlato(String cod) {
		
		String sql1 = "DELETE FROM DETALLE_MENU WHERE PLATO = '" + cod + "'";
		String sql2 = "DELETE FROM RECETA WHERE PLATO = '" + cod + "'";
		String sql3 = "DELETE FROM PLATO WHERE CODPLA = '" + cod + "'";
		
		try {
			jdbcTemplate.update(sql1);
			jdbcTemplate.update(sql2);
			jdbcTemplate.update(sql3);
		}catch (Exception e) {
			
		}
		
	}
	
	
}
