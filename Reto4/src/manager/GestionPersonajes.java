package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controlador.Metodos;
import modelo.Habilidad;
import modelo.Jugador;
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
	

	public static ArrayList <String> personajeMasJugado() {
		
		int contador=0;
		
		ArrayList <Jugador> jugadores;
		jugadores=GestionUsuarios.cargaInicialJugadores();
		ArrayList <String> resultado = new ArrayList<String>();
		
        try (Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERPLAYER, DBUtils.PASS)) {
            String query = "SELECT name FROM champions  WHERE champions.id=(SELECT champion FROM matches WHERE id=(SELECT id FROM players where id="+jugadores.get(contador).getId()+") GROUP BY champion ORDER BY COUNT(*) DESC LIMIT 1) ;";

            Statement stmt = conexion.createStatement(); 
		    ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
			    String personaje=rs.getString("name");
			    
			    resultado.add(jugadores.get(contador).getNombre());
			    resultado.add(personaje);
			    contador++;
	             
            }
            
            

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    	
	}
	
	public static ArrayList <String> habilidadesDePersonajes() {
	
		ArrayList <String> resultado = new ArrayList<String>();
		
		String unNombre="";
		
        try (Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERPLAYER, DBUtils.PASS)) {
            String query = "SELECT champions.name AS champion, abilities.name, description FROM abilities join champions on abilities.champion_id=champions.id ;";

            Statement stmt = conexion.createStatement(); 
		    ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
            	
			    String personaje=rs.getString("champion");
			    String habilidad=rs.getString("name");
			    String descripcion=rs.getString("description");
			   
			    if(!unNombre.equals(personaje)) {
			    	resultado.add(personaje);
			    	unNombre=personaje;
			    }
			    resultado.add(habilidad);
			    resultado.add(descripcion);
			   
            }
            
            

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
	}
		

	//SELECT Complejo: Buscar personaje por id de habilidad
	public static ArrayList<Personaje> buscarPorhabilidad(int id) {
		
		ArrayList<Personaje> campeones = new ArrayList<Personaje>();
		 try (Connection connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS)) {
		        String query = "SELECT * FROM champions,abilities WHERE abilities.id="+id+" AND abilities.id_champion=champions.id";

		        Statement stmt = connection.createStatement(); 
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
		            Personaje personaje = new Personaje(id,name,role,difficulty,abilities,attackDamage,abilityPower,health,mana);
		            campeones.add(personaje);
	            }
		      
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		 return campeones;

	}

}
