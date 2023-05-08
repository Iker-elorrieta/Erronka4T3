package vista;

import controlador.metodos;
import modelo.Jugador;

public class probar {

	public static void main(String[] args) {
		Jugador jug=new Jugador();
		jug.setNombre("Juan");
		metodos.inicioSesionTXT(jug);
	}

}
