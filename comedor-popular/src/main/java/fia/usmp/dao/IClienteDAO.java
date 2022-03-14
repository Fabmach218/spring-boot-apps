package fia.usmp.dao;

import java.util.List;

import fia.usmp.model.Cliente;

public interface IClienteDAO {

	public List<Cliente> getClientes();
	public List<Cliente> filtrarClientes(String nom, String sexo, String dis);
	public Cliente getClienteByDni(String dni);
	public List<String> getDistritos();
	
	public boolean existeCliente(String dni);
	public void ingresarCliente(Cliente c);
	public void editarCliente(Cliente c);
	public void eliminarCliente(String dni);
	
	public List<Cliente> getClientesPorMenu(int codMenu);
	
	
	
	
	
	
}
