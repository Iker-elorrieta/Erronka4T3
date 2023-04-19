package modelo;


public class Personaje {
	
	//Valores
	
	private String nombre;
	private String maestria;
	private Habilidad [] habilidades;
	private String categoria;
	private int partidas;
	private Usuario [] usuario;
	
	//getter setter
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMaestria() {
		return maestria;
	}
	public void setMaestria(String maestria) {
		this.maestria = maestria;
	}
	public Habilidad[] getHabilidades() {
		return habilidades;
	}
	public void setHabilidades(Habilidad[] habilidades) {
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
	public Usuario[] getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario[] usuario) {
		this.usuario = usuario;
	}
	
	//constructor
	
	public Personaje(String nombre, String maestria, Habilidad[] habilidades, String categoria, int partidas,
			Usuario[] usuario) {
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
