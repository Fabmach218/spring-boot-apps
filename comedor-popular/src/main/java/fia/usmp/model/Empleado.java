package fia.usmp.model;

import java.sql.Date;

public class Empleado {

	private String dni;
	private String ape_pat;
	private String ape_mat;
	private String nombres;
	private String cargo;
	private double sueldo;
	private Date fecha_ingreso;
	private int edad;
	private Date fecha_nacimiento;
	private String sexo;
	private String num_celular;
	
	public Empleado() {
		
	}

	public Empleado(String dni, String ape_pat, String ape_mat, String nombres, String cargo, double sueldo, Date fecha_ingreso,
			int edad, Date fecha_nacimiento, String sexo, String num_celular) {
		super();
		this.dni = dni;
		this.ape_pat = ape_pat;
		this.ape_mat = ape_mat;
		this.nombres = nombres;
		this.cargo = cargo;
		this.sueldo = sueldo;
		this.fecha_ingreso = fecha_ingreso;
		this.edad = edad;
		this.fecha_nacimiento = fecha_nacimiento;
		this.sexo = sexo;
		this.num_celular = num_celular;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getApe_pat() {
		return ape_pat;
	}

	public void setApe_pat(String ape_pat) {
		this.ape_pat = ape_pat;
	}
	
	public String getApe_mat() {
		return ape_mat;
	}

	public void setApe_mat(String ape_mat) {
		this.ape_mat = ape_mat;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNum_celular() {
		return num_celular;
	}

	public void setNum_celular(String num_celular) {
		this.num_celular = num_celular;
	}
	
	
	
}
