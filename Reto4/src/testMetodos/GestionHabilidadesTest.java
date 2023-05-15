package testMetodos;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import manager.GestionHabilidades;
import modelo.Habilidad;
import utils.ConexionBD;
import utils.DBUtils;

class GestionHabilidadesTest {

	@Test
	void testgethabilidadesByChampId() {
		Connection conexion = null;
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int id=2;
		ArrayList<Habilidad>habilidades=GestionHabilidades.getHabilidadesByChampId(conexion ,id);
		assertEquals(habilidades.size(),10);
		}
	
	@Test
	void testupdateHabilidad() {
		Connection conexion = null;
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Habilidad habilidad= new Habilidad(2,"Ataque lodo","Una bomba de lodo inunda el campo de batalla");
		GestionHabilidades.updateHabilidad(conexion, habilidad);
	}
	
	@Test
	void testinsertarHabilidad() {
		Connection conexion = null;
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Habilidad habilidad=new Habilidad(13, "Botellazo","Coje una botella y te ataca con ella");
		int cod=2;
		GestionHabilidades.insertarHabilidad(conexion, habilidad, cod);
	}
	@Test
	void testeliminarHabilidad() {
		Connection conexion = null;
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Habilidad habilidad=new Habilidad(1, "Lechazo", "Un Ostion que te deja tontorron");
		GestionHabilidades.eliminarHabilidad(conexion, habilidad);
	}
	@Test
	void testgetHabilidad() {
		Connection conexion = null;
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Habilidad> habilidades = GestionHabilidades.getHabilidad(conexion);
		assertEquals(habilidades.size(),10);
	}
	
	@Test
	void testgetHabilidadYPersonaje() {
		Connection conexion = null;
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> habilidades = GestionHabilidades.getHabilidadyPersonaje(conexion);
		assertEquals(habilidades.size(),30);
	}
	
}
