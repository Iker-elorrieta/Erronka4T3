package modelo;

import java.util.ArrayList;
import java.util.Date;

public class Jugador extends Usuario{
	protected int id;
	protected int nivel;
	protected ArrayList<Partida> partidasRecientes;
	protected ArrayList<Personaje> personajes;
	protected Date fecha;
	protected String rango;
	protected boolean bloqueado;

	public boolean isBloqueado() {
		return bloqueado;
	}


	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}


	public Jugador(String contrasenya, String nombre, String rango, int nivel, ArrayList<Personaje> personajes, ArrayList<Partida> partidasRecientes,int id, Date fecha, boolean bloqueado) {
		super(id, nombre,contrasenya);
		this.rango=rango;
		this.nivel=nivel;
		this.personajes=personajes;
		this.partidasRecientes=partidasRecientes;
		this.fecha=fecha;
		this.bloqueado=bloqueado;
	}

	
	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
