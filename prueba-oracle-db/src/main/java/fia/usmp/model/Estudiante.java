package fia.usmp.model;

public class Estudiante {

	private String cod;
	private String nom;
	private String seccion;
	private String correo;
	private String dis;
	private String prov;
	private String dep;
	private String dni;
	private String mod_ing;
	private String sem_ing;
	
	public Estudiante(String cod, String nom, String seccion, String correo, String dis, String prov, String dep,
			String dni, String mod_ing, String sem_ing) {
		super();
		this.cod = cod;
		this.nom = nom;
		this.seccion = seccion;
		this.correo = correo;
		this.dis = dis;
		this.prov = prov;
		this.dep = dep;
		this.dni = dni;
		this.mod_ing = mod_ing;
		this.sem_ing = sem_ing;
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

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDis() {
		return dis;
	}

	public void setDis(String dis) {
		this.dis = dis;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getMod_ing() {
		return mod_ing;
	}

	public void setMod_ing(String mod_ing) {
		this.mod_ing = mod_ing;
	}

	public String getSem_ing() {
		return sem_ing;
	}

	public void setSem_ing(String sem_ing) {
		this.sem_ing = sem_ing;
	}
	
}
