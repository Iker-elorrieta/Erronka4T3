package controlador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

import exceptions.PlayerNotFoundException;
import modelo.Usuario;
import utils.DBUtils;
import vista.MenuAdministrador;
import vista.MenuJugador;


public class Metodos {

	//Conexion a BD 
	public static void conexionBDUpdate(String consulta) {
		
		try {
		    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERPLAYER, DBUtils.PASS);
		    Statement stmt = conexion.createStatement(); 
		    stmt.executeUpdate(consulta);
		    conexion.close();
		} catch (SQLException e) {
		    System.err.println("Error al establecer la conexión con MySQL: " + e.getMessage());
		}
		
	}
	//Redireccion ArrayList H
	public static void redireccionLogin(String userType, Usuario usuario) throws PlayerNotFoundException {


    	if (userType != null) {
    	    if (userType.equals("admin")) {
    	        // Redirigir al usuario a la pantalla de administrador.
    	    	new MenuAdministrador(usuario).setVisible(true);;
    	    } else if (userType.equals("jugador")) {
    	        // Redirigir al usuario a la pantalla de jugador.
    	    	new MenuJugador(usuario).setVisible(true);
    	    	
    	    }
    	} else {
    		 throw new PlayerNotFoundException("El usuario o la contraseña no son correctos.");
    	}
    }	
		
	//Registro de los inicios de sesion a txt
	public static void inicioSesionTXT(Usuario usuario) {
		File file = new File("Archivos/Log.txt");
		Date fecha = new Date();
			try {
				FileWriter fichero= new FileWriter(file);
				String date = fecha.toString();
				String dia=date.substring(0,10);
				String hora=date.substring(12,20);
				fichero.write("El usuario: "+usuario.getNombre()+" ha iniciado sesion el dia: "+dia+" a las: "+hora);
				fichero.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
	}
	public void guardarCambios(DefaultTableModel modelo, int selectedPanelInt) {
		// TODO Auto-generated method stub
		
	}
}