package fia.usmp.dao;

import java.sql.Date;
import java.util.List;

import fia.usmp.model.Menu;

public interface IMenuDAO {

	public List<Menu> getMenuMesActual();
	public List<Menu> getMenuPorMesAnio(String mes_anio);
	public Menu getMenuByCod(int cod);
	public List<String> getEntradas();
	public List<String> getPlatos();
	public List<String> getPostres();
	public List<String> getRefrescos();
	public int getPK();
	public boolean existePK(int cod);
	public void ingresarMenu(Menu m);
	public void editarMenu(Menu m);
	public void eliminarMenu(int cod);
	public boolean verificarFechaRepetida(Date fecha);
	
	public List<Menu> getMenusCliente(String dni);
	public void ingresarMenuCliente(String dni, Menu m);
	public void eliminarMenuCliente(String dni, int codMenu);
	public boolean existeMenuCliente(String dni, int codMenu);
	public boolean existeMenuFecha(Date fecha);
	public Menu buscarMenuPorFecha(Date fecha);
	
	
}
