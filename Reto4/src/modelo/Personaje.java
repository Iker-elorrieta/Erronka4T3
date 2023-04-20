package modelo;

import java.util.ArrayList;

public class Personaje {
	
	//Valores
	
	private String nombre;
	private int maestria;
	private ArrayList <Habilidad> habilidades;
	private String categoria;
	private int partidas;
	private int usuario;
	
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
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
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
		this.categoria = categoria;
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
