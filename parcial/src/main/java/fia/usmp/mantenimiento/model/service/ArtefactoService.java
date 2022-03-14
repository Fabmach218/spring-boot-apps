package fia.usmp.mantenimiento.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fia.usmp.mantenimiento.model.dao.IArtefactoDAO;
import fia.usmp.mantenimiento.model.entity.Artefacto;

@Service
public class ArtefactoService implements IArtefactoService{

	@Autowired
	private IArtefactoDAO artefactoDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Artefacto> findAll() {
		return artefactoDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Artefacto artefacto) {
		artefactoDAO.save(artefacto);
	}

	@Override
	@Transactional(readOnly = true)
	public Artefacto findOne(Long id) {
		return artefactoDAO.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		artefactoDAO.delete(id);
	}
	
	
	
	
}
