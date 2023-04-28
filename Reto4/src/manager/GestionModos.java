package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import modelo.Estadisticas;
import modelo.Modo;
import modelo.Partida;
import modelo.Personaje;
import utils.DBUtils;

public class GestionModos {
	
	public static Modo getModoById(int id) {
    	Modo modo =null;
    	try (Connection connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS)) {
            String query = "SELECT nombre FROM modos WHERE id = ?";

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

	public static ArrayList<Modo> cargaInicialModos() {
		String consulta="SELECT * FROM modos";
		ArrayList<Modo> modos= new ArrayList<>();
		try {
		    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
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
    

}
