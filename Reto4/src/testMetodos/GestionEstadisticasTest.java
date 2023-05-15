package testMetodos;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import manager.GestionEstadisticas;
import modelo.Estadisticas;
import utils.ConexionBD;
import utils.DBUtils;



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
	
	@Test
	void testestadisticasJugador() {
		Connection conexion = null;
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String nombre="Faker";
		Estadisticas est=GestionEstadisticas.estadisticasJugador(conexion, nombre);
		assertEquals(est.getKills(),2);
	}
}
