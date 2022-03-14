package fia.usmp.colleccion.models;

import java.util.ArrayList;

public class ColeccionElectrodomesticos {

	ArrayList<ElectrodomesticoEntity> lista = new ArrayList<>();
	
	public void registrar(ElectrodomesticoEntity objEE) {
		lista.add(objEE);
	}
	
	public ArrayList<ElectrodomesticoEntity> getLista(){
		return lista;
	}
	
	public ArrayList<ElectrodomesticoEntity> listarXMarca(String marca){
		
		ArrayList<ElectrodomesticoEntity> listaXMarca = new ArrayList<>();
		
		for (ElectrodomesticoEntity objEE : lista) {
			
			if (objEE.getMarca().equalsIgnoreCase(marca)) {
				listaXMarca.add(objEE);
			}
			
		}
		
		return listaXMarca;
		
	}
	
	
	
}
