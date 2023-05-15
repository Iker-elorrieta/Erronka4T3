package manager;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import controlador.Metodos;
import modelo.Estadisticas;

import modelo.Jugador;
import modelo.Modo;
import modelo.Partida;
import modelo.Personaje;


public class GestionPartidas {
	GestionEstadisticas gestionE = new GestionEstadisticas();
	GestionPersonajes gestionPJ = new GestionPersonajes();
	GestionModos gestionM = new GestionModos();
	 static GestionUsuarios gestionU = new GestionUsuarios();
	//SELECT inicial 

	public ArrayList<Partida> cargaInicialPartidas(Connection conexion) {
		String consulta = "SELECT players.name as jugador, matches.id, matches.duration, matches.modo_id, matches.result, matches.date, matches.Estadisticas, matches.champion_id FROM players JOIN matches ON players.id = matches.player_id";

	ArrayList<Partida> partidas= new ArrayList<>();
	try {

	    Statement stmt = conexion.createStatement(); 
	    ResultSet rs = stmt.executeQuery(consulta);
		while (rs.next()) 
		{
			int id=rs.getInt("id");
			int duracion = rs.getInt("duration");
			Modo modo = null;
			try {
				modo = gestionM.getModoById(conexion, rs.getInt("modo_id"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean resultado=rs.getBoolean("result");
			Date fecha=rs.getDate("date");
			Jugador jugador=gestionU.getJugadorByNombre(conexion, rs.getString("jugador"));

			Estadisticas estadistica=gestionE.obtenerEstadistica(rs.getString("Estadisticas"));
			Personaje personaje = GestionPersonajes.getPersonajeById(conexion, rs.getInt("champion_id"));
			
			Partida partida = new Partida(id, jugador, modo, personaje, estadistica, resultado, fecha, duracion);
			partidas.add(partida);
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return partidas;
}


public ArrayList<Partida> getPartidasByJugador(Connection conexion, String jugador) {
    ArrayList<Partida> partidas = new ArrayList<>();
    String query = "SELECT * FROM matches WHERE player_id = (SELECT id FROM players WHERE name='"+jugador+"') ORDER By id DESC LIMIT 10; ;";
    try {
    	
        Statement stmt = conexion.createStatement(); 
	    ResultSet resultSet = stmt.executeQuery(query);
        
        while (resultSet.next()) {
        	int id=resultSet.getInt("id");
			int duracion = resultSet.getInt("duration");
			Modo modo= gestionM.getModoById(conexion, resultSet.getInt("modo_id"));
			boolean resultado=resultSet.getBoolean("result");
			Date fecha=resultSet.getDate("date");
			Estadisticas estadistica=gestionE.obtenerEstadistica(resultSet.getString("Estadisticas"));
			Personaje personaje = GestionPersonajes.getPersonajeById(conexion, resultSet.getInt("champion_id"));
			
			Partida partida = null;
			try {
				partida = new Partida(id, gestionU.getJugadorByNombre(conexion, jugador), modo, personaje, estadistica, resultado, fecha, duracion);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			partidas.add(partida);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return partidas;
}
	
	//INSERT partida 
public void insertarPartida(Connection conexion, Partida partida) {
    int resultado = partida.isResultado() ? 1 : 0;
    String estadisticas = partida.getEstadisticas().toString(); // Convertir las estadísticas a una cadena de texto

    String consulta = "INSERT INTO `matches` (`duration`, `result`, `estadisticas`, `date`, `champion_id`, `modo_id`, `player_id`) VALUES ("
            + "'" + partida.getDuracion() + "', '" + resultado + "', '" + estadisticas + "', '" + partida.StringFecha() + "', '" + partida.getPersonaje().getId() + "', '" + partida.getModo().getId() + "', '" + partida.getJugador().getId() + "')";

    Metodos.conexionBDUpdate(conexion, consulta);
}


	//DELETE partida 

	 public void eliminarPartida(Connection conexion,int id) throws SQLException {
	        String sql = "DELETE FROM matches WHERE id="+id;
	        try (Statement statement = conexion.createStatement()) {
	            statement.executeUpdate(sql);
	        }
	    }
	
	//SELECT Complejo: sacar victorias y derrotas de usuario
	public static Integer[] sacarResultados(Connection conexion, String nombre) {
		Integer[] result= new Integer[2];
		result[0]=0;
		result[1]=0;
		 try {
		        String query = "SELECT result FROM matches,players WHERE players.name='"+nombre+"' AND players.id=matches.player_id ";


		        Statement stmt = conexion.createStatement(); 
			    ResultSet resultSet = stmt.executeQuery(query);
		        
		        while (resultSet.next()) {
		        	byte resul=resultSet.getByte("result");
		        	if(resul==1)
		        		result[0]++;
		        	else
		        		result[1]++;
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		
		return result;
		
	}

	public Partida crearPartidaAleatoria(Connection conexion, Jugador jugador , String modo, String personaje) {
		Random r = new Random();
        int duracion = r.nextInt(86) + 45; // Duración aleatoria entre 45 y 130 minutos
        boolean victoria = r.nextBoolean();

        
		Partida partida =new Partida(duracion, jugador, null, null, null, victoria, null, duracion);
		partida.setDuracion(duracion);
		partida.setEstadisticas(gestionE.generarEstadisticasAleatorias());
		partida.setFecha(new Date());
		partida.setModo(gestionM.getModoByName(conexion, modo));
		partida.setPersonaje(gestionPJ.getPersonajeByNombre(conexion, personaje));
		partida.setJugador(jugador);
		partida.setResultado(Boolean.valueOf(victoria));
		return partida;
		
	}
	


	
}
