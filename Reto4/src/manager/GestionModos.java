package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    	try (Connection connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USERPLAYER, DBUtils.PASS)) {
            String query = "SELECT nombre FROM modos WHERE id ="+id;

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
		 String consulta = "UPDATE matches SET name="+modo.getNombre()+"  WHERE id ="+modo.getId();
	     metodos.conexionBDUpdate(consulta);
	}
	
	//INSERT modo 
	public static  void insertarModo(Modo modo) { 
			String consulta="INSERT INTO `modos`(`id`, `name`) VALUES ('"+modo.getId()+"','"+modo.getNombre()+"')";
			metodos.conexionBDUpdate(consulta);
		}
	 
	//DELETE modo 
	public void eliminarModo(Modo modo,ArrayList<Modo> modos) {
	    	modos.remove(modo);
	    	String consulta="DELETE FROM `modo` WHERE id="+modo.getId();
			metodos.conexionBDUpdate(consulta);
	    }
}
