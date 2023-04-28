package manager;

import java.util.ArrayList;
import java.util.Scanner;

import controlador.metodos;
import modelo.Jugador;

public class GestionUsuarios {
    
  
    
    // Método para preguntar si el usuario desea eliminar un jugador
    public void preguntarEliminarJugador(ArrayList<Jugador> jugadores, Jugador jugador) {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Está seguro que desea eliminar al jugador " + jugador.getNombre() + "? (s/n)");
        String respuesta = sc.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            eliminarJugador(jugadores,jugador);
        }
    }
    
    // Método para eliminar un jugador
    public void eliminarJugador(ArrayList<Jugador> jugadores, Jugador jugador) {
        jugadores.remove(jugador);
        eliminarUsuario(jugador);
    }
    
    // Método para eliminar el usuario de la base de datos
    private void eliminarUsuario(Jugador jugador) {
        // Código para eliminar al jugador de la base de datos
    	String consulta="DELETE FROM `players` WHERE id="+jugador.getId();
		metodos.conexionBDUpdate(consulta);
    }
    
 // Método para eliminar un jugador
    public void anadirJugador(ArrayList<Jugador> jugadores, Jugador jugador) {
        jugadores.add(jugador);
        insertarUsuario(jugador);
    }
    
    // Método para añadir el usuario a la base de datos
    public  void insertarUsuario(Jugador jugador) { 
		String consulta="INSERT INTO `players`(`id`, `name`, `password_hash`, `registration_date`, `level`, `rank`, `bloqueado`) VALUES ('"+jugador.getId()+"','"+jugador.getNombre()+"','"+jugador.getContrasenya()+"','"+jugador.getFecha()+"','"+jugador.getNivel()+"','"+jugador.getRango()+"','"+jugador.isBloqueado()+"')";
		metodos.conexionBDUpdate(consulta);
	}
    
    // Método para cambiar el estado de bloqueo del jugador
    public void cambiarEstadoBloqueo( Jugador jugador, boolean bloqueado) {
    	jugador.setBloqueado(bloqueado);
        actualizarEstadoBloqueo(jugador, bloqueado);
    }
    
    // Método para actualizar el estado de bloqueo en la base de datos
    private void actualizarEstadoBloqueo(Jugador jugador, boolean bloqueado) {
        String consulta = "UPDATE players SET bloqueado = "+bloqueado+" WHERE id ="+jugador.getId();
        metodos.conexionBDUpdate(consulta);
    }
    
    // Método para cambiar el estado de bloqueo de un jugador en el ArrayList y en la base de datos
    public void cambiarEstadoBloqueo(ArrayList<Jugador> jugadores, int idJugador, boolean bloqueado) {
    	int i = 0;
       while(jugadores.get(i).getId() != idJugador) {
    	   
            if (jugadores.get(i).getId() == idJugador) {
                cambiarEstadoBloqueo(jugadores.get(i), bloqueado);
            }else {
            	i++;
            }
        }
    }
    

}
