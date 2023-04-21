package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Administrador;

class AdministradorTest {

	@Test
	void testcomprobarContrasenya() {
		Administrador admin = new Administrador(1,"admin1","hola" );
		String contrasenya1= "hola";
		String contrasenya2= "adios";//
		assertTrue(admin.comprobarContrasenya(contrasenya1));
		assertFalse(admin.comprobarContrasenya(contrasenya2));
	}
	@Test
	void testAdministrador() {
		String nombre= "David";
		String contra= "23";
		int id=0;
		Administrador admin1 = new Administrador(0,"g","i") ;
		admin1.setId(1);
		admin1.setNombre(nombre);
		admin1.setContrasenya(contra);
		assertEquals(admin1.getNombre(),nombre);
		assertEquals(admin1.getContrasenya(),contra);
		
		
	}

}
