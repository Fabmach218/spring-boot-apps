package fia.usmp.database.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fia.usmp.database.entity.Curso;

public class CursoDAO implements ICursoDAO{

	@PersistenceContext
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Curso> findAll() {
		return em.createQuery("from Curso").getResultList();
	}

	@Override
	public void save(Curso curso) {
		
		if (curso.getId() != null && curso.getId() > 0) {
			em.merge(curso);
		} else {
			em.persist(curso);
		}
		
	}

	@Override
	public Curso findOne(Long id) {
		return em.find(Curso.class, id);
	}

	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
