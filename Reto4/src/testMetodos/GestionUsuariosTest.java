package testMetodos;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import exceptions.PlayerNotFoundException;
import manager.GestionUsuarios;
import modelo.Administrador;
import modelo.Jugador;

class GestionUsuariosTest {
	
	@Test
	void testcargaInicialJugadores() {
		ArrayList<Jugador>metodo=GestionUsuarios.cargaInicialJugadores();
		assertEquals(2,metodo.get(0).getId());
	}
	@Test
	void testgetJugadorByNombre() {
		Jugador jug1=GestionUsuarios.getJugadorByNombre("Faker");
		assertEquals(jug1.getNombre(),"Faker");
	}
	
	@Test
	void testlogin() throws PlayerNotFoundException {
			GestionUsuarios.login("Faker","fakerpass");
			GestionUsuarios.login("gaizka", "gaizkacontra");
	}
	
	@Test
	void testactualizarJugador() {
		Date fecha= new Date();
		Jugador jugador=new Jugador(2, "David", "123",4, "Maestro", fecha, false);
		GestionUsuarios.actualizarJugador(jugador);
	}
	@Test
	void testcambiarEstadoBloqueo() {
		Date date= new Date();
		Jugador jug= new Jugador(2, "Faker", "fakerpass", 0, "Maestro", date, true);
		boolean bloqueado=false;
		GestionUsuarios.cambiarEstadoBloqueo(jug, bloqueado);
		Jugador jug1=GestionUsuarios.getJugadorByNombre("Faker");
		assertEquals(jug1.isbloqueado(),false);
		
	}
	@Test
	void testcambiarBloqueo() {
		Date date= new Date();
		Jugador jug= new Jugador(1, "Faker", "fakerpass", 0, "Maestro", date, false);
		Jugador jug1= new Jugador(1, "jonhdoe", "lol", 0, "Maestro", date, false);
		ArrayList<Jugador> jugadores= new ArrayList<Jugador>();
		jugadores.add(jug);
		jugadores.add(jug1);
		int id=1;
		boolean bloqueo=true;
		GestionUsuarios.cambiarBloqueo(jugadores, id, bloqueo);
	}
	@Test
	void testinsertarUsuario() {
		Date date=new Date();
		Jugador jug= new Jugador(4,"Leon","2345",3,"rol",date,false);
		ArrayList<Jugador> jugadores= new ArrayList<Jugador>();
		GestionUsuarios.insertarUsuario(jug, jugadores);
	}
	/*@Test
	void testeliminarAdministrador() {
		Administrador administrador=new Administrador(1, null, null);
		ArrayList<Administrador> Administradores=new ArrayList<Administrador>();
		Administradores.add(administrador);
		GestionUsuarios.eliminarAdministrador(administrador, Administradores);
	}
	@Test
	void testeliminarJugador() {
		Jugador Jugador=new Jugador();
		Jugador.setId(1);
		GestionUsuarios.eliminarJugador(Jugador);
	}*/
}
