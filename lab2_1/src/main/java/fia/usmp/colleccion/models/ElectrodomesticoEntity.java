package fia.usmp.colleccion.models;

public class ElectrodomesticoEntity {

	private String cod;
	private String nom;
	private String marca;
	private String modelo;
	private double precio;
	private String desc;
	
	public ElectrodomesticoEntity(String cod, String nom, String marca, String modelo, double precio, String desc) {
		super();
		this.cod = cod;
		this.nom = nom;
		this.marca = marca;
		this.modelo = modelo;
		this.precio = precio;
		this.desc = desc;
	}
	
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
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
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
	
}
