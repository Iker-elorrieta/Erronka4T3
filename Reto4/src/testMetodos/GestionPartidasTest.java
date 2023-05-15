package testMetodos;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import manager.GestionPartidas;
import modelo.Estadisticas;
import modelo.Jugador;
import modelo.Modo;
import modelo.Partida;
import modelo.Personaje;
import utils.ConexionBD;
import utils.DBUtils;

class GestionPartidasTest {

	@Test
	void testcargaInicialPartidas() {
		Connection conexion = null;
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Partida> partidas=GestionPartidas.cargaInicialPartidas(conexion);
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
		Personaje personaje=new Personaje(2, "Ruben", "Tecnico de sistemas", 4, null, 100, 20, 50, 60);
		Partida partida= new Partida(5, jug, modo, personaje, est, false, date, 40);
	
	}
	
	@Test
	void testeliminarPartida() {
		Jugador jug= new Jugador();
		Date date= new Date();
		Modo modo= new Modo(1, "Clasificatorio");
		Estadisticas est= new Estadisticas(0, 0, 0);
		Personaje personaje=new Personaje(9, "Ruben", "Tecnico de sistemas", 3, null, 100, 20, 50, 60);
		Partida partida= new Partida(1, jug, modo, personaje, est, false, date, 40);
		GestionPartidas.eliminarPartida(partida);
	}
	
	@Test
	void testsacarResultados() {
		Connection conexion = null;
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String nombre="Faker";
		Integer[] resultados=GestionPartidas.sacarResultados(conexion, nombre);
		assertEquals(resultados[0],1);
		assertEquals(resultados[1],2);
	}
}
