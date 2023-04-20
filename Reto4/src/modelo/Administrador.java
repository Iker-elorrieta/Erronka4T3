package modelo;



public class Administrador extends Usuario {

    

    public Administrador(int id, String nombre, String contrasena) {
		super(id, nombre, contrasena);
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "Administrador nombre" + nombre;
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

	


	



