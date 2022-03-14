package fia.usmp.database.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fia.usmp.database.model.dao.IProductoDAO;
import fia.usmp.database.model.entity.Producto;

@Service
public class ProductoService implements IProductoService{

	@Autowired
	private IProductoDAO objProductoDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) objProductoDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Producto producto) {
		objProductoDAO.save(producto);
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findOne(Long id) {
		return objProductoDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		objProductoDAO.deleteById(id);
	}

	
	
	
}
