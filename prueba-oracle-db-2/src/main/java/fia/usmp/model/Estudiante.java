package fia.usmp.model;

public class Estudiante {

	public String cod;
	public String nom;
	public String seccion;
	public String correo;
	public String dis;
	public String prov;
	public String dep;
	public String dni;
	public String moding;
	public String seming;
	
	public Estudiante() {
		
	}
	
	public Estudiante(String cod, String nom, String seccion, String correo, String dis, String prov, String dep,
			String dni, String moding, String seming) {
		super();
		this.cod = cod;
		this.nom = nom;
		this.seccion = seccion;
		this.correo = correo;
		this.dis = dis;
		this.prov = prov;
		this.dep = dep;
		this.dni = dni;
		this.moding = moding;
		this.seming = seming;
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

	public String getModing() {
		return moding;
	}

	public void setModing(String moding) {
		this.moding = moding;
	}

	public String getSeming() {
		return seming;
	}

	public void setSeming(String seming) {
		this.seming = seming;
	}
	
	
	
	
}
