package fia.usmp.colleccion.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fia.usmp.colleccion.models.AlumnoEntity;
import fia.usmp.colleccion.models.ColeccionAlumnos;

@Controller
public class AlumnoController {

	ColeccionAlumnos objCE = new ColeccionAlumnos();
	
	@GetMapping({"/", "/form"})
	public String mostrarFormulario() {
		return "frmFormulario";
	}
	
	@PostMapping("/registrar")
	public String registrarAlumno(@Validated AlumnoEntity alumno) {
		
		objCE.registrarAlumno(alumno);
		
		return "frmFormulario";
		
	}
	
	@GetMapping("/lista")
	public String listarAlumnos(Model model) {
		
		List<AlumnoEntity> lista = new ArrayList<>();
		lista = objCE.getLista();
		model.addAttribute("lista", lista);
		return "frmLista";
		
	}
	
	@PostMapping("/listaHM")
	public String listarHM(Model model) {
		
		List<AlumnoEntity> listaMujeresMayores = new ArrayList<>();
		List<AlumnoEntity> listaHombresMenores = new ArrayList<>();
		
		listaMujeresMayores = objCE.listarMujeresMayores();
		listaHombresMenores = objCE.listarHombresMenores();
		
		model.addAttribute("listaM", listaMujeresMayores);
		model.addAttribute("listaH", listaHombresMenores);
		
		return "frmListaGenEdad";
		
	}
	
}
