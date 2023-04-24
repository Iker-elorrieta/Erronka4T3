package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import modelo.Administrador;
import modelo.Jugador;
import modelo.Usuario;
import utils.DBUtils;

public class metodos {
	
	public void iniciarSesion() {
		
	}
	
	public ResultSet conexionBD(String consulta) {
		ResultSet rs = null;
		try {
		    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		    Statement stmt = conexion.createStatement(); 
		    rs = stmt.executeQuery(consulta);
		} catch (SQLException e) {
		    System.err.println("Error al establecer la conexión con MySQL: " + e.getMessage());
		}
		return rs;
	}
	
	public ArrayList<Usuario> seleccionUsuarios(ResultSet rs) {
		ArrayList<Usuario> usuarios= new ArrayList<>();
		try {
			while (rs.next()) 
			{
				 String nombre = rs.getString("nombre");
	             String contrasenya = rs.getString("contraseña");
	             int id= rs.getInt("id");
				
				if(Objects.isNull(rs.getInt("nivel")))
				{
					int nivel=rs.getInt("nivel");
					String rango=rs.getString("rango");
					Date fecha = rs.getDate("fecha");
					Jugador jugador = new Jugador(contrasenya, nombre, rango, nivel, null, null, id, fecha);
					usuarios.add(jugador);
				}
				else
				{
					Administrador admin = new Administrador(id, nombre, contrasenya);
					usuarios.add(admin);
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}
	
	public void seleccionPersonajes(ResultSet rs) {
		
	}
	
	public void seleccionPartidas(ResultSet rs) {
		
	}
	
	public void seleccionJugadores(ResultSet rs) {
		
	}
}
