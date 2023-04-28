package manager;

<<<<<<< HEAD
public class GestionEstadisticas {
=======
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
>>>>>>> branch 'S2' of https://github.com/Iker-elorrieta/Erronka4T3.git

}
