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
		ArraylistGestionPersonajes.personajeMasJugado()
	}

}


