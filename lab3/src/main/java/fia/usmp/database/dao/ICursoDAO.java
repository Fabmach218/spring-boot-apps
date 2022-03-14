package fia.usmp.database.dao;

import java.util.List;

import fia.usmp.database.entity.Curso;

public interface ICursoDAO {

	public List<Curso> findAll();
	public void save(Curso curso);
	public Curso findOne(Long id);
	public void delete(Long id);
	
}
