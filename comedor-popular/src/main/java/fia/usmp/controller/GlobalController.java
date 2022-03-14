package fia.usmp.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.web.ServerProperties.ForwardHeadersStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import fia.usmp.dao.IClienteDAO;
import fia.usmp.dao.IEmpleadoDAO;
import fia.usmp.dao.IEspecialidadDAO;
import fia.usmp.dao.IInsumoDAO;
import fia.usmp.dao.IMenuDAO;
import fia.usmp.dao.IPlatoDAO;
import fia.usmp.dao.IUsuarioDAO;
import fia.usmp.model.Cliente;
import fia.usmp.model.Empleado;
import fia.usmp.model.Especialidad;
import fia.usmp.model.Insumo;
import fia.usmp.model.Menu;
import fia.usmp.model.Plato;
import fia.usmp.model.Usuario;

@Controller
public class GlobalController {

	IUsuarioDAO daoUsuario;
	IMenuDAO daoMenu;
	IPlatoDAO daoPlato;
	IInsumoDAO daoInsumo;
	IEmpleadoDAO daoEmpleado;
	IClienteDAO daoCliente;
	IEspecialidadDAO daoEspecialidad;
	
	public GlobalController(IUsuarioDAO daoUsuario, IMenuDAO daoMenu, IPlatoDAO daoPlato, IInsumoDAO daoInsumo, IEmpleadoDAO daoEmpleado, IClienteDAO daoCliente, IEspecialidadDAO daoEspecialidad) {
		this.daoUsuario = daoUsuario;
		this.daoMenu = daoMenu;
		this.daoPlato = daoPlato;
		this.daoInsumo = daoInsumo;
		this.daoEmpleado = daoEmpleado;
		this.daoCliente = daoCliente;
		this.daoEspecialidad = daoEspecialidad;
	}
	
	@RequestMapping(value = "/")
	public String paginaInicial(Model model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			Usuario u = daoUsuario.getUsuarioActivo();
			model.addAttribute("bienvenido", "Bienvenido, " + u.getNom());
			return "inicio";
			
		}else {
			model.addAttribute("error", "");
			return "inicioSesion";
		}
		
		
		   
	}
	
	@RequestMapping(value = "/iniciar" , method = RequestMethod.POST)
	public String verificarInicioDeSesion( Model model , Usuario usuario) {
		
		if(daoUsuario.validarCredenciales(usuario.getNom(), usuario.getPassword())) {
			
			daoUsuario.iniciarSesion(usuario);
			return "redirect:/";
		
		}else {
			
			if(daoUsuario.existeUsuario(usuario.getNom())){
				
				model.addAttribute("error","Contraseña incorrecta!!!");
				return "inicioSesion";
				
			}else {
					
				model.addAttribute("error","El usuario que ha ingresado no existe!!!");
				return "inicioSesion";
			}
			
		}
		
	}
	
	@RequestMapping(value = "/cerrarSesion")
	public String cerrarSesion() {
		
		daoUsuario.cerrarSesion(daoUsuario.getUsuarioActivo());
		return "redirect:/";
		
	}
	
	@RequestMapping(value = "/listaMenus")
	public String abrirListaMenus(Model model, @RequestParam(name = "mes", defaultValue = "") String mes, @RequestParam(name = "anio", defaultValue = "") String anio) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			List<Menu> listaMenus;
			
			if(!mes.equals("") && !anio.equals("")) {
				listaMenus = daoMenu.getMenuPorMesAnio(mes + "/" + anio);
			}else {
				listaMenus = daoMenu.getMenuMesActual();
			}
			
			model.addAttribute("listaMenus", listaMenus);
			model.addAttribute("titulo", "Lista de menús");
			
			return "listaMenus";
			
		}else {
			
			return "redirect:/";
			
		}
		
		
	}
	
	@RequestMapping(value = "/formMenu")
	public String abrirFormularioMenu(Map<String, Object> model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			Menu m = new Menu();
			m.setCod(daoMenu.getPK());
			
			model.put("menu", m);
			model.put("entradas", daoMenu.getEntradas());
			model.put("platos_medios", daoMenu.getPlatos());
			model.put("postres", daoMenu.getPostres());
			model.put("refrescos", daoMenu.getRefrescos());
			model.put("titulo", "Registrar menú");
			model.put("error", "");
			
			return "registroMenu";
			
		}else {
			return "redirect:/";
		}
		
		
	}
	
	@RequestMapping(value = "/formMenu/{cod}")
	public String editarMenu(@PathVariable(value = "cod") int cod, Map<String, Object> model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			Menu m = daoMenu.getMenuByCod(cod);
			
			model.put("menu", m);
			model.put("entradas", daoMenu.getEntradas());
			model.put("platos_medios", daoMenu.getPlatos());
			model.put("postres", daoMenu.getPostres());
			model.put("refrescos", daoMenu.getRefrescos());
			model.put("titulo", "Editar menú");
			model.put("error", "");
			
			return "registroMenu";
			
		}else {
			return "redirect:/";
		}
		
		
	}
	
	@RequestMapping(value = "/registrarMenu", method = RequestMethod.POST)
	public String guardarMenu(@Validated Menu m, BindingResult result, Model model, SessionStatus status) {
		
		String error = "";
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Registrar menú");
			model.addAttribute("entradas", daoMenu.getEntradas());
			model.addAttribute("platos_medios", daoMenu.getPlatos());
			model.addAttribute("postres", daoMenu.getPostres());
			model.addAttribute("refrescos", daoMenu.getRefrescos());
			return "registroMenu";
		}
		
		if(!daoMenu.verificarFechaRepetida(m.getFecha()) && !daoMenu.existePK(m.getCod())) { //Fecha repetida
			error += "\u2022La fecha no debe repetirse!!!\n";
		}
		
		if(m.getEntrada().equals("-") || m.getPlato_principal().equals("-") || m.getRefresco().equals("-")) {
			error += "\u2022El menú debe contener al menos entrada, plato principal y refresco!!!\n";
		}
		
		if(m.getCant_prep() <= 0 || m.getCant_prep() >= 1000) { //Cantidad no válida
			error += "\u2022La cantidad preparada no puede ser mayor a 1000!!!\n";
		}
		
		if(m.getPrecio() <= 0 || m.getPrecio() >= 10) { //Precio no válido
			error += "\u2022El precio no puede ser mayor a S/. 10";
		}
		
		if(error.equals("")) {
			
			if(daoMenu.existePK(m.getCod())) {
				daoMenu.editarMenu(m);
			}else {
				daoMenu.ingresarMenu(m);
			}
			
			status.setComplete();
			
			return "redirect:/listaMenus?mes=" + m.getFecha().toString().substring(5, 7) + "&anio=" + m.getFecha().toString().substring(0,4);
			
		}else {
			
			if(daoMenu.existePK(m.getCod())) {
				model.addAttribute("titulo", "Editar menú");
			}else {
				model.addAttribute("titulo", "Registrar menú");
			}
			
			model.addAttribute("error", error);
			model.addAttribute("entradas", daoMenu.getEntradas());
			model.addAttribute("platos_medios", daoMenu.getPlatos());
			model.addAttribute("postres", daoMenu.getPostres());
			model.addAttribute("refrescos", daoMenu.getRefrescos());
			return "registroMenu";
		}
		
		
	}
	
	@RequestMapping(value = "/eliminarMenu/{cod}")
	public String eliminarMenu(@PathVariable(value = "cod") int cod) {
		
		if (daoUsuario.existeSesionActiva()) {
			
			daoMenu.eliminarMenu(cod);
			
			return "redirect:/listaMenus";
			
		}else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value = "/listaClientesMenu/{cod}")
	public String abrirListaClientesMenu(@PathVariable(value = "cod") int cod, Map<String, Object> model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			List<Cliente> lista = daoCliente.getClientesPorMenu(cod);
			
			model.put("listaClientes", lista);
			model.put("titulo", "Registro: Menú N° " + cod);
			
			return "listaClientesMenu";
			
		}else {
			return "redirect:/";
		}
		
		
	}
	
	@RequestMapping(value = "/listaPlatos")
	public String abrirListaPlatos(Model model,
			@RequestParam(name = "nom", defaultValue = "") String nom,
			@RequestParam(name = "tipo", defaultValue = "") String tipo,
			@RequestParam(name = "categoria", defaultValue = "") String categoria)  {

		if(daoUsuario.existeSesionActiva()) {
			
			List<Plato> listaPlatos;
			List<String> listaTipos, listaCategorias;
			
			
			listaPlatos = daoPlato.getPlatos();
			listaTipos = daoPlato.getTipos();
			listaCategorias = daoPlato.getCategorias();
			
			if(nom.equals("") && tipo.equals("") && categoria.equals("")) {
				listaPlatos = daoPlato.getPlatos();
			}else {
				listaPlatos = daoPlato.filtrarPlatos(nom, tipo, categoria);
			}
			
			model.addAttribute("listaPlatos", listaPlatos);
			model.addAttribute("listaTipos", listaTipos);
			model.addAttribute("listaCategorias", listaCategorias);
			model.addAttribute("titulo", "Lista de platos");
			
			return "listaPlatos";
			
		}else {
			
			return "redirect:/";
			
		}

	}
	
	@RequestMapping(value = "/formPlato")
	public String abrirFormularioPlato(Map<String, Object> model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			Plato p = new Plato();
			p.setCod(daoPlato.getPK());
			
			model.put("plato", p);
			model.put("tipos", daoPlato.getTipos());
			model.put("categorias", daoPlato.getCategorias());
			model.put("titulo", "Registrar plato");
			model.put("error", "");
			
			return "registroPlatos";
			
		}else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value = "/formPlato/{cod}")
	public String editarPlato(@PathVariable(value = "cod") String cod, Map<String, Object> model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			Plato p = daoPlato.getPlatoByCod(cod);
			
			model.put("plato", p);
			model.put("tipos", daoPlato.getTipos());
			model.put("categorias", daoPlato.getCategorias());
			model.put("titulo", "Editar plato");
			model.put("error", "");
			
			return "registroPlatos";
			
		}else {
			return "redirect:/";
		}
		
		
	}
	
	@RequestMapping(value = "/registrarPlato", method = RequestMethod.POST)
	public String guardarPlato(@Validated Plato p, BindingResult result, Model model, SessionStatus status) {
		
		String error = "";
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Registrar plato");
			model.addAttribute("tipos", daoPlato.getTipos());
			model.addAttribute("categorias", daoPlato.getCategorias());
			return "registroPlatos";
		}
		
		if(p.getNombre().length() > 40) { //Nombre muy largo
			error += "\u2022El nombre es muy largo!!!";
		}
		
		if(error.equals("")) {
			
			if(daoPlato.existePK(p.getCod())) {
				daoPlato.editarPlato(p);
			}else {
				daoPlato.ingresarPlato(p);
			}
			
			status.setComplete();
			
			return "redirect:/listaPlatos";
			
		}else {
			
			if(daoPlato.existePK(p.getCod())) {
				model.addAttribute("titulo", "Editar plato");
			}else {
				model.addAttribute("titulo", "Registrar plato");
			}
			
			model.addAttribute("error", error);
			model.addAttribute("titulo", "Registrar plato");
			model.addAttribute("tipos", daoPlato.getTipos());
			model.addAttribute("categorias", daoPlato.getCategorias());
			return "registroPlatos";
		}
		
		
	}
	
	@RequestMapping(value = "/eliminarPlato/{cod}")
	public String eliminarPlato(@PathVariable(value = "cod") String cod) {
		
		if (daoUsuario.existeSesionActiva()) {
			
			daoPlato.eliminarPlato(cod);
			
			return "redirect:/listaPlatos";
			
		}else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value = "/detalle/{cod}")
	public String abrirDetallePlato(@PathVariable(value = "cod") String cod, Map<String, Object> model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			Plato p = daoPlato.getPlatoByCod(cod);
			List<Insumo> listaIngredientes = daoInsumo.getIngredientesPorPlato(cod);
			
			model.put("plato", p);
			model.put("titulo", p.getNombre());
			model.put("listaIngredientes", listaIngredientes);
			
			return "detallePlato";
			
		}else {
			return "redirect:/";
		}
		
		
	}
	
	@RequestMapping(value = "/formReceta/{cod}")
	public String abrirRecetaPlato(@PathVariable(value = "cod") String cod, @RequestParam(name = "ing", defaultValue = "") String ing, Map<String, Object> model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			Plato p = daoPlato.getPlatoByCod(cod);
			
			List<Insumo> listaIngredientes;
			
			if(ing.equals("")) {
				listaIngredientes = daoInsumo.getIngredientesPorPlato(cod);
			}else {
				listaIngredientes = daoInsumo.buscarIngredientesPorPlato(cod, ing);
			}
			
			
			model.put("plato", p);
			model.put("titulo", "Receta: " + p.getNombre());
			model.put("listaIngredientes", listaIngredientes);
			
			return "listaReceta";
			
		}else {
			return "redirect:/";
		}
		
		
	}
	
	@RequestMapping(value = "/editarPreparacion/{cod}", method = RequestMethod.POST)
	public String editarPreparacion(@PathVariable(value = "cod") String cod, @Validated Plato p, BindingResult result, Model model, SessionStatus status) {
		
		if(result.hasErrors()) {
			return "redirect:/formReceta/" + cod;
		}
		
		daoPlato.editarPreparacion(cod, p.getPreparacion());
		status.setComplete();
		
		return "redirect:/detalle/" + cod;
		
	}
	
	@RequestMapping(value = "/formIngrediente/{cod}")
	public String abrirFormularioIngrediente(@PathVariable(value = "cod") String cod, Map<String, Object> model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			Insumo i = new Insumo();
			
			model.put("ingrediente", i);
			model.put("codPlato", cod);
			model.put("listaInsumos", daoInsumo.getListaInsumos());
			model.put("listaUnidades", daoInsumo.getUnidadesIngredientes());
			model.put("titulo", "Registrar ingrediente");
			model.put("error", "");
			
			return "registroIngrediente";
			
		}else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value = "/formIngrediente/{codPla}/{codIng}")
	public String editarIngrediente(@PathVariable(value = "codPla") String codPla, @PathVariable(value = "codIng") String codIng, Map<String, Object> model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			Insumo i = daoInsumo.getIngredientePorPlato(codPla, codIng);
			
			model.put("ingrediente", i);
			model.put("codPlato", codPla);
			model.put("listaInsumos", daoInsumo.getListaInsumos());
			model.put("listaUnidades", daoInsumo.getUnidadesIngredientes());
			model.put("titulo", "Registrar ingrediente");
			model.put("error", "");
			
			return "registroIngrediente";
			
		}else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value = "/registrarIngrediente/{codPla}", method = RequestMethod.POST)
	public String guardarIngrediente(@PathVariable(value = "codPla") String codPla, @Validated Insumo ingrediente, BindingResult result, Model model, SessionStatus status) {
		
		String error = "";
		
		if(result.hasErrors()) {
			return "redirect:/formIngrediente/" + codPla;
		}
		
		if(ingrediente.getCantidad() <= 0 || ingrediente.getCantidad() >= 1000) {
			error += "\u2022La cantidad debe ser menor a 1000!!!";
		}
		
		if(error.equals("")) {
			
			if(daoInsumo.existeIngrediente(codPla, ingrediente.getInsumo())) {
				daoInsumo.editarIngrediente(codPla, ingrediente);
			}else {
				daoInsumo.ingresarIngrediente(codPla, ingrediente);
			}
			
			status.setComplete();
			
			return "redirect:/formReceta/" + codPla;
			
		}else {
			
			model.addAttribute("error", error);
			model.addAttribute("ingrediente", ingrediente);
			model.addAttribute("codPlato", codPla);
			model.addAttribute("titulo", "Registrar ingrediente");
			model.addAttribute("listaInsumos", daoInsumo.getListaInsumos());
			model.addAttribute("listaUnidades", daoInsumo.getUnidadesIngredientes());
			
			return "registroIngrediente";
			
		}
		
	}
	
	@RequestMapping(value = "/eliminarIngrediente/{codPla}/{codIng}")
	public String eliminarIngrediente(@PathVariable(value = "codPla") String codPla, @PathVariable(value = "codIng") String codIng) {
		
		if (daoUsuario.existeSesionActiva()) {
			
			daoInsumo.eliminarIngrediente(codPla, daoInsumo.getIngredientePorPlato(codPla, codIng).getInsumo());
			
			return "redirect:/formReceta/" + codPla;
			
		}else {
			return "redirect:/";
		}

	}
	
	@RequestMapping(value = "/listaInsumos")
	public String abrirListaInsumos(@RequestParam(name = "nomIns", defaultValue = "") String nomIns, Model model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			List<Insumo> listaInsumos;
			
			if(nomIns.equals("")) {
				listaInsumos = daoInsumo.getInsumos();
			}else {
				listaInsumos = daoInsumo.buscarInsumos(nomIns);
			}
			
			model.addAttribute("listaInsumos", listaInsumos);
			model.addAttribute("titulo", "Inventario de insumos");
			
			return "listaInsumos";
			
		}else {
			
			return "redirect:/";
			
		}
		
		
	}
	
	@RequestMapping(value = "/formInsumo")
	public String abrirFormularioInsumo(Map<String, Object> model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			Insumo i = new Insumo();
			i.setCod(daoInsumo.getPK());
			
			model.put("insumo", i);
			model.put("listaUnidades", daoInsumo.getUnidadesInsumos());
			model.put("titulo", "Registrar insumo");
			model.put("error", "");
			
			return "registroInsumo";
			
		}else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value = "/formInsumo/{cod}")
	public String editarInsumo(@PathVariable(value = "cod") String cod, Map<String, Object> model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			Insumo i = daoInsumo.getInsumo(cod);
			
			model.put("insumo", i);
			model.put("listaUnidades", daoInsumo.getUnidadesInsumos());
			model.put("titulo", "Registrar insumo");
			model.put("error", "");
			
			return "registroInsumo";
			
		}else {
			return "redirect:/";
		}
		
		
	}
	
	@RequestMapping(value = "/registrarInsumo", method = RequestMethod.POST)
	public String guardarInsumo(@Validated Insumo insumo, BindingResult result, Model model, SessionStatus status) {
		
		String error = "";
		
		if(result.hasErrors()) {
			return "redirect:/formInsumo";
		}
		
		if(insumo.getInsumo().length() > 40) {
			error += "\u2022La longitud del nombre debe ser de máximo 40 caracteres!!!\n";
		}
		
		if(insumo.getCantidad() <= 0 || insumo.getCantidad() >= 1000) {
			error += "\u2022La cantidad debe ser menor a 1000!!!";
		}
		
		if(error.equals("")) {
			
			if(daoInsumo.existePK(insumo.getCod())) {
				daoInsumo.editarInsumo(insumo);
			}else {
				daoInsumo.ingresarInsumo(insumo);
			}
			
			status.setComplete();
			
			return "redirect:/listaInsumos";
			
		}else {
			
			model.addAttribute("error", error);
			model.addAttribute("titulo", "Registrar insumo");
			model.addAttribute("listaUnidades", daoInsumo.getUnidadesInsumos());
			
			return "registroInsumo";
			
		}
		
	}
	
	@RequestMapping(value = "/eliminarInsumo/{codIns}")
	public String eliminarInsumo(@PathVariable(value = "codIns") String codIns) {
		
		if (daoUsuario.existeSesionActiva()) {
			
			daoInsumo.eliminarInsumo(codIns);
			
			return "redirect:/listaInsumos";
			
		}else {
			return "redirect:/";
		}

	}
	
	@RequestMapping(value = "/listaEmpleados")
	public String abrirListaEmpleados(Model model,
			@RequestParam(name = "nom", defaultValue = "") String nom,
			@RequestParam(name = "sexo", defaultValue = "") String sexo,
			@RequestParam(name = "cargo", defaultValue = "") String cargo) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			List<Empleado> listaEmpleados;
			
			if(nom.equals("") && sexo.equals("") && cargo.equals("")) {
				listaEmpleados = daoEmpleado.getEmpleados();
			}else {
				listaEmpleados = daoEmpleado.filtrarEmpleados(nom, sexo, cargo);
			}
			
			model.addAttribute("listaCargos", daoEmpleado.getCargos());
			model.addAttribute("listaEmpleados", listaEmpleados);
			model.addAttribute("titulo", "Lista de empleados");
			
			return "listaEmpleados";
			
		}else {
			
			return "redirect:/";
			
		}
		
		
		
	}
	
	@RequestMapping(value = "/formEmpleado")
	public String abrirFormularioEmpleado(Map<String, Object> model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			Empleado e = new Empleado();
			
			model.put("empleado", e);
			model.put("titulo", "Registrar empleado");
			model.put("error", "");
			
			return "registroEmpleado";
			
		}else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value = "/formEmpleado/{dni}")
	public String editarEmpleado(@PathVariable(value = "dni") String dni, Model model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			Empleado e = daoEmpleado.getEmpleadoByDni(dni);
			
			model.addAttribute("empleado", e);
			model.addAttribute("titulo", "Editar empleado");
			model.addAttribute("error", "");
			
			return "registroEmpleado";
			
		}else {
			return "redirect:/";
		}
		
		
	}
	
	@RequestMapping(value = "/registrarEmpleado", method = RequestMethod.POST)
	public String guardarEmpleado(@Validated Empleado e, BindingResult result, Model model, SessionStatus status) {
		
		String error = "";
		
		if(result.hasErrors()) {
			return "redirect:/formEmpleado";
		}
		
		if(e.getDni().length() != 8) {
			error += "\u2022El DNI debe tener 8 caracteres!!!\n";
		}
		
		if(e.getApe_pat().length() > 20) {
			error += "\u2022La longitud del apellido paterno debe ser de máximo 20 caracteres!!!\n";
		}
		
		if(e.getApe_mat().length() > 20) {
			error += "\u2022La longitud del apellido materno debe ser de máximo 20 caracteres!!!\n";
		}
		
		if(e.getNombres().length() > 30) {
			error += "\u2022La longitud del nombre debe ser de máximo 30 caracteres!!!\n";
		}
		
		if(e.getSueldo() < 1800 || e.getSueldo() > 6000) {
			error += "\u2022El sueldo debe encontrarse entre S/. 1800 y S/. 6000!!!\n";
		}
		
		if(Integer.parseInt(e.getFecha_nacimiento().toString().substring(0, 4)) < 1950) {
			error += "\u2022La fecha de nacimiento no puede ser menor del 01/01/1950!!!\n";
		}
		
		if(e.getNum_celular().length() != 9) {
			error += "\u2022Ingrese un número de celular válido!!!";
		}
		
		if(error.equals("")) {
			
			if(daoEmpleado.existeEmpleado(e.getDni())) {
				daoEmpleado.editarEmpleado(e);
			}else {
				daoEmpleado.ingresarEmpleado(e);;
			}
			
			status.setComplete();
			
			return "redirect:/perfilEmpleado/" + e.getDni();
			
		}else {
			
			model.addAttribute("error", error);
			model.addAttribute("titulo", "Registrar empleado");
			
			return "registroEmpleado";
			
		}
		
	}
	
	@RequestMapping(value = "/eliminarEmpleado/{dni}")
	public String eliminarEmpleado(@PathVariable(value = "dni") String dni) {
		
		if (daoUsuario.existeSesionActiva()) {
			
			daoEmpleado.eliminarEmpleado(dni);
			
			return "redirect:/listaEmpleados";
			
		}else {
			return "redirect:/";
		}

	}
	
	@RequestMapping(value = "/perfilEmpleado/{dni}")
	public String abrirPerfilEmpleado(@PathVariable(value = "dni") String dni, Map<String, Object> model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			Empleado e = daoEmpleado.getEmpleadoByDni(dni);
			
			model.put("empleado", e);
			model.put("titulo", "Perfil de empleado");
			model.put("listaEspecialidad", daoEspecialidad.getEspecialidadesEmpleado(dni));
			
			return "perfilEmpleado";
			
		}else {
			return "redirect:/";
		}
		
		
	}
	
	@RequestMapping(value = "/listaEspecialidades/{dni}")
	public String abrirEspecialidadEmpleado(@PathVariable(value = "dni") String dni, Map<String, Object> model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			Empleado e = daoEmpleado.getEmpleadoByDni(dni);
			
			List<Especialidad> listaEspecialidad = daoEspecialidad.getEspecialidadesEmpleado(dni);
			
			/*if(ing.equals("")) {
				listaIngredientes = daoInsumo.getIngredientesPorPlato(cod);
			}else {
				listaIngredientes = daoInsumo.buscarIngredientesPorPlato(cod, ing);
			}*/
			
			model.put("empleado", e);
			model.put("titulo", "Especialidades de empleado");
			model.put("listaEspecialidad", listaEspecialidad);
			
			return "listaEspecialidades";
			
		}else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value = "/formEspecialidad/{dni}")
	public String abrirFormularioEspecialidad(@PathVariable(value = "dni") String dni, Map<String, Object> model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			Especialidad e = new Especialidad();
			
			model.put("especialidad", e);
			model.put("dni", dni);
			model.put("listaEspecialidades", daoPlato.getCategorias());
			model.put("titulo", "Registrar especialidad");
			model.put("error", "");
			
			return "registroEspecialidades";
			
		}else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value = "/formEspecialidad/{dni}/{cod}")
	public String editarEspecialidad(@PathVariable(value = "dni") String dni, @PathVariable(value = "cod") String cod, Map<String, Object> model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			Especialidad e = daoEspecialidad.getEspecialidadByCod(dni, cod);
			
			model.put("especialidad", e);
			model.put("dni", dni);
			model.put("listaEspecialidades", daoPlato.getCategorias());
			model.put("titulo", "Editar especialidad");
			model.put("error", "");
			
			return "registroEspecialidades";
			
		}else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value = "/registrarEspecialidad/{dni}", method = RequestMethod.POST)
	public String guardarEspecialidad(@PathVariable(value = "dni") String dni, @Validated Especialidad e, BindingResult result, Model model, SessionStatus status) {
		
		if(result.hasErrors()) {
			return "redirect:/formEspecialidad/" + dni;
		}
		
		if(daoEspecialidad.existeEspecialidad(dni, e.getNom())) {
			daoEspecialidad.editarEspecialidad(dni, e);
		}else {
			daoEspecialidad.ingresarEspecialidad(dni, e);
		}
		
			
		status.setComplete();
			
		return "redirect:/listaEspecialidades/" + dni;
			
		
	}
	
	@RequestMapping(value = "/eliminarEspecialidad/{dni}/{codEsp}")
	public String eliminarEspecialidad(@PathVariable(value = "dni") String dni, @PathVariable(value = "codEsp") String codEsp) {
		
		if (daoUsuario.existeSesionActiva()) {
			
			daoEspecialidad.eliminarEspecialidad(dni, daoEspecialidad.getEspecialidadByCod(dni, codEsp).getNom());
			
			return "redirect:/listaEspecialidades/" + dni;
			
		}else {
			return "redirect:/";
		}

	}
	
	@RequestMapping(value = "/listaClientes")
	public String abrirListaClientes(Model model,
			@RequestParam(name = "nom", defaultValue = "") String nom,
			@RequestParam(name = "sexo", defaultValue = "") String sexo,
			@RequestParam(name = "dis", defaultValue = "") String dis) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			List<Cliente> listaClientes;
			
			if(nom.equals("") && sexo.equals("") && dis.equals("")) {
				listaClientes = daoCliente.getClientes();
			}else {
				listaClientes = daoCliente.filtrarClientes(nom, sexo, dis);
			}
			
			model.addAttribute("listaDistritos", daoCliente.getDistritos());
			model.addAttribute("listaClientes", listaClientes);
			model.addAttribute("titulo", "Lista de clientes");
			
			return "listaClientes";
			
		}else {
			
			return "redirect:/";
			
		}

	}
	
	@RequestMapping(value = "/formCliente")
	public String abrirFormularioCliente(Map<String, Object> model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			Cliente c = new Cliente();
			
			model.put("cliente", c);
			model.put("listaDistritos", daoCliente.getDistritos());
			model.put("titulo", "Registrar cliente");
			model.put("error", "");
			
			return "registroCliente";
			
		}else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value = "/formCliente/{dni}")
	public String editarCliente(@PathVariable(value = "dni") String dni, Model model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			Cliente c = daoCliente.getClienteByDni(dni);
			
			model.addAttribute("cliente", c);
			model.addAttribute("listaDistritos", daoCliente.getDistritos());
			model.addAttribute("titulo", "Editar cliente");
			model.addAttribute("error", "");
			
			return "registroCliente";
			
		}else {
			return "redirect:/";
		}
		
		
	}
	
	@RequestMapping(value = "/registrarCliente", method = RequestMethod.POST)
	public String guardarEmpleado(@Validated Cliente c, BindingResult result, Model model, SessionStatus status) {
		
		String error = "";
		
		if(result.hasErrors()) {
			return "redirect:/formEmpleado";
		}
		
		if(c.getDni().length() != 8) {
			error += "\u2022El DNI debe tener 8 caracteres!!!\n";
		}
		
		if(c.getApe_pat().length() > 20) {
			error += "\u2022La longitud del apellido paterno debe ser de máximo 20 caracteres!!!\n";
		}
		
		if(c.getApe_mat().length() > 20) {
			error += "\u2022La longitud del apellido materno debe ser de máximo 20 caracteres!!!\n";
		}
		
		if(c.getNombres().length() > 30) {
			error += "\u2022La longitud del nombre debe ser de máximo 30 caracteres!!!\n";
		}
		
		if(c.getNum_celular().length() != 9 && !c.getNum_celular().equals("")) {
			error += "\u2022Ingrese un número de celular válido!!!";
		}
		
		if(error.equals("")) {
			
			if(daoCliente.existeCliente(c.getDni())) {
				
				daoCliente.editarCliente(c);
				
				status.setComplete();
				
				return "redirect:/perfilCliente/" + c.getDni();
			}else {
				
				daoCliente.ingresarCliente(c);
				
				status.setComplete();
				
				return "redirect:/listaClientes";
			}
			
			
			
		}else {
			
			model.addAttribute("error", error);
			model.addAttribute("listaDistritos", daoCliente.getDistritos());
			model.addAttribute("titulo", "Registrar cliente");
			
			return "registroCliente";
			
		}
		
	}
	
	@RequestMapping(value = "/eliminarCliente/{dni}")
	public String eliminarCliente(@PathVariable(value = "dni") String dni) {
		
		if (daoUsuario.existeSesionActiva()) {
			
			daoCliente.eliminarCliente(dni);
			
			return "redirect:/listaClientes";
			
		}else {
			return "redirect:/";
		}

	}
	
	@RequestMapping(value = "/perfilCliente/{dni}")
	public String abrirPerfilCliente(@PathVariable(value = "dni") String dni, Map<String, Object> model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			Cliente c = daoCliente.getClienteByDni(dni);
			
			model.put("cliente", c);
			model.put("titulo", "Perfil de cliente");
			model.put("listaMenus", daoMenu.getMenusCliente(dni));
			
			return "perfilCliente";
			
		}else {
			return "redirect:/";
		}
		
		
	}
	
	@RequestMapping(value = "/listaMenusClientes/{dni}")
	public String abrirMenusClientes(@PathVariable(value = "dni") String dni, Map<String, Object> model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			Cliente c = daoCliente.getClienteByDni(dni);
			
			List<Menu> listaMenus = daoMenu.getMenusCliente(dni);
			
			/*if(ing.equals("")) {
				listaIngredientes = daoInsumo.getIngredientesPorPlato(cod);
			}else {
				listaIngredientes = daoInsumo.buscarIngredientesPorPlato(cod, ing);
			}*/
			
			model.put("cliente", c);
			model.put("titulo", "Registro de asistencias");
			model.put("listaMenus", listaMenus);
			
			return "listaMenusClientes";
			
		}else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value = "/formMenuCliente/{dni}")
	public String abrirFormularioMenuCliente(@PathVariable(value = "dni") String dni, Map<String, Object> model) {
		
		if(daoUsuario.existeSesionActiva()) {
			
			Menu m = new Menu();
			
			model.put("menu", m);
			model.put("dni", dni);
			model.put("titulo", "Registrar menú");
			model.put("error", "");
			
			return "registroMenuCliente";
			
		}else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value = "/registrarMenuCliente/{dni}", method = RequestMethod.POST)
	public String guardarMenuCliente(@PathVariable(value = "dni") String dni, @Validated Menu m, BindingResult result, Model model, SessionStatus status) {
		
		String error = "";
		
		if(result.hasErrors()) {
			return "redirect:/formMenuCliente/" + dni;
		}
		
		if(!daoMenu.existeMenuFecha(m.getFecha())) {
			error += "\u2022No existe un menú registrado en esa fecha!!!";
		}
		
		Menu menu = daoMenu.buscarMenuPorFecha(m.getFecha());
		
		if(error.equals("")) {
			
			daoMenu.ingresarMenuCliente(dni, menu);
			
			status.setComplete();
			
			return "redirect:/listaMenusClientes/" + dni;
			
		}else {
			
			model.addAttribute("error", error);
			model.addAttribute("titulo", "Registrar menú");
			
			return "registroMenuCliente";
			
		}
			
		
	}
	
	@RequestMapping(value = "/eliminarMenuCliente/{dni}/{codMenu}")
	public String eliminarMenuCliente(@PathVariable(value = "dni") String dni, @PathVariable(value = "codMenu") int codMenu) {
		
		if (daoUsuario.existeSesionActiva()) {
			
			daoMenu.eliminarMenuCliente(dni, codMenu);
			
			return "redirect:/listaMenusClientes/" + dni;
			
		}else {
			return "redirect:/";
		}

	}
	
}
