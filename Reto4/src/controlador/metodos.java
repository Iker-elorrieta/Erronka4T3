package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import modelo.Jugador;
import modelo.Partida;
import modelo.Personaje;
import utils.DBUtils;
import utils.Modos;

public class metodos {
	

	public static void conexionBDUpdate(String consulta) {
		
		try {
		    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERADMIN, DBUtils.PASS);
		    Statement stmt = conexion.createStatement(); 
		    stmt.executeQuery(consulta);
		    conexion.close();
		} catch (SQLException e) {
		    System.err.println("Error al establecer la conexión con MySQL: " + e.getMessage());
		}
		
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


		
		public static Jugador iniciarSesionUsuarios(String nombre, String contrasenya) {
			boolean inicioSesion=false;
			ArrayList<Jugador> usuarios=seleccionJugador();
			int i=0;
			Jugador enviar = new Jugador(contrasenya, contrasenya, contrasenya, i, null, null, i, null, inicioSesion);
			boolean contra=false;
			do {
				if(usuarios.get(i).getNombre().equals(nombre))
				{
					if(usuarios.get(i).comprobarContrasenya(contrasenya))
					{
						inicioSesion=true;
					}
				}
				if(usuarios.size()==i+4)
				{
					contra=true;
				}
				i++;
			}while(!inicioSesion||contra==false);
			if(!contra)
				enviar=usuarios.get(i-1);
			return enviar;
		}
		
		public static ArrayList<Jugador> seleccionJugador() {
			String consulta="Select * FROM players";
			ArrayList<Jugador> enviar = new ArrayList<Jugador>();
			try {
			    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERADMIN, DBUtils.PASS);
			    Statement stmt = conexion.createStatement(); 
			    ResultSet rs = stmt.executeQuery(consulta);
			     while (rs.next()) 
					{
						String nombre = rs.getString("name");
			            String contrasenya = rs.getString("password_hash");
			            int id= rs.getInt("id");
						
						int nivel=rs.getInt("level");
						String rango = rs.getString("rank");
						boolean bloqueado = rs.getBoolean("bloqueado");
						Jugador jugador = new Jugador(nombre,contrasenya,rango,nivel,null, null,id,null, bloqueado);
						enviar.add(jugador);	
					}
			     conexion.close();
			     return enviar;
			} catch (SQLException e) {
			    System.err.println("Error al establecer la conexión con MySQL: " + e.getMessage());
			}
			return enviar;
		}
		
		public static ArrayList<Personaje> seleccionPersonajes() {
			String consulta="SELECT * FROM champions";
			ArrayList<Personaje> campeones = new ArrayList<Personaje>();
			try {
			    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERADMIN, DBUtils.PASS);
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
			    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERADMIN, DBUtils.PASS);
			    Statement stmt = conexion.createStatement(); 
			    ResultSet rs = stmt.executeQuery(consulta);
				while (rs.next()) 
				{
					int id=rs.getInt("id");
					Modos modo=metodos.validarModo(rs.getString("modo"));
					boolean resultado=rs.getBoolean("resultado");
					Date fecha=rs.getDate("date");
					
					
					Partida partida = new Partida(id, null, modo, null, null, resultado, fecha, id);
					partidas.add(partida);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return partidas;
		}
		
		
}