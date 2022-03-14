package fia.usmp.mvc.model;

public class Usuario {

	private String ape;
	private String nom;
	private String email;
	
	public Usuario(String ape, String nom, String email) {
		super();
		this.ape = ape;
		this.nom = nom;
		this.email = email;
	}
	
	public Usuario() {
		super();
	}

	public String getApe() {
		return ape;
	}

	public void setApe(String ape) {
		this.ape = ape;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
