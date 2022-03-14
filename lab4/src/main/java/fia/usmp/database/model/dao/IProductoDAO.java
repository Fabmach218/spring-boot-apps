package fia.usmp.database.model.dao;

import org.springframework.data.repository.CrudRepository;

import fia.usmp.database.model.entity.Producto;

public interface IProductoDAO extends CrudRepository<Producto, Long>{

}
