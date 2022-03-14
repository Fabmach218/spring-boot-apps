package fia.usmp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fia.usmp.model.Persona;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Integer>{
	
}
