package modelo;


import java.util.ArrayList;


public class Personaje {
	
	//Valores
	int cod_personaje;
	private String nombre;
	private String role;
	private int difficulty;
	private ArrayList <Habilidad> habilidades;
	private int attackDamage;
	private int abilityPower;
	private int health;
	private int mana;
	private int partidas;
	private int usuario;
	private int maestria;
	//getter setter
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getMaestria() {
		return maestria;
	}
	public void setMaestria(int maestria) {
		this.maestria = maestria;
	}
	public ArrayList<Habilidad> getHabilidades() {
		return habilidades;
	}
	public void setHabilidades(ArrayList<Habilidad> habilidades) {
		this.habilidades = habilidades;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String categoria) {
		this.role = categoria;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public int getPartidas() {
		return partidas;
	}
	public void setPartidas(int partidas) {
		this.partidas = partidas;
	}
	public int getUsuario() {
		return usuario;
	}
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	
	//constructor
	
	public Personaje(String nombre, int maestria, ArrayList<Habilidad> habilidades, String categoria, int partidas,
			int usuario) {
		super();
		this.nombre = nombre;
		this.maestria = maestria;
		this.habilidades = habilidades;
		this.role = categoria;
		this.partidas = partidas;
		this.usuario = usuario;
	}
	
	
	//metodos
	
	public void partidaJugada(boolean victoria) {
		//sube maestria de un personaje en un usuario concreto
		
		if(victoria) {
			this.maestria=this.maestria+100;
		}else {
			this.maestria=this.maestria+20;
		}
		
		this.partidas++;
		
	}
	
	
	
}
