package testMetodos;

import java.util.Date;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import modelo.Jugador;

class metodosTest {

	@Test
	void testinicioSesionTXT() {
		Date date= new Date();
		Jugador jugador= new Jugador(4, "Moranco", "1267", 4, "Aprendiz", date, false);
		Metodos.inicioSesionTXT(jugador);
	}

}