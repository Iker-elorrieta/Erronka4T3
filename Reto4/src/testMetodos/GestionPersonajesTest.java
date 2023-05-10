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
		assertEquals(personajes.size(),3);
	}

	@Test
	void testgetPersonajesById() {
		int id=1;
		Personaje personaje=GestionPersonajes.getPersonajeById(id);
		assertEquals(personaje.getName(),"Ruben");
	}
	@Test
	void testupdatePersonaje() {
		Personaje personaje=new Personaje(1, "Ruben", "Tecnico de sistemas", 3, null, 100, 20, 50, 40);
		GestionPersonajes.updatePersonaje(personaje);
	}
	
	@Test
	void testinsertarPersonaje() {
		Personaje personaje=new Personaje(9, "Ruben", "Tecnico de maras", 3, null, 100, 20, 50, 50);
		GestionPersonajes.insertarPersonaje(personaje);
	}
	
	/*@Test
	void testeliminarPersonaje() {
		Personaje personaje=new Personaje(1, "Ruben", "Tecnico de program", 3, null, 100, 20, 50, 60);
		GestionPersonajes.eliminarPersonaje(personaje);
}*/}
