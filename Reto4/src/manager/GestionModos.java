package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import controlador.Metodos;
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
	public ArrayList<Modo> cargaInicialModos() {
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
	     Metodos.conexionBDUpdate(consulta);
	}
	
	//INSERT modo 
	public static  void insertarModo(Modo modo) { 

			String consulta="INSERT INTO `modos`(`id`, `nombre`) VALUES ('"+modo.getId()+"','"+modo.getNombre()+"')";
			Metodos.conexionBDUpdate(consulta);
		}
	 
	//DELETE modo 
	public static void eliminarModo(Modo modo,ArrayList<Modo> modos) {
	    	modos.remove(modo);

	    	String consulta="DELETE FROM `modos` WHERE id="+modo.getId();
			Metodos.conexionBDUpdate(consulta);
	}

	public Modo getModoByName(String modoStr) {
	    Modo modo = null;
	    String query = "SELECT * FROM modos WHERE nombre = ?";
	    try (Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERPLAYER, DBUtils.PASS);
	         PreparedStatement ps = conexion.prepareStatement(query)) {

	        ps.setString(1, modoStr);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            int id = rs.getInt("id");
	            String nombre = rs.getString("nombre");
	            modo = new Modo(id, nombre);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return modo;
	}

}
