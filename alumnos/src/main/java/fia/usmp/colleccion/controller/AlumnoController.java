package fia.usmp.colleccion.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import fia.usmp.colleccion.model.AlumnoEntity;
import fia.usmp.colleccion.model.ColeccionAlumnos;

@Controller
public class AlumnoController {
	
	ColeccionAlumnos objCA = new ColeccionAlumnos();
	
	@GetMapping("/")
	public String index(Model model) {
		return "frmRegistrar";
	}
	
	@PostMapping("/registrar")
	//@RequestMapping(value = "/frmRegistrar", method = RequestMethod.POST)
	public String registrarAlumno(@Validated AlumnoEntity alumno) {
		if(alumno != null) {
			objCA.registrarAlumno(alumno);
		}
		return "frmRegistrar";
	}
	
	@GetMapping("/lista")
	public String getLista(Model model) {
		List<AlumnoEntity> listaAlu = new ArrayList<>();
		listaAlu = objCA.getAlumnos();
		model.addAttribute("listaA", listaAlu);
		return "frmLista";
	}
	
	@GetMapping("/listaxDistrito")
	public String getListaXDistrito(Model model) {
		List<AlumnoEntity> listaAluXDistrito = new ArrayList<>();
		listaAluXDistrito = objCA.getAlumnosXDistrito();
		model.addAttribute("listaA", listaAluXDistrito);
		return "frmLista";
	}
	
	
}
