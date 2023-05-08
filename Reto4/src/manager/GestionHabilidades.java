package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controlador.metodos;
import modelo.Habilidad;
import utils.DBUtils;

public class GestionHabilidades {
	
	//SELECT by ChampId 
	public static ArrayList<Habilidad> getHabilidadesByChampId(int id) {
        ArrayList<Habilidad> habilidades = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USERPLAYER, DBUtils.PASS)) {
            String query = "SELECT * FROM abilities WHERE champion_id ="+id;

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
            	int id_habilidad=resultSet.getInt("id");
            	String nombre=resultSet.getString("name");
            	String descripcion=resultSet.getString("description");
            	Habilidad habilidad= new Habilidad(id_habilidad, nombre, descripcion);
                habilidades.add(habilidad);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return habilidades;
    }
	
	//SELECT all 
	public static  ArrayList<Habilidad> getHabilidad() {
		 ArrayList<Habilidad> habilidades = new ArrayList<>();

	        try (Connection connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USERADMIN, DBUtils.PASS)) {
	            String query = "SELECT * FROM abilities";
	            PreparedStatement statement = connection.prepareStatement(query);
	            ResultSet resultSet = statement.executeQuery();
	            
	            while (resultSet.next()) {
	            	int id_habilidad=resultSet.getInt("id");
	            	String nombre=resultSet.getString("name");
	            	String descripcion=resultSet.getString("description");
	            	Habilidad habilidad= new Habilidad(id_habilidad, nombre, descripcion);
	                habilidades.add(habilidad);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return habilidades;
	    }
	
	//UPDATE Habilidad 
	public static  void updateHabilidad(Habilidad habilidad) { 
		String consulta = "UPDATE matches SET name="+habilidad.getNombre()+",description="+habilidad.getDescripcion()+" WHERE id ="+habilidad.getCod();
	    metodos.conexionBDUpdate(consulta);
	}
	
	//INSERT 
	public static  void insertarHabilidad(Habilidad Habilidad, int cod) { 
		String consulta="INSERT INTO `abilities`(`id`, `champion_id`, `name`, `description`) VALUES ("+Habilidad.getCod()+"','"+cod+"','"+Habilidad.getNombre()+","+Habilidad.getDescripcion()+")";
		metodos.conexionBDUpdate(consulta);
	}
	
	//DELETE habilidad 
	public void eliminarHabilidad(Habilidad habilidad) {
    	String consulta="DELETE FROM `abilities` WHERE id="+habilidad.getCod();
		metodos.conexionBDUpdate(consulta);
    }
	

}
