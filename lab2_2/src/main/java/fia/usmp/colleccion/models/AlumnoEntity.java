package fia.usmp.colleccion.models;

public class AlumnoEntity {

	private String cod;
	private String ape;
	private String nom;
	private String gen;
	private int edad;
	
	public AlumnoEntity(String cod, String ape, String nom, String gen, int edad) {
		super();
		this.cod = cod;
		this.ape = ape;
		this.nom = nom;
		this.gen = gen;
		this.edad = edad;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
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

	public String getGen() {
		return gen;
	}

	public void setGen(String gen) {
		this.gen = gen;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	
}
