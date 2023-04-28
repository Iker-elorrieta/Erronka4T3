package vista;

		import java.util.Scanner;

		import manager.GestionUsuarios;
		import modelo.Administrador;
		import modelo.Jugador;
		import modelo.Usuario;

		public class menu {

			public static void main(String[] args) {
				Scanner sc = new Scanner(System.in);
				boolean iniciarSesion=false;
				Administrador admin= new Administrador();
				Jugador usuario = new Jugador();
				do {
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
					Jugador jugador = new Jugador(nombre,contrasenya,rango,nivel,null, null,0,null, false);
					GestionUsuarios.anyadirJugador(null, jugador);
				}
				if(opc==2)
				{
					 System.out.println("jugador o admin?");
					 int usu=sc.nextInt();
					 if(usu==1)
					 {
						usuario= GestionUsuarios.iniciarSesionUsuarios(nombre, contrasenya);
						if(usuario.getNombre()==null)
							System.out.println("No has iniciado sesion");
						else
						{
							System.out.println("Has iniciado sesion");
							iniciarSesion=true;
						}
					 }
					 if(usu==2)
					 {
						 admin= GestionUsuarios.iniciarSesionAdmin(nombre,contrasenya);
						 if(admin.getNombre()==null)
								System.out.println("No has iniciado sesion");
						 else
						{
							System.out.println("Has iniciado sesion");
							iniciarSesion=true;
						}
					 }
				}
				}while(!iniciarSesion);
				if(admin.getNombre()==null)
					pestañasJug(usuario);
				else
					pestañaAdmin(usuario, sc);
				sc.close();
		}
			public static void pestañasJug(Jugador jug) {
				
				

		}
			public static void pestañaAdmin(Usuario usuario, Scanner sc) {
			
			}
		}