package modelo;

public class Administrador extends Usuario{

	public Administrador(String nombre, String contrasenya,int id) {
		super(nombre, contrasenya,id);
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
