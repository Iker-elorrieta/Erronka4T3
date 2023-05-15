package modelo;

abstract public class Usuario implements Saludar{
	protected String nombre;
	protected String password;
	protected int id;
	
	
	public String getContrasenya() {
		return password;
	}
	public Usuario(){
		
	}

	public void setContrasenya(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	public Usuario(int id, String nombre,String password )
	{
		this.nombre=nombre;
		this.password=password;
		this.id=id;
	}
	
	public boolean comprobarContrasenya(String contra) {
		boolean inicioSesion = false;
		if(contra.equals(password))
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
	
	@Override
	public String toString() {
		return nombre;
	}
}
