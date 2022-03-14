package fia.usmp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fia.usmp.model.Persona;
import fia.usmp.service.IPersonaService;

@Controller
public class PersonaController {

	@Autowired
	private IPersonaService personaService;
	
	public PersonaController(IPersonaService personaService) {
		this.personaService = personaService;
	}
	
	@RequestMapping(value = "/form")
	public String abrirFormularioCrear(Model model) {
		model.addAttribute("persona", new Persona());
		return "frmCrear";
	}
	
	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
	public String registrarPersona(Persona p) {
		
		personaService.save(p);
		return "redirect:/";
		
	}
	
	@RequestMapping(value = "/")
	public String listarPersonas(Model model) {
		
		List<Persona> listaPersonas = new ArrayList<>();
		listaPersonas = personaService.findAll();
		model.addAttribute("listaPersonas", listaPersonas);
		return "listaPersonas";
		
	}
	
	@RequestMapping(value = "/form/{id}")
	public String abrirFormularioEditar(@PathVariable(value = "id") int id, Model model) {
		model.addAttribute("persona", personaService.findOne(id));
		return "frmEditar";
	}
	
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.POST)
	public String editarPersona(@PathVariable(value = "id") int id, Persona p) {
		
		Persona p2 = personaService.findOne(id);
		p2.setNombre(p.getNombre());
		p2.setApellido(p.getApellido());
		p2.setEmail(p.getEmail());
		p2.setTelefono(p.getTelefono());
		p2.setDireccion(p.getDireccion());
		personaService.save(p2);
		
		return "redirect:/";
		
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminarPersona(@PathVariable(value = "id") int id) {
		
		personaService.delete(id);
		return "redirect:/";
		
	}
	
	
}
