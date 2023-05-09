package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import modelo.Estadisticas;
import utils.DBUtils;

public class GestionEstadisticas {
	
	public static Estadisticas obtenerEstadistica(String estadistica) {
		String killString=estadistica.substring(0,1);
		int kills=Integer.parseInt(killString);
		String assistString=estadistica.substring(2,3);
		int assists=Integer.parseInt(assistString);
		String deathString=estadistica.substring(4,5);
		int death=Integer.parseInt(deathString);
		Estadisticas stats= new Estadisticas(kills,death,assists);
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
	public static Estadisticas estadisticasJugador(String nombre) {
		ArrayList<Estadisticas> estad= new ArrayList<Estadisticas>();
		String consulta="SELECT Estadisticas FROM players,matches WHERE name='"+nombre+"' AND players.id=matches.player_id";
		try {
		    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERPLAYER, DBUtils.PASS);
		    Statement stmt = conexion.createStatement(); 
		    ResultSet rs = stmt.executeQuery(consulta);
			while (rs.next()) 
			{
				Estadisticas est=obtenerEstadistica(rs.getString("Estadisticas"));
				estad.add(est);
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Estadisticas total= new Estadisticas(0, 0, 0);
		for(int i=0;i<estad.size();i++)
		{
			total.setKills(total.getKills()+estad.get(i).getKills());
			total.setDeath(total.getDeath()+estad.get(i).getDeath());
			total.setAssists(total.getAssists()+estad.get(i).getAssists());
		}
		return total;
		
	}
}
