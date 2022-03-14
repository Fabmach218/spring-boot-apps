package fia.usmp.database.service;

import java.util.List;

import fia.usmp.database.entity.Curso;

public interface ICursoService {

	public List<Curso> findAll();
	public void save(Curso curso);
	public Curso findOne(Long id);
	public void delete(Long id);
	
}
