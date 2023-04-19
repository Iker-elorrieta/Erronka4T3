package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import modelo.Habilidad;
import modelo.Personaje;

public class testHabilidad {
	
	Personaje Potemkin;
	Personaje Gief;
	
	
	@Test
	void test() {
		String testDesc;
		Personaje testPers;
		String testNomb;
		
		
		Habilidad Grab = new Habilidad("Piledriver", Gief, "360");
		testDesc=Grab.getDescripcion();
		testNomb=Grab.getNombre();
		testPers=Grab.getPersonaje();
		
		assertEquals("Piledriver",testNomb);
		assertEquals("360",testDesc);
		assertEquals(Gief,testPers);
		
		Grab.setDescripcion("6246P");
		Grab.setNombre("Potemkin Buster");
		Grab.setPersonaje(Potemkin);
		testDesc=Grab.getDescripcion();
		testNomb=Grab.getNombre();
		testPers=Grab.getPersonaje();
		
		assertEquals("Potemkin Buster",testNomb);
		assertEquals("6246P",testDesc);
		assertEquals(Potemkin,testPers);
		
	}
}
