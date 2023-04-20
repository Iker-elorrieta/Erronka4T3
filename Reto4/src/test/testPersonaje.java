package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import modelo.Habilidad;
import modelo.Personaje;

public class testPersonaje {
	
	Habilidad Piledriver = new Habilidad("Piledriver", null, "360Lp");
	Habilidad Spinning_lariat = new Habilidad("Spinning_lariat", null, "Lp-Mp-Hp");
	Habilidad Siverian_storm = new Habilidad("Siverian_storm", null, "214Lp");
	
	ArrayList<Habilidad>habilidades= new ArrayList<Habilidad>();
	
	@Test
	void test() {
		
		String testNom = "Gief";
		int testMae = 100;
		habilidades.add(Piledriver);
		habilidades.add(Spinning_lariat);
		String testCat = "Grappler";
		int testPar = 10;
		int testUsu = 928;
		
		Personaje Gief=new Personaje("Axl", 80, habilidades, "Zoner", 3, 123);
		
		Gief.setNombre(testNom);
		assertEquals(Gief.getNombre(),testNom);
		
		Gief.setMaestria(testMae);
		assertEquals(Gief.getMaestria(),testMae);
		
		habilidades.add(Siverian_storm);
		Gief.setHabilidades(habilidades);
		assertEquals(Gief.getHabilidades(),habilidades);
		
		Gief.setCategoria(testCat);
		assertEquals(Gief.getCategoria(),testCat);
		
		Gief.setPartidas(testPar);
		assertEquals(Gief.getPartidas(),testPar);
	
		Gief.setUsuario(testUsu);
		assertEquals(Gief.getUsuario(),testUsu);
		
		Gief.partidaJugada(true);
		assertEquals(Gief.getPartidas(),testPar+1);
		assertEquals(Gief.getMaestria(),testMae+100);
		
		Gief.partidaJugada(false);
		assertEquals(Gief.getPartidas(),testPar+2);
		assertEquals(Gief.getMaestria(),testMae+120);
	}
}
