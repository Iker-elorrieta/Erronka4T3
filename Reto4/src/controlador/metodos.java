package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import manager.GestionHabilidades;
import manager.GestionModos;
import manager.GestionPartidas;
import manager.GestionPersonajes;
import manager.GestionUsuarios;
import modelo.Habilidad;
import modelo.Jugador;
import modelo.Modo;
import modelo.Partida;
import modelo.Personaje;
import utils.DBUtils;


public class metodos {

	//Conexion a BD H
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
	public static void redireccionLogin(String userType) {
    	if (userType != null) {
    	    if (userType.equals("admin")) {
    	    	ArrayList<Jugador> jugadores =GestionUsuarios.cargaInicialJugadores();
    	    	ArrayList<Partida> partidas =GestionPartidas.cargaInicialPartidas();
    	    	ArrayList<Personaje> campeones =GestionPersonajes.cargaInicialPersonajes();
    	    	ArrayList<Modo> modos =GestionModos.cargaInicialModos();
    	    	ArrayList<Habilidad> habilidades=GestionHabilidades.getHabilidad();
    	    	
    	        // Redirigir al usuario a la pantalla de administrador.
    	    } else if (userType.equals("jugador")) {
    	        // Redirigir al usuario a la pantalla de jugador.
    	    	
    	    }
    	} else {
    	    // Mostrar un mensaje de error al usuario.
    	}
    }	
		
}