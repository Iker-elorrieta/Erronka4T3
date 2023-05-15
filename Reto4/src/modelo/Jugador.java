package modelo;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Jugador extends Usuario{
	protected int id;
	protected int nivel;
	protected Date fecha;
	

	protected String rango;
	protected boolean bloqueado;

	public boolean isBloqueado() {
		return bloqueado;
	}
	public Jugador() {
		
	}


	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}


	public Jugador(int id,String nombre, String password, int nivel,String rango,  Date fecha, boolean bloqueado) {
		super(id, nombre,password);
		this.rango=rango;
		this.nivel=nivel;
		this.fecha=fecha;
		this.bloqueado=bloqueado;
	}

	public boolean isbloqueado() {
		return bloqueado;
}
	public Date getFecha() {
		return fecha;

	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	
	public String StringFecha() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaString = formato.format(fecha);
		return fechaString;
	}
	@Override
	public String toString() {
		 return super.toString();
	}
	
	

}
