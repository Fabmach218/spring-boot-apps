package fia.usmp.model;

public class Usuario {

	private String nom;
	private String password;
	private char log;
	
	public Usuario(String nom, String password, char log) {
		super();
		this.nom = nom;
		this.password = password;
		this.log = log;
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public char getLog() {
		return log;
	}

	public void setLog(char log) {
		this.log = log;
	}
	
}
