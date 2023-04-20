package modelo;

abstract public class Usuario {
	protected int id;
	protected String nombre;
	protected String contrasena;
	
	
	public String getContrasenya() {
		return contrasena;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasena = contrasenya;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario(int id, String nombre,String contrasena )
	{
		this.nombre=nombre;
		this.contrasena=contrasena;
		this.id=id;
	}
	
	public abstract boolean comprobarContrasenya(String contra);

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}
