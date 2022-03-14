package fia.usmp.dao;

import java.util.List;

import fia.usmp.model.Empleado;

public interface IEmpleadoDAO {

	public List<Empleado> getEmpleados();
	public List<Empleado> filtrarEmpleados(String nom, String sexo, String cargo);
	public List<String> getCargos();
	public Empleado getEmpleadoByDni(String dni);
	public boolean existeEmpleado(String dni);
	public void ingresarEmpleado(Empleado e);
	public void editarEmpleado(Empleado e);
	public void eliminarEmpleado(String dni);
	
}
