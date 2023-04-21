package modelo;



public class Administrador extends Usuario {

    public Administrador(int id, String nombre, String contrasena) {
		super(id, nombre, contrasena);
	}

	@Override
	public boolean comprobarContrasenya(String contra) {
		boolean inicioSesion = false;
		if(contra.equals(contrasena))
		{
			inicioSesion=true;
		}
		return inicioSesion;
	}
}

	


	



