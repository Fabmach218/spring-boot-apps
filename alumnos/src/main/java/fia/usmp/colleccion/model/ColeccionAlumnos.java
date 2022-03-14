package fia.usmp.colleccion.model;

import java.util.ArrayList;

public class ColeccionAlumnos {

	ArrayList<AlumnoEntity> listaAlu = new ArrayList<>();
	
	public void registrarAlumno(AlumnoEntity objAE) {
		listaAlu.add(objAE);
	}
	
	public ArrayList<AlumnoEntity> getAlumnos(){
		return listaAlu;
	}
	
	public ArrayList<AlumnoEntity> getAlumnosXDistrito(){
		
		ArrayList<AlumnoEntity> alumnosXDistrito = new ArrayList<>();
		
		for (AlumnoEntity ae: listaAlu) {
			if (ae.getDistrito().equalsIgnoreCase("La Molina")) {
				alumnosXDistrito.add(ae);
			}
		}
		
		return alumnosXDistrito;
		
	}
	
	public ArrayList<AlumnoEntity> getAlumnosXDistrito(String distrito){
		
		ArrayList<AlumnoEntity> alumnosXDistrito = new ArrayList<>();
		
		for (AlumnoEntity ae: listaAlu) {
			if (ae.getDistrito().equalsIgnoreCase(distrito)) {
				alumnosXDistrito.add(ae);
			}
		}
		
		return alumnosXDistrito;
		
	}
	
}
