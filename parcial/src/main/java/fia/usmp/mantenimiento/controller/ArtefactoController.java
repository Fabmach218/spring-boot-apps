package fia.usmp.mantenimiento.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import fia.usmp.mantenimiento.model.entity.Artefacto;
import fia.usmp.mantenimiento.model.service.IArtefactoService;

@Controller
public class ArtefactoController {

	@Autowired
	private IArtefactoService artefactoService;
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de artefactos");
		model.addAttribute("artefactos", artefactoService.findAll());
		return "frmLista";
	}
	
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		
		Artefacto artefacto = new Artefacto();
		model.put("artefacto", artefacto);
		model.put("titulo", "Formulario de artefacto");
		return "frmFormulario";
		
	}
	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		
		Artefacto artefacto = null;
		
		if(id > 0) {
			artefacto = artefactoService.findOne(id);
		}else {
			return "redirect:/lista";
		}
		
		model.put("artefacto", artefacto);
		model.put("titulo", "Formulario de artefacto");
		return "frmFormulario";
		
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Validated Artefacto artefacto, BindingResult result, Model model, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de artefacto");
			return "frmFormulario";
		}
		
		artefactoService.save(artefacto);
		status.setComplete();
		return "redirect:/lista";
		
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		
		if(id > 0) {
			artefactoService.delete(id);
		}
		
		return "redirect:/lista";
		
	}
	
}
