package fia.usmp.colleccion.models.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import fia.usmp.colleccion.models.dao.IClienteDAO;
import fia.usmp.colleccion.models.entity.Cliente;

public class ClienteService implements IClienteService{

	@Autowired
	private IClienteDAO clienteDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return clienteDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDAO.save(cliente);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		return clienteDAO.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDAO.delete(id);
		
	}
	
	
}
