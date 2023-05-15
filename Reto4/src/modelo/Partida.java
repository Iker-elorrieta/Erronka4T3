package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Partida { 
	public int cod_partida;
	public Jugador jugador;
	public Modo modo;
	public Estadisticas estadisticas;
	public boolean resultado;
	public Personaje personaje;
	public Date fecha;
	public int duracion;
	
	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public Partida(int cod_partida, Jugador jugador, Modo modo, Personaje personaje, Estadisticas estadisticas,
			boolean resultado, Date fecha, int duracion) {
		super();
		this.cod_partida = cod_partida;
		this.jugador = jugador;
		this.modo = modo;
		this.personaje = personaje;
		this.estadisticas = estadisticas;
		this.resultado = resultado;
		this.fecha = fecha;
		this.duracion=duracion;
	}

	public Partida() {
		// TODO Auto-generated constructor stub
	}

	

	public int getCod_partida() {
		return cod_partida;
	}

	public Usuario getJugador() {
		return jugador;
	}

	public Modo getModo() {
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

	public void setModo(Modo modo) {
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

		return victoria()+"  "+ modo.getNombre() +"  "+ personaje.getName() + "  " + estadisticas.toString() + "  " + fecha+" "+duracion;

	}
	public String StringFecha() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaString = formato.format(fecha);
		return fechaString;
	}

	
}
