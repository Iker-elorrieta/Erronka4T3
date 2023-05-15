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
	public ArrayList<Personaje> cargaInicialPersonajes(Connection conexion){
		String consulta="SELECT * FROM champions";
		ArrayList<Personaje> campeones = new ArrayList<Personaje>();
		try {
		  
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
			    ArrayList<Habilidad> abilities= GestionHabilidades.getHabilidadesByChampId(conexion, id);
	            Personaje personaje = new Personaje(id,name,role,difficulty,attackDamage,abilityPower,health,mana);
	            campeones.add(personaje);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return campeones;
		}
	
	//SELECT by id 
	public static Personaje getPersonajeById(Connection conexion, int id) {
	        Personaje personaje = null;

	        try {
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
				    ArrayList<Habilidad> abilities= GestionHabilidades.getHabilidadesByChampId(conexion, id);
		             personaje = new Personaje(id,name,role,difficulty,attackDamage,abilityPower,health,mana);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return personaje;

	    }
	 
	 public ArrayList<Personaje> getPersonajeByJugadorLvL(Connection conexion,int lvl) {
	        Personaje personaje = null;
	        int cantidadDesbloqueos = lvl / 10;
	        ArrayList<Personaje> personajes = new ArrayList<>();
	        for (int id = 1; id < cantidadDesbloqueos+1; id++) {

			
	        try  {
	            String query = "SELECT * FROM champions WHERE id = ?";


	            PreparedStatement stmt = conexion.prepareStatement(query); 
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
				    ArrayList<Habilidad> abilities= GestionHabilidades.getHabilidadesByChampId(conexion, id);
		            personaje = new Personaje(id,name,role,difficulty,attackDamage,abilityPower,health,mana);
		            personajes.add(personaje);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        }
	        return personajes;
	    }

	 public static ArrayList<Personaje> getPersonajeByPartida(Connection conexion, int cod) {
	        ArrayList<Personaje> personajes = new ArrayList<>();
				
	        try {
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
				    ArrayList<Habilidad> abilities= GestionHabilidades.getHabilidadesByChampId(conexion, id);
		           Personaje personaje = new Personaje(id,name,role,difficulty,attackDamage,abilityPower,health,mana);
		            personajes.add(personaje);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return personajes;
	    }

	 
	//UPDATE personaje
	public static  void updatePersonaje(Connection conexionm, Personaje personaje){
		String consulta = "UPDATE champions SET name='"+personaje.getName()+"',role='"+personaje.getRole()+"',difficulty='"+personaje.getDifficulty()+"',attack_damage='"+personaje.getAttackDamage()+"',ability_power='"+personaje.getAbilityPower()+"',life='"+personaje.getHealth()+"',mana='"+personaje.getMana()+"' WHERE id ="+personaje.getId();
		Metodos.conexionBDUpdate(conexionm, consulta);
		
	}
	
	//INSERT personaje 
	public static  void insertarPersonaje(Personaje personaje) { 
			String consulta="INSERT INTO `champions`(`id`, `name`, `role`, `difficulty`, `attack_damage`, `ability_power`,`life`,`mana`) VALUES"

					+ " ('"+personaje.getId()+"','"+personaje.getName()+"','"+personaje.getRole()+"','"+personaje.getDifficulty()+"','"+personaje.getAttackDamage()+"','"+personaje.getAbilityPower()+"','"+personaje.getHealth()+"','"+personaje.getMana()+"')";
			Metodos.conexionBDUpdate(null, consulta);
	}

	//DELETE personaje 
	public static void eliminarPersonaje(Connection conexion, Personaje personaje) {
			String consulta="DELETE FROM `champions` WHERE id="+personaje.getId();
			Metodos.conexionBDUpdate(conexion, consulta);
		}
	

	public static ArrayList <String> personajeMasJugado(Connection conexion) {
		
		int contador=0;
		ArrayList <Jugador> jugadores;
		jugadores=GestionUsuarios.cargaInicialJugadores(conexion);
		ArrayList <String> resultado = new ArrayList<String>();
		
        try {
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
	
	public static ArrayList <String> habilidadesDePersonajes(Connection conexion) {
	
		ArrayList <String> resultado = new ArrayList<String>();
		String unNombre="";
        try  {
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
	public static Personaje buscarPorhabilidad(Connection conexion, int id) {
		  Personaje personaje = new Personaje();
		 try{
		        String query = "SELECT champions.id,champions.name FROM champions,abilities WHERE abilities.id='"+id+"' AND abilities.champion_id=champions.id";
		        Statement stmt = conexion.createStatement(); 
			    ResultSet rs = stmt.executeQuery(query);
			    if (rs.next()) {
				    personaje.setId(rs.getInt("id"));
				    personaje.setName(rs.getString("name"));
				   
	            }
		      
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		 return personaje;
	}

	public Personaje getPersonajeByNombre(Connection conexion, String personajeStr) {
		   Personaje personaje = null;

	        try {
	            String query = "SELECT * FROM champions WHERE name='"+personajeStr+"'";

	            Statement stmt = conexion.createStatement(); 
			    ResultSet rs = stmt.executeQuery(query);

	            if (rs.next()) {
	            	int id = rs.getInt("id");
				    String name=rs.getString("name");
				    String role=rs.getString("role");
				    int difficulty=rs.getInt("difficulty");
				    int attackDamage=rs.getInt("attack_Damage");
				    int abilityPower=rs.getInt("ability_Power");
				    int health=rs.getInt("life");
				    int mana=rs.getInt("mana");
		             personaje = new Personaje(id,name,role,difficulty,attackDamage,abilityPower,health,mana);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return personaje;

	}

}
