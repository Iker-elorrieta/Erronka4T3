package manager;

import java.util.Random;

import modelo.Estadisticas;

public class GestionEstadisticas {
	
	public static Estadisticas obtenerEstadistica(String estadistica) {
		String killString=estadistica.substring(0);
		int kills=Integer.parseInt(killString);
		String assistString=estadistica.substring(2);
		int assists=Integer.parseInt(assistString);
		String deathString=estadistica.substring(4);
		int death=Integer.parseInt(deathString);
		Estadisticas stats= new Estadisticas(kills,death,assists);
		return stats;
	}
	
	 public static Estadisticas generarEstadisticasAleatorias() {
	        Random rand = new Random();
	        
	        int kills = rand.nextInt(15) + 1; // Genera un número aleatorio entre 1 y 15
	        int deaths = rand.nextInt(6); // Genera un número aleatorio entre 0 y 5
	        int assists = rand.nextInt(17) + 4; // Genera un número aleatorio entre 4 y 20
	        Estadisticas estadisticas= new Estadisticas(kills, deaths, assists);
	        // Devuelve una cadena con las estadísticas aleatorias generadas
	        return estadisticas;
	    }

}
