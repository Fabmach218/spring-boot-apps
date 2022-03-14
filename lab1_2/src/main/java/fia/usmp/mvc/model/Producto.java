package fia.usmp.mvc.model;

public class Producto {

	private String codigo;
	private String nom;
	private String marca;
	private double precio;
	
	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Producto(String codigo, String nom, String marca, double precio) {
		super();
		this.codigo = codigo;
		this.nom = nom;
		this.marca = marca;
		this.precio = precio;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	
}
