package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import modelo.Estadisticas;
import utils.DBUtils;

public class GestionEstadisticas {
	
	public static Estadisticas obtenerEstadistica(String estadistica) {
		 String[] valores = estadistica.split("/");
		 
		    int kills = Integer.valueOf(valores[0]);
		    int assists = Integer.valueOf(valores[1]);
		    int deaths = Integer.valueOf(valores[2]);
		   
		    Estadisticas stats = new Estadisticas(kills, deaths, assists);
		    return stats;
	}
	
	 public static Estadisticas generarEstadisticasAleatorias() {
	        Random rand = new Random();
	        
	        int kills = rand.nextInt(15) + 1; // Genera un número aleatorio entre 1 y 15
	        int deaths = rand.nextInt(6); // Genera un número aleatorio entre 0 y 5
	        int assists = rand.nextInt(17) + 4; // Genera un número aleatorio entre 4 y 20
	        Estadisticas estadisticas= new Estadisticas(kills, deaths, assists) ;
	        // Devuelve una cadena con las estadísticas aleatorias generadas
	        return estadisticas;
	    }

	 //Seleccion compleja: suma de estadisticas
	public static Estadisticas estadisticasJugador(Connection conexion, String nombre) {
		Estadisticas estad= new Estadisticas(0, 0, 0);
		String consulta="SELECT Estadisticas FROM matches,players WHERE players.name='"+nombre+"' AND players.id=matches.player_id";
		try {
		    
		    Statement stmt = conexion.createStatement(); 
		    ResultSet rs = stmt.executeQuery(consulta);
			while (rs.next()) 
			{
				Estadisticas est=obtenerEstadistica(rs.getString("Estadisticas"));
				estad.setKills(estad.getKills()+est.getKills());
				estad.setDeath(estad.getDeath()+est.getDeath());
				estad.setAssists(estad.getAssists()+est.getAssists());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return estad;
		
	}
}
