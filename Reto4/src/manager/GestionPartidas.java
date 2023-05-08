package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import controlador.metodos;
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


public static ArrayList<Partida> getPartidasByJugador(Jugador jugador) {
    ArrayList<Partida> partidas = new ArrayList<>();

    try (Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERPLAYER, DBUtils.PASS)) {
        String query = "SELECT * FROM matches WHERE player_id='"+jugador.getId()+"'";

        Statement stmt = conexion.createStatement(); 
	    ResultSet resultSet = stmt.executeQuery(query);
        
        while (resultSet.next()) {
        	int id=resultSet.getInt("id");
			int duracion = resultSet.getInt("duration");
			Modo modo= GestionModos.getModoById(resultSet.getInt("modo_id"));
			boolean resultado=resultSet.getBoolean("result");
			Date fecha=resultSet.getDate("date");
			Estadisticas estadistica=GestionEstadisticas.obtenerEstadistica(resultSet.getString("Estadisticas"));
			Personaje personaje = GestionPersonajes.getPersonajeById(resultSet.getInt("champion_id"));
			
			Partida partida = new Partida(id, jugador, modo, personaje, estadistica, resultado, fecha, duracion);
			partidas.add(partida);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return partidas;
}

	//SELECT complejo: Partidos jugados con un personaje de maxima dificultad de un jugador
    public static ArrayList<Partida> partidosDificultad(Jugador jugador) {
    	String consulta="SELECT * FROM matches,champions WHERE champions.difficulty=3 AND champions.id=matches.champion_id AND player_id="+jugador.getId()+"";
		ArrayList<Partida> partidas= new ArrayList<>();
		try {
		    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERPLAYER, DBUtils.PASS);
		    Statement stmt = conexion.createStatement(); 
		    ResultSet rs = stmt.executeQuery(consulta);
			while (rs.next()) 
			{
				int id=rs.getInt("id");
				int duracion = rs.getInt("duration");
				Modo modo= GestionModos.getModoById(rs.getInt("modo_id"));
				boolean resultado=rs.getBoolean("result");
				Date fecha=rs.getDate("date");
				Jugador jugador1=GestionUsuarios.getJugadorByNombre(rs.getString("player_id"));
				Estadisticas estadistica=GestionEstadisticas.obtenerEstadistica(rs.getString("estadisticas"));
				Personaje personaje = GestionPersonajes.getPersonajeById(rs.getInt("champion_id"));
				
				Partida partida1 = new Partida(id, jugador1, modo, personaje, estadistica, resultado, fecha, duracion);
				partidas.add(partida1);
				
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return partidas;
	}
	
	//UPDATE partida 
	public static  void updatePartida(Partida partida) {
		int result=0;
		if(partida.isResultado())
			result=1;
		 String consulta = "UPDATE matches SET duration='"+partida.getDuracion()+"',result='"+result+"',modo_id='"+partida.getModo().getId()+"',date='"+partida.getFecha()+"',player_id='"+partida.getJugador().getId()+"',champion_id='"+partida.getPersonaje().getId()+"' WHERE id ="+partida.getCod_partida();
	     metodos.conexionBDUpdate(consulta);
	}
	
	//INSERT partida 
	public static  void insertarPartida(Partida partida) { 
	String consulta="INSERT INTO `matches`(`id`, `date`, `duration`, `result`, `champion`, `player`,`estadisticas`) VALUES ('"
			+ "'"+partida.getCod_partida()+"','"+partida.getFecha()+"','"+partida.getDuracion()+"','"+partida.isResultado()+"','"+partida.getPersonaje()+"','"+partida.getJugador()+"','"+partida.getEstadisticas().toString()+")";
	metodos.conexionBDUpdate(consulta);
}

	//DELETE partida 
	public static void eliminarPartida(Partida partida) {
	String consulta="DELETE FROM `matches` WHERE id="+partida.getCod_partida();
	metodos.conexionBDUpdate(consulta);
}



}
