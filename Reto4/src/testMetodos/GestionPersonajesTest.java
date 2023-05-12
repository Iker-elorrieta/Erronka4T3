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
	void testupdatePersonaje() {
		Personaje personaje=new Personaje(2, "Ruben", "Tecnico de sistemas", 3, null, 100, 20, 50, 40);
		GestionPersonajes.updatePersonaje(personaje);
	}
	@Test
	void testinsertarPersonaje() {
		Personaje personaje=new Personaje(4, "Iker", "Profe de marcas", 3, null, 100, 20, 50, 50);
		GestionPersonajes.insertarPersonaje(personaje);
	}
	@Test
	void testeliminarPersonaje() {
		Personaje personaje=new Personaje(5, "Melendi", "Marine", 3, null, 100, 20, 50, 60);
		GestionPersonajes.eliminarPersonaje(personaje);
	}
	@Test
	void testpersonajeMasJugado() {
		ArrayList<String> pers=GestionPersonajes.personajeMasJugado();
		assertEquals(pers.get(0),"Faker");
		assertEquals(pers.get(1),"Ashe");
	}
	@Test
	void testhabilidadesDePersonajes() {
		ArrayList<String> pers=GestionPersonajes.habilidadesDePersonajes();
		assertEquals(pers.get(0),"Ganondorf");
		assertEquals(pers.get(1),"Flecha de hielo");
		assertEquals(pers.get(2),"Ashe dispara una flecha que inflige daño y ralentiza al enemigo alcanzado.");
	}
	@Test
	void testbuscarPorHabilidad() {
		Personaje pers =GestionPersonajes.buscarPorhabilidad(3);
		assertEquals(pers.getName(),"Ruben");
	}
}

