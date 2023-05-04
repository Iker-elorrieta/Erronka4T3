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
<<<<<<< HEAD
import exceptions.PasswordMismatchException;
import exceptions.PlayerNotFoundException;
=======
import modelo.Administrador;
>>>>>>> branch 'S2' of https://github.com/Iker-elorrieta/Erronka4T3.git
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
	
<<<<<<< HEAD
    
    // Método para eliminar un jugador
    public void eliminarJugador(ArrayList<Jugador> jugadores, Jugador jugador) {
        jugadores.remove(jugador);
        eliminarUsuario(jugador);
    }
    
    // Método para eliminar el usuario de la base de datos
    private void eliminarUsuario(Jugador jugador) {
    	String consulta="DELETE FROM `players` WHERE id="+jugador.getId();
		metodos.conexionBDUpdate(consulta);
    }
    
 // Método para añadir un jugador
    public static void anyadirJugador(ArrayList<Jugador> jugadores, Jugador jugador) {
        jugadores.add(jugador);
        insertarUsuario(jugador);
    }
    
    // Método para añadir el usuario a la base de datos
    public static  void insertarUsuario(Jugador jugador) { 
		String consulta="INSERT INTO `players`(`id`, `name`, `password_hash`, `registration_date`, `level`, `rank`, `bloqueado`) VALUES ('"+jugador.getId()+"','"+jugador.getNombre()+"','"+jugador.getContrasenya()+"',','"+jugador.getNivel()+"','"+jugador.getRango()+"','"+jugador.isbloqueado()+"')";
		metodos.conexionBDUpdate(consulta);
	}
    
    // Método para cambiar el estado de bloqueo del jugador
    public void cambiarEstadoBloqueo( Jugador jugador, boolean bloqueado) {
    	jugador.setBloqueado(bloqueado);
        actualizarEstadoBloqueo(jugador, bloqueado);
    }
    
    // Método para actualizar el estado de bloqueo en la base de datos
    private void actualizarEstadoBloqueo(Jugador jugador, boolean bloqueado) {
        String consulta = "UPDATE players SET bloqueado = "+bloqueado+" WHERE id ="+jugador.getId();
        metodos.conexionBDUpdate(consulta);
    }
    
    // Método para cambiar el estado de bloqueo de un jugador en el ArrayList y en la base de datos
    public void cambiarEstadoBloqueo(ArrayList<Jugador> jugadores, int idJugador, boolean bloqueado) {
    	int i = 0;
       while(jugadores.get(i).getId() != idJugador) {
    	   
            if (jugadores.get(i).getId() == idJugador) {
                cambiarEstadoBloqueo(jugadores.get(i), bloqueado);
            }else {
            	i++;
            }
        }
    }
    
    public void login(String username, String password) throws PlayerNotFoundException, PasswordMismatchException {
        String sqlAdmin = "SELECT * FROM admins WHERE username = ? AND password = ?";
        String sqlJugador = "SELECT * FROM jugadores WHERE username = ? AND password = ?";
        String respuesta = null;
        try (Connection conn = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
=======
	//Login 
    public void login(String username, String password) throws LoginException {
    	
        String sqlAdmin = "SELECT * FROM admins WHERE username ="+username+" AND password ="+password;
        String sqlJugador = "SELECT * FROM jugadores WHERE username ="+username+" AND password ="+password;
        String respuesta;
        try (Connection conn = DriverManager.getConnection(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
>>>>>>> branch 'S2' of https://github.com/Iker-elorrieta/Erronka4T3.git
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
            } else if (rsJugador.next() && !(rsJugador.getBoolean("bloqueado"))) {
            	respuesta= "jugador"; // El usuario es un jugador.
            } else {
            	 throw new PlayerNotFoundException("No se pudo encontrar el usuario.");
            }

        } catch (SQLException e) {
        	
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
