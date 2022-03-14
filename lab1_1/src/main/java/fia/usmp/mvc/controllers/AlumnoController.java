package fia.usmp.mvc.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import fia.usmp.mvc.model.Alumno;

@Controller
public class AlumnoController {

	@GetMapping("/lista")
	public String getLista(Model model) {
		
		List<Alumno> objA = new ArrayList<>();
		objA.add(new Alumno("20190035", "Marangon", "Fabio", "Surco", "Av. Central 960"));
		objA.add(new Alumno("20190193", "Roca", "Ricardo", "La Molina", "Jr. Soria 180"));
		objA.add(new Alumno("20190728", "Jiménez", "Alejandro", "La Molina", "Av. Castilla La Nueva 225"));
		objA.add(new Alumno("20190955", "Quispe", "Gabriel", "La Molina", "Av. Laguna Grande 380"));
		objA.add(new Alumno("20190419", "Muñoz", "Sergio", "Surco", "Av. Mariscal Ramón Castilla 1825"));
		
		model.addAttribute("alumnos", objA);
		return "frmListaAlumnos";
		
	}
	
	@GetMapping("/distrito")
	public String getDistrito(Model model) {
		
		List<Alumno> listaAlumnos = new ArrayList<>();
		listaAlumnos.add(new Alumno("20190035", "Marangon", "Fabio", "Surco", "Av. Central 960"));
		listaAlumnos.add(new Alumno("20190193", "Roca", "Ricardo", "La Molina", "Jr. Soria 180"));
		listaAlumnos.add(new Alumno("20190728", "Jiménez", "Alejandro", "La Molina", "Av. Castilla La Nueva 225"));
		listaAlumnos.add(new Alumno("20190955", "Quispe", "Gabriel", "La Molina", "Av. Laguna Grande 380"));
		listaAlumnos.add(new Alumno("20190419", "Muñoz", "Sergio", "Surco", "Av. Mariscal Ramón Castilla 1825"));
		
		List<Alumno> listaAlumnosLaMolina = new ArrayList<>();
		
		for(int i = 0; i < listaAlumnos.size(); i++) {
			
			if(listaAlumnos.get(i).getDistrito().equals("La Molina")) {
				
				listaAlumnosLaMolina.add(listaAlumnos.get(i));
				
			}
			
		}
		
		model.addAttribute("listaLaMolina", listaAlumnosLaMolina);
		return "frmAlumnosXDistrito";
		
	}
	
	
	
}
