package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Estadisticas;

class EstadisticasTest {
	
	int kill=0;
	int death=15;
	int assists=45;
	Estadisticas est = new Estadisticas(kill,death,assists);

	@Test
	void testEstadisticas() {
		assertEquals(est.getKills(),kill);
		assertEquals(est.getDeath(),death);
		assertEquals(est.getAssists(),assists);
	}
	@Test
	void testgetKills()
	{
		assertEquals(est.getKills(),kill);
	}
	@Test
	void testgetDeath()
	{
		assertEquals(est.getDeath(),death);
	}
	@Test
	void testgetAssists()
	{
		assertEquals(est.getAssists(),assists);
	}
	
	@Test
	void testsetKills() {
		int kill1=45;
		est.setKills(kill1);
		assertEquals(est.getKills(),kill1);
	}
	
	@Test
	void testsetDeath() {
		int death1=45;
		est.setDeath(death1);
		assertEquals(est.getDeath(),death1);
	}
	
	@Test
	void testsetAssists() {
		int assists1=20;
		est.setAssists(assists1);
		assertEquals(est.getAssists(),assists1);
	}

	@Test
	void testtoString() {
		String String1 ="0/15/45";
		assertEquals(est.toString(),String1);
	}


}
