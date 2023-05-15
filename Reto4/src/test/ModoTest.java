package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Modo;

class ModoTest {

	int id=0;
	String nombre="Aram";
	Modo modo= new Modo(id, nombre);
	
	@Test
	void testModo() {
		assertEquals(modo.getId(),id);
		assertEquals(modo.getNombre(),nombre);
	}
	@Test
	void testpublicModo() {
		Modo modo=new Modo();
		assertEquals(modo.getNombre(),null);
	}
	
	@Test
	void testGetters() {
		assertEquals(modo.getId(),id);
		assertEquals(modo.getNombre(),nombre);
	}
	
	@Test
	void testSetters() {
		int id1=1;
		String nombre1="Normal";
		modo.setId(id1);
		modo.setNombre(nombre1);
		assertEquals(modo.getId(),id1);
		assertEquals(modo.getNombre(),nombre1);
	}
	
	@Test
	void testEquals() {
		 Modo modo1 = new Modo(id, nombre);
	        Modo modo2 = new Modo(id,nombre);
	        Modo modo3 = new Modo(1,nombre);

	        assertTrue(modo1.equals(modo2));
	        assertFalse(modo1.equals(modo3));
	        assertTrue(modo1.equals(modo1));
	        assertFalse(modo1.equals(null));
	        assertFalse(modo1.equals("otro objeto"));
	    }
	}
