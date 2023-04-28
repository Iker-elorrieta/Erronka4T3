package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Habilidad;
import modelo.Personaje;
import utils.DBUtils;

public class GestionPersonajes {
	
	public static ArrayList<Personaje> cargaInicialPersonajes(){
		String consulta="SELECT * FROM champions";
		ArrayList<Personaje> campeones = new ArrayList<Personaje>();
		try {
		    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		    Statement stmt = conexion.createStatement(); 
		    ResultSet rs = stmt.executeQuery(consulta);
			while (rs.next()) 
			{
				int id=rs.getInt("id");
			    String name=rs.getString("name");
			    String role=rs.getString("role");
			    int difficulty=rs.getInt("difficulty");
			    int attackDamage=rs.getInt("attackDamage");
			    int abilityPower=rs.getInt("abilityPower");
			    int health=rs.getInt("health");
			    int mana=rs.getInt("mana");
			    ArrayList<Habilidad> abilities= GestionHabilidades.getHabilidadesByChampId(id);
	            Personaje personaje = new Personaje(id,name,role,difficulty,abilities,attackDamage,abilityPower,health,mana);
	            campeones.add(personaje);
	            conexion.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return campeones;
		}
	
	 public static Personaje getPersonajeById(int id) {
	        Personaje personaje = null;

	        try (Connection connection = DriverManager.getConnection("url_de_la_bd", "usuario", "contraseña")) {
	            String query = "SELECT * FROM personaje WHERE id = ?";

	            PreparedStatement statement = connection.prepareStatement(query);
	            statement.setInt(1, id);

	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	    
				    String name=resultSet.getString("name");
				    String role=resultSet.getString("role");
				    int difficulty=resultSet.getInt("difficulty");
				    int attackDamage=resultSet.getInt("attackDamage");
				    int abilityPower=resultSet.getInt("abilityPower");
				    int health=resultSet.getInt("health");
				    int mana=resultSet.getInt("mana");
				    ArrayList<Habilidad> abilities= GestionHabilidades.getHabilidadesByChampId(id);
		             personaje = new Personaje(id,name,role,difficulty,abilities,attackDamage,abilityPower,health,mana);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return personaje;
	    }

}
