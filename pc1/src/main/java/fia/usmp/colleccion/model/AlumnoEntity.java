package fia.usmp.colleccion.model;

public class AlumnoEntity {

	private String ape;
	private String nom;
	private double n1;
	private double n2;
	private double n3;
	private double n4;
	private double mayor;
	private double menor;
	private double prom;
	
	public AlumnoEntity(String ape, String nom, double n1, double n2, double n3, double n4) {
		super();
		this.ape = ape;
		this.nom = nom;
		this.n1 = n1;
		this.n2 = n2;
		this.n3 = n3;
		this.n4 = n4;
		this.mayor = Math.max(Math.max(n1, n2), Math.max(n3, n4));
		this.menor = Math.min(Math.min(n1, n2), Math.min(n3, n4));
		this.prom = (n1 + n2 + n3 + n4 - menor) / 3.0;
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

	public double getN1() {
		return n1;
	}

	public void setN1(double n1) {
		this.n1 = n1;
	}

	public double getN2() {
		return n2;
	}

	public void setN2(double n2) {
		this.n2 = n2;
	}

	public double getN3() {
		return n3;
	}

	public void setN3(double n3) {
		this.n3 = n3;
	}

	public double getN4() {
		return n4;
	}

	public void setN4(double n4) {
		this.n4 = n4;
	}
	
	public double getMayor() {
		return mayor;
	}
	
	public double getMenor() {
		return menor;
	}
	
	public double getProm() {
		return prom;
	}
	
}
