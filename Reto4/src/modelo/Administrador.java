package modelo;

public class Administrador extends Usuario{

	public Administrador(String nombre, String contrasenya) {
		super(nombre, contrasenya);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean comprobarContrasenya(String contra) {
		boolean inicioSesion = false;
		if(contra.equals(contrasenya))
		{
			inicioSesion=true;
		}
		return inicioSesion;
	}


	


}
