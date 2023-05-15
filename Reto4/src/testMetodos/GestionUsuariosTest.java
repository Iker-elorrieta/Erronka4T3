package testMetodos;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import exceptions.PlayerNotFoundException;
import manager.GestionEstadisticas;
import manager.GestionUsuarios;
import modelo.Administrador;
import modelo.Jugador;
import utils.ConexionBD;
import utils.DBUtils;

class GestionUsuariosTest {
	private static Connection conexion;
	GestionUsuarios gestionU = new GestionUsuarios();
	
	 @BeforeClass
	    public static void setUp() {

			try {
				conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	@Test
	void testcargaInicialJugadores() {
		

	}
	//1
	@Test
	void testgetJugadorByNombre() {
		Jugador jug1=gestionU.getJugadorByNombre(null, "Faker");
		assertEquals(jug1.getNombre(),"Faker");
	}
	//1
	@Test
	void testlogin() throws PlayerNotFoundException {
			gestionU.login(null, "Faker","fakerpass");
			gestionU.login(null, "gaizka", "gaizkacontra");
	}
	//2
	@Test
	void testactualizarJugador() {
		Date fecha= new Date();
		Jugador jugador=new Jugador(2, "Fernando", "123",4, "Maestro", fecha, false);
		
		assertEquals(gestionU.getJugadorByNombre(null, "Fernando").getNivel(),4);
	}
	//3
	@Test
	void testcambiarEstadoBloqueo() {
		Date date= new Date();
		Jugador jug= new Jugador(2, "Fernando", "fakerpassguord", 0, "Maestro", date,false);
		boolean bloqueado=true;
	
		Jugador jug1=gestionU.getJugadorByNombre(null, "Fernando");
		assertEquals(jug1.isBloqueado(),true);
	}
	@Test
	void testcambiarBloqueo() {
		Date date= new Date();
		Jugador jug= new Jugador(4, "Mamita", "caramelo", 0, "Maestro", date, false);
		Jugador jug1= new Jugador(2, "jonhdoe", "lol", 0, "Maestro", date, false);
		ArrayList<Jugador> jugadores= new ArrayList<Jugador>();
		jugadores.add(jug);
		jugadores.add(jug1);
		int id=4;
		boolean bloqueo=true;

	}
	//5
	@Test
	void testinsertarUsuario() {
		Date date=new Date();
		Jugador jug= new Jugador(5,"Leon","2345",3,"rol",date,false);
		ArrayList<Jugador> jugadores= new ArrayList<Jugador>();
	
	}
	//3
	@Test
	void testeliminarAdministrador() {
		Administrador administrador=new Administrador(3, null, null);
		ArrayList<Administrador> Administradores=new ArrayList<Administrador>();
		Administradores.add(administrador);
	
	}
	//4
	@Test
	void testeliminarJugador() {
		Jugador Jugador=new Jugador();
		Jugador.setId(3);
	
	}
}
