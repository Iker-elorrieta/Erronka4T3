package testMetodos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import modelo.Jugador;
import utils.ConexionBD;
import utils.DBUtils;

class metodosTest {

	@Test
	void testinicioSesionTXT() {
		Date date= new Date();
		Jugador jugador= new Jugador(4, "Moranco", "1267", 4, "Aprendiz", date, false);
		Metodos.inicioSesionTXT(jugador);
	}

	@Test
	void testconexionBDUpdate() {
		Connection conexion = null;
		try {
			conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String update="UPDATE players SET name='Manolito' WHERE id='2'";
		Metodos.conexionBDUpdate(conexion, update);
	}
}
