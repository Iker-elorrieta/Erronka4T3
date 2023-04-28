package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Modo;

public class GestionModos {
	
	public static Modo getModoById(int id) {
    	Modo modo =null;
    	try (Connection connection = DriverManager.getConnection("url_de_la_bd", "usuario", "contraseña")) {
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
    

}
