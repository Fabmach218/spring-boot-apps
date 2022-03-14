package fia.usmp.mantenimiento.model.service;

import java.util.List;

import fia.usmp.mantenimiento.model.entity.Artefacto;

public interface IArtefactoService {

	public List<Artefacto> findAll();
	public void save(Artefacto artefacto);
	public Artefacto findOne(Long id);
	public void delete(Long id);
	
}
