package fia.usmp.dao;

import java.util.List;

import fia.usmp.model.Especialidad;

public interface IEspecialidadDAO {
	
	public List<Especialidad> getEspecialidadesEmpleado(String dni);
	public Especialidad getEspecialidadByCod(String dni, String cod);
	public boolean existeEspecialidad(String dni, String nomEsp);
	public void ingresarEspecialidad(String dni, Especialidad e);
	public void editarEspecialidad(String dni, Especialidad e);
	public void eliminarEspecialidad(String dni, String nomEsp);
	
}
