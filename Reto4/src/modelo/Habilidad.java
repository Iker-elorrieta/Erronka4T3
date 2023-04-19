package modelo;
public class Habilidad {
	
	
	private String nombre;
	private Personaje personaje;
	private String descripcion;
	
	//getter setter
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Personaje getPersonaje() {
		return personaje;
	}
	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	//constructor
	
	public Habilidad(String nombre, Personaje personaje, String descripcion) {
		super();
		this.nombre = nombre;
		this.personaje = personaje;
		this.descripcion = descripcion;
	}
	
	
	//metodos
	
	
	
}
