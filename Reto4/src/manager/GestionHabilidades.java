package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controlador.Metodos;
import modelo.Habilidad;
import utils.DBUtils;

public class GestionHabilidades {
	
	//SELECT by ChampId 
	public static ArrayList<Habilidad> getHabilidadesByChampId(Connection conexion, int id) {
        ArrayList<Habilidad> habilidades = new ArrayList<>();

        try {
            String query = "SELECT * FROM abilities WHERE champion_id ="+id;

            Statement stmt = conexion.createStatement(); 
		    ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
            	int id_habilidad=rs.getInt("id");
            	String nombre=rs.getString("name");
            	String descripcion=rs.getString("description");
            	Habilidad habilidad= new Habilidad(id_habilidad, nombre, descripcion);
                habilidades.add(habilidad);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return habilidades;
    }
	
	//SELECT all 
	public static  ArrayList<Habilidad> getHabilidad(Connection conexion) {
		 ArrayList<Habilidad> habilidades = new ArrayList<>();

	        try  {
	            String query = "SELECT * FROM abilities";
	            PreparedStatement statement = conexion.prepareStatement(query);
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

	public static  void updateHabilidad(Connection conexion, Habilidad habilidad) { 
		String consulta = "UPDATE abilities SET name='"+habilidad.getNombre()+"',description='"+habilidad.getDescripcion()+"' WHERE id ='"+habilidad.getCod()+"'";
	    Metodos.conexionBDUpdate(conexion, consulta);


	}
	
	//INSERT 

	public static  void insertarHabilidad(Connection conexion, Habilidad Habilidad, int cod) { 
		String consulta="INSERT INTO `abilities`(`id`, `champion_id`, `name`, `description`) VALUES ('"+Habilidad.getCod()+"','"+cod+"','"+Habilidad.getNombre()+"','"+Habilidad.getDescripcion()+"')";
		Metodos.conexionBDUpdate(conexion, consulta);
	}
	
	//DELETE habilidad 
	public static void eliminarHabilidad(Connection conexion, Habilidad habilidad) {
    	String consulta="DELETE FROM `abilities` WHERE id="+habilidad.getCod();
		Metodos.conexionBDUpdate(conexion, consulta);
    }
	

}
