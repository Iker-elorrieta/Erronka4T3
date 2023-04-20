package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import modelo.Habilidad;

public class HabilidadTest {

    @Test
    public void testConstructorAndGetters() {
        Habilidad habilidad = new Habilidad(1, "Disparo de flecha", "Dispara una flecha hacia el objetivo.");

        assertEquals(1, habilidad.getCod());
        assertEquals("Disparo de flecha", habilidad.getNombre());
        assertEquals("Dispara una flecha hacia el objetivo.", habilidad.getDescripcion());
    }

    @Test
    public void testToString() {
        Habilidad habilidad = new Habilidad(2, "Escudo protector", "Protege al personaje con un escudo mágico.");

        String expected = "Habilidad{cod=2, nombre='Escudo protector', descripcion='Protege al personaje con un escudo mágico.'}";
        String actual = habilidad.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void testEquals() {
        Habilidad habilidad1 = new Habilidad(3, "Salto ágil", "El personaje realiza un salto ágil.");
        Habilidad habilidad2 = new Habilidad(3, "Salto ágil", "El personaje realiza un salto ágil.");
        Habilidad habilidad3 = new Habilidad(4, "Estallido final", "El personaje libera una gran explosión mágica.");

        assertEquals(habilidad1, habilidad2);
        assertEquals(habilidad2, habilidad1);

        assertEquals(habilidad2, habilidad2);
        assertEquals(habilidad2, habilidad1);
       

        assertEquals(habilidad1.equals(habilidad3), false);
    }

}
