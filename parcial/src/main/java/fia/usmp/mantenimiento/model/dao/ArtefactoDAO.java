package fia.usmp.mantenimiento.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import fia.usmp.mantenimiento.model.entity.Artefacto;

@Repository
public class ArtefactoDAO implements IArtefactoDAO{

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Artefacto> findAll() {
		return em.createQuery("from Artefacto").getResultList();
	}

	@Override
	public void save(Artefacto artefacto) {
		if (artefacto.getId() != null && artefacto.getId() > 0) {
			em.merge(artefacto);
		}else {
			em.persist(artefacto);
		}
	}

	@Override
	public Artefacto findOne(Long id) {
		return em.find(Artefacto.class, id);
	}

	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
	}

	
	
	
}
