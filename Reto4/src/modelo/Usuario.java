package modelo;

abstract public class Usuario {
	protected String nombre;
	protected String contrasenya;
	protected int id;
	
	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario(String contraseña, String nombre,int id)
	{
		this.nombre=nombre;
		this.contrasenya=contraseña;
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
