package fia.usmp.dao;

import java.util.List;

import fia.usmp.model.Estudiante;

public interface IEstudianteDAO {
	
	public List<Estudiante> getEstudiantes();
	public int getCantidad();
	public void setFiltro(String filtro);
	public String getPK();
	public Estudiante getEstudianteByCod(String cod);
	public List<Estudiante> buscarEstudiantes(String nom);
	public int getCantidadResultadosBusqueda(String nom);
	public void registrarEstudiante(Estudiante e);
	public void actualizarEstudiante(Estudiante e);
	public void eliminarEstudiante(String cod);
	
}
