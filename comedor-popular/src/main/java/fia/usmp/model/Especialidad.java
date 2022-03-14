package fia.usmp.model;

public class Especialidad {

	private String cod;
	private String nom;
	private String notas;
	
	public Especialidad() {
		
	}
	
	public Especialidad(String cod, String nom, String notas) {
		this.cod = cod;
		this.nom = nom;
		this.notas = notas;
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

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}
	
	
}
