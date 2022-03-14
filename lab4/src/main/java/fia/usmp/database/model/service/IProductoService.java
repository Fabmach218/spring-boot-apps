package fia.usmp.database.model.service;

import java.util.List;

import fia.usmp.database.model.entity.Producto;

public interface IProductoService {

	public List<Producto> findAll();
	public void save(Producto producto);
	public Producto findOne(Long id);
	public void delete(Long id);
	
}
