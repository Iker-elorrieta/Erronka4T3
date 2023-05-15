package testMetodos;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import manager.GestionModos;
import modelo.Modo;
import utils.ConexionBD;
import utils.DBUtils;

class GestionModosTest {

	@Test
	void testgetModoById() {
		Connection conexion = null;
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int id=3;
		Modo modo=GestionModos.getModoById(conexion, id);
		assertEquals(modo.getNombre(),"Normal");
	}
	
	@Test
	void testupdateModo() {
		Connection conexion = null;
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Modo Modo=new Modo(1, "Fuertenocturno");
		GestionModos.updateModo(conexion, Modo);
	}
	
	@Test
	void testinsertarModo() {
		Connection conexion = null;
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Modo modo= new Modo(38, "Fuertenocurno");
		GestionModos.insertarModo(conexion, modo);
	}
	
	@Test
	void testeliminarModo() {
		Connection conexion = null;
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Modo Modo=new Modo(4, "Congelacion");
		ArrayList<Modo> modos= new ArrayList<Modo>();
		modos.add(Modo);
		GestionModos.eliminarModo(conexion, Modo, modos);
	}
	@Test
	void testcargaInicialModos() {
		
		
		
	}
}
