package fia.usmp.mantenimiento.models.service;

import java.util.List;

import fia.usmp.mantenimiento.models.entity.Curso;

public interface ICursoService {

	public List<Curso> findAll();
	public void save(Curso curso);
	public Curso findOne(Long id);
	public void delete(Long id);
	
}
