package modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Personaje implements Saludar{
    private int id;
    private String name;
    private String role;
    private int difficulty;
    private int attackDamage;
    private int abilityPower;
    private int health;
    private int mana;

    public Personaje(int id, String name, String role, int difficulty, int attackDamage, int abilityPower, int health, int mana) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.difficulty = difficulty;
        this.attackDamage = attackDamage;
        this.abilityPower = abilityPower;
        this.health = health;
        this.mana = mana;
   
    }

    public Personaje() {
		
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getAbilityPower() {
        return abilityPower;
    }

    public void setAbilityPower(int abilityPower) {
        this.abilityPower = abilityPower;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
    @Override
    public String toString() {
        return name;
                
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personaje champion = (Personaje) o;
        return id == champion.id;
    }

   

	@Override
	public String Saludo() {
		String saludo="";
		if(difficulty==1)
			saludo="Recomendado para principientes";
		if(difficulty==2)
			saludo="Ideal para jugadores mas expermientados";
		if(difficulty==3)
			saludo="Solo para expertos en el mundo de LOL";
		return saludo;
	}
}
