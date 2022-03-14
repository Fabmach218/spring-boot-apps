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

import fia.usmp.model.Insumo;
import fia.usmp.model.Plato;

@Component
public class InsumoDAO implements IInsumoDAO{

	private static final Logger log = LoggerFactory.getLogger(InsumoDAO.class);
	private JdbcTemplate jdbcTemplate;
	
	public InsumoDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Insumo> getIngredientesPorPlato(String cod) {
		
		String sql = "SELECT R.INSUMO, I.NOMINS, R.CANT, R.UNID FROM RECETA R, INSUMO I WHERE I.CODINS = R.INSUMO AND R.PLATO = '" + cod + "'";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Insumo>>() {

			@Override
			public List<Insumo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<Insumo> lista = new ArrayList<Insumo>();
				Insumo i;
				
				try {
					
					while(rs.next()) {
						
						i = new Insumo(
								rs.getString(1),
								rs.getString(2),
								rs.getDouble(3),
								rs.getString(4));
						
						lista.add(i);
						
					}
					
				}catch (Exception ex) {
					lista.add(null);
				}
				
				return lista;
				
			}
			
			
		});
		
		
		
	}

	@Override
	public List<Insumo> buscarIngredientesPorPlato(String codPla, String ing){
		
		String sql = "SELECT R.INSUMO, I.NOMINS, R.CANT, R.UNID FROM RECETA R, INSUMO I WHERE I.CODINS = R.INSUMO AND R.PLATO = '" + codPla + "' AND UPPER(I.NOMINS) LIKE '%" + ing.toUpperCase() + "%'";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Insumo>>() {

			@Override
			public List<Insumo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<Insumo> lista = new ArrayList<Insumo>();
				Insumo i;
				
				try {
					
					while(rs.next()) {
						
						i = new Insumo(
								rs.getString(1),
								rs.getString(2),
								rs.getDouble(3),
								rs.getString(4));
						
						lista.add(i);
						
					}
					
				}catch (Exception ex) {
					lista.add(null);
				}
				
				return lista;
				
			}
			
			
		});
		
	}
	
	
	@Override
	public List<String> getUnidadesIngredientes() {
		
		String sql = "SELECT DISTINCT UNID FROM RECETA ORDER BY 1";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<String> lista = new ArrayList<String>();
				
				try {
					
					while(rs.next()) {
						
						lista.add(rs.getString(1));
						
					}
					
				}catch (Exception ex) {
					lista.add(null);
				}
				
				return lista;
				
			}
			
			
		});
		
	}

	@Override
	public List<String> getListaInsumos() {
		
		String sql = "SELECT NOMINS FROM INSUMO ORDER BY 1";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<String> lista = new ArrayList<String>();
				
				try {
					
					while(rs.next()) {
						
						lista.add(rs.getString(1));
						
					}
					
				}catch (Exception ex) {
					lista.add(null);
				}
				
				return lista;
				
			}
			
			
		});
		
	}

	@Override
	public Insumo getIngredientePorPlato(String codPla, String codIns) {
		
		String sql = "SELECT R.INSUMO, I.NOMINS, R.CANT, R.UNID FROM RECETA R, INSUMO I WHERE I.CODINS = R.INSUMO AND R.PLATO = '" + codPla + "' AND R.INSUMO = '" + codIns + "'";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Insumo>() {

			@Override
			public Insumo extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Insumo i = null;
				
				try {
					
					while(rs.next()) {
						
						i = new Insumo(
								rs.getString(1),
								rs.getString(2),
								rs.getDouble(3),
								rs.getString(4));
						
					}
					
				}catch (Exception ex) {
					
				}
				
				return i;
				
			}
			
			
		});
		
		
	}
	
	@Override
	public void ingresarIngrediente(String codPla, Insumo i) {
		
		String sql = "INSERT INTO RECETA VALUES ('" + codPla + "',(SELECT CODINS FROM INSUMO WHERE NOMINS = '" + i.getInsumo() + "'),'"; //+ i.getCantidad() + "','" + i.getUnidad() + "')";
		
		if(i.getCantidad() < 10) {
			sql += (i.getCantidad() + "").substring(0, 1) + "," + (i.getCantidad() + "").substring(2);
		}else if(i.getCantidad() < 100) {
			sql += (i.getCantidad() + "").substring(0, 2) + "," + (i.getCantidad() + "").substring(3);
		}else {
			sql += (i.getCantidad() + "").substring(0, 3) + "," + (i.getCantidad() + "").substring(4);
		}
		
		sql += "','" + i.getUnidad() + "')";
		
		try {
			jdbcTemplate.update(sql);
		}catch (Exception e) {
			
		}
		
		
	}

	@Override
	public void editarIngrediente(String codPla, Insumo i) {
		
		String sql = "UPDATE RECETA SET\n"
				+ "CANT = '";
		
		if(i.getCantidad() < 10) {
			sql += (i.getCantidad() + "").substring(0, 1) + "," + (i.getCantidad() + "").substring(2);
		}else if(i.getCantidad() < 100) {
			sql += (i.getCantidad() + "").substring(0, 2) + "," + (i.getCantidad() + "").substring(3);
		}else {
			sql += (i.getCantidad() + "").substring(0, 3) + "," + (i.getCantidad() + "").substring(4);
		}
		
		sql += "',\n"
				+ "UNID = '" + i.getUnidad() + "'\n"
				+ "WHERE PLATO = '" + codPla + "' AND INSUMO = (SELECT CODINS FROM INSUMO WHERE NOMINS = '" + i.getInsumo() + "')";
		
		try {
			jdbcTemplate.update(sql);
		}catch (Exception e) {
			
		}
		
	}

	@Override
	public void eliminarIngrediente(String codPla, String nomIng) {
		
		String sql = "DELETE FROM RECETA WHERE PLATO = '" + codPla +"' AND INSUMO = (SELECT CODINS FROM INSUMO WHERE NOMINS = '" + nomIng + "')";
		
		try {
			jdbcTemplate.update(sql);
		}catch (Exception e) {
			
		}
		
	}

	@Override
	public boolean existeIngrediente(String codPla, String nomIng) {
		
		String sql = "SELECT I.NOMINS FROM INSUMO I, RECETA R WHERE R.INSUMO = I.CODINS AND I.NOMINS = '" + nomIng + "' AND R.PLATO = '" + codPla + "'";
		
		String ingrediente = jdbcTemplate.query(sql, new ResultSetExtractor<String>() {

			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				String ingrediente = "";
				
				try {
					
					while(rs.next()) {
						
						ingrediente = rs.getString(1);
						
					}
					
				}catch (Exception ex) {
					
				}
				
				return ingrediente;
				
			}
			
			
		});
		
		if(nomIng.equals(ingrediente)) {
			return true;
		}else {
			return false;
		}
		
		
	}

	@Override
	public List<Insumo> getInsumos() {
		
		String sql = "SELECT * FROM INSUMO ORDER BY CODINS";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Insumo>>() {

			@Override
			public List<Insumo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<Insumo> lista = new ArrayList<Insumo>();
				Insumo i;
				
				try {
					
					while(rs.next()) {
						
						i = new Insumo(
								rs.getString(1),
								rs.getString(2),
								rs.getDouble(3),
								rs.getString(4));
						
						lista.add(i);
						
					}
					
				}catch (Exception ex) {
					lista.add(null);
				}
				
				return lista;
				
			}
			
			
		});
		
	}

	@Override
	public List<Insumo> buscarInsumos(String nom) {
		
		String sql = "SELECT * FROM INSUMO WHERE UPPER(NOMINS) LIKE '%" + nom.toUpperCase() + "%' ORDER BY CODINS";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Insumo>>() {

			@Override
			public List<Insumo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<Insumo> lista = new ArrayList<Insumo>();
				Insumo i;
				
				try {
					
					while(rs.next()) {
						
						i = new Insumo(
								rs.getString(1),
								rs.getString(2),
								rs.getDouble(3),
								rs.getString(4));
						
						lista.add(i);
						
					}
					
				}catch (Exception ex) {
					lista.add(null);
				}
				
				return lista;
				
			}
			
			
		});
		
		
	}

	@Override
	public Insumo getInsumo(String codIns) {
		
		String sql = "SELECT * FROM INSUMO WHERE CODINS = '" + codIns + "'";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Insumo>() {

			@Override
			public Insumo extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Insumo i = null;
				
				try {
					
					while(rs.next()) {
						
						i = new Insumo(
								rs.getString(1),
								rs.getString(2),
								rs.getDouble(3),
								rs.getString(4));
						
					}
					
				}catch (Exception ex) {
					
				}
				
				return i;
				
			}
			
			
		});
		
		
		
	}

	@Override
	public List<String> getUnidadesInsumos(){
		
		String sql = "SELECT DISTINCT UNIDAD FROM INSUMO ORDER BY 1";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<String> lista = new ArrayList<String>();
				
				try {
					
					while(rs.next()) {
						
						lista.add(rs.getString(1));
						
					}
					
				}catch (Exception ex) {
					lista.add(null);
				}
				
				return lista;
				
			}
			
			
		});
		
		
		
	}
	
	@Override
	public void ingresarInsumo(Insumo i) {
		
		String sql = "INSERT INTO INSUMO VALUES ('" + i.getCod() + "',\n"
				+ "'" + i.getInsumo() + "','";
				
		if(i.getCantidad() < 10) {
			sql += (i.getCantidad() + "").substring(0, 1) + "," + (i.getCantidad() + "").substring(2);
		}else if(i.getCantidad() < 100) {
			sql += (i.getCantidad() + "").substring(0, 2) + "," + (i.getCantidad() + "").substring(3);
		}else {
			sql += (i.getCantidad() + "").substring(0, 3) + "," + (i.getCantidad() + "").substring(4);
		}
				
		sql += "','" + i.getUnidad() + "')";
		
		try {
			jdbcTemplate.update(sql);
		}catch (Exception e) {
			
		}
		
	}

	@Override
	public void editarInsumo(Insumo i) {
		
		String sql = "UPDATE INSUMO SET\n"
				+ "NOMINS = '" + i.getInsumo() + "',\n"
				+ "CANT_INV = '";
		
		if(i.getCantidad() < 10) {
			sql += (i.getCantidad() + "").substring(0, 1) + "," + (i.getCantidad() + "").substring(2);
		}else if(i.getCantidad() < 100) {
			sql += (i.getCantidad() + "").substring(0, 2) + "," + (i.getCantidad() + "").substring(3);
		}else {
			sql += (i.getCantidad() + "").substring(0, 3) + "," + (i.getCantidad() + "").substring(4);
		}		
				
		sql += "',\n"
				+ "UNIDAD = '" + i.getUnidad() + "'\n"
				+ "WHERE CODINS = '" + i.getCod() + "'";
		
		try {
			jdbcTemplate.update(sql);
		}catch (Exception e) {
			
		}
		
	}

	@Override
	public void eliminarInsumo(String codIns) {
		
		String sql1 = "DELETE FROM RECETA WHERE INSUMO = '" + codIns + "'";
		String sql2 = "DELETE FROM INSUMO WHERE CODINS = '" + codIns + "'";
		
		try {
			jdbcTemplate.update(sql1);
			jdbcTemplate.update(sql2);
		}catch (Exception e) {
			
		}
		
	}


	@Override
	public boolean existePK(String codIns) {
		
		String sql = "SELECT CODINS FROM INSUMO WHERE CODINS = '" + codIns + "'";
		
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
	public String getPK() {
		
		String sqlMin = "SELECT MIN(CODINS) FROM INSUMO";
		String sqlMax = "SELECT MAX(CODINS) FROM INSUMO";
		
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
		
		String sqlListaPKs = "SELECT CODINS FROM INSUMO ORDER BY 1";
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
	
	
	
	
}
