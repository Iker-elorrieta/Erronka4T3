package controlador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Random;

import javax.swing.table.DefaultTableModel;

import exceptions.PlayerNotFoundException;
import modelo.Administrador;
import modelo.Jugador;
import modelo.Partida;
import modelo.Usuario;
import utils.DBUtils;
import vista.MenuAdministrador;
import vista.MenuJugador;


public class Metodos {

	//Conexion a BD 
	public static void conexionBDUpdate(Connection conexion, String consulta) {
		
		try {
		 
		    Statement stmt = conexion.createStatement(); 
		    stmt.executeUpdate(consulta);
		    
		} catch (SQLException e) {
		    System.err.println("Error al establecer la conexión con MySQL: " + e.getMessage());
		}
		
	}
	//Redireccion ArrayList H
	public static void redireccionLogin(Usuario usuario) throws PlayerNotFoundException {


    	if (usuario != null) {
    	    if (usuario instanceof Administrador) {
    	        // Redirigir al usuario a la pantalla de administrador.
    	    	new MenuAdministrador(usuario).setVisible(true);;
    	    } else if (usuario instanceof Jugador) {
    	        // Redirigir al usuario a la pantalla de jugador.
    	    	new MenuJugador(usuario).setVisible(true);
    	    	
    	    }
    	} else {
    		 throw new PlayerNotFoundException("El usuario o la contraseña no son correctos.");
    	}
    }	
		
	 public static void inicioSesionTXT(Usuario usuario) {
		 	Date fecha = new Date();
	        String nombreArchivo = "Archivos/Log.txt";
	        String date = fecha.toString();
			String dia=date.substring(0,10);
			String hora=date.substring(12,20);
			String contenido =("El usuario: "+usuario.getNombre()+" ha iniciado sesion el dia: "+dia+" a las: "+hora);
	        try (FileWriter escritor = new FileWriter(nombreArchivo, true)) {
	            escritor.write(contenido);
	            escritor.write(System.lineSeparator()); 
	        } catch (IOException e) {
	        }
	    }
	

	
	
}