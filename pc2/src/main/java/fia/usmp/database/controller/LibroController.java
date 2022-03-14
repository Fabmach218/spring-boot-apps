package fia.usmp.database.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import fia.usmp.database.model.entity.Libro;
import fia.usmp.database.model.service.ILibroService;

@Controller
@SessionAttributes("libro")
public class LibroController {

	@Autowired
	private ILibroService libroService;
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("libros", libroService.findAll());
		return "frmLista";
	}
	
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		
		Libro libro = new Libro();
		model.put("libro", libro);
		model.put("titulo", "Registro de libros");
		return "frmFormulario";
		
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		
		Libro libro = null;
		
		if(id > 0) {
			libro = libroService.findOne(id);
		}else {
			return "redirect:/lista";
		}
		
		model.put("libro", libro);
		model.put("titulo", "Editar libro");
		return "frmFormulario";
		
		
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Validated Libro libro, BindingResult result, Model model, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Mantenimiento tabla libro");
			return "frmFormulario";
		}
		
		libroService.save(libro);
		status.setComplete();
		return "redirect:/lista";
		
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		
		if(id > 0) {
			libroService.delete(id);
		}
		
		return "redirect:/lista";
		
	}
	
}
