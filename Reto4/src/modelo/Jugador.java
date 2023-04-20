package modelo;

import java.util.ArrayList;

public class Jugador extends Usuario{
	
	protected String rango;
	protected int nivel;
	protected ArrayList<Personaje> personajes;
	protected ArrayList<Partida> partidasRecientes;
	

	public Jugador(String contraseña, String nombre, String rango, int nivel, ArrayList<Personaje> personajes, ArrayList<Partida> partidasRecientes,int id) {
		super(contraseña, nombre,id);
		this.rango=rango;
		this.nivel=nivel;
		this.personajes=personajes;
		this.partidasRecientes=partidasRecientes;
	}

	
	public void anyadirPartidas(Partida ultimaPartida){
		if(partidasRecientes.size()==5)
		{
			partidasRecientes.remove(0);
		}
			partidasRecientes.add(ultimaPartida);
	}
	
	public String getRango() {
		return rango;
	}


	public void setRango(String rango) {
		this.rango = rango;
	}


	public int getNivel() {
		return nivel;
	}


	public void setNivel(int nivel) {
		this.nivel = nivel;
	}


	public ArrayList<Personaje> getPersonajes() {
		return personajes;
	}


	public void setPersonajes(ArrayList<Personaje> personajes) {
		this.personajes = personajes;
	}


	public ArrayList<Partida> getPartidasRecientes() {
		return partidasRecientes;
	}


	public void setPartidasRecientes(ArrayList<Partida> partidasRecientes) {
		this.partidasRecientes = partidasRecientes;
	}


	
	public void subidaNivel()
	{
		if(partidasRecientes.get(partidasRecientes.size()-1).isResultado())
			nivel++;
	}

	public boolean comprobarContrasenya(String contra) {
		boolean inicioSesion = false;
		if(contra.equals(contrasenya))
			inicioSesion=true;
		
		return inicioSesion;
	}
	

}
