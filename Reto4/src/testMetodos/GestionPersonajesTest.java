package testMetodos;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import manager.GestionPersonajes;
import modelo.Jugador;
import modelo.Personaje;
import utils.ConexionBD;
import utils.DBUtils;

class GestionPersonajesTest {

	@Test
	void testcargaInicialPersonajes() {
		Connection conexion = null;
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Personaje> personajes =GestionPersonajes.cargaInicialPersonajes(conexion);
		assertEquals(personajes.size(),4);
	}
	@Test
	void testgetPersonajesById() {
		Connection conexion = null;
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int id=1;
		Personaje personaje=GestionPersonajes.getPersonajeById(conexion, id);
		assertEquals(personaje.getName(),"Ashe");
	}
	@Test
	void testupdatePersonaje() {
		Connection conexion = null;
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Personaje personaje=new Personaje(2, "Ruben", "Tecnico de sistemas", 3, null, 100, 20, 50, 40);
		GestionPersonajes.updatePersonaje(conexion, personaje);
	}
	@Test
	void testinsertarPersonaje() {
		Personaje personaje=new Personaje(4, "Iker", "Profe de marcas", 3, null, 100, 20, 50, 50);
		GestionPersonajes.insertarPersonaje(personaje);
	}
	@Test
	void testeliminarPersonaje() {
		Connection conexion = null;
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Personaje personaje=new Personaje(5, "Melendi", "Marine", 3, null, 100, 20, 50, 60);
		GestionPersonajes.eliminarPersonaje(conexion, personaje);
	}
	@Test
	void testpersonajeMasJugado() {
		Connection conexion = null;
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Jugador jugador = new Jugador();
		jugador.setId(1);
		jugador.setNombre("Faker");
		ArrayList<String> pers=GestionPersonajes.personajeMasJugado(conexion, jugador);
		assertEquals(pers.get(0),"Faker");
		assertEquals(pers.get(1),"Ruben");
	}
	@Test
	void testhabilidadesDePersonajes() {
		Connection conexion = null;
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> pers=GestionPersonajes.habilidadesDePersonajes(conexion);
		assertEquals(pers.get(0),"Ganondorf");
		assertEquals(pers.get(1),"Flecha de hielo");
		assertEquals(pers.get(2),"Ashe dispara una flecha que inflige daño y ralentiza al enemigo alcanzado.");
	}
	@Test
	void testbuscarPorHabilidad() {
		Connection conexion = null;
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Personaje pers =GestionPersonajes.buscarPorhabilidad(conexion, 3);
		assertEquals(pers.getName(),"Ruben");
	}
}

