package fia.usmp.colleccion.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fia.usmp.colleccion.models.ColeccionElectrodomesticos;
import fia.usmp.colleccion.models.ElectrodomesticoEntity;

@Controller
public class ElectrodomesticoController {

	ColeccionElectrodomesticos objCE = new ColeccionElectrodomesticos();
	
	@GetMapping({"/", "/form", "/home"})
	public String mostrarFormulario() {
		return "frmFormulario";
	}
	
	@PostMapping("/registrar")
	public String registrarProducto(@Validated ElectrodomesticoEntity objEE) {
		
		if (objEE != null) {
			objCE.registrar(objEE);
		}
		
		return "frmFormulario";
		
	}
	
	@GetMapping("/lista")
	public String listarProductos(Model model) {
		
		List<ElectrodomesticoEntity> lista = new ArrayList<>();
		lista = objCE.getLista();
		model.addAttribute("lista", lista);
		return "frmLista";
		
	}
	
	@PostMapping("/listaxMarca")
	public String registrarMarca(String marca, Model model) {
		
		List<ElectrodomesticoEntity> listaXMarca = new ArrayList<>();
		listaXMarca = objCE.listarXMarca(marca);
		model.addAttribute("lista", listaXMarca);
		return "frmLista";
		
	}
	
	
}
