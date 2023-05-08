package testMetodos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import manager.GestionEstadisticas;
import modelo.Estadisticas;

class GestionEstadisticasTest {

	@Test
	void testobtenerEstadisticas() {
		String estadistica="4/5/9";
		Estadisticas estadisticas=GestionEstadisticas.obtenerEstadistica(estadistica);
		assertEquals(estadisticas.getKills(),4);
		assertEquals(estadisticas.getAssists(),5);
		assertEquals(estadisticas.getDeath(),9);
	}

	@Test
	void testgenerarEstadisticasAleatorias() {
		Estadisticas est= GestionEstadisticas.generarEstadisticasAleatorias();
		 assertTrue(est.getKills() >= 1 && est.getKills() <= 15);
	     assertTrue(est.getDeath() >= 0 && est.getDeath() <= 6);
	     assertTrue(est.getAssists() >= 4 && est.getAssists() <= 20);
		
	}
}
