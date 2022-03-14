package fia.usmp.colleccion.model;

import java.util.ArrayList;

public class ColeccionAlumnos {

	ArrayList<AlumnoEntity> lista = new ArrayList<>();
	private int qAlumnos;
	private int qAprobados;
	private int qDesaprobados;
	private double porAprobados;
	private double porDesaprobados;
	private double promGen;
	private double mayorGen;
	private double menorGen;
	private double mediana;
	
	public void registrarAlumno(AlumnoEntity alumno) {
		lista.add(alumno);
		qAlumnos++;
	}
	
	public ArrayList<AlumnoEntity> getLista(){
		return lista;
	}
	
	public void actualizarDatos() {
		setqAprobados(calcularQAprobados());
		setqDesaprobados(calcularQDesprobados());
		
		if(getqAlumnos() > 0) {
			setPorAprobados(getqAprobados() * 1.0 / getqAlumnos() * 100);
			setPorDesaprobados(getqDesaprobados() * 1.0 / getqAlumnos() * 100);
		}else {
			setPorAprobados(0);
			setPorDesaprobados(0);
		}
		
		setMayorGen(calcularMayorGen());
		setMenorGen(calcularMenorGen());
		setPromGen(calcularPromGen());
		setMediana(calcularMediana());
	}
	
	public int calcularQAprobados() {
		
		int contador = 0;
		
		for (AlumnoEntity alumno : lista) {
			
			if (alumno.getProm() >= 10.5) {
				contador++;
			}
			
		}
		
		return contador;
		
	}
	
	public int calcularQDesprobados() {
		
		int contador = 0;
		
		for (AlumnoEntity alumno : lista) {
			
			if (alumno.getProm() < 10.5) {
				contador++;
			}
			
		}
		
		return contador;
		
	}
	
	public double calcularPromGen() {
		
		double suma = 0;
		double prom;
		
		for (AlumnoEntity alumno : lista) {
			suma += alumno.getProm();
		}
		
		prom = suma / qAlumnos;
		
		return prom;
		
	}
	
	public double calcularMayorGen() {
		
		double mayor = lista.get(0).getMayor();
		
		for (AlumnoEntity alumno : lista) {
			mayor = Math.max(alumno.getMayor(), mayor);
		}
		
		return mayor;
		
	}
	
	public double calcularMenorGen() {
		
		double menor = lista.get(0).getMenor();
		
		for (AlumnoEntity alumno : lista) {
			menor = Math.min(alumno.getMenor(), menor);
		}
		
		return menor;
		
	}
	
	public double calcularMediana() {
		
		double listaProm[] = new double[lista.size()];
		double aux;
		double med;
		
		for (int i = 0; i < lista.size(); i++) {
			listaProm[i] = lista.get(i).getProm();
		}
		
		for (int i = 0; i < listaProm.length - 1; i++) {
			for (int j = i; j < listaProm.length; j++) {
				
				if (listaProm[i] > listaProm[j]) {
					aux = listaProm[i];
					listaProm[i] = listaProm[j];
					listaProm[j] = aux;
				}
				
			}
		}
		
		
		if(listaProm.length % 2 != 0) {
			med = listaProm[listaProm.length / 2];
		}else {
			med = (listaProm[listaProm.length / 2 - 1] + listaProm[listaProm.length / 2]) / 2.0;
		}
		
		return med;
		
	}


	public int getqAlumnos() {
		return qAlumnos;
	}


	public void setqAlumnos(int qAlumnos) {
		this.qAlumnos = qAlumnos;
	}


	public int getqAprobados() {
		return qAprobados;
	}


	public void setqAprobados(int qAprobados) {
		this.qAprobados = qAprobados;
	}


	public int getqDesaprobados() {
		return qDesaprobados;
	}


	public void setqDesaprobados(int qDesaprobados) {
		this.qDesaprobados = qDesaprobados;
	}


	public double getPorAprobados() {
		return porAprobados;
	}


	public void setPorAprobados(double porAprobados) {
		this.porAprobados = porAprobados;
	}


	public double getPorDesaprobados() {
		return porDesaprobados;
	}


	public void setPorDesaprobados(double porDesaprobados) {
		this.porDesaprobados = porDesaprobados;
	}


	public double getPromGen() {
		return promGen;
	}


	public void setPromGen(double promGen) {
		this.promGen = promGen;
	}


	public double getMayorGen() {
		return mayorGen;
	}


	public void setMayorGen(double mayorGen) {
		this.mayorGen = mayorGen;
	}


	public double getMenorGen() {
		return menorGen;
	}


	public void setMenorGen(double menorGen) {
		this.menorGen = menorGen;
	}


	public double getMediana() {
		return mediana;
	}


	public void setMediana(double mediana) {
		this.mediana = mediana;
	}


	public void setLista(ArrayList<AlumnoEntity> lista) {
		this.lista = lista;
	}
	
	
}
