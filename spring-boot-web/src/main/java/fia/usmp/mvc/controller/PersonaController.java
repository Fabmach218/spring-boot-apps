package fia.usmp.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fia.usmp.mvc.model.Persona;

@Controller
public class PersonaController {
	
	@GetMapping({"/persona", "/"})
	public String getDatosPersona(Persona p, Model model) {
		model.addAttribute("ape", p.getApe());
		model.addAttribute("nom", p.getNom());
		model.addAttribute("correo", p.getCorreo());
		model.addAttribute("edad", p.getEdad());
		return "frmPersona";
	}
	
	
	
}
