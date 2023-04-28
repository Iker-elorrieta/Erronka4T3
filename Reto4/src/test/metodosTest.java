package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controlador.metodos;
import modelo.Jugador;

class metodosTest {

	@Test
	void testcargaInicialJugadores() {
		metodos met = new metodos();
		ArrayList<Jugador> jugadores= met.cargaInicialJugadores();
		assertEquals(jugadores,jugadores);
		
	}
	
	@Test
	void testiniciarSesionUsuarios() {
		metodos met = new metodos();
		String nombre="Faker";
		String contrasenya="fakerpass";
		String nombre1="David";
		String contrasenya1="12345";
		
		Jugador jugador=metodos.iniciarSesionUsuarios(nombre, contrasenya);
		assertEquals(jugador,jugador);
		Jugador jugador1 = metodos.iniciarSesionUsuarios(nombre1, contrasenya1);
		assertEquals(jugador1, null);
		
		
	}
	

}
