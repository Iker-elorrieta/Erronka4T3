package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controlador.Metodos;
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
			    int attackDamage=rs.getInt("attack_Damage");
			    int abilityPower=rs.getInt("ability_Power");
			    int health=rs.getInt("life");
			    int mana=rs.getInt("mana");
			    ArrayList<Habilidad> abilities= GestionHabilidades.getHabilidadesByChampId(id);
	            Personaje personaje = new Personaje(id,name,role,difficulty,abilities,attackDamage,abilityPower,health,mana);
	            campeones.add(personaje);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return campeones;
		}
	
	//SELECT by id 
	public static Personaje getPersonajeById(int id) {
	        Personaje personaje = null;

	        try (Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERPLAYER, DBUtils.PASS)) {
	            String query = "SELECT * FROM champions WHERE id='"+id+"'";

	            Statement stmt = conexion.createStatement(); 
			    ResultSet rs = stmt.executeQuery(query);

	            if (rs.next()) {
	    
				    String name=rs.getString("name");
				    String role=rs.getString("role");
				    int difficulty=rs.getInt("difficulty");
				    int attackDamage=rs.getInt("attack_Damage");
				    int abilityPower=rs.getInt("ability_Power");
				    int health=rs.getInt("life");
				    int mana=rs.getInt("mana");
				    ArrayList<Habilidad> abilities= GestionHabilidades.getHabilidadesByChampId(id);
		             personaje = new Personaje(id,name,role,difficulty,abilities,attackDamage,abilityPower,health,mana);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return personaje;
	    }  
	 
	//UPDATE personaje
	public static  void updatePersonaje(Personaje personaje){
		String consulta = "UPDATE champions SET name='"+personaje.getName()+"',role='"+personaje.getRole()+"',difficulty='"+personaje.getDifficulty()+"',attack_damage='"+personaje.getAttackDamage()+"',ability_power='"+personaje.getAbilityPower()+"',life='"+personaje.getHealth()+"',mana='"+personaje.getMana()+"' WHERE id ="+personaje.getId();
		Metodos.conexionBDUpdate(consulta);
		
	}
	
	//INSERT personaje 
	public static  void insertarPersonaje(Personaje personaje) { 
			String consulta="INSERT INTO `champions`(`id`, `name`, `role`, `difficulty`, `attack_damage`, `ability_power`,`life`,`mana`) VALUES"

					+ " ('"+personaje.getId()+"','"+personaje.getName()+"','"+personaje.getRole()+"','"+personaje.getDifficulty()+"','"+personaje.getAttackDamage()+"','"+personaje.getAbilityPower()+"','"+personaje.getHealth()+"','"+personaje.getMana()+"')";
			Metodos.conexionBDUpdate(consulta);
	}

	//DELETE personaje 
	public static void eliminarPersonaje(Personaje personaje) {
			String consulta="DELETE FROM `champions` WHERE id="+personaje.getId();
			Metodos.conexionBDUpdate(consulta);
		}
	
	//SELECT Complejo: Buscar personaje por id de habilidad
	public static Personaje buscarPorhabilidad(int id) {
		  Personaje personaje = new Personaje();
		 try (Connection connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS)) {
		        String query = "SELECT champions.id,champions.name FROM champions,abilities WHERE abilities.id='"+id+"' AND abilities.champion_id=champions.id";
		        Statement stmt = connection.createStatement(); 
			    ResultSet rs = stmt.executeQuery(query);
			    if (rs.next()) {
				    personaje.setId(rs.getInt("id"));
				    personaje.setName(rs.getString("name"));
				    personaje.setRole(rs.getString("role"));
				   personaje.setAbilities(GestionHabilidades.getHabilidadesByChampId(id));
	            }
		      
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		 return personaje;
	}

}
