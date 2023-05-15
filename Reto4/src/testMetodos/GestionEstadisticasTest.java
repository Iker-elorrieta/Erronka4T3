package testMetodos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import manager.GestionEstadisticas;
import modelo.Estadisticas;
import utils.ConexionBD;
import utils.DBUtils;

public class GestionEstadisticasTest {
	private static Connection conexion = null;
    private static GestionEstadisticas gestionEstadisticas;
    
    @BeforeClass
    public static void setUp() {
        gestionEstadisticas = new GestionEstadisticas();
        
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void testObtenerEstadistica() {
        String estadistica = "5/2/3";
        Estadisticas resultado = gestionEstadisticas.obtenerEstadistica(estadistica);
        
        assertNotNull(resultado);
        assertEquals(5, resultado.getKills());
        assertEquals(2, resultado.getDeath());
        assertEquals(3, resultado.getAssists());
    }
    
    @Test
    public void testGenerarEstadisticasAleatorias() {
        Estadisticas resultado = gestionEstadisticas.generarEstadisticasAleatorias();
        
        assertNotNull(resultado);
        // Verificar que los valores estén dentro del rango esperado
        assertTrue(resultado.getKills() >= 1 && resultado.getKills() <= 15);
        assertTrue(resultado.getDeath() >= 0 && resultado.getDeath() <= 5);
        assertTrue(resultado.getAssists() >= 4 && resultado.getAssists() <= 20);
    }
    
    // Aquí podrías agregar más tests para otros métodos de la clase GestionEstadisticas
    
    @Test
    public void testEstadisticasJugador() {
        // Asegúrate de tener datos de prueba en la base de datos
        String jugador = "Fake";
        
        Estadisticas resultado = GestionEstadisticas.estadisticasJugador(conexion, jugador);
        
        assertNotNull(resultado);
        // Verificar que los valores sean los esperados
        // ...
    }
}
