package fia.usmp.database.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fia.usmp.database.model.dao.ILibroDAO;
import fia.usmp.database.model.entity.Libro;

@Service
public class LibroService implements ILibroService{

	@Autowired
	private ILibroDAO libroDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<Libro> findAll() {
		return libroDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Libro libro) {
		libroDAO.save(libro);
	}

	@Override
	@Transactional(readOnly = true)
	public Libro findOne(Long id) {
		return libroDAO.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		libroDAO.delete(id);
	}

}
