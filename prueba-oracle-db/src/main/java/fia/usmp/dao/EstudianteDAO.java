package fia.usmp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.pool.OracleDataSource;

public class EstudianteDAO {

	String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	
	String usuario = "SYSTEM";
	String clave = "SYSTEM";
	
	Connection con;
	Statement stm;
	ResultSet rs;
	String consulta;
	
	public void getDBConnection() throws SQLException {
		OracleDataSource ds;
		ds = new OracleDataSource();
		ds.setURL(jdbcUrl);
		con = ds.getConnection(usuario, clave);
	}
	
	public ResultSet listarTodo() throws SQLException{
		
		getDBConnection();
		stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		consulta = "SELECT * FROM ALUMNOS";
		System.out.println("Ejecutando sentencia SQL: " + consulta + ";");
		rs = stm.executeQuery(consulta);
		return rs;
		
	}
	
}
