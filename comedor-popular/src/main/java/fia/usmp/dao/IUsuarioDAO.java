package fia.usmp.dao;

import fia.usmp.model.Usuario;

public interface IUsuarioDAO {

	public void iniciarSesion(Usuario u);
	public void cerrarSesion(Usuario u);
	public boolean existeSesionActiva();
	public Usuario getUsuarioActivo();
	public boolean existeUsuario(String nom);
	public boolean validarCredenciales(String nom, String password);
	
}
