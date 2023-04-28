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


}
