package fia.usmp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import fia.usmp.dao.IEstudianteDAO;
import fia.usmp.model.Estudiante;

@Controller
public class EstudianteController {

	private IEstudianteDAO dao;
	
	public EstudianteController(IEstudianteDAO dao) {
		this.dao = dao;
	}
	
	@GetMapping("/lista")
	public String mostrarLista(Model model, @RequestParam(name = "nom", defaultValue = "") String nom) {
		
		//String filtro = "PROVINCIA = 'LIMA'";
		//dao.setFiltro(filtro);
		
		List<Estudiante> lista;
		int cant;
		String textoCantidad = "";
		
		if(nom.equals("")) {
			lista = dao.getEstudiantes();
			cant = dao.getCantidad();
			textoCantidad = "Hay " + cant + " registros";
		}else {
			lista = dao.buscarEstudiantes(nom);
			cant = dao.getCantidadResultadosBusqueda(nom);
			textoCantidad = "Se encontraron " + cant + " registros que contienen " + nom;
		}
		
		
		//List<Estudiante> lista = new ArrayList<Estudiante>();
		//lista.add(new Estudiante("", "", "", "", "", "", "", "", "", ""));
		
		model.addAttribute("lista", lista);
		model.addAttribute("cant", textoCantidad);
		return "frmLista";
		
	}
	
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		
		Estudiante e = new Estudiante();
		model.put("e", e);
		model.put("titulo", "Registro de estudiantes");
		model.put("boton", "Registrar estudiante");
		return "frmEstudiante";
		
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Validated Estudiante e, BindingResult result, Model model, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Registro de estudiantes");
			return "frmEstudiante";
		}
		
		if(e.getCod().equals("")) {
			e.setCod(dao.getPK());
			dao.registrarEstudiante(e);
		}else {
			dao.actualizarEstudiante(e);
		}
		
		status.setComplete();
		
		return "redirect:/lista";
		
	}
	
	@RequestMapping(value = "/form/{cod}")
	public String editar(@PathVariable(value = "cod") String cod, Map<String, Object> model) {
		
		Estudiante e = null;
		
		e = dao.getEstudianteByCod(cod);
		
		model.put("e", e);
		model.put("titulo", "Editar estudiante");
		model.put("boton", "Editar estudiante");
		return "frmEstudiante";
		
	}
	
	@RequestMapping(value = "/eliminar/{cod}")
	public String eliminar(@PathVariable(value = "cod") String cod) {
		
		dao.eliminarEstudiante(cod);
		
		return "redirect:/lista";
		
	}
	
}
