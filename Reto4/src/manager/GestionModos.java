package manager;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import controlador.Metodos;
import modelo.Modo;


public class GestionModos {
	
	
	//SELECT by ID 
	public  Modo getModoById(Connection conexion, int id) {
    	Modo modo =null;
    	try {
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
	public ArrayList<Modo> cargaInicialModos(Connection conexion) {
		String consulta="SELECT * FROM modos";
		ArrayList<Modo> modos= new ArrayList<>();
		try {
		   
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
	public   void updateModo(Connection conexion, Modo modo) {

		 String consulta = "UPDATE modos SET nombre='"+modo.getNombre()+"'  WHERE id ="+modo.getId();
	     Metodos.conexionBDUpdate(conexion, consulta);
	}
	
	//INSERT modo 
	public  void insertarModo(Connection conexion, Modo modo) { 

			String consulta="INSERT INTO `modos`(`id`, `nombre`) VALUES ('"+modo.getId()+"','"+modo.getNombre()+"')";
			Metodos.conexionBDUpdate(conexion, consulta);
		}
	 
	//DELETE modo 
	 public void eliminarModo(Connection conexion,int id) throws SQLException {
	        String sql = "DELETE FROM modos WHERE id="+id;
	        try (Statement statement = conexion.createStatement()) {
	            statement.executeUpdate(sql);
	        }
	    }

	public Modo getModoByName(Connection conexion, String modoStr) {
	    Modo modo = null;
	    String query = "SELECT * FROM modos WHERE nombre = ?";
	    try {
	         PreparedStatement ps = conexion.prepareStatement(query);

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
