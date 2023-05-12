package controlador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import modelo.Modo;
import modelo.Personaje;
import utils.DBUtils;



public class MetodosVista {
	CartaMouseListener mouseListener = new CartaMouseListener();
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
	
	
	public void crearCartas(ArrayList<Personaje> personajes, JScrollPane scrollPane) {
	    JPanel panelCartas = new JPanel();
	    panelCartas.setOpaque(false);
	    panelCartas.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // 20 de espacio horizontal y vertical entre cartas
	    scrollPane.setViewportView(panelCartas); // Asignamos el panel de cartas al scroll pane
	    
	    for (Personaje personaje : personajes) {
	    	
	    	
			Border bordeDorado = BorderFactory.createCompoundBorder(
				    BorderFactory.createLineBorder(Color.decode("#C89B3C"), 5),
				    BorderFactory.createEmptyBorder(5, 5, 5, 5)
				);
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(150, 220));
			panel.setBackground(Color.MAGENTA);
			panel.setBorder(bordeDorado);
			panel.setLayout(null);
			panel.setToolTipText(String.valueOf(personaje.getId()));
			panel.addMouseListener(mouseListener);

			JLabel lblTitulo = new JLabel(personaje.getName());
			lblTitulo.setBounds(55, 190, 50, 15);
			lblTitulo.setForeground(Color.WHITE);
			panel.add(lblTitulo);
			
			  JLabel lblSombra = new JLabel();
				lblSombra.setBounds(0, 0, 150, 220);
				lblSombra.setForeground(Color.WHITE);
				lblSombra.setIcon(rescaleImage("ImagenesAplicacion/Utils/sombra.png",150,220));
				
				panel.add(lblSombra);
			
			JLabel lblImagen = new JLabel();
			lblImagen.setBounds(5, 5, 140, 215);
			lblImagen.setIcon(rescaleImage( "ImagenesAplicacion/PortadasChampions/" + personaje.getName() + ".jpg",140, 220));
			lblImagen.setBackground(Color.RED);
			lblImagen.setOpaque(true);
			panel.add(lblImagen);
			
	        panelCartas.add(panel); // Agregamos la carta al panel de cartas
	    }
	    
	    // Calculamos la cantidad de columnas que caben en el ancho del scroll pane
	    int columnas = (scrollPane.getWidth() - 20) / (150 + 20);
	    
	    // Calculamos la cantidad de filas necesarias para mostrar todos los personajes
	    int filas = (int) Math.ceil((double) personajes.size() / columnas);
	    
	    // Asignamos el tamaño mínimo al panel de cartas para que se ajuste al tamaño del contenido
	    panelCartas.setPreferredSize(new Dimension(scrollPane.getWidth() - 20, filas * (220 + 20)));
	}
	
	public void crearPanelesModos(JScrollPane scrollPanel, ArrayList<Modo> modos) {
	    JPanel panelModos = new JPanel();
	    panelModos.setBackground(Color.blue);
	    panelModos.setLayout(new BoxLayout(panelModos, BoxLayout.X_AXIS));
	    scrollPanel.setViewportView(panelModos);
	    
	    for (Modo modo : modos) {
	        JPanel panel = new JPanel();
	        panel.setPreferredSize(new Dimension(193, 148));
	        panel.setBackground(Color.red);
	        panel.setLayout(null);

	        JLabel lblImagenModo = new JLabel();
	        lblImagenModo.setLocation(0, 0);
	        lblImagenModo.setSize(193, 148);
	        lblImagenModo.setIcon(rescaleImage("ImagenesAplicacion/ImagenesModos/"+modo.getNombre()+".png",193,148));

	        JLabel lblNombreModo = new JLabel(modo.getNombre());
	        lblNombreModo.setForeground(Color.WHITE);
	        lblNombreModo.setBounds(75, 120, 46, 14);

	        JLabel Sombra = new JLabel();
	        Sombra.setLocation(0, 0);
	        Sombra.setIcon((rescaleImage("ImagenesAplicacion/Utils/sombra.png",193,148)));
	        Sombra.setSize(193, 148);

	        panel.add(Sombra);
	        panel.add(lblImagenModo);
	        panel.add(lblNombreModo);

	        panelModos.add(panel);
	    }
	    scrollPanel.setViewportView(panelModos);

	}
	
	public static ImageIcon rescaleImage(String path, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

	public ImageIcon cambiarIconoPorRango(String rango) {
	    String rutaImagen = "ImagenesAplicacion/IconosClasificatoria/"+rango+"_1.png";
	    System.out.println(rutaImagen);
	   rescaleImage(rutaImagen, 130, 149);
	    ImageIcon imagen = rescaleImage(rutaImagen, 130, 149);
	  return imagen;
	}

	

}
