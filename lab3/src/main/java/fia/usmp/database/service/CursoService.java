package fia.usmp.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import fia.usmp.database.dao.ICursoDAO;
import fia.usmp.database.entity.Curso;

public class CursoService implements ICursoService{

	@Autowired
	private ICursoDAO cursoDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Curso> findAll() {
		return cursoDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Curso curso) {
		cursoDAO.save(curso);
	}

	@Override
	@Transactional(readOnly = true)
	public Curso findOne(Long id) {
		return cursoDAO.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		cursoDAO.delete(id);
	}
	
	
	
}
