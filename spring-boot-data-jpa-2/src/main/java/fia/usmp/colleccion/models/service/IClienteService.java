package fia.usmp.colleccion.models.service;

import java.util.List;

import fia.usmp.colleccion.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();
	public void save(Cliente cliente);
	public Cliente findOne(Long id);
	public void delete(Long id);
	
}
