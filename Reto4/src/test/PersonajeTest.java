package test;

import org.junit.jupiter.api.Test;

import modelo.Habilidad;
import modelo.Personaje;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class PersonajeTest {

    @Test
    public void testConstructorAndGetters() {
        ArrayList<Habilidad> abilities = new ArrayList<>();
        abilities.add(new Habilidad(1, "Habilidad Q", "Descripcion de Habilidad Q"));
        abilities.add(new Habilidad(2, "Habilidad W", "Descripcion de Habilidad W"));
        abilities.add(new Habilidad(3, "Habilidad E", "Descripcion de Habilidad E"));
        abilities.add(new Habilidad(4, "Habilidad R", "Descripcion de Habilidad R"));

        Personaje champion = new Personaje(1, "Aatrox", "Fighter", 3, abilities, 60, 0, 580, 0);
        assertEquals(1, champion.getId());
        assertEquals("Aatrox", champion.getName());
        assertEquals("Fighter", champion.getRole());
        assertEquals(3, champion.getDifficulty());
        assertEquals(abilities, champion.getAbilities());
        assertEquals(60, champion.getAttackDamage());
        assertEquals(0, champion.getAbilityPower());
        assertEquals(580, champion.getHealth());
        assertEquals(0, champion.getMana());
     
    }

    @Test
    public void testEquals() {
        ArrayList<Habilidad> abilities = new ArrayList<>();
        abilities.add(new Habilidad(1, "Habilidad Q", "Descripcion de Habilidad Q"));
        abilities.add(new Habilidad(2, "Habilidad W", "Descripcion de Habilidad W"));
        abilities.add(new Habilidad(3, "Habilidad E", "Descripcion de Habilidad E"));
        abilities.add(new Habilidad(4, "Habilidad R", "Descripcion de Habilidad R"));

        Personaje champion1 = new Personaje(1, "Aatrox", "Fighter", 3, abilities, 60, 0, 580, 0);
        Personaje champion2 = new Personaje(1, "Aatrox", "Fighter", 3, abilities, 60, 0, 580, 0);
        Personaje champion3 = new Personaje(2, "Garen", "Tank", 2, abilities, 60, 0, 600, 0);

        assertTrue(champion1.equals(champion2));
        assertFalse(champion1.equals(champion3));
        assertFalse(champion2.equals(abilities));
        assertFalse(champion1.equals(null));
    }

    @Test
    public void testToString() {
        ArrayList<Habilidad> abilities = new ArrayList<>();
        abilities.add(new Habilidad(1, "Habilidad Q", "Descripcion de Habilidad Q"));
        abilities.add(new Habilidad(2, "Habilidad W", "Descripcion de Habilidad W"));
        abilities.add(new Habilidad(3, "Habilidad E", "Descripcion de Habilidad E"));
        abilities.add(new Habilidad(4, "Habilidad R", "Descripcion de Habilidad R"));

        Personaje champion = new Personaje(1, "Ashe", "Marksman", 2, abilities, 58, 0, 539, 280);
        
        String expectedToString = "Champion{id=1, name='Ashe', role='Marksman', difficulty=2, abilities=[Habilidad{cod=1, nombre='Habilidad Q', descripcion='Descripcion de Habilidad Q'}, Habilidad{cod=2, nombre='Habilidad W', descripcion='Descripcion de Habilidad W'}, Habilidad{cod=3, nombre='Habilidad E', descripcion='Descripcion de Habilidad E'}, Habilidad{cod=4, nombre='Habilidad R', descripcion='Descripcion de Habilidad R'}], attackDamage=58, abilityPower=0, health=539, mana=280}";
        
        assertEquals(expectedToString, champion.toString());
    }
    
    @Test
    public void testsetters() {
    	 ArrayList<Habilidad> abilities = new ArrayList<>();
         abilities.add(new Habilidad(1, "Habilidad Q", "Descripcion de Habilidad Q"));
         abilities.add(new Habilidad(2, "Habilidad W", "Descripcion de Habilidad W"));
         abilities.add(new Habilidad(3, "Habilidad E", "Descripcion de Habilidad E"));
         abilities.add(new Habilidad(4, "Habilidad R", "Descripcion de Habilidad R"));

         Personaje champion = new Personaje(1, "Ashe", "Marksman", 2, abilities, 58, 0, 539, 280);
         
         String role="Asesino";
         String name="David";
         int id=666;
         int difficulty=69;
         int ataque=0;
         int habilidad=100;
         int health=200;
         int mana=300;
         int mastery=5;
         ArrayList<Habilidad> abilities1 = new ArrayList<>();
         abilities.add(new Habilidad(3, "Habilidad E", "Descripcion de Habilidad E"));
         
         champion.setAbilities(abilities1);
         champion.setAbilityPower(habilidad);
         champion.setRole(role);
         champion.setName(name);
         champion.setMana(mana);
         champion.setId(id);
         champion.setHealth(health);
         champion.setDifficulty(difficulty);
         champion.setAttackDamage(ataque);
         
         assertEquals(champion.getAbilities(),abilities1);
         assertEquals(champion.getAbilityPower(),habilidad);
         assertEquals(champion.getAttackDamage(),ataque);
         
         assertEquals(champion.getDifficulty(),difficulty);
         assertEquals(champion.getHealth(),health);
         assertEquals(champion.getId(),id);
         
         assertEquals(champion.getMana(),mana);
         assertEquals(champion.getName(),name);
         assertEquals(champion.getRole(),role);
    }
    
  
    
  
    
    @Test
    public void testSaludo() {
    Personaje personaje1 = new Personaje(1, null, null, 0, null, 0, 0, 0, 0);
    personaje1.setDifficulty(1);
    assertEquals(personaje1.Saludo(),"Recomendado para principientes");
    personaje1.setDifficulty(2);
    assertEquals(personaje1.Saludo(),"Ideal para jugadores mas expermientados");
    personaje1.setDifficulty(3);
    assertEquals(personaje1.Saludo(),"Solo para expertos en el mundo de LOL");
    }
}