package testMetodos;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import manager.GestionEstadisticas;
import manager.GestionHabilidades;
import modelo.Habilidad;
import utils.ConexionBD;
import utils.DBUtils;

class GestionHabilidadesTest {
	public GestionHabilidades gestionH = new GestionHabilidades();
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
	void testgethabilidadesByChampId() {
	
		
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
		
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Habilidad habilidad= new Habilidad(2,0, "Ataque lodo","Una bomba de lodo inunda el campo de batalla");
		GestionHabilidades.updateHabilidad(conexion, habilidad);
	}
	
	@Test
	void testinsertarHabilidad() {
		
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Habilidad habilidad=new Habilidad(13, 0, "Botellazo","Coje una botella y te ataca con ella");
		int cod=2;
		gestionH.insertarHabilidad(conexion, habilidad);
	}
	@Test
	void testeliminarHabilidad() {
		
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Habilidad habilidad=new Habilidad(1, 0, "Lechazo", "Un Ostion que te deja tontorron");
		try {
			gestionH.eliminarHabilidad(conexion, habilidad.getCod());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	void testgetHabilidad() {
		
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
