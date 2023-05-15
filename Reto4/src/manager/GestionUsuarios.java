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
	private static Connection conexion;
	
	//SELECT inicial 
	public  ArrayList<Jugador> cargaInicialJugadores(){
		String consulta="Select * FROM players";
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		try {
		     conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERADMIN, DBUtils.PASS);
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
		     conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERADMIN, DBUtils.PASS);
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
			
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
             PreparedStatement stmtAdmin = conexion.prepareStatement(sqlAdmin);
             PreparedStatement stmtJugador = conexion.prepareStatement(sqlJugador);

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
    
   
	public boolean usuarioExistente(String usuario) throws SQLException {
		 String sql = "SELECT COUNT(*) FROM (SELECT name FROM players UNION ALL SELECT name FROM admins) usuarios WHERE name = ?";
	        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
	            statement.setString(1, usuario);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    int count = resultSet.getInt(1);
	                    return count > 0;
	                }
	            }
	        }
	        return false;
		
	}
		
	//UPDATE Jugador 
	 public void actualizarJugador(Jugador jugador) throws SQLException {
		 int bloqueado=0;
			if(jugador.isbloqueado())
				bloqueado=1;
	        String sql ="UPDATE players SET name='"+jugador.getNombre()+"',password_hash='"+jugador.getContrasenya()+"',level='"+jugador.getNivel()+"',rank='"+jugador.getRango()+"',bloqueado='"+bloqueado+"',registration_date='"+jugador.StringFecha()+"' WHERE id ="+jugador.getId();
	        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
	            statement.executeUpdate();
	        }
	    }
	 
	 public void actualizarAdministrador(Administrador admin) throws SQLException {
	        String sql = "UPDATE admins SET name=? ,password=? WHERE id="+admin.getId();
	        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
	            statement.setString(1, admin.getNombre());
	            statement.setString(2, admin.getContrasenya());
	            statement.executeUpdate();
	        }
	    }

	    public void insertarJugador(Jugador jugador) throws SQLException {
	        String sql = "INSERT INTO `players`(`name`, `password_hash`, `registration_date`, `level`, `rank`) VALUES ('"+jugador.getNombre()+"','"+jugador.getContrasenya()+"','"+jugador.StringFecha()+"','"+jugador.getNivel()+"','"+jugador.getRango()+"');";
	        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
	            statement.executeUpdate();
	        }
	    }
	    
	    public void insertarAdministrador(Administrador admin) throws SQLException {
	    	 String sql = "INSERT admins SET name=? ,password=?";
		        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
		            statement.setString(1, admin.getNombre());
		            statement.setString(2, admin.getContrasenya());
		            statement.executeUpdate();
		        }
	    }
	    //DELETE Jugador
	    public void eliminarJugador(int id) throws SQLException {
	        String sql = "DELETE FROM `players` WHERE id="+id;
	        try (Statement statement = conexion.createStatement()) {
	            statement.executeUpdate(sql);
	        }
	    }
	        //DELETE Admin   
	    public void eliminarAdministrador(int id) throws SQLException {
		      String sql ="DELETE FROM `admins` WHERE id="+id;
		        try (Statement statement = conexion.createStatement()) {
		            statement.executeUpdate(sql);
		        }
	    }

		public int getNivelById(int id) {
			String consulta="Select level FROM players WHERE id="+id;
			int nivel = 0;
			try {
			     conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERADMIN, DBUtils.PASS);
			    Statement stmt = conexion.createStatement(); 
			    ResultSet rs = stmt.executeQuery(consulta);
			     while (rs.next()) 
					{    
			    	 	
						
						nivel=rs.getInt("level");
						
					}
			    
			} catch (SQLException e) {
			    System.err.println("Error al establecer la conexión con MySQL: " + e.getMessage());
			}
			return nivel;
			
		}

		public void subirNivel(int id) {
			int nivel= getNivelById(id)+1;
			
	        String sql ="UPDATE players SET level='"+nivel+"' WHERE id ="+id;
	        try {
	        		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
	        		PreparedStatement statement = conexion.prepareStatement(sql) ;
	            statement.executeUpdate();
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		}
	   
		
}
