package fia.usmp.colleccion.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import fia.usmp.colleccion.models.entity.Cliente;
import fia.usmp.colleccion.models.service.IClienteService;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	private IClienteService clienteService;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo","Listado de clientes");
		model.addAttribute("clientes", clienteService.findAll());
		return "frmLista";
	}
	
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		
		Cliente objC = new Cliente();
		model.put("cliente", objC);
		model.put("titulo", "Formulario de registro");
		return "frmFormulario";
		
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		
		Cliente cliente = null;
		if(id > 0) {
			cliente = clienteService.findOne(id);
		}else {
			return "redirect:/listar";
		}
		
		model.put("cliente", cliente);
		model.put("titulo", "Editar cliente");
		return "frmFormulario";
		
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Validated Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de registro");
			return "frmFormulario";
		}
		
		clienteService.save(cliente);
		status.setComplete();
		return "redirect:/listar";
		
	}
	
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		
		if(id > 0) {
			clienteService.delete(id);
		}
		
		return "redirect:/listar";
		
	}
	
	
	
}
