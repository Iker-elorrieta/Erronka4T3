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
import exceptions.PlayerNotFoundException;
import modelo.Administrador;
import modelo.Jugador;
import modelo.Usuario;
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
					Date fechaRegistro = rs.getDate("registration_date");
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
		String consulta="Select * FROM players WHERE name='"+nombre+"'";
		Jugador jugador = new Jugador();
		try {
		    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERADMIN, DBUtils.PASS);
		    Statement stmt = conexion.createStatement(); 
		    ResultSet rs = stmt.executeQuery(consulta);
		     while (rs.next()) 
				{    
		    	 	int id= rs.getInt("id");
					String nombre1 = rs.getString("name");
		            String contrasenya = rs.getString("password_hash");
					int nivel=rs.getInt("level");
					String rango = rs.getString("rank");
					Date fechaRegistro = rs.getDate("registration_date");
					boolean bloqueado = rs.getBoolean("bloqueado");
					jugador = new Jugador(id, nombre1, contrasenya, nivel, rango, fechaRegistro, bloqueado);
				}
		     conexion.close();
		} catch (SQLException e) {
		    System.err.println("Error al establecer la conexión con MySQL: " + e.getMessage());
		}
		return jugador;
	}


    public static void login(String username, String password) throws PlayerNotFoundException{
        String sqlAdmin = "SELECT * FROM admins WHERE name = ? AND password = ?";
        String sqlJugador = "SELECT * FROM players WHERE name = ? AND password_hash = ?";
        Usuario usur= null;
		try {
			Connection conn = DriverManager.getConnection(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);

             PreparedStatement stmtAdmin = conn.prepareStatement(sqlAdmin);
             PreparedStatement stmtJugador = conn.prepareStatement(sqlJugador);

            stmtAdmin.setString(1, username);
            stmtAdmin.setString(2, password);

            stmtJugador.setString(1, username);
            stmtJugador.setString(2, password);

            ResultSet rsAdmin = stmtAdmin.executeQuery();
            ResultSet rsJugador = stmtJugador.executeQuery();

            if (rsAdmin.next()) {
            	 usur= new Administrador(rsAdmin.getInt(1), rsAdmin.getString(2), rsAdmin.getString(3));
            } else if (rsJugador.next() && !(rsJugador.getBoolean("bloqueado"))) {
            	 usur= new Jugador(rsJugador.getInt(1), rsJugador.getString(2), rsJugador.getString(3),rsJugador.getInt(5), rsJugador.getString(6), rsJugador.getDate(4), rsJugador.getBoolean(7));
            }

		} catch (SQLException e) {
			e.printStackTrace();
		}
        Metodos.redireccionLogin(usur );
    
    }
    
    //UPDATE Jugador 

  	public static void actualizarJugador(Jugador jugador) {
  			int bloqueado=0;
  			if(jugador.isbloqueado())
  				bloqueado=1;
  		
  	        String consulta = "UPDATE players SET name='"+jugador.getNombre()+"',password_hash='"+jugador.getContrasenya()+"',level='"+jugador.getNivel()+"',rank='"+jugador.getRango()+"',bloqueado='"+bloqueado+"',registration_date='"+jugador.StringFecha()+"' WHERE id ="+jugador.getId();
  	        Metodos.conexionBDUpdate(consulta);

  	    }
  	
  	//UPDATE Bloqueo 
    public static void actualizarEstadoBloqueo(Jugador jugador, boolean bloqueado) {
        String consulta = "UPDATE players SET bloqueado = "+bloqueado+" WHERE id ="+jugador.getId();
        Metodos.conexionBDUpdate(consulta);
    }
    
    //ARRAY UPDATE Bloqueo 
    public static void cambiarEstadoBloqueo( Jugador jugador, boolean bloqueado) {
    	actualizarEstadoBloqueo(jugador, bloqueado);
    	jugador.setBloqueado(bloqueado);
        
    }

    //Array Bloqueo 
    public static void cambiarBloqueo(ArrayList<Jugador> jugadores, int idJugador, boolean bloqueado) {
      for(int i=0;i<jugadores.size();i++) {
    	   
            if (jugadores.get(i).getId() == idJugador) {
                cambiarEstadoBloqueo(jugadores.get(i), bloqueado);
            }
            	i++;
            
       }}
    
    //INSERT Usuario Array 
    public static  void insertarUsuario(Jugador jugador,ArrayList<Jugador> jugadores) { 
    	if (jugadores!=null)
    	jugadores.add(jugador);

		String consulta="INSERT INTO `players`(`name`, `password_hash`, `registration_date`, `level`, `rank`) VALUES ('"+jugador.getNombre()+"','"+jugador.getContrasenya()+"','"+jugador.StringFecha()+"','"+jugador.getNivel()+"','"+jugador.getRango()+"');";
		Metodos.conexionBDUpdate(consulta);
	}
    
    //DELETE Admin 
	public static void eliminarAdministrador(Administrador Administrador,ArrayList<Administrador> Administradores) {
    	Administradores.remove(Administrador);
    	String consulta="DELETE FROM `admins` WHERE id="+Administrador.getId();
		Metodos.conexionBDUpdate(consulta);
    }

    //DELETE Jugador
    public static void eliminarJugador(Jugador jugador) {
    	String consulta="DELETE FROM `players` WHERE id="+jugador.getId();
		Metodos.conexionBDUpdate(consulta);
    }
}
