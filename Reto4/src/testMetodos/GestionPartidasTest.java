package testMetodos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import manager.GestionPartidas;
import modelo.Estadisticas;
import modelo.Jugador;
import modelo.Modo;
import modelo.Partida;
import modelo.Personaje;

class GestionPartidasTest {

	@Test
	void testcargaInicialPartidas() {
		ArrayList<Partida> partidas=GestionPartidas.cargaInicialPartidas();
		assertEquals(partidas.size(),2);

		assertEquals(partidas.size(),4);

	}
	
	@Test
	void testgetPartidasByJugador() {
		ArrayList<Partida> partidas=GestionPartidas.getPartidasByJugador("Fernando");
		assertEquals(partidas.size(),1);
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
		GestionPartidas.insertarPartida(partida);
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
		String nombre="Faker";
		Integer[] resultados=GestionPartidas.sacarResultados(nombre);
		assertEquals(resultados[0],1);
		assertEquals(resultados[1],2);
	}
}
