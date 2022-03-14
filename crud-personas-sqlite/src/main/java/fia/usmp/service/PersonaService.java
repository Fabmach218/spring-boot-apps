package fia.usmp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fia.usmp.model.Persona;
import fia.usmp.repository.PersonaRepository;

@Service
public class PersonaService implements IPersonaService{
	
	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public List<Persona> findAll() {
		return (List<Persona>) personaRepository.findAll();
	}

	@Override
	public Persona findOne(int id) {
		return personaRepository.findById(id).get();
	}

	@Override
	public Persona save(Persona p) {
		return personaRepository.save(p);
	}

	@Override
	public void delete(int id) {
		personaRepository.deleteById(id);
	}
	
	
	
	
	
	
	
}
