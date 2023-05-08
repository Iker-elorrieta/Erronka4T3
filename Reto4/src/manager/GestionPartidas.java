package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import controlador.Metodos;
import modelo.Estadisticas;

import modelo.Habilidad;

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
			int duracion = rs.getInt("duracion");
			Modo modo= GestionModos.getModoById(rs.getInt("modo"));
			boolean resultado=rs.getBoolean("resultado");
			Date fecha=rs.getDate("date");
			Jugador jugador=GestionUsuarios.getJugadorByNombre(rs.getString("player"));
			Estadisticas estadistica=GestionEstadisticas.obtenerEstadistica(rs.getString("estadisticas"));
			Personaje personaje = GestionPersonajes.getPersonajeById(rs.getInt("champion"));
			
			Partida partida = new Partida(id, jugador, modo, personaje, estadistica, resultado, fecha, duracion);
			partidas.add(partida);
			conexion.close();
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return partidas;
}


public static ArrayList<Partida> getPartidasByJugador(Jugador jugador) {
    ArrayList<Partida> partidas = new ArrayList<>();

    try (Connection connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS)) {
        String query = "SELECT * FROM matches WHERE player_id = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, jugador.getId());

        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
        	int id=resultSet.getInt("id");
			int duracion = resultSet.getInt("duracion");
			Modo modo= GestionModos.getModoById(resultSet.getInt("modo"));
			boolean resultado=resultSet.getBoolean("resultado");
			Date fecha=resultSet.getDate("date");
			Estadisticas estadistica=GestionEstadisticas.obtenerEstadistica(resultSet.getString("estadisticas"));
			Personaje personaje = GestionPersonajes.getPersonajeById(resultSet.getInt("champion"));
			
			Partida partida = new Partida(id, jugador, modo, personaje, estadistica, resultado, fecha, duracion);
			partidas.add(partida);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return partidas;
}

	//UPDATE partida 
	public static  void updatePartida(Partida partida) {
		 String consulta = "UPDATE matches SET duration="+partida.getDuracion()+",result="+partida.isResultado()+",modo="+partida.getModo()+",date="+partida.getFecha()+",player="+partida.getJugador().getId()+",champion="+partida.getPersonaje().getId()+" WHERE id ="+partida.getCod_partida();
	     Metodos.conexionBDUpdate(consulta);
	}
	
	//INSERT partida 
	public static  void insertarPartida(Partida partida) { 
	String consulta="INSERT INTO `matches`(`id`, `date`, `duration`, `result`, `champion`, `player`,`estadisticas`) VALUES ('"
			+ ""+partida.getCod_partida()+"','"+partida.getFecha()+"','"+partida.getDuracion()+"','"+partida.isResultado()+"','"+partida.getPersonaje()+"','"+partida.getJugador()+"','"+partida.getEstadisticas()+")";
	Metodos.conexionBDUpdate(consulta);
}

	//DELETE partida 
	public void eliminarUsuario(Jugador jugador) {
	String consulta="DELETE FROM `players` WHERE id="+jugador.getId();
	Metodos.conexionBDUpdate(consulta);
}



}
