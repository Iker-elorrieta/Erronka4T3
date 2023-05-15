package manager;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.ConexionBD;
import utils.DBUtils;

public class GestionGenerico {
	public void actualizarRegistro(Connection conexion, Object objeto, String tabla) {
	    try {
	        
	        String query  = "UPDATE " + tabla + " SET ";
	        Field[] fields = objeto.getClass().getDeclaredFields();
	        for (int i = 0; i < fields.length; i++) {
	            String fieldName = fields[i].getName();
	            if (!fieldName.equals("id")) {
	                query += fieldName + "=?";
	                if (i < fields.length - 1) {
	                    query += ",";
	                }
	            }
	        }
	        query += " WHERE id=?";
	        PreparedStatement ps = conexion.prepareStatement(query);

	        // Establecer los valores de los parámetros en la consulta SQL
	        int j = 1;
	        for (int i = 0; i < fields.length; i++) {
	            String fieldName = fields[i].getName();
	            fields[i].setAccessible(true);
	            if (!fieldName.equals("id")) {
	                Object value = fields[i].get(objeto);
	                ps.setObject(j, value);
	                j++;
	            }
	        }
	        Object idValue = fields[0].get(objeto);
	        ps.setObject(j, idValue);

	        // Ejecutar la consulta SQL
	        ps.executeUpdate();

	     
	       
	    } catch (SQLException | IllegalAccessException e) {
	        e.printStackTrace();
	    }
	}

}
