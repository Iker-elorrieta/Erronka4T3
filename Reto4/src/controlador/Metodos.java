package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import exceptions.PasswordMismatchException;
import exceptions.PlayerNotFoundException;
import manager.GestionHabilidades;

import manager.GestionModos;
import manager.GestionPartidas;
import manager.GestionPersonajes;
import manager.GestionUsuarios;
import modelo.Habilidad;
import modelo.Jugador;
import modelo.Modo;
import modelo.Partida;
import modelo.Personaje;
import modelo.Usuario;
import utils.DBUtils;
import vista.MenuAdministrador;
import vista.MenuJugador;


public class Metodos {
	MetodosVista metodosVista = new MetodosVista();

	//Conexion a BD H
	public static void conexionBDUpdate(String consulta) {
		
		try {
		    Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		    Statement stmt = conexion.createStatement(); 
		    stmt.executeUpdate(consulta);
		    conexion.close();
		} catch (SQLException e) {
		    System.err.println("Error al establecer la conexión con MySQL: " + e.getMessage());
		}
		
	}


	//Redireccion ArrayList H
	public static void redireccionLogin(String userType, Usuario usuario) throws PlayerNotFoundException {

    	if (userType != null) {
    	    if (userType.equals("admin")) {
    	        // Redirigir al usuario a la pantalla de administrador.
    	    	new MenuAdministrador(usuario).setVisible(true);;
    	    } else if (userType.equals("jugador")) {
    	        // Redirigir al usuario a la pantalla de jugador.
    	    	new MenuJugador(usuario).setVisible(true);
    	    	
    	    }
    	} else {
    		 throw new PlayerNotFoundException("El usuario o la contraseña no son correctos.");
    	}
    }	
	
	public void guardarCambios(DefaultTableModel model, int codigo) throws SQLException {
	    Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    try {
	        // Paso 1: Establecer la conexión
	        conn = DriverManager.getConnection(DBUtils.URL, DBUtils.USERADMIN, DBUtils.PASS);

	        // Paso 2: Crear la consulta SQL para actualizar la tabla
	        String sql = "";
	        if (codigo == 1) {
	            sql = "SELECT * FROM players";
	        } else if (codigo == 2) {
	            sql = "UPDATE matches SET  id= ? ,date= ? ,duration= ? , modo = ?, estadisticas = ? , result = ? ,champion= ? WHERE id= ?";
	        } else if (codigo == 3) {
	            sql = "SELECT * FROM champions";
	        } else if (codigo == 4) {
	            sql = "SELECT * FROM abilities";
	        } else if (codigo == 5) {
	            sql = "SELECT * FROM modos";
	        }
	       

	        // Paso 3: Obtener el número de columnas y filas del modelo de la tabla
	        int numCols = model.getColumnCount();
	        int numRows = model.getRowCount();

	        // Paso 4: Recorrer el modelo y actualizar la base de datos
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        for (int row = 0; row < numRows; row++) {
	            // Verificar que la última fila esté completa
	            if (row == numRows - 1 && metodosVista.isRowIncomplete(model, row)) {
	                JOptionPane.showMessageDialog(null, "La última fila no está completa. Por favor, completa todos los campos antes de guardar los cambios.");
	                return;
	            }
	            for (int col = 0; col < numCols; col++) {
	                Object value = model.getValueAt(row, col);
	                pstmt.setObject(col + 1, value);
	            }
	            Object id = model.getValueAt(row, 0);
	            pstmt.setObject(numCols + 1, id);
	            pstmt.executeUpdate();
	        }

	        JOptionPane.showMessageDialog(null, "Los cambios se han guardado exitosamente.");

	    } finally {
	        // Paso 5: Cerrar la conexión
	        if (rs != null) {
	            rs.close();
	        }
	        if (stmt != null) {
	            stmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    }
	}

		
}