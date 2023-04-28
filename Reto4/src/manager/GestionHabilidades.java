package manager;

<<<<<<< HEAD
public class GestionHabilidades {
=======
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Habilidad;

public class GestionHabilidades {
	
	public static ArrayList<Habilidad> getHabilidadesByChampId(int id) {
        ArrayList<Habilidad> habilidades = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("url_de_la_bd", "usuario", "contraseña")) {
            String query = "SELECT * FROM abilities WHERE champion_id = ?";

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
>>>>>>> branch 'S2' of https://github.com/Iker-elorrieta/Erronka4T3.git

}
