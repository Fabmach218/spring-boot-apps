package fia.usmp.mvc.model;

public class Alumno {

	private String codigo;
	private String ape;
	private String nom;
	private String distrito;
	private String direccion;
	
	public Alumno() {
		super();
	}

	public Alumno(String codigo, String ape, String nom, String distrito, String direccion) {
		super();
		this.codigo = codigo;
		this.ape = ape;
		this.nom = nom;
		this.distrito = distrito;
		this.direccion = direccion;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
}
