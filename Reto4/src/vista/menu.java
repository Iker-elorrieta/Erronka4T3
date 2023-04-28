package vista;

		import java.util.Scanner;

		import controlador.metodos;
		import modelo.Administrador;
		import modelo.Jugador;
		import modelo.Usuario;

		public class menu {

			public static void main(String[] args) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Te tienes que registrar o tienes cuenta? (1-2)");
				int opc=sc.nextInt();
				sc.nextLine();
				System.out.println("Introduce tu nombre");
				String nombre=sc.nextLine();
				System.out.println("Introduce la contraseña");
				String contrasenya=sc.nextLine();
				if(opc==1)
				{
					int nivel=1;
					String rango="Amateur";
					Jugador jugador = new Jugador(nombre,contrasenya,rango,nivel,null, null,0,false);
					metodos.registrarse(jugador);
				}
				if(opc==2)
				{
					 System.out.println("jugador o admin?");
					 int usu=sc.nextInt();
					 if(usu==1)
					 {
						Usuario usuario= metodos.iniciarSesionUsuarios(nombre, contrasenya);
						if(usuario==null)
							System.out.println("No has iniciado sesion");
						else
							System.out.println("Has iniciado sesion");
					 }
					 if(usu==2)
					 {
						 Administrador admin= metodos.iniciarSesionAdmin(nombre,contrasenya);
						 if(admin==null)
								System.out.println("No has iniciado sesion");
							else
								System.out.println("Has iniciado sesion");
					 }
				}
				sc.close();
		}
			public static void pestañasJug(Usuario usuario) {
				// TODO Auto-generated method stub
				

		}
			public static void pestañaAdmin(Usuario usuario, Scanner sc) {
				System.out.println("Que quieres editar?\n"
						+ "1- Usuarios \n"
						+ "2- Personajes \n"
						+ "3- Partidas");
				int eleg= sc.nextInt();
				System.out.println("Que quieres hacer? \n"
						+ "1- Insertar \n"
						+ "2- Modificar \n"
						+ "3- Borrar");
				int accion= sc.nextInt();
				
				switch(accion)
				{
				case 1: metodos.insertar(eleg);break;
				case 2: metodos.modificar(eleg);break;
				case 3: metodos.borrar(eleg);break;
				}
				
			}}