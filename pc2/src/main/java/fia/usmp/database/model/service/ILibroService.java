package fia.usmp.database.model.service;

import java.util.List;

import fia.usmp.database.model.entity.Libro;

public interface ILibroService {

	public List<Libro> findAll();
	public void save(Libro libro);
	public Libro findOne(Long id);
	public void delete(Long id);
	
}
