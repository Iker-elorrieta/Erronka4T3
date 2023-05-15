package utils;

import java.sql.*;

public class ConexionBD {
    private static Connection conexion;

    private ConexionBD(String url, String usuario, String contrasena) throws SQLException {
        conexion = DriverManager.getConnection(url, usuario, contrasena);
    }

    public static Connection obtenerConexion(String url, String usuario, String contrasena) throws SQLException {
        if (conexion == null) {
            new ConexionBD(url, usuario, contrasena);
        }
        return conexion;
    }

    public static void cerrarConexion() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
}
