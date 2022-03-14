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

import fia.usmp.database.model.entity.Producto;
import fia.usmp.database.model.service.IProductoService;

@Controller
@SessionAttributes("productos")
public class ProductoController {

	@Autowired
	private IProductoService productoService;
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("productos", productoService.findAll());
		return "frmLista";
	}
	
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		
		Producto producto = new Producto();
		model.put("producto", producto);
		model.put("titulo", "Mantenimiento tabla producto");
		return "frmFormulario";
		
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		
		Producto producto = null;
		
		if(id > 0) {
			producto = productoService.findOne(id);
		}else {
			return "redirect:/lista";
		}
		
		model.put("producto", producto);
		model.put("titulo", "Editar producto");
		return "frmFormulario";
		
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Validated Producto producto, BindingResult result, Model model, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Mantenimiento tabla producto");
			return "frmFormulario";
		}
		
		productoService.save(producto);
		status.setComplete();
		return "redirect:/lista";
		
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		
		if(id > 0) {
			productoService.delete(id);
		}
		
		return "redirect:/lista";
		
	}
	
	
	
}
