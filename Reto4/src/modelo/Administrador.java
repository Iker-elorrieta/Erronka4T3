package modelo;

public class Administrador extends Usuario{

	public Administrador(String contraseña, String nombre) {
		super(contraseña, nombre);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean comprobarContraseña() {
		return false;
	}

}
