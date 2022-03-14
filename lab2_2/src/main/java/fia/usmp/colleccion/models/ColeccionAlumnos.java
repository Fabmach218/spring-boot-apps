package fia.usmp.colleccion.models;

import java.util.ArrayList;

public class ColeccionAlumnos {

	ArrayList<AlumnoEntity> lista = new ArrayList<>();
	
	public void registrarAlumno(AlumnoEntity objAE) {
		lista.add(objAE);
	}
	
	public ArrayList<AlumnoEntity> getLista(){
		return lista;
	}
	
	public ArrayList<AlumnoEntity> listarMujeresMayores(){
		
		ArrayList<AlumnoEntity> listaMujeresMayores = new ArrayList<>();
		
		for (AlumnoEntity alumno : lista) {
			
			if (alumno.getGen().equalsIgnoreCase("Mujer") && alumno.getEdad() >= 18) {
				listaMujeresMayores.add(alumno);
			}
			
		}
		
		return listaMujeresMayores;
		
	}
	
	public ArrayList<AlumnoEntity> listarHombresMenores(){
		
		ArrayList<AlumnoEntity> listaHombresMenores = new ArrayList<>();
		
		for (AlumnoEntity alumno : lista) {
			
			if (alumno.getGen().equalsIgnoreCase("Hombre") && alumno.getEdad() < 18) {
				listaHombresMenores.add(alumno);
			}
			
		}
		
		return listaHombresMenores;
		
	}
	
}
