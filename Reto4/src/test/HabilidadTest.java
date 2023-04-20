package test;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import modelo.Habilidad;

public class HabilidadTest {
	
	
	@Test
	void test() {
		String testDesc;
		String testNomb;
		
		
		Habilidad Grab = new Habilidad("Piledriver", null, "360");
		testDesc=Grab.getDescripcion();
		testNomb=Grab.getNombre();
		assertEquals("Piledriver",testNomb);
		assertEquals("360",testDesc);
		
		Grab.setDescripcion("6246P");
		Grab.setNombre("Potemkin Buster");
		
		testDesc=Grab.getDescripcion();
		testNomb=Grab.getNombre();
		
		assertEquals("Potemkin Buster",testNomb);
		assertEquals("6246P",testDesc);
		
	}
}
