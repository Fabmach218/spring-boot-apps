package fia.usmp.colleccion.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fia.usmp.colleccion.model.AlumnoEntity;
import fia.usmp.colleccion.model.ColeccionAlumnos;

@Controller
public class AlumnoController {
	
	ColeccionAlumnos objCA = new ColeccionAlumnos();
	
	@GetMapping({"/", "/form"})
	public String abrirFormulario() {
		return "frmFormulario";
	}
	
	@PostMapping("/registrar")
	public String registrarAlumno(AlumnoEntity alumno) {
		
		objCA.registrarAlumno(alumno);
		return "frmFormulario";
		
	}
	
	@GetMapping("/lista")
	public String mostrarLista(Model model) {
		
		List<AlumnoEntity> listaAlumnos = new ArrayList<>();
		listaAlumnos = objCA.getLista();
		model.addAttribute("lista", listaAlumnos);
		return "frmLista";
		
	}
	
	@GetMapping("/reporte")
	public String mostrarReporte(Model model) {
		
		objCA.actualizarDatos();
		model.addAttribute("stats", objCA);
		return "frmReporte";
		
	}
	
	
	
}
