package fia.usmp.mvc.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import fia.usmp.mvc.model.Producto;

@Controller
public class ProductoController {
	
	@GetMapping("/form")
	public String getForm(Model model) {
		return "frmFormulario";
	}
	
	@PostMapping("/form")
	public String getProducto(Producto p, Model model) {
		
		List<Producto> listaProductos = new ArrayList<>();
		listaProductos.add(p);
		
		
		model.addAttribute("lista", listaProductos);
		return "frmLista";
		
	}
	
}
