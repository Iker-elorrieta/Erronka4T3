package modelo;

public abstract class Usuario {
	protected String nombre;
	protected String contraseña;
	
	public Usuario(String contraseña, String nombre)
	{
		this.nombre=nombre;
		this.contraseña=contraseña;
	}
	
	public abstract boolean comprobarContraseña();
	
}
