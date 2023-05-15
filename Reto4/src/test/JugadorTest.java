package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

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
	Date fechaReg= new Date() ;
	boolean bloqueado=false;
	Jugador jug1 = new Jugador(id, nombre, contra, nivel, rango, fechaReg, bloqueado);
	
	@Test
	void testJugador() {
		
		assertEquals(jug1.getNombre(),nombre);
		assertEquals(jug1.getContrasenya(),contra);
		assertEquals(jug1.getNivel(),nivel);
		assertEquals(jug1.getRango(),rango);
		assertEquals(jug1.getFecha(),fechaReg);
		assertEquals(jug1.getId(),id);
		Jugador jug2= new Jugador();
		assertEquals(jug2.getContrasenya(),null);
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
	void testsetFecha() {
		Date fechaReg1= new Date();
		jug1.setFecha(fechaReg1);
		assertEquals(jug1.getFecha(),fechaReg1);
	}
	
	@Test
	void testgetFecha() {
		assertEquals(jug1.getFecha(),fechaReg);
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
	@Test
	void testStringfecha() {
		Date date = new Date();
		Jugador jugador=new Jugador();
		jugador.setFecha(date);
		String fecha=jugador.StringFecha();
		assertEquals(fecha,"2023-05-15");
	}
	
}
