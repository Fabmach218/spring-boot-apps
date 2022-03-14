package fia.usmp.controller;

import java.sql.ResultSet;

import fia.usmp.dao.EstudianteDAO;

public class PruebaConsola {

	public static void main(String args[]) {
		
		PruebaConsola pc = new PruebaConsola();
		
		ResultSet rs;
		EstudianteDAO objD = new EstudianteDAO();
		
		try {
			
			rs = objD.listarTodo();
			
			while(rs.next()) {
				System.out.println(rs.getString(0) + "\t" + rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getString(7) + "\t" + rs.getString(8) + "\t" + rs.getString(9));
			}
			
		}catch (Exception e) {
			System.out.println("Error");
		}
		
		
		
	}
	
	
	
}
