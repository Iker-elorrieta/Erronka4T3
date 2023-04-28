package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import exceptions.LoginException;
import modelo.Estadisticas;
import modelo.Jugador;
import modelo.Modo;
import modelo.Partida;
import modelo.Personaje;
import utils.DBUtils;


public class metodos {
	public static ArrayList<Jugador> cargaInicialJugadores(){
		String consulta="Select * FROM players";
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		try {
		    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERADMIN, DBUtils.PASS);
		    Statement stmt = conexion.createStatement(); 
		    ResultSet rs = stmt.executeQuery(consulta);
		     while (rs.next()) 
				{    
		    	 	int id= rs.getInt("id");
					String nombre = rs.getString("name");
		            String contrasenya = rs.getString("password_hash");
					int nivel=rs.getInt("level");
					String rango = rs.getString("rank");
					Date fechaRegistro = rs.getDate("registratio_date");
					boolean bloqueado = rs.getBoolean("bloqueado");
					Jugador jugador = new Jugador(id, nombre, contrasenya, nivel, rango, fechaRegistro, bloqueado);
					jugadores.add(jugador);	
				}
		     conexion.close();
		} catch (SQLException e) {
		    System.err.println("Error al establecer la conexión con MySQL: " + e.getMessage());
		}
		return jugadores;
	}
		
		public static Jugador iniciarSesionUsuarios(String nombre, String contrasenya) {
			boolean inicioSesion=false;
			ArrayList<Jugador> usuarios=seleccionJugador();
			int i=0;
			Jugador enviar = new Jugador();
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
						Date fecha = new Date();
						Jugador jugador = new Jugador(id,nombre,contrasenya,nivel,rango,fecha,bloqueado);
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
			return null;
		}
		public static ArrayList<Personaje> cargaInicialPersonajes(){
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

	public static void conexionBDUpdate(String consulta) {
		
		try {
		    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERPLAYER, DBUtils.PASS);
		    Statement stmt = conexion.createStatement(); 
		    stmt.executeUpdate(consulta);
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

	


		

    public void login(String username, String password) throws LoginException {
        String sqlAdmin = "SELECT * FROM admins WHERE username = ? AND password = ?";
        String sqlJugador = "SELECT * FROM jugadores WHERE username = ? AND password = ?";
        String respuesta;
        try (Connection conn = DriverManager.getConnection(DBUtils.URL, DBUtils.USERPLAYER, DBUtils.PASS);
             PreparedStatement stmtAdmin = conn.prepareStatement(sqlAdmin);
             PreparedStatement stmtJugador = conn.prepareStatement(sqlJugador)) {

            stmtAdmin.setString(1, username);
            stmtAdmin.setString(2, password);

            stmtJugador.setString(1, username);
            stmtJugador.setString(2, password);

            ResultSet rsAdmin = stmtAdmin.executeQuery();
            ResultSet rsJugador = stmtJugador.executeQuery();

            if (rsAdmin.next()) {
            	respuesta= "admin"; // El usuario es un administrador.
            } else if (rsJugador.next()) {
            	respuesta= "jugador"; // El usuario es un jugador.
            } else {
            	respuesta= null; // No se encontró un usuario con esos datos.
            }

        } catch (SQLException e) {
        	throw new LoginException("Error al hacer login", e);
        }
        redireccionLogin(respuesta);
    }
    public static Modo getModoById(int id) {
    	Modo modo =null;
    	try (Connection connection = DriverManager.getConnection("url_de_la_bd", "usuario", "contraseña")) {
            String query = "SELECT nombre FROM modos WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                modo = new Modo(id, nombre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modo;
    }
    
    public static Personaje getPersonajeById(int id) {
        Personaje personaje = null;

        try (Connection connection = DriverManager.getConnection("url_de_la_bd", "usuario", "contraseña")) {
            String query = "SELECT * FROM personaje WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
    
			    String name=resultSet.getString("name");
			    String role=resultSet.getString("role");
			    int difficulty=resultSet.getInt("difficulty");
			    int attackDamage=resultSet.getInt("attackDamage");
			    int abilityPower=resultSet.getInt("abilityPower");
			    int health=resultSet.getInt("health");
			    int mana=resultSet.getInt("mana");
			    int mastery=resultSet.getInt("mastery");
	             personaje = new Personaje(id,name,role,difficulty,null,attackDamage,abilityPower,health,mana,mastery);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personaje;
    }

    
    public void redireccionLogin(String userType) {
    	if (userType != null) {
    	    if (userType.equals("admin")) {
    	    	ArrayList<Jugador> jugadores =cargaInicialJugadores();
    	    	ArrayList<Partida> partidas =cargaInicialPartidas();
    	    	ArrayList<Personaje> campeones =cargaInicialPersonajes();
    	        // Redirigir al usuario a la pantalla de administrador.
    	    } else if (userType.equals("jugador")) {
    	        // Redirigir al usuario a la pantalla de jugador.
    	    	
    	    }
    	} else {
    	    // Mostrar un mensaje de error al usuario.
    	}
    }

		
		
		
		
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
					Modo modo= getModoById(rs.getInt("modo"));
					boolean resultado=rs.getBoolean("resultado");
					Date fecha=rs.getDate("date");


					Estadisticas estadistica=metodos.obtenerEstadistica(rs.getString("estadisticas"));
					Personaje personaje = getPersonajeById(rs.getInt("champion"));

					Partida partida = new Partida(id, null, modo, personaje, estadistica, resultado, fecha, duracion);
					partidas.add(partida);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return partidas;
		}
		
		
}