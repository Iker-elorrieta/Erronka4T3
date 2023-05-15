package testMetodos;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import manager.GestionEstadisticas;
import manager.GestionPersonajes;
import modelo.Jugador;
import modelo.Personaje;
import utils.ConexionBD;
import utils.DBUtils;

class GestionPersonajesTest {
	private static Connection conexion = null;
    private static GestionEstadisticas gestionEstadisticas;
    GestionPersonajes gestionP = new GestionPersonajes();
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
	void testcargaInicialPersonajes() {
		
		ArrayList<Personaje> personajes = null;
		try {
			personajes = gestionP.cargaInicialPersonajes(conexion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(personajes.size(),4);
	}
	@Test
	void testgetPersonajesById() {
		
		int id=1;
		Personaje personaje=GestionPersonajes.getPersonajeById(conexion, id);
		assertEquals(personaje.getName(),"Ashe");
	}
	@Test
	void testupdatePersonaje() {
		
		Personaje personaje=new Personaje(2, "Ruben", "Tecnico de sistemas", 3, 100, 20, 50, 40);
		GestionPersonajes.updatePersonaje(conexion, personaje);
	}
	@Test
	void testinsertarPersonaje() {
		Personaje personaje=new Personaje(4, "Iker", "Profe de marcas", 3, 100, 20, 50, 50);
		gestionP.insertarPersonaje(conexion, personaje);
	}
	@Test
	void testeliminarPersonaje() {
		
		Personaje personaje=new Personaje(5, "Melendi", "Marine", 3, 100, 20, 50, 60);
		try {
			gestionP.eliminarPersonaje(conexion, personaje.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	void testpersonajeMasJugado() {
		
		Jugador jugador = new Jugador();
		jugador.setId(1);
		jugador.setNombre("Faker");
		ArrayList<String> pers=GestionPersonajes.personajeMasJugado(conexion, jugador);
		assertEquals(pers.get(0),"Faker");
		assertEquals(pers.get(1),"Ruben");
	}
	@Test
	void testhabilidadesDePersonajes() {
		
		ArrayList<String> pers=GestionPersonajes.habilidadesDePersonajes(conexion);
		assertEquals(pers.get(0),"Ganondorf");
		assertEquals(pers.get(1),"Flecha de hielo");
		assertEquals(pers.get(2),"Ashe dispara una flecha que inflige daño y ralentiza al enemigo alcanzado.");
	}
	@Test
	void testbuscarPorHabilidad() {
		
		Personaje pers =GestionPersonajes.buscarPorhabilidad(conexion, 3);
		assertEquals(pers.getName(),"Ruben");
	}
	
	
	@Test
	void getPersonajeByJugadorLvL(){
		
		
		int LVL=1;
		ArrayList<Personaje> personaje = null;
		try {
			 personaje = gestionP.getPersonajeByJugadorLvL(conexion, LVL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		assertEquals(personaje.get(0).getName(),"Ashe");		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

