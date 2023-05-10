package testMetodos;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import manager.GestionHabilidades;
import modelo.Habilidad;

class GestionHabilidadesTest {

	@Test
	void testgethabilidadesByChampId() {
		int id=1;
		ArrayList<Habilidad>habilidades=GestionHabilidades.getHabilidadesByChampId(id);
		assertEquals(habilidades.size(),0);
		}
	
	@Test
	void testupdateHabilidad() {
		Habilidad habilidad= new Habilidad(2,"Ataque lodo","Una bomba de lodo inunda el campo de batalla");
		GestionHabilidades.updateHabilidad(habilidad);
	}
	
	@Test
	void testinsertarHabilidad() {
		Habilidad habilidad=new Habilidad(14, "Botellazo","Coje una botella y te ataca con ella");
		int cod=2;
		GestionHabilidades.insertarHabilidad(habilidad, cod);
	}
	/*@Test
	void testeliminarHabilidad() {
		Habilidad habilidad=new Habilidad(0, "Lechazo", "Un Ostion que te deja tontorron");
		GestionHabilidades.eliminarHabilidad(habilidad);
	}*/
	@Test
	void testgetHabilidad() {
		ArrayList<Habilidad> habilidades = GestionHabilidades.getHabilidad();
		assertEquals(habilidades.size(),12);
	}
	
}
