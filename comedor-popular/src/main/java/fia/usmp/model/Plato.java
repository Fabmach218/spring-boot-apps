package fia.usmp.model;

public class Plato {

	private String cod;
	private String nombre;
	private String tipo;
	private String categoria;
	private String preparacion;
	
	public Plato() {
		
	}

	public Plato(String cod, String nombre, String tipo, String categoria, String preparacion) {
		super();
		this.cod = cod;
		this.nombre = nombre;
		this.tipo = tipo;
		this.categoria = categoria;
		this.preparacion = preparacion;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getPreparacion() {
		return preparacion;
	}
	
	public void setPreparacion(String preparacion) {
		this.preparacion = preparacion;
	}
	
}
