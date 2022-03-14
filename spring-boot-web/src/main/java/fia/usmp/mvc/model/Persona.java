package fia.usmp.mvc.model;

public class Persona {
	
	private String nom;
	private String ape;
	private int edad;
	private String correo;
	
	public Persona() {
		this.nom = "Fabio";
		this.ape = "Marangon Chávarri";
		this.edad = 18;
		this.correo = "fabio_marangon@usmp.pe";
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getApe() {
		return ape;
	}

	public void setApe(String ape) {
		this.ape = ape;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
	
}
