package fia.usmp.database.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import fia.usmp.database.model.entity.Libro;

@Repository
public class LibroDAO implements ILibroDAO{

	@PersistenceContext
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Libro> findAll() {
		return em.createQuery("from Libro").getResultList();
	}

	@Override
	public void save(Libro libro) {
		
		if(libro.getId() != null && libro.getId() > 0) {
			em.merge(libro);
		}else {
			em.persist(libro);
		}
		
	}

	@Override
	public Libro findOne(Long id) {
		return em.find(Libro.class, id);
	}

	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
	}

	
	
	
}
