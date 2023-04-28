package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import modelo.Jugador;
import modelo.Partida;
import modelo.Personaje;

class JugadorTest {

	ArrayList<Personaje> personajes= new ArrayList<Personaje>();
	ArrayList<Partida> partidas= new ArrayList<Partida>();
	String nombre = "David";
	String contra = "mango123";
	String rango = "Maestro";
	int nivel = 69;
	int id=0;   
	boolean bloqueado=false;
	Jugador jug1 = new Jugador(nombre, contra, rango, nivel, personajes, partidas,id, null, bloqueado);
	
	@Test
	void testJugador() {
		
		assertEquals(jug1.getNombre(),nombre);
		assertEquals(jug1.getContrasenya(),contra);
		assertEquals(jug1.getPartidasRecientes(),partidas);
		assertEquals(jug1.getPersonajes(),personajes);
		assertEquals(jug1.getNivel(),nivel);
		assertEquals(jug1.getRango(),rango);
		assertEquals(jug1.getId(),id);
		Jugador jug2= new Jugador(contra, contra, contra, id, personajes, partidas, id, null, bloqueado);
		assertEquals(jug2.getContrasenya(),null);
	}
	
	@Test
	void testanyadirPartidas() {
		Partida partida1 = new Partida(0, jug1, null, null, null, false, null, 0);
		jug1.anyadirPartidas(partida1);
		assertEquals(jug1.getPartidasRecientes().get(0),partida1);
		
		Partida partida2 = new Partida(1, jug1, null, null, null, false, null, 0);
		jug1.anyadirPartidas(partida2);
		jug1.anyadirPartidas(partida2);
		jug1.anyadirPartidas(partida2);
		jug1.anyadirPartidas(partida2);
		jug1.anyadirPartidas(partida1);
		
		assertEquals(jug1.getPartidasRecientes().get(0),partida2);
		assertEquals(jug1.getPartidasRecientes().get(4),partida1);
	}
	
	@Test
	void testgetRango() {
		assertEquals(jug1.getRango(),rango);
	}
	
	@Test
	void testsetRango() {
		String rango2 ="Amateur";
		jug1.setRango(rango2);
		assertEquals(jug1.getRango(),rango2);
	}
	
	@Test
	void testgetNivel() {
		assertEquals(jug1.getNivel(),nivel);
	}
	
	@Test
	void testsetNivel() {
		int nivel2 = 2;
		jug1.setNivel(nivel2);
		assertEquals(jug1.getNivel(),nivel2);
	}
	
	@Test
	void testgetPersonajes() {
		assertEquals(jug1.getPersonajes(),personajes);
	}
	
	@Test
	void testsetPersonajes() {
		ArrayList<Personaje> personajes2= new ArrayList<Personaje>();
		jug1.setPersonajes(personajes2);
		assertEquals(jug1.getPersonajes(),personajes2);
	}
	
	@Test
	void testgetPartidasrecientes() {
		assertEquals(jug1.getPartidasRecientes(),partidas);
	}
	
	@Test
	void testsetPartidasRecientes() {
		ArrayList<Partida> partidas2= new ArrayList<Partida>();
		jug1.setPartidasRecientes(partidas2);
		assertEquals(jug1.getPartidasRecientes(),partidas2);
	}

	@Test
	void testsubidaNivel() {
		Partida partida1 = new Partida(0, jug1, null, null, null, true, null, 0);
		jug1.anyadirPartidas(partida1);
		jug1.subidaNivel();
		assertEquals(jug1.getNivel(),nivel+1);
	}
	
	@Test
	void testcomprobarContrasenya() {
		String contra2= "adios";
		assertTrue(jug1.comprobarContrasenya(contra));
		assertFalse(jug1.comprobarContrasenya(contra2));
	}
	
	@Test
	void testsetbloqueado() {
		boolean bloqueado2=true;  

		jug1.setBloqueado(bloqueado2);
		assertEquals(jug1.isbloqueado(),bloqueado2);

		jug1.setBloqueado(bloqueado2);
		assertEquals(jug1.isBloqueado(),bloqueado2);

	}
	
	@Test
	void testbloqueado() {
		assertEquals(jug1.isBloqueado(),bloqueado);

	}
	
	@Test
	void testSaludo() {
		assertEquals(jug1.Saludo(),"Buenas, saludos "+jug1.getNombre()+" ,bienvenido al mundo de LOL");
	}
	
	
}
