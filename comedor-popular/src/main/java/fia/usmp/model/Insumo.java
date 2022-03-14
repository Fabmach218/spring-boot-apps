package fia.usmp.model;

public class Insumo {
	
	String cod;
	String insumo;
	double cantidad;
	String unidad;
	
	public Insumo(String cod, String insumo, double cantidad, String unidad) {
		super();
		this.cod = cod;
		this.insumo = insumo;
		this.cantidad = cantidad;
		this.unidad = unidad;
	}
	
	public Insumo() {
		
	}

	public String getCod() {
		return cod;
	}
	
	public void setCod(String cod) {
		this.cod = cod;
	}
	
	public String getInsumo() {
		return insumo;
	}

	public void setInsumo(String insumo) {
		this.insumo = insumo;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	
	
	
}
