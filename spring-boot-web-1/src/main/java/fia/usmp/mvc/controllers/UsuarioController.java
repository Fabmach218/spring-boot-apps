package fia.usmp.mvc.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import fia.usmp.mvc.model.Usuario;

@Controller
public class UsuarioController {
	
	@GetMapping({"/index", "/", "/home", ""})
	public String index(Model model) {
		model.addAttribute("titulo", "Hola Spring");
		return "index";
	}
	
	@RequestMapping("/perfil")
	public String getPerfil(Model model) {
		
		Usuario usu = new Usuario();
		
		usu.setApe("Marangon");
		usu.setNom("Fabio");
		usu.setEmail("fabio_marangon@usmp.pe");
		model.addAttribute("usu", usu);
		
		return "frmPerfil";
	}
	
	@GetMapping("/list")
	public String getLista(Model model) {
		
		List<Usuario> objUsu = new ArrayList<>();
		objUsu.add(new Usuario("Marangon","Fabio","fabio_marangon@usmp.pe"));
		objUsu.add(new Usuario("Chávarri","Claudia","claudia.chavarri@hotmail.com"));
		objUsu.add(new Usuario("Marangon","Claudio","cmarangon01@gmail.com"));
		
		model.addAttribute("usu", objUsu);
		return "frmLista";
		
	}
	
	
	
}
