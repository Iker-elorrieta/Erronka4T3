package modelo;

abstract public class Usuario implements Saludar{
	protected String nombre;
	protected String contrasenya;
	protected int id;
	
	
	public String getContrasenya() {
		return contrasenya;
	}
	public Usuario(){
		
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

	public Usuario(int id, String nombre,String contrasena )
	{
		this.nombre=nombre;
		this.contrasenya=contrasena;
		this.id=id;
	}
	
	public boolean comprobarContrasenya(String contra) {
		boolean inicioSesion = false;
		if(contra.equals(contrasenya))
		{
			inicioSesion=true;
		}
		return inicioSesion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String Saludo()
	{
		return "Buenas, saludos "+nombre+" ,bienvenido al mundo de LOL";
	}
}
