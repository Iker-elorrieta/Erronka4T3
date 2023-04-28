package modelo;

import java.util.ArrayList;

public class Jugador extends Usuario{
	
	public int nivel;
	public ArrayList<Partida> partidasRecientes;
	public ArrayList<Personaje> personajes;
	public boolean bloqueado;
	public String rango;

	public Jugador( String nombre,String contrasenya, String rango, int nivel, ArrayList<Personaje> personajes, ArrayList<Partida> partidasRecientes,int id, boolean bloqueado) {
		super(id, nombre,contrasenya);
		this.rango=rango;
		this.nivel=nivel;
		this.personajes=personajes;
		this.partidasRecientes=partidasRecientes;
		this.bloqueado=bloqueado;
	}
	public Jugador() {
		
	}

	
	public boolean isbloqueado() {
		return bloqueado;
	}


	public void setbloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
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
	

}
