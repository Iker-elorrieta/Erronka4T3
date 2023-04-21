package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import modelo.Estadisticas;
import modelo.Jugador;
import modelo.Partida;
import modelo.Personaje;
import utils.Modos;

class PartidaTest {
	
	int cod1=0;
	Jugador jug1 = new Jugador(null, null, null, 0, null, null, cod1);
	String hola = "Aram";
	Modos modo= Modos.Aram;
	Personaje pers1 = new Personaje(1, "Aatrox", "Fighter", 3, null, 60, 0, 580, 0, 0);
	Estadisticas est1 = new Estadisticas(0, 0, 0);
	boolean resul1 = true;
	Date date1=new Date();   
	Partida partida1 = new Partida(cod1, jug1, modo, pers1, est1, resul1, date1);

	@Test
	void testPartida() {
		
		assertEquals(partida1.getCod_partida(),cod1);
		assertEquals(partida1.getJugador(),jug1);
		assertEquals(partida1.getModo(),modo);
		assertEquals(partida1.getPersonaje(),pers1);
		assertEquals(partida1.getEstadisticas(),est1);
		assertEquals(partida1.isResultado(),resul1);
		assertEquals(partida1.getFecha(),date1);
	}
	
	@Test
	void testgetCod_partida() {
		assertEquals(partida1.getCod_partida(),cod1);
	}
	
	@Test
	void testgetJugador() {
		assertEquals(partida1.getJugador(),jug1);
	}
	
	@Test
	void testgetModo() {
		assertEquals(partida1.getModo(),modo);
	}
	
	@Test
	void testgetPersonaje() {
		assertEquals(partida1.getPersonaje(),pers1);
	}
	
	@Test
	void testgetEstadisticas() {
		assertEquals(partida1.getEstadisticas(),est1);
	}
	
	@Test
	void testisResultado() {
		assertEquals(partida1.isResultado(),resul1);
	}
	
	@Test
	void testgetFecha() {
		assertEquals(partida1.getFecha(),date1);
	}

	@Test
	void testsetCod_partida() {
		int cod2=69;
		partida1.setCod_partida(cod2);
		assertEquals(partida1.getCod_partida(),cod2);
	}
	
	@Test
	void testsetJugador() {
		Jugador jug2 = new Jugador(null, null, null, 1, null, null, cod1);
		partida1.setJugador(jug2);
		assertEquals(partida1.getJugador(),jug2);
	}
	
	@Test
	void testsetModo() {
		Modos modo2= Modos.Normal;
		partida1.setModo(modo2);
		assertEquals(partida1.getModo(),modo2);
	}
	
	@Test
	void testsetPersonaje() {
		Personaje pers2 = new Personaje(1, "Aatrox", "Fighter", 3, null, 60, 0, 580, 0, 0);
		partida1.setPersonaje(pers2);
		assertEquals(partida1.getPersonaje(),pers2);
	}
	
	@Test
	void testsetEstadisticas() {
		Estadisticas est2 = new Estadisticas(0, 0, 0);
		partida1.setEstadisticas(est2);
		assertEquals(partida1.getEstadisticas(),est2);
	}
	
	@Test
	void testsetResultado() {
		boolean resul2=false;
		partida1.setResultado(resul2);
		assertEquals(partida1.isResultado(),resul2);
	}
	
	@Test
	void testsetFecha() {
		Date date2=new Date();   
		partida1.setFecha(date2);
		assertEquals(partida1.getFecha(),date2);
	}
	
	@Test
	void testVictoria() {
		String victoria= "Victoria";
		String derrota="Derrota";
		assertEquals(partida1.victoria(),victoria);
		partida1.setResultado(false);
		assertEquals(partida1.victoria(),derrota);
	}
	
	@Test
	void testtoString() {
	 String enviar= "Victoria  Aram  Aatrox  0/0/0  "+date1;
	 assertEquals(partida1.toString(),enviar);
	}
	
	@Test
	void testequals() {
	        Partida partida2 = new Partida(2, jug1, modo, pers1, est1, resul1, date1);
	        Partida partida3 = new Partida(1, jug1, modo, pers1, est1, resul1, date1);
	        
	        assertTrue(partida1.equals(partida1));
	        assertFalse(partida1.equals(null));
	        assertFalse(partida1.equals(partida2));
	        assertFalse(partida1.equals(partida3));
	        assertFalse(partida1.equals("hola"));
	        	    
	}
	
}
