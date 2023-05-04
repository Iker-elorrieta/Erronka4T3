package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import controlador.metodos;
import modelo.Estadisticas;
import modelo.Habilidad;
import modelo.Jugador;
import modelo.Modo;
import modelo.Partida;
import modelo.Personaje;
import utils.DBUtils;

public class GestionPartidas {

public static ArrayList<Partida> cargaInicialPartidas() {
	String consulta="SELECT * FROM matches";
	ArrayList<Partida> partidas= new ArrayList<>();
	try {
	    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
	    Statement stmt = conexion.createStatement(); 
	    ResultSet rs = stmt.executeQuery(consulta);
		while (rs.next()) 
		{
			int id=rs.getInt("id");
			int duracion = rs.getInt("duracion");
			Modo modo= GestionModos.getModoById(rs.getInt("modo"));
			boolean resultado=rs.getBoolean("resultado");
			Date fecha=rs.getDate("date");
			Estadisticas estadistica=GestionEstadisticas.obtenerEstadistica(rs.getString("estadisticas"));
			Personaje personaje = GestionPersonajes.getPersonajeById(rs.getInt("champion"));
			
			Partida partida = new Partida(id, null, modo, personaje, estadistica, resultado, fecha, duracion);
			partidas.add(partida);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return partidas;
}

public static ArrayList<Partida> getPartidasByJugador(Jugador jugador) {
    ArrayList<Partida> partidas = new ArrayList<>();

    try (Connection connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS)) {
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

}
