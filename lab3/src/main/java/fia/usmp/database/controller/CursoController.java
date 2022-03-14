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

import fia.usmp.database.entity.Curso;
import fia.usmp.database.service.ICursoService;

@Controller
@SessionAttributes("curso")
public class CursoController {

	@Autowired
	public ICursoService cursoService;
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Lista de cursos");
		model.addAttribute("cursos", cursoService.findAll());
		return "frmLista";
	}
	
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Curso curso = new Curso();
		model.put("curso", curso);
		model.put("titulo", "Formulario de Curso");
		return "frmFormulario";
		
	}
	
	
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		
		Curso curso = null;
		
		if(id > 0) {
			curso = cursoService.findOne(id);
		} else {
			return "redirect:/lista";
		}
		model.put("curso", curso);
		model.put("titulo", "Editar Curso");
		return "frmFormulario";
		
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Validated Curso curso, BindingResult result, Model model, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Curso");
			return "frmFormulario";
		}
		
		cursoService.save(curso);
		status.setComplete();
		return "redirect:/lista";
		
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		
		if(id > 0) {
			cursoService.delete(id);
		}
		return "redirect:/lista";
		
	}
	
	
}
