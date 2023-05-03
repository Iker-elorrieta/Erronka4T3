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

	        // Comprobamos que los objetos con el mismo id son iguales
	        assertTrue(modo1.equals(modo2));

	        // Comprobamos que los objetos con ids diferentes son diferentes
	        assertFalse(modo1.equals(modo3));

	        // Comprobamos que un objeto es igual a sí mismo
	        assertTrue(modo1.equals(modo1));

	        // Comprobamos que un objeto no es igual a null
	        assertFalse(modo1.equals(null));

	        // Comprobamos que un objeto no es igual a un objeto de otra clase
	        assertFalse(modo1.equals("otro objeto"));
	    }
	}
