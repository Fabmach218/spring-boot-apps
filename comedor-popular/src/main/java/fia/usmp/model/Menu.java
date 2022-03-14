package fia.usmp.model;

import java.sql.Date;

public class Menu {

	private int cod;
	private Date fecha;
	private String entrada;
	private String plato_principal;
	private String postre;
	private String refresco;
	private int cant_prep;
	private double precio;
	
	public Menu() {
		
	}
	
	public Menu(int cod, Date fecha, String entrada, String plato_principal, String postre, String refresco,
			int cant_prep, double precio) {
		super();
		this.cod = cod;
		this.fecha = fecha;
		this.entrada = entrada;
		this.plato_principal = plato_principal;
		this.postre = postre;
		this.refresco = refresco;
		this.cant_prep = cant_prep;
		this.precio = precio;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getPlato_principal() {
		return plato_principal;
	}

	public void setPlato_principal(String plato_principal) {
		this.plato_principal = plato_principal;
	}

	public String getPostre() {
		return postre;
	}

	public void setPostre(String postre) {
		this.postre = postre;
	}

	public String getRefresco() {
		return refresco;
	}

	public void setRefresco(String refresco) {
		this.refresco = refresco;
	}

	public int getCant_prep() {
		return cant_prep;
	}

	public void setCant_prep(int cant_prep) {
		this.cant_prep = cant_prep;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
}
