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
<<<<<<< HEAD
	    }
	 
	 public ArrayList<Personaje> getPersonajeByJugadorLvL(int lvl) {
	        Personaje personaje = null;
	        int cantidadDesbloqueos = lvl / 10;
	        ArrayList<Personaje> personajes = new ArrayList<>();
	        for (int id = 1; id < cantidadDesbloqueos+1; id++) {

			
	        try (Connection connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USERPLAYER, DBUtils.PASS)) {
	            String query = "SELECT * FROM champions WHERE id = ?";


	            PreparedStatement stmt = connection.prepareStatement(query); 
	            stmt.setInt(1, id);
			    ResultSet resultSet = stmt.executeQuery();
			   
			    
	            if (resultSet.next()) {
	    
				    String name=resultSet.getString("name");
				    String role=resultSet.getString("role");
				    int difficulty=resultSet.getInt("difficulty");
				    int attackDamage=resultSet.getInt("attack_Damage");
				    int abilityPower=resultSet.getInt("ability_Power");
				    int health=resultSet.getInt("life");
				    int mana=resultSet.getInt("mana");
				    ArrayList<Habilidad> abilities= GestionHabilidades.getHabilidadesByChampId(id);
		            personaje = new Personaje(id,name,role,difficulty,abilities,attackDamage,abilityPower,health,mana);
		            personajes.add(personaje);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        }
	        return personajes;
	    }

	 public static ArrayList<Personaje> getPersonajeByPartida(int cod) {
	        ArrayList<Personaje> personajes = new ArrayList<>();
				
	        try (Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERPLAYER, DBUtils.PASS)) {
	            String query = "";
	            Statement stmt = conexion.createStatement(); 
			    ResultSet resultSet = stmt.executeQuery(query);

	            if (resultSet.next()) {
	            	int id=resultSet.getInt("id");
				    String name=resultSet.getString("name");
				    String role=resultSet.getString("role");
				    int difficulty=resultSet.getInt("difficulty");
				    int attackDamage=resultSet.getInt("attack_Damage");
				    int abilityPower=resultSet.getInt("ability_Power");
				    int health=resultSet.getInt("life");
				    int mana=resultSet.getInt("mana");
				    ArrayList<Habilidad> abilities= GestionHabilidades.getHabilidadesByChampId(id);
		           Personaje personaje = new Personaje(id,name,role,difficulty,abilities,attackDamage,abilityPower,health,mana);
		            personajes.add(personaje);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return personajes;
	    }
=======
	    }  
>>>>>>> branch 'S2' of https://github.com/Iker-elorrieta/Erronka4T3.git
	 
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
            String query = "SELECT name FROM champions  WHERE champions.id=(SELECT champion_id FROM matches WHERE id=(SELECT id FROM players where id='"+jugadores.get(contador).getId()+"') GROUP BY champion_id ORDER BY COUNT(*) DESC LIMIT 1)";

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
            String query = "SELECT champions.name AS champion, abilities.name, description FROM abilities join champions on abilities.champion_id=champions.id";

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
	public static Personaje buscarPorhabilidad(int id) {
		  Personaje personaje = new Personaje();
		 try (Connection connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS)) {
		        String query = "SELECT champions.id,champions.name FROM champions,abilities WHERE abilities.id='"+id+"' AND abilities.champion_id=champions.id";
		        Statement stmt = connection.createStatement(); 
			    ResultSet rs = stmt.executeQuery(query);
			    if (rs.next()) {
				    personaje.setId(rs.getInt("id"));
				    personaje.setName(rs.getString("name"));
				   personaje.setAbilities(GestionHabilidades.getHabilidadesByChampId(id));
	            }
		      
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		 return personaje;
	}

}
