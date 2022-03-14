package fia.usmp.dao;

import java.util.List;

import fia.usmp.model.Plato;

public interface IPlatoDAO {

	public List<Plato> getPlatos();
	public List<Plato> filtrarPlatos(String nom, String tipo, String cat);
	public Plato getPlatoByCod(String cod);
	public List<String> getCategorias();
	public List<String> getTipos();
	public String getPK();
	public boolean existePK(String cod);
	public void ingresarPlato(Plato p);
	public void editarPlato(Plato p);
	public void editarPreparacion(String cod, String prep);
	public void eliminarPlato(String cod);
	
	
}
