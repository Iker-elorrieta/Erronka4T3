package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controlador.metodos;
import modelo.Habilidad;
import modelo.Personaje;
import utils.DBUtils;

public class GestionPersonajes {
	
	//SELECT inicial 
	public static ArrayList<Personaje> cargaInicialPersonajes(){
		String consulta="SELECT * FROM champions";
		ArrayList<Personaje> campeones = new ArrayList<Personaje>();
		try {
		    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERPLAYER, DBUtils.PASS);
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
	
	//SELECT by id 
	public static Personaje getPersonajeById(int id) {
	        Personaje personaje = null;

	        try (Connection connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USERPLAYER, DBUtils.PASS)) {
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
	 
	 public static Personaje getPersonajeByJugadorLvL(int lvl) {
	        Personaje personaje = null;
	        int cantidadDesbloqueos = lvl / 10;
	        ArrayList<Personaje> personajes = new ArrayList<>();
	        for (int id = 0; id < cantidadDesbloqueos; id++) {
				
			
	        try (Connection connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS)) {
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
		             personajes.add(personaje);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        }
	        return personaje;
	    }

	//UPDATE personaje
	public static  void updatePersonaje(Personaje personaje){
		String consulta = "UPDATE players SET name="+personaje.getName()+",role="+personaje.getRole()+",difficulty="+personaje.getDifficulty()+",attack_damage="+personaje.getAttackDamage()+",ability_power="+personaje.getAbilityPower()+",life"+personaje.getHealth()+",mana"+personaje.getMana()+" WHERE id ="+personaje.getId();
		metodos.conexionBDUpdate(consulta);
		
	}
	
	//INSERT personaje 
	public static  void insertarPersonaje(Personaje personaje) { 
			String consulta="INSERT INTO `champions`(`id`, `name`, `role`, `difficulty`, `attack_damage`, `ability_power`,`life`,`mana`) VALUES"
					+ " ('"+personaje.getId()+"','"+personaje.getName()+"','"+personaje.getRole()+"','"+personaje.getDifficulty()+"','"+personaje.getAttackDamage()+"','"+personaje.getAbilityPower()+"','"+personaje.getHealth()+"','"+personaje.getMana()+")";
			metodos.conexionBDUpdate(consulta);
		}

	//DELETE personaje 
	public void eliminarPersonaje(Personaje personaje) {
			String consulta="DELETE FROM `players` WHERE id="+personaje.getId();
			metodos.conexionBDUpdate(consulta);
		}

}
