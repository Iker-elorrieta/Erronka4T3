package testMetodos;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import manager.GestionEstadisticas;
import manager.GestionModos;
import modelo.Modo;
import utils.ConexionBD;
import utils.DBUtils;

class GestionModosTest {
	GestionModos gestionM = new GestionModos();
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
	void testgetModoById() {
		
		
		int id=3;
		Modo modo = null;
		try {
			modo = gestionM.getModoById(conexion, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(modo.getNombre(),"Normal");
	}
	
	@Test
	void testupdateModo() {
		
		
		Modo Modo=new Modo(1, "Fuertenocturno");
		gestionM.updateModo(conexion, Modo);
	}
	
	@Test
	void testinsertarModo() {
		
		Modo modo= new Modo(38, "Fuertenocurno");
		gestionM.insertarModo(conexion, modo);
	}
	
	@Test
	void testeliminarModo() {
		
		Modo Modo=new Modo(4, "Congelacion");
		ArrayList<Modo> modos= new ArrayList<Modo>();
		modos.add(Modo);
		try {
			gestionM.eliminarModo(conexion, Modo.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		
}
