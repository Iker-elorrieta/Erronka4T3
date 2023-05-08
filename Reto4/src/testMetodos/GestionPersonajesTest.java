package testMetodos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import manager.GestionPersonajes;
import modelo.Personaje;

class GestionPersonajesTest {

	@Test
	void testcargaInicialPersonajes() {
		ArrayList<Personaje> personajes =GestionPersonajes.cargaInicialPersonajes();
		assertEquals(personajes.size(),4);
	}

	@Test
	void testgetPersonajesById() {
		int id=1;
		Personaje personaje=GestionPersonajes.getPersonajeById(id);
		assertEquals(personaje.getName(),"Ashe");
	}
	@Test
	void testgetPErsonajeByJugadorLvL() {
		int lvl=20;
		ArrayList<Personaje> personajes = GestionPersonajes.getPersonajeByJugadorLvL(lvl);
		assertEquals(personajes.size(),2);
	}
	@Test
	void testupdatePersonaje() {
		Personaje personaje=new Personaje(1, "Ruben", "Tecnico de sistemas", 3, null, 100, 20, 50, 60);
		GestionPersonajes.updatePersonaje(personaje);
	}
	
	@Test
	void testinsertarPersonaje() {
		Personaje personaje=new Personaje(9, "Ruben", "Tecnico de sistemas", 3, null, 100, 20, 50, 60);
		GestionPersonajes.insertarPersonaje(personaje);
	}
	
	@Test
	void testeliminarPersonaje() {
		Personaje personaje=new Personaje(1, "Ruben", "Tecnico de sistemas", 3, null, 100, 20, 50, 60);
		GestionPersonajes.eliminarPersonaje(personaje);
}}
