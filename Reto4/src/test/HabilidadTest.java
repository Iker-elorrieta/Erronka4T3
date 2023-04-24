package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import modelo.Habilidad;

public class HabilidadTest {
	
	int id=0;
	String nombre="Disparo de flecha";
	String descripcion="Dispara una flecha hacia el objetivo.";
	Habilidad habilidad = new Habilidad(id,nombre,descripcion);
	
    @Test
    public void testConstructorAndGetters() {
        assertEquals(id, habilidad.getCod());
        assertEquals(nombre, habilidad.getNombre());
        assertEquals(descripcion, habilidad.getDescripcion());
    }
    
    @Test
    public void testSetters() {
    	
    	int id1=1;
    	String nombre1="Escudo protector";
    	String descripcion1="Protege al personaje con un escudo mágico.";
    	
    	habilidad.setCod(id1);
    	habilidad.setDescripcion(descripcion1);
    	habilidad.setNombre(nombre1);
    	
    	assertEquals(habilidad.getCod(),id1);
    	assertEquals(habilidad.getNombre(),nombre1);
    	assertEquals(habilidad.getDescripcion(),descripcion1);
    	
    }
    

    @Test
    public void testToString() {
        Habilidad habilidad1 = new Habilidad(2, "Escudo protector", "Protege al personaje con un escudo mágico.");

        String expected = "Habilidad{cod=2, nombre='Escudo protector', descripcion='Protege al personaje con un escudo mágico.'}";
        String actual = habilidad1.toString();

        assertEquals(expected, actual);
    }

	@Test
    public void testEquals() {
    	Habilidad habilidad1 = new Habilidad(1, "Habilidad 1", descripcion);
        Habilidad habilidad2 = new Habilidad(1, "Habilidad 2", descripcion);
        Habilidad habilidad3 = new Habilidad(2, "Habilidad 3", descripcion);
        Habilidad habilidad4 = null;
        String habilidad5 = "Habilidad 1";

        assertTrue("Mismo objeto debería ser igual", habilidad1.equals(habilidad1));
        assertTrue("Mismo código debería ser igual", habilidad1.equals(habilidad2));
        assertFalse("Diferente código debería ser diferente", habilidad1.equals(habilidad3));
        assertFalse("Objeto nulo debería ser diferente", habilidad1.equals(habilidad4));
        assertFalse("Diferente clase debería ser diferente", habilidad1.equals(habilidad5));
    }

}
