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
import exceptions.LoginException;
import modelo.Administrador;
import modelo.Jugador;
import utils.DBUtils;

public class GestionUsuarios {
	
	//SELECT inicial 
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
	
	//SELECT by nombre 
	public static Jugador getJugadorByNombre(String nombre){
		Jugador jugador =null;
    	try (Connection connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USERPLAYER, DBUtils.PASS)) {
            String query = "SELECT * FROM players WHERE name ="+nombre;

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nombre);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
            	int id= resultSet.getInt("id");
	            String contrasenya = resultSet.getString("password_hash");
				int nivel=resultSet.getInt("level");
				String rango = resultSet.getString("rank");
				Date fechaRegistro = resultSet.getDate("registratio_date");
				boolean bloqueado = resultSet.getBoolean("bloqueado");
				 jugador = new Jugador(id, nombre, contrasenya, nivel, rango, fechaRegistro, bloqueado);
               
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jugador;
	}
	
	//Login 
    public void login(String username, String password) throws LoginException {
    	
        String sqlAdmin = "SELECT * FROM admins WHERE username ="+username+" AND password ="+password;
        String sqlJugador = "SELECT * FROM jugadores WHERE username ="+username+" AND password ="+password;
        String respuesta;
        try (Connection conn = DriverManager.getConnection(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
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
        metodos.redireccionLogin(respuesta);
    
    }
    
    //UPDATE Jugador 
  	public void actualizarJugador(Jugador jugador) {
  	        String consulta = "UPDATE players SET name="+jugador.getNombre()+",password_hash="+jugador.getContrasenya()+",registration_date="+jugador.getNivel()+",rank="+jugador.getRango()+",bloqueado="+jugador.isbloqueado()+" WHERE id ="+jugador.getId();
  	        metodos.conexionBDUpdate(consulta);
  	    }
  	
  	//UPDATE Bloqueo 
    private void actualizarEstadoBloqueo(Jugador jugador, boolean bloqueado) {
        String consulta = "UPDATE players SET bloqueado = "+bloqueado+" WHERE id ="+jugador.getId();
        metodos.conexionBDUpdate(consulta);
    }
    
    //ARRAY UPDATE Bloqueo 
    public void cambiarEstadoBloqueo( Jugador jugador, boolean bloqueado) {
    	actualizarEstadoBloqueo(jugador, bloqueado);
    	jugador.setBloqueado(bloqueado);
        
    }

    //Array Bloqueo 
    public void cambiarEstadoBloqueo(ArrayList<Jugador> jugadores, int idJugador, boolean bloqueado) {
    	int i = 0;
       while(jugadores.get(i).getId() != idJugador) {
    	   
            if (jugadores.get(i).getId() == idJugador) {
                cambiarEstadoBloqueo(jugadores.get(i), bloqueado);
            }else {
            	i++;
            }
       }}
    
    //INSERT Usuario Array 
    public static  void insertarUsuario(Jugador jugador,ArrayList<Jugador> jugadores) { 
    	jugadores.add(jugador);
		String consulta="INSERT INTO `players`(`id`, `name`, `password_hash`, `registration_date`, `level`, `rank`, `bloqueado`) VALUES ('"+jugador.getId()+"','"+jugador.getNombre()+"','"+jugador.getContrasenya()+"','"+jugador.getFecha()+"','"+jugador.getNivel()+"','"+jugador.getRango()+"','"+jugador.isbloqueado();
		metodos.conexionBDUpdate(consulta);
	}
    
    //DELETE Admin 
	public void eliminarAdministrador(Administrador Administrador,ArrayList<Administrador> Administradores) {
    	Administradores.remove(Administrador);
    	String consulta="DELETE FROM `admins` WHERE id="+Administrador.getId();
		metodos.conexionBDUpdate(consulta);
    }

    //DELETE Jugador
    public void eliminarJugador(Jugador jugador) {
    	String consulta="DELETE FROM `players` WHERE id="+jugador.getId();
		metodos.conexionBDUpdate(consulta);
    }
}
