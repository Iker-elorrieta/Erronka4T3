package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import controlador.metodos;
import modelo.Modo;
import utils.DBUtils;

public class GestionModos {
	
	//SELECT by ID 
	public static Modo getModoById(int id) {
    	Modo modo =null;
    	try (Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERPLAYER, DBUtils.PASS)) {
            String query = "SELECT nombre FROM modos WHERE id ="+id;

            Statement stmt = conexion.createStatement(); 
		    ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                modo = new Modo(id, nombre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modo;
    }

	//SELECT inicial 
	public static ArrayList<Modo> cargaInicialModos() {
		String consulta="SELECT * FROM modos";
		ArrayList<Modo> modos= new ArrayList<>();
		try {
		    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERPLAYER, DBUtils.PASS);
		    Statement stmt = conexion.createStatement(); 
		    ResultSet rs = stmt.executeQuery(consulta);
			while (rs.next()) 
			{
				int id=rs.getInt("id");
				String nombre= rs.getString("nombre");
				Modo modo = new Modo(id, nombre);
				modos.add(modo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modos;
	}
    
	//UPDATE modo 
	public static  void updateModo(Modo modo) {
		 String consulta = "UPDATE modos SET nombre='"+modo.getNombre()+"'  WHERE id ="+modo.getId();
	     metodos.conexionBDUpdate(consulta);
	}
	
	//INSERT modo 
	public static  void insertarModo(Modo modo) { 
			String consulta="INSERT INTO `modos`(`id`, `nombre`) VALUES ('"+modo.getId()+"','"+modo.getNombre()+"')";
			metodos.conexionBDUpdate(consulta);
		}
	 
	//DELETE modo 
	public static void eliminarModo(Modo modo,ArrayList<Modo> modos) {
	    	modos.remove(modo);
	    	String consulta="DELETE FROM `modos` WHERE id="+modo.getId();
			metodos.conexionBDUpdate(consulta);
	    }
	
	//Seleccion compleja: SElecciona los modos
	public static Modo modosJugador(int id) {
		String consulta="SELECT id, nombre FROM modos WHERE id = (SELECT modo_id FROM matches WHERE player_id ="+id+" GROUP BY modo_id ORDER BY COUNT(*)";

		Modo modo=new Modo();
		try {
		    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERPLAYER, DBUtils.PASS);
		    Statement stmt = conexion.createStatement(); 
		    ResultSet rs = stmt.executeQuery(consulta);
			while (rs.next()) 
			{
				modo.setId(rs.getInt("id"));
				modo.setNombre(rs.getString("nombre"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modo;
	}
}
