package fia.usmp.colleccion.models.dao;

import java.util.List;

import fia.usmp.colleccion.models.entity.Cliente;

public interface IClienteDAO {

	public List<Cliente> findAll();
	public void save(Cliente cliente);
	public Cliente findOne(Long id);
	public void delete(Long id);
	
}
