package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import modelo.Estadisticas;
import modelo.Partida;
import modelo.Personaje;
import utils.DBUtils;
import utils.Modos;

public class metodos {
	

	public static void conexionBDUpdate(String consulta) {
		
		try {
		    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		    Statement stmt = conexion.createStatement(); 
		    stmt.executeQuery(consulta);
		    conexion.close();
		} catch (SQLException e) {
		    System.err.println("Error al establecer la conexión con MySQL: " + e.getMessage());
		}
		
	}
	
	static Estadisticas obtenerEstadistica(String lineaTexto) {
		String killString=lineaTexto.substring(0);
		int kills=Integer.parseInt(killString);
		String assistString=lineaTexto.substring(2);
		int assists=Integer.parseInt(assistString);
		String deathString=lineaTexto.substring(4);
		int death=Integer.parseInt(deathString);
		Estadisticas estad= new Estadisticas(kills,death,assists);
		return estad;
	}

	static Modos validarModo(String modo) {
		Modos Modo = null;
		if(modo.equals("Aram"))
			Modo=Modos.Aram;
		if(modo.equals("Normal"))
			Modo=Modos.Normal;
		if(modo.equals("Clasficatoria"))
			Modo=Modos.Clasificatoria;
		return Modo;
	}
		
		public static ArrayList<Personaje> seleccionPersonajes() {
			String consulta="SELECT * FROM champions";
			ArrayList<Personaje> campeones = new ArrayList<Personaje>();
			try {
			    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			    Statement stmt = conexion.createStatement(); 
			    ResultSet rs = stmt.executeQuery(consulta);
				while (rs.next()) 
				{
					int id=rs.getInt("id");
				    String name=rs.getString("name");
				    String role=rs.getString("role");
				    int difficulty=rs.getInt("difficulty");
				    int attackDamage=rs.getInt("attackDamage");
				    int abilityPower=rs.getInt("abilityPower");
				    int health=rs.getInt("health");
				    int mana=rs.getInt("mana");
				    int mastery=rs.getInt("mastery");
		            Personaje personaje = new Personaje(id,name,role,difficulty,null,attackDamage,abilityPower,health,mana,mastery);
		            campeones.add(personaje);
		            conexion.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return campeones;
			}
		
		public static ArrayList<Partida> seleccionPartidas() {
			String consulta="SELECT * FROM matches";
			ArrayList<Partida> partidas= new ArrayList<>();
			try {
			    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			    Statement stmt = conexion.createStatement(); 
			    ResultSet rs = stmt.executeQuery(consulta);
				while (rs.next()) 
				{
					int id=rs.getInt("id");
					Modos modo=metodos.validarModo(rs.getString("modo"));
					boolean resultado=rs.getBoolean("resultado");
					Date fecha=rs.getDate("date");
					Estadisticas estadistica=metodos.obtenerEstadistica(rs.getString("estadisticas"));
					
					Partida partida = new Partida(id, null, modo, null, estadistica, resultado, fecha, id);
					partidas.add(partida);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return partidas;
		}
		
		
}