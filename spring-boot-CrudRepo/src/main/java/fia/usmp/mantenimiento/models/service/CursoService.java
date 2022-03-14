package fia.usmp.mantenimiento.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fia.usmp.mantenimiento.models.dao.ICursoDAO;
import fia.usmp.mantenimiento.models.entity.Curso;

@Service
public class CursoService implements ICursoService{

	@Autowired
	private ICursoDAO objCursoDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Curso> findAll() {
		return (List<Curso>) objCursoDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Curso curso) {
		objCursoDAO.save(curso);
	}

	@Override
	@Transactional(readOnly = true)
	public Curso findOne(Long id) {
		return objCursoDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		objCursoDAO.deleteById(id);
	}
	
	
	
}
