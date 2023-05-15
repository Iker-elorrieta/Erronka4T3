package testMetodos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import manager.GestionModos;
import modelo.Modo;

class GestionModosTest {

	@Test
	void testgetModoById() {
		int id=3;
		Modo modo=GestionModos.getModoById(id);
		assertEquals(modo.getNombre(),"Normal");
	}
	
	@Test
	void testupdateModo() {
		Modo Modo=new Modo(1, "Fuertenocturno");
		GestionModos.updateModo(Modo);
	}
	
	@Test
	void testinsertarModo() {
		Modo modo= new Modo(38, "Fuertenocurno");
		GestionModos.insertarModo(modo);
	}
	
	@Test
	void testeliminarModo() {
		Modo Modo=new Modo(4, "Congelacion");
		ArrayList<Modo> modos= new ArrayList<Modo>();
		modos.add(Modo);
		GestionModos.eliminarModo(Modo, modos);
	}
	@Test
	void testcargaInicialModos() {
		
		
		
	}
}
