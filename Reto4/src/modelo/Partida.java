package modelo;

import java.util.Date;


import utils.Modos;

public class Partida {
	int cod_partida;
	Jugador jugador;
	Modos modo;
	Personaje personaje;
	Estadisticas estadisticas;
	public boolean resultado;
	Date fecha;
	
	public Partida(int cod_partida, Jugador jugador, Modos modo, Personaje personaje, Estadisticas estadisticas,
			boolean resultado, Date fecha) {
		super();
		this.cod_partida = cod_partida;
		this.jugador = jugador;
		this.modo = modo;
		this.personaje = personaje;
		this.estadisticas = estadisticas;
		this.resultado = resultado;
		this.fecha = fecha;
	}

	public int getCod_partida() {
		return cod_partida;
	}

	public Usuario getJugador() {
		return jugador;
	}

	public Modos getModo() {
		return modo;
	}

	public Personaje getPersonaje() {
		return personaje;
	}

	public Estadisticas getEstadisticas() {
		return estadisticas;
	}

	public boolean isResultado() {
		return resultado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setCod_partida(int cod_partida) {
		this.cod_partida = cod_partida;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public void setModo(Modos modo) {
		this.modo = modo;
	}

	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}

	public void setEstadisticas(Estadisticas estadisticas) {
		this.estadisticas = estadisticas;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String victoria() {
		String[] resul= {"Victoria", "Derrota"};
		
		return resultado==true?resul[0]:resul[1];
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partida other = (Partida) obj;
		return cod_partida == other.cod_partida;
	}

	@Override
	public String toString() {

		return victoria()+"  "+ modo +"  "+ personaje.getNombre() + "  " + estadisticas.toString() + "  " + fecha;

	}

	
}
