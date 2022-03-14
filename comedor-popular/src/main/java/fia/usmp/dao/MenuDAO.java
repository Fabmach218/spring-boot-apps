package fia.usmp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import fia.usmp.model.Cliente;
import fia.usmp.model.Menu;

@Component
public class MenuDAO implements IMenuDAO{

	private static final Logger log = LoggerFactory.getLogger(MenuDAO.class);
	private JdbcTemplate jdbcTemplate;
	
	public MenuDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Menu> getMenuMesActual() {
		
		String sql = "SELECT A.MENU, M.FECMENU AS FECHA, A.ENTRADA, A.PLATO_MEDIO, A.POSTRE, A.REFRESCO, M.CANT_PREPARADA, M.PRECIO\n"
				+ "FROM (SELECT MENU, MAX(ENTRADA) AS ENTRADA, MAX(PLATO_MEDIO) AS PLATO_MEDIO, MAX(POSTRE) AS POSTRE, MAX(REFRESCO) AS REFRESCO\n"
				+ "    FROM (SELECT D.MENU,\n"
				+ "    (CASE WHEN P.TIPOPLA = 'E' THEN NOMPLA ELSE '-' END) AS ENTRADA,\n"
				+ "    (CASE WHEN P.TIPOPLA = 'M' THEN NOMPLA ELSE '-' END) AS PLATO_MEDIO,\n"
				+ "    (CASE WHEN P.TIPOPLA = 'P' THEN NOMPLA ELSE '-' END) AS POSTRE,\n"
				+ "    (CASE WHEN P.TIPOPLA = 'R' THEN NOMPLA ELSE '-' END) AS REFRESCO\n"
				+ "    FROM DETALLE_MENU D, PLATO P WHERE D.PLATO = P.CODPLA)\n"
				+ "    GROUP BY MENU) A , MENU M\n"
				+ "WHERE A.MENU = M.CODMENU AND TO_CHAR(M.FECMENU,'mm/yyyy') = TO_CHAR(SYSDATE, 'mm/yyyy')\n"
				+ "ORDER BY M.CODMENU";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Menu>>() {

			@Override
			public List<Menu> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<Menu> listaMenu = new ArrayList<Menu>();
				Menu m;
				
				try {
					
					while(rs.next()) {
						
						m = new Menu(
								rs.getInt(1),
								rs.getDate(2),
								rs.getString(3),
								rs.getString(4),
								rs.getString(5),
								rs.getString(6),
								rs.getInt(7),
								rs.getDouble(8));
						
						listaMenu.add(m);
						
					}
					
				}catch (Exception ex) {
					listaMenu.add(null);
				}
				
				return listaMenu;
				
			}
			
			
		});
		
		
		
	}
	
	@Override
	public List<Menu> getMenuPorMesAnio(String mes_anio){
		
		String sql = "SELECT A.MENU, M.FECMENU AS FECHA, A.ENTRADA, A.PLATO_MEDIO, A.POSTRE, A.REFRESCO, M.CANT_PREPARADA, M.PRECIO\n"
				+ "FROM (SELECT MENU, MAX(ENTRADA) AS ENTRADA, MAX(PLATO_MEDIO) AS PLATO_MEDIO, MAX(POSTRE) AS POSTRE, MAX(REFRESCO) AS REFRESCO\n"
				+ "    FROM (SELECT D.MENU,\n"
				+ "    (CASE WHEN P.TIPOPLA = 'E' THEN NOMPLA ELSE '-' END) AS ENTRADA,\n"
				+ "    (CASE WHEN P.TIPOPLA = 'M' THEN NOMPLA ELSE '-' END) AS PLATO_MEDIO,\n"
				+ "    (CASE WHEN P.TIPOPLA = 'P' THEN NOMPLA ELSE '-' END) AS POSTRE,\n"
				+ "    (CASE WHEN P.TIPOPLA = 'R' THEN NOMPLA ELSE '-' END) AS REFRESCO\n"
				+ "    FROM DETALLE_MENU D, PLATO P WHERE D.PLATO = P.CODPLA)\n"
				+ "    GROUP BY MENU) A , MENU M\n"
				+ "WHERE A.MENU = M.CODMENU AND TO_CHAR(M.FECMENU,'mm/yyyy') = '" +  mes_anio + "'\n"
				+ "ORDER BY M.CODMENU";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Menu>>() {

			@Override
			public List<Menu> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<Menu> listaMenu = new ArrayList<Menu>();
				Menu m;
				
				try {
					
					while(rs.next()) {
						
						m = new Menu(
								rs.getInt(1),
								rs.getDate(2),
								rs.getString(3),
								rs.getString(4),
								rs.getString(5),
								rs.getString(6),
								rs.getInt(7),
								rs.getDouble(8));
						
						listaMenu.add(m);
						
					}
					
				}catch (Exception ex) {
					listaMenu.add(null);
				}
				
				return listaMenu;
				
			}
			
			
		});
		
	}
	
	
	
	@Override
	public Menu getMenuByCod(int cod) {
		
		String sql = "SELECT A.MENU, M.FECMENU AS FECHA, A.ENTRADA, A.PLATO_MEDIO, A.POSTRE, A.REFRESCO, M.CANT_PREPARADA, M.PRECIO\n"
				+ "FROM (SELECT MENU, MAX(ENTRADA) AS ENTRADA, MAX(PLATO_MEDIO) AS PLATO_MEDIO, MAX(POSTRE) AS POSTRE, MAX(REFRESCO) AS REFRESCO\n"
				+ "    FROM (SELECT D.MENU,\n"
				+ "    (CASE WHEN P.TIPOPLA = 'E' THEN NOMPLA ELSE '-' END) AS ENTRADA,\n"
				+ "    (CASE WHEN P.TIPOPLA = 'M' THEN NOMPLA ELSE '-' END) AS PLATO_MEDIO,\n"
				+ "    (CASE WHEN P.TIPOPLA = 'P' THEN NOMPLA ELSE '-' END) AS POSTRE,\n"
				+ "    (CASE WHEN P.TIPOPLA = 'R' THEN NOMPLA ELSE '-' END) AS REFRESCO\n"
				+ "    FROM DETALLE_MENU D, PLATO P WHERE D.PLATO = P.CODPLA)\n"
				+ "    GROUP BY MENU) A , MENU M\n"
				+ "WHERE A.MENU = M.CODMENU AND A.MENU = '" + cod + "'\n"
				+ "ORDER BY M.CODMENU";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Menu>() {

			@Override
			public Menu extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Menu m = null;
				
				try {
					
					while(rs.next()) {
						
						m = new Menu(
								rs.getInt(1),
								rs.getDate(2),
								rs.getString(3),
								rs.getString(4),
								rs.getString(5),
								rs.getString(6),
								rs.getInt(7),
								rs.getDouble(8));
						
					}
					
				}catch (Exception ex) {
					
				}
				
				return m;
				
			}
			
			
		});
		
		
	}

	@Override
	public List<String> getEntradas() {
		
		String sql = "SELECT * FROM\n"
				+ "(SELECT\n"
				+ "(CASE WHEN TIPOPLA = 'E' THEN NOMPLA END) AS ENTRADA\n"
				+ "FROM PLATO)\n"
				+ "WHERE ENTRADA IS NOT NULL\n"
				+ "ORDER BY 1";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {

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
		
		
	}

	@Override
	public List<String> getPlatos() {
		
		String sql = "SELECT * FROM\n"
				+ "(SELECT\n"
				+ "(CASE WHEN TIPOPLA = 'M' THEN NOMPLA END) AS PLATO_MEDIO\n"
				+ "FROM PLATO)\n"
				+ "WHERE PLATO_MEDIO IS NOT NULL\n"
				+ "ORDER BY 1";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {

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
		
	}

	@Override
	public List<String> getPostres() {
		
		String sql = "SELECT * FROM\n"
				+ "(SELECT\n"
				+ "(CASE WHEN TIPOPLA = 'P' THEN NOMPLA END) AS POSTRE\n"
				+ "FROM PLATO)\n"
				+ "WHERE POSTRE IS NOT NULL\n"
				+ "ORDER BY 1";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {

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
		
	}

	@Override
	public List<String> getRefrescos() {
		
		String sql = "SELECT * FROM\n"
				+ "(SELECT\n"
				+ "(CASE WHEN TIPOPLA = 'R' THEN NOMPLA END) AS REFRESCO\n"
				+ "FROM PLATO)\n"
				+ "WHERE REFRESCO IS NOT NULL\n"
				+ "ORDER BY 1";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {

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
		
	}

	@Override
	public int getPK() {
		
		String sqlMin = "SELECT MIN(CODMENU) FROM MENU";
		String sqlMax = "SELECT MAX(CODMENU) FROM MENU";
		
		int min = jdbcTemplate.query(sqlMin, new ResultSetExtractor<Integer>(){

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Integer min = null;
				
				try {
					
					while(rs.next()) {
						min = rs.getInt(1);
					}
					
				}catch (Exception e){
					
				}
				
				return min;
			}
			
			
		});
		
		int max = jdbcTemplate.query(sqlMax, new ResultSetExtractor<Integer>(){

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Integer max = null;
				
				try {
					
					while(rs.next()) {
						max = rs.getInt(1);
					}
					
				}catch (Exception e){
					
				}
				
				return max;
			}
			
			
		});
		
		String sqlListaPKs = "SELECT CODMENU FROM MENU ORDER BY 1";
		List<Integer> listaPKs = jdbcTemplate.query(sqlListaPKs, new ResultSetExtractor<List<Integer>>(){

			@Override
			public List<Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<Integer> lista = new ArrayList<Integer>();
				
				try {
					
					while(rs.next()) {
						lista.add(rs.getInt(1));
					}
					
				}catch (Exception e){
					
				}
				
				return lista;
			}
			
			
		});
		
		int flag = 1; //Están todas las PKs
		
		for (int i = 0; i < listaPKs.size(); i++) {
			
			if(i > 0) {
				
				if(listaPKs.get(i) - listaPKs.get(i - 1) != 1) {
					flag = 0; //No están todas las PKs
				}
				
			}
				
		}
		
		int pk = 0;
		
		if(flag == 0) {
			
			for (int i = 0; i < listaPKs.size(); i++) {
				
				if(listaPKs.get(i) != min + i) { //Se busca en orden ascendente la PK que falta
					pk = min + i;
					break; //Se encuentra y se saca esa
				}
			
			}
			
		}else {
			pk = max + 1;
		}
		
		return pk;
		
	}
	
	@Override
	public boolean existePK(int cod) {
		
		String sql = "SELECT CODMENU FROM MENU WHERE CODMENU = '" + cod + "'";
		
		Integer pk = jdbcTemplate.query(sql, new ResultSetExtractor<Integer>(){

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Integer pk = null;
				
				try {
					
					while(rs.next()) {
						pk = rs.getInt(1);
					}
					
				}catch (Exception e){
					
				}
				
				return pk;
			}
			
			
		});
		
		if(pk == null) {
			return false;
		}else {
			return true;
		}
		
		
	}
	
	
	@Override
	public void ingresarMenu(Menu m) {
		
		String sql1 = "INSERT INTO MENU VALUES (\n"
				+ "'" + m.getCod() + "',\n"
				+ "TO_DATE('" + m.getFecha().toString() + "', 'yyyy-mm-dd'),\n"
				+ "'" + m.getCant_prep() + "',\n"
				+ "'" + ((m.getPrecio() + "").substring(0, 1) + "," + (m.getPrecio() + "").substring(2)) + "')";
		
		String sql2 = "INSERT INTO DETALLE_MENU (\n"
				+ "SELECT '" + m.getCod() + "', CODPLA FROM PLATO\n"
				+ "WHERE NOMPLA IN (\n"
				+ "'" + m.getEntrada() + "',\n"
				+ "'" + m.getPlato_principal() + "',\n"
				+ "'" + m.getPostre() + "',\n"
				+ "'" + m.getRefresco() + "'))";
		
		try {
			jdbcTemplate.update(sql1);
			jdbcTemplate.update(sql2);
		}catch (Exception e) {
			
		}
		
		
	}

	@Override
	public void editarMenu(Menu m) {
		
		String sql1 = "UPDATE MENU SET "
				+ "FECMENU = TO_DATE('" + m.getFecha().toString() + "','yyyy-mm-dd'), "
				+ "CANT_PREPARADA = '" + m.getCant_prep() + "', "
				+ "PRECIO = '" + ((m.getPrecio() + "").substring(0, 1) + "," + (m.getPrecio() + "").substring(2)) + "' "
				+ "WHERE CODMENU = '" + m.getCod() + "'";
		
		String sql2 = "DELETE FROM DETALLE_MENU WHERE MENU = '" + m.getCod() + "'";
		String sql3 = "INSERT INTO DETALLE_MENU (\n"
				+ "SELECT '" + m.getCod() + "', CODPLA FROM PLATO\n"
				+ "WHERE NOMPLA IN (\n"
				+ "'" + m.getEntrada() + "',\n"
				+ "'" + m.getPlato_principal() + "',\n"
				+ "'" + m.getPostre() + "',\n"
				+ "'" + m.getRefresco() + "'))";
		
		
		try {
			jdbcTemplate.update(sql1);
			jdbcTemplate.update(sql2);
			jdbcTemplate.update(sql3);
		}catch (Exception e) {
			
		}
		
	}

	@Override
	public void eliminarMenu(int cod) {
		
		String sql1 = "DELETE FROM DETALLE_MENU WHERE MENU = '" + cod + "'";
		String sql2 = "DELETE FROM MENU WHERE CODMENU = '" + cod + "'";
		
		try {
			jdbcTemplate.update(sql1);
			jdbcTemplate.update(sql2);
		}catch (Exception e) {
			
		}
		
	}
	
	@Override
	public boolean verificarFechaRepetida(java.sql.Date fecha) {
		
		String sql = "SELECT TO_CHAR(FECMENU, 'yyyy-mm-dd') FROM MENU";
		
		List<String> listaFechas = jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>(){

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<String> fechas = new ArrayList<String>();
				
				try {
					
					while(rs.next()) {
						fechas.add(rs.getString(1));
					}
					
				}catch (Exception e){
					
				}
				
				return fechas;
			}
		
		});
		
		int flag = 0; //No repetido
		
		for (String f : listaFechas) {
			
			if(fecha.toString().equals(f)) {
				flag = 1; //Repetido
			}
			
		}
	
		if (flag == 0) {
			return true;
		}else {
			return false;
		}
		
		
	}

	@Override
	public List<Menu> getMenusCliente(String dni) {
		
		String sql = "SELECT MENU FROM REGISTRO_CLIENTES WHERE CLIENTE = '" + dni + "' ORDER BY 1";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Menu>>() {

			@Override
			public List<Menu> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<Menu> listaMenu = new ArrayList<Menu>();
				Menu m;
				
				try {
					
					while(rs.next()) {
						listaMenu.add(getMenuByCod(rs.getInt(1)));
					}
					
				}catch (Exception ex) {
					listaMenu.add(null);
				}
				
				return listaMenu;
				
			}
			
			
		});
		
		
		
	}

	@Override
	public void ingresarMenuCliente(String dni, Menu m) {
		
		String sql = "INSERT INTO REGISTRO_CLIENTES VALUES ('" + m.getCod() + "','" + dni + "')";
		
		try {
			jdbcTemplate.update(sql);
		}catch(Exception e) {
			
		}
		
	}

	@Override
	public void eliminarMenuCliente(String dni, int codMenu) {
		
		String sql = "DELETE FROM REGISTRO_CLIENTES WHERE MENU = '" + codMenu + "' AND CLIENTE = '" + dni + "'";
		
		try {
			jdbcTemplate.update(sql);
		}catch(Exception e) {
			
		}
		
	}

	@Override
	public boolean existeMenuCliente(String dni, int codMenu) {
		
		String sql = "SELECT MENU FROM REGISTRO_CLIENTES WHERE CLIENTE = '" + dni + "' AND MENU = '" + codMenu + "'";
		
		int cod = jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Integer cod = 0;
				
				try {
					cod = rs.getInt(1);
				}catch (Exception e){
					
				}
				
				return cod;
				
			}
			
		});
		
		Menu m = getMenuByCod(cod);
		
		if(m == null) {
			return false;
		}else {
			return true;
		}
		
		
	}
	
	@Override
	public boolean existeMenuFecha(java.sql.Date fecha) {
		
		String sql = "SELECT CODMENU FROM MENU WHERE TO_CHAR(FECMENU, 'yyyy-mm-dd') = '" + fecha.toString() + "'";
		
		int cod = jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Integer cod = 0;
				
				try {
					
					while(rs.next()) {
						cod = rs.getInt(1);
					}
					
				}catch(Exception e) {
					
				}
				
				return cod;
				
			}
			
		});
		
		Menu m = getMenuByCod(cod);
		
		if(m == null) {
			return false;
		}else {
			return true;
		}
		
		
	}

	@Override
	public Menu buscarMenuPorFecha(java.sql.Date fecha) {
		
		String sql = "SELECT CODMENU FROM MENU WHERE TO_CHAR(FECMENU, 'yyyy-mm-dd') = '" + fecha.toString() + "'";
		
		int cod = jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Integer cod = 0;
				
				try {
					
					while(rs.next()) {
						cod = rs.getInt(1);
					}
					
				}catch(Exception e) {
					
				}
				
				return cod;
				
			}
			
		});
		
		return getMenuByCod(cod);
		
	}
	
	
	
	
	
}
