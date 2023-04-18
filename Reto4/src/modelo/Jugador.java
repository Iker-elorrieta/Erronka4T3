package modelo;

import java.util.ArrayList;

public class Jugador extends Usuario{
	
	protected String rango;
	protected int nivel;
	protected ArrayList<Personaje> personajes;
	protected ArrayList<Partida> partidasRecientes;
	

	public Jugador(String contraseña, String nombre, String rango, int nivel, ArrayList<Personaje> personajes, ArrayList<Partida> partidasRecientes) {
		super(contraseña, nombre);
		this.rango=rango;
		this.nivel=nivel;
		this.personajes=personajes;
		this.partidasRecientes=partidasRecientes;
	}

	@Override
	public boolean comprobarContraseña() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void anyadirPartidas(Partida ultimaPartida){
		if(partidasRecientes.size()==5)
		{
			partidasRecientes.remove(0);
		}
			partidasRecientes.add(ultimaPartida);
	}
	
	public void enseñarPersonajes()
	{
		for (int i=0;i<partidasRecientes.size();i++) {
			partidasRecientes.get(i).toString();
		}
	}
	
	public void subidaNivel()
	{
		if(partidasRecientes.get(partidasRecientes.size()).isResultado())
			nivel++;
	}

}
