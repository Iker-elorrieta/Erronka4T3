package testMetodos;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import manager.GestionEstadisticas;
import manager.GestionPartidas;
import modelo.Estadisticas;
import modelo.Jugador;
import modelo.Modo;
import modelo.Partida;
import modelo.Personaje;
import utils.ConexionBD;
import utils.DBUtils;

class GestionPartidasTest {
	GestionPartidas gestionP = new GestionPartidas();
	private static Connection conexion = null;
    private static GestionEstadisticas gestionEstadisticas;
    
    @BeforeClass
    public static void setUp() {
        gestionEstadisticas = new GestionEstadisticas();
        
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	@Test
	void testcargaInicialPartidas() {
				ArrayList<Partida> partidas = null;
		try {
			partidas = gestionP.cargaInicialPartidas(conexion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(partidas.size(),3);

	}
	
	@Test
	void testgetPartidasByJugador() {
		
	
		
	}
	
	@Test
	void testinsertarPartida() {
		Jugador jug= new Jugador();
		jug.setId(1);
		Date date= new Date();
		Modo modo= new Modo(1, "Aram");
		Estadisticas est= new Estadisticas(0, 0, 0);
		Personaje personaje=new Personaje(2, "Ruben", "Tecnico de sistemas", 4, 100, 20, 50, 60);
		Partida partida= new Partida(5, jug, modo, personaje, est, false, date, 40);
	
	}
	
	@Test
	void testeliminarPartida() {
		Connection conexion = null;
		Jugador jug= new Jugador();
		Date date= new Date();
		Modo modo= new Modo(1, "Clasificatorio");
		Estadisticas est= new Estadisticas(0, 0, 0);
		Personaje personaje=new Personaje(9, "Ruben", "Tecnico de sistemas", 3, 100, 20, 50, 60);
		Partida partida= new Partida(1, jug, modo, personaje, est, false, date, 40);
		try {
			gestionP.eliminarPartida(conexion,1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testsacarResultados() {
		
		String nombre="Faker";
		Integer[] resultados=GestionPartidas.sacarResultados(conexion, nombre);
		assertEquals(resultados[0],1);
		assertEquals(resultados[1],2);
	}
}
