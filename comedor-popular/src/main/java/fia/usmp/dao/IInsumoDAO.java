package fia.usmp.dao;

import java.util.List;

import fia.usmp.model.Insumo;

public interface IInsumoDAO {

	public List<Insumo> getIngredientesPorPlato(String cod);
	public List<Insumo> buscarIngredientesPorPlato(String codPla, String ing);
	public List<String> getUnidadesIngredientes();
	public List<String> getListaInsumos();
	public Insumo getIngredientePorPlato(String codPla, String codIng);
	public void ingresarIngrediente(String codPla, Insumo i);
	public void editarIngrediente(String codPla, Insumo i);
	public void eliminarIngrediente(String codPla, String nomIng);
	public boolean existeIngrediente(String codPla, String nomIng);
	
	public List<Insumo> getInsumos();
	public List<Insumo> buscarInsumos(String nom);
	public Insumo getInsumo(String codIns);
	public List<String> getUnidadesInsumos();
	public void ingresarInsumo(Insumo i);
	public void editarInsumo(Insumo i);
	public void eliminarInsumo(String codIns);
	public boolean existePK(String codIns);
	public String getPK();
	
}
