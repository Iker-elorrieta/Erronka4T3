package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import controlador.metodos;
import modelo.Administrador;
import modelo.Jugador;
import utils.DBUtils;

public class GestionUsuarios {
    
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
		    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		    Statement stmt = conexion.createStatement(); 
		    ResultSet rs = stmt.executeQuery(consulta);
		     while (rs.next()) 
				{
					String nombre = rs.getString("name");
		            String contrasenya = rs.getString("password");
		            int id= rs.getInt("id");

					Administrador jugador = new Administrador(id,nombre,contrasenya);
					enviar.add(jugador);	
				}
		     conexion.close();
		     return enviar;
		} catch (SQLException e) {
		    System.err.println("Error al establecer la conexión con MySQL: " + e.getMessage());
		}
		return enviar;
	}
	public static Jugador iniciarSesionUsuarios(String nombre, String contrasenya) {
		boolean inicioSesion=false;
		ArrayList<Jugador> usuarios=seleccionJugador();
		int i=0;
		boolean contra=false;
		Jugador enviar = new Jugador();
		do {
			if(usuarios.get(i).getNombre().equals(nombre))
			{
				if(usuarios.get(i).comprobarContrasenya(contrasenya))
				{
					inicioSesion=true;
				}
			}
			
			if(i+1==usuarios.size())
			{
				contra=true;
				inicioSesion=true;
			}
			i++;
		}while(!inicioSesion);
		if(!contra)
			enviar=usuarios.get(i-1);
		return enviar;
	}
	
	public static ArrayList<Jugador> seleccionJugador() {
		String consulta="Select * FROM players";
		ArrayList<Jugador> enviar = new ArrayList<Jugador>();
		try {
		    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
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

}
