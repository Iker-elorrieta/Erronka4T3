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
	public static ArrayList<Habilidad> getHabilidadesByChampId(int id) {
        ArrayList<Habilidad> habilidades = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERPLAYER, DBUtils.PASS)) {
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
		String consulta = "UPDATE abilities SET name='"+habilidad.getNombre()+"',description='"+habilidad.getDescripcion()+"' WHERE id ='"+habilidad.getCod()+"'";
	    Metodos.conexionBDUpdate(consulta);


	}
	
	//INSERT 

	public static  void insertarHabilidad(Habilidad Habilidad, int cod) { 
		String consulta="INSERT INTO `abilities`(`id`, `champion_id`, `name`, `description`) VALUES ('"+Habilidad.getCod()+"','"+cod+"','"+Habilidad.getNombre()+"','"+Habilidad.getDescripcion()+"')";
		Metodos.conexionBDUpdate(consulta);
	}
	
	//DELETE habilidad 
	public static void eliminarHabilidad(Habilidad habilidad) {
    	String consulta="DELETE FROM `abilities` WHERE id="+habilidad.getCod();
		Metodos.conexionBDUpdate(consulta);
    }
	
	public static  ArrayList<String> getHabilidadyPersonaje() {
		 ArrayList<String> resultado = new ArrayList<>();

	        try (Connection connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USERADMIN, DBUtils.PASS)) {
	            String query = "SELECT champions.name as champ, abilities.name, abilities.description FROM champions join abilities on abilities.champion_id=champions.id";
	            PreparedStatement statement = connection.prepareStatement(query);
	            ResultSet resultSet = statement.executeQuery();
	            
	            while (resultSet.next()) {
	            	String nombre=resultSet.getString("champ");
	            	String hab=resultSet.getString("name");
	            	String descripcion=resultSet.getString("description");
	                resultado.add(nombre);
	                resultado.add(hab);
	                resultado.add(descripcion);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return resultado;
	    }
	
}
