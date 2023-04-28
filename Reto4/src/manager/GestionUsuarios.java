package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import controlador.metodos;
import exceptions.LoginException;
import modelo.Administrador;
import modelo.Jugador;
import modelo.Modo;
import utils.DBUtils;

public class GestionUsuarios {
	
	public static ArrayList<Jugador> cargaInicialJugadores(){
		String consulta="Select * FROM players";
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		try {
		    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
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
	
	public void preguntarEliminarJugador(ArrayList<Jugador> jugadores,Jugador jugador) {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Está seguro que desea eliminar al jugador " + jugador.getNombre() + "? (s/n)");
        String respuesta = sc.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            eliminarJugador(jugadores, jugador);
        }
    }
	
	public static Jugador getJugadorByNombre(String nombre){
		Jugador jugador =null;
    	try (Connection connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS)) {
            String query = "SELECT nombre FROM modos WHERE name = ?";

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
    

    public static Administrador iniciarSesionAdmin(String nombre, String contrasenya) {
		boolean inicioSesion=false;
		ArrayList<Administrador> usuarios=seleccionAdmin();
		int i=0;
		Administrador enviar = new Administrador();
		boolean contra=false;
		do {
			if(usuarios.get(i).getNombre().equals(nombre))
			{
				if(usuarios.get(i).comprobarContrasenya(contrasenya))
				{
					inicioSesion=true;
				}
			}
			if(i+1==usuarios.size())
				contra=true;
			i++;
		}while(!inicioSesion);
		if(!contra)
			enviar=usuarios.get(i-1);
		return enviar;
	}
	
	public static ArrayList<Administrador> seleccionAdmin() {
		String consulta="Select * FROM admins";
		ArrayList<Administrador> enviar = new ArrayList<Administrador>();
		try {
		    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERADMIN, DBUtils.PASS);
		    Statement stmt = conexion.createStatement(); 
		    ResultSet rs = stmt.executeQuery(consulta);
		     while (rs.next()) 
				{
					String nombre = rs.getString("name");
		            String contrasenya = rs.getString("password");
		            int id= rs.getInt("id");
		            Administrador admin=new Administrador(id,nombre,contrasenya);
				}
		     conexion.close();
		     return enviar;
		} catch (SQLException e) {
		    System.err.println("Error al establecer la conexión con MySQL: " + e.getMessage());
		}
		return enviar;
	}
		     
    public void login(String username, String password) throws LoginException {
        String sqlAdmin = "SELECT * FROM admins WHERE username = ? AND password = ?";
        String sqlJugador = "SELECT * FROM jugadores WHERE username = ? AND password = ?";
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
					Jugador jugador = new Jugador(id, nombre, contrasenya, nivel, rango, fecha, bloqueado);
					enviar.add(jugador);	
				}
		     conexion.close();
		     return enviar;
		} catch (SQLException e) {
		    System.err.println("Error al establecer la conexión con MySQL: " + e.getMessage());
		}
		return enviar;
	
	}}
