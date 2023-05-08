package controlador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ScrollPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import utils.DBUtils;



public class MetodosVista {
	public void mostrarTabla(int codigo, JPanel panel) throws SQLException {
	    Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    try {
	        // Paso 1: Establecer la conexión
	        conn = DriverManager.getConnection(DBUtils.URL, DBUtils.USERADMIN, DBUtils.PASS);

	        // Paso 2: Crear la consulta SQL
	        String sql = "";
	        if (codigo == 1) {
	            sql = "SELECT * FROM players";
	        } else if (codigo == 2) {
	            sql = "SELECT * FROM matches";
	        } else if (codigo == 3) {
	            sql = "SELECT * FROM champions";
	        } else if (codigo == 4) {
	            sql = "SELECT * FROM abilities";
	        } else if (codigo == 5) {
	            sql = "SELECT * FROM modos";
	        }

	        // Paso 3: Ejecutar la consulta SQL
	        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        rs = stmt.executeQuery(sql);

	        // Paso 4: Crear la tabla en memoria
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int columnCount = rsmd.getColumnCount();
	        String[] columnNames = new String[columnCount];
	        for (int i = 1; i <= columnCount; i++) {
	            columnNames[i - 1] = rsmd.getColumnName(i);
	        }

	        Object[][] data = new Object[getRowCount(rs)][columnCount];
	        int row = 0;
	        while (rs.next()) {
	            for (int col = 1; col <= columnCount; col++) {
	                data[row][col - 1] = rs.getObject(col);
	            }
	            row++;
	        }
	        DefaultTableModel modelo = new DefaultTableModel(data, columnNames);
	        JTable table = new JTable(modelo);
	        table.setEnabled(false);

	        // Paso 5: Agregar la tabla al scroll panel
	        JScrollPane scroll = new JScrollPane(table);
	        scroll.setBounds(30, 75, 650, 200);
	        scroll.setBackground(new Color(225, 240, 255));
	        panel.add(scroll);

	    } finally {
	        // Paso 6: Cerrar la conexión
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

	private int getRowCount(ResultSet resultSet) throws SQLException {
	    int rowCount;
	    int currentRow = resultSet.getRow(); // Save current row
	    rowCount = resultSet.last() ? resultSet.getRow() : 0; // Move to last row and get row count
	    if (currentRow == 0) {
	        resultSet.beforeFirst(); // Move back to before first row
	    } else {
	        resultSet.absolute(currentRow); // Move back to where we were
	    }
	    return rowCount;
	}
	public boolean isRowIncomplete(DefaultTableModel model, int rowIndex) {
	    for (int i = 0; i < model.getColumnCount(); i++) {
	        Object value = model.getValueAt(rowIndex, i);
	        if (value == null || value.toString().trim().isEmpty()) {
	            return true;
	        }
	    }
	    return false;
	}

}
