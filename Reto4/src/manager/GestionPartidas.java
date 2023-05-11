package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import controlador.Metodos;
import modelo.Estadisticas;

import modelo.Jugador;
import modelo.Modo;
import modelo.Partida;
import modelo.Personaje;
import utils.DBUtils;

public class GestionPartidas {

	//SELECT inicial 
	public static ArrayList<Partida> cargaInicialPartidas() {
	String consulta="SELECT * FROM matches";
	ArrayList<Partida> partidas= new ArrayList<>();
	try {
	    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERADMIN, DBUtils.PASS);
	    Statement stmt = conexion.createStatement(); 
	    ResultSet rs = stmt.executeQuery(consulta);
		while (rs.next()) 
		{
			int id=rs.getInt("id");
			int duracion = rs.getInt("duration");
			Modo modo= GestionModos.getModoById(rs.getInt("modo_id"));
			boolean resultado=rs.getBoolean("result");
			Date fecha=rs.getDate("date");
			Jugador jugador=GestionUsuarios.getJugadorByNombre(rs.getString("player_id"));
			Estadisticas estadistica=GestionEstadisticas.obtenerEstadistica(rs.getString("Estadisticas"));
			Personaje personaje = GestionPersonajes.getPersonajeById(rs.getInt("champion_id"));
			
			Partida partida = new Partida(id, jugador, modo, personaje, estadistica, resultado, fecha, duracion);
			partidas.add(partida);
		}
		conexion.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return partidas;
}


public static ArrayList<Partida> getPartidasByJugador(String jugador) {
    ArrayList<Partida> partidas = new ArrayList<>();


    try (Connection connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS)) {
        String query = "SELECT * FROM matches WHERE player_id = (SELECT id FROM player WHERE name='"+jugador+"'";


        Statement stmt = connection.createStatement(); 
	    ResultSet resultSet = stmt.executeQuery(query);
        
        while (resultSet.next()) {
        	int id=resultSet.getInt("id");
			int duracion = resultSet.getInt("duration");
			Modo modo= GestionModos.getModoById(resultSet.getInt("modo_id"));
			boolean resultado=resultSet.getBoolean("result");
			Date fecha=resultSet.getDate("date");
			Estadisticas estadistica=GestionEstadisticas.obtenerEstadistica(resultSet.getString("Estadisticas"));
			Personaje personaje = GestionPersonajes.getPersonajeById(resultSet.getInt("champion_id"));
			
			Partida partida = new Partida(id, GestionUsuarios.getJugadorByNombre(jugador), modo, personaje, estadistica, resultado, fecha, duracion);
			partidas.add(partida);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return partidas;
}
	
	//INSERT partida 
	public static  void insertarPartida(Partida partida) { 
	int resultado=0;
	if(partida.isResultado())
		resultado=1;
	String consulta="INSERT INTO `matches`(`id`, `duration`, `result`,`estadisticas`,`date`,`champion_id`,`modo_id`,`player_id`) VALUES ("
			+"'"+partida.getCod_partida()+"','"+partida.getDuracion()+"','"+resultado+"','"+partida.getEstadisticas().toString()+"','"+partida.StringFecha()+"','"+partida.getPersonaje().getId()+"','"+partida.getModo().getId()+"','"+partida.getJugador().getId()+"')";
			Metodos.conexionBDUpdate(consulta);

}

	//DELETE partida 

	public static void eliminarPartida(Partida partida) {
		String consulta="DELETE FROM `matches` WHERE id="+partida.getCod_partida();
		Metodos.conexionBDUpdate(consulta);
	}
	
	//SELECT Complejo: sacar victorias y derrotas de usuario
	public static Integer[] sacarResultados(String nombre) {
		Integer[] result= new Integer[2];
		 try (Connection connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS)) {
		        String query = "SELECT result FROM matches,players WHERE players.name="+nombre+" AND players.id=matches.player_id ";


		        Statement stmt = connection.createStatement(); 
			    ResultSet resultSet = stmt.executeQuery(query);
		        
		        while (resultSet.next()) {
		        	boolean resul=resultSet.getBoolean("Estadisticas");
		        	if(resul)
		        		result[0]=result[0]++;
		        	else
		        		result[1]=result[1]++;
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		
		return result;
		
	}


	


	
}
