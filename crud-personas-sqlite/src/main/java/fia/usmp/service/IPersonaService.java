package fia.usmp.service;

import java.util.List;

import fia.usmp.model.Persona;

public interface IPersonaService {

	public List<Persona> findAll();
	public Persona findOne(int id);
	public Persona save(Persona p);
	public void delete(int id);
	
}
