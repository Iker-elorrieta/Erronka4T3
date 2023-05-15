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
	
	
	
	public ArrayList<Habilidad> cargaInicialHabilidades(Connection conexion) {
        ArrayList<Habilidad> habilidades = new ArrayList<>();

        try {
            String query = "SELECT * FROM abilities";

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
	 public void eliminarHabilidad(Connection conexion,int id) throws SQLException {
	        String sql = "DELETE FROM abilities WHERE id="+id;
	        try (Statement statement = conexion.createStatement()) {
	            statement.executeUpdate(sql);
	        }
	    }
	
	public static  ArrayList<String> getHabilidadyPersonaje(Connection conexion) {
		 ArrayList<String> resultado = new ArrayList<>();

		 try  {
	            String query = "SELECT champions.name as champ, abilities.name, abilities.description FROM champions join abilities on champions.id=abilities.champion_id";
	            PreparedStatement statement = conexion.prepareStatement(query);
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
