package modelo;

abstract public class Usuario {
	protected String nombre;
	protected String contrasenya;
	
	public Usuario(String contraseña, String nombre)
	{
		this.nombre=nombre;
		this.contrasenya=contraseña;
	}
	
	public abstract boolean comprobarContrasenya(String contra);

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contrasenya;
	}

	public void setContraseña(String contraseña) {
		this.contrasenya = contraseña;
	}
	
}
