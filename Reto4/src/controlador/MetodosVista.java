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
import java.awt.Point;
import java.awt.ScrollPane;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
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
import javax.swing.table.TableModel;

import modelo.Modo;
import modelo.Partida;
import modelo.Personaje;
import utils.DBUtils;



public class MetodosVista {
	CartaMouseListener mouseListener = new CartaMouseListener();
	
	public <T> void crearTabla(ArrayList<T> datos, String[] nombresColumnas, JPanel panel) {
	    // Creamos el modelo de la tabla
		 panel.removeAll();
		
		DefaultTableModel  modeloTabla = new DefaultTableModel (nombresColumnas, 0) { 
		        	
		        	private static final long serialVersionUID = 1L;
		
					@Override
		            public boolean isCellEditable(int row, int column) {
		            return false;
		        	}
		        };

	    // Agregamos los datos al modelo de la tabla
		        for (Object objeto : datos) {
		            Object[] fila = new Object[nombresColumnas.length];
		            for (int i = 0; i < nombresColumnas.length; i++) {
		                String[] atributos = nombresColumnas[i].split("\\."); // Separamos los nombres de atributos con el punto
		                Object valor = objeto;
		                for (String atributo : atributos) {
		                    try {
		                        Field campo = getField(valor.getClass(), atributo);
		                        campo.setAccessible(true);
		                        valor = campo.get(valor);
		                    } catch (NoSuchFieldException | IllegalAccessException e) {
		                        e.printStackTrace();
		                        valor = null;
		                    }
		                }
		                fila[i] = (valor != null) ? valor.toString() : "";
		            }
		            modeloTabla.addRow(fila);
		        }

	    JTable tabla = new JTable(modeloTabla);
	    tabla.setPreferredScrollableViewportSize(new Dimension(500, 300));

	    // Establecemos la posición de la tabla en el centro del JPanel
	    panel.setLayout(new BorderLayout());
	    panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
	    panel.setPreferredSize(new Dimension(500, 300));
	    
	    // Actualizamos el panel para que muestre la nueva tabla
	    panel.revalidate();
	    panel.repaint();
	}
	
	private Field getField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
	    try {
	        return clazz.getDeclaredField(fieldName);
	    } catch (NoSuchFieldException e) {
	        Class<?> superClass = clazz.getSuperclass();
	        if (superClass != null) {
	            return getField(superClass, fieldName);
	        } else {
	            throw e;
	        }
	    }
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
			lblTitulo.setBounds(0, 190, 150, 15);
			 lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
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
	   
	    panelModos.setLayout(new BoxLayout(panelModos, BoxLayout.X_AXIS));
	    panelModos.setOpaque(false);
	    scrollPanel.setViewportView(panelModos);
	    
	    for (Modo modo : modos) {
	        JPanel panel = new JPanel();
	        panel.setPreferredSize(new Dimension(193, 148));
	       panel.setOpaque(false);
	        panel.setLayout(null);

	        JLabel lblImagenModo = new JLabel();
	        lblImagenModo.setLocation(0, 0);
	        lblImagenModo.setSize(193, 148);
	        lblImagenModo.setIcon(rescaleImage("ImagenesAplicacion/ImagenesModos/"+modo.getNombre()+".png",193,148));

	        JLabel lblNombreModo = new JLabel(modo.getNombre());
	        lblNombreModo.setForeground(Color.WHITE);
	        lblNombreModo.setBounds(75, 120, 193, 15);

	        JLabel Sombra = new JLabel();
	        Sombra.setLocation(0, 0);
	        Sombra.setIcon((rescaleImage("ImagenesAplicacion/Utils/sombra.png",193,148)));
	        Sombra.setSize(193, 148);
	        
	        panel.add(lblNombreModo);
	        panel.add(Sombra);
	        panel.add(lblImagenModo);
	        

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

	public void cambiarIconoPorRango(String rango, JLabel labelRank) {
	    String rutaImagen = "ImagenesAplicacion/IconosClasificatoria/"+rango+"_1.png";
	    System.out.println(rutaImagen);
	   rescaleImage(rutaImagen, 130, 149);
	    ImageIcon imagen = rescaleImage(rutaImagen, 20, 50);
	    labelRank.setIcon( imagen);
	}

	
	public void mostrarPartidas(ArrayList<Partida> partidas, JScrollPane scrollPane) {
	    // Creamos un JPanel que contendrá las filas de la tabla
	    JPanel panelTabla = new JPanel();
	    panelTabla.setLayout(new BoxLayout(panelTabla, BoxLayout.Y_AXIS));

	    // Añadimos cada partida como una fila en el panel
	    for (int i = 0; i < partidas.size(); i++) {
	    	Partida partida = partidas.get(i);
	        JPanel fila = new JPanel(new GridLayout(1, 6)); // Creamos una fila con 4 columnas

	        // Añadimos cada columna de la fila con los datos de la partida
	        fila.add(new JLabel(partida.victoria()));
	        fila.add(new JLabel(partida.getPersonaje().getName()));
	        fila.add(new JLabel(partida.getEstadisticas().toString()));
	        fila.add(new JLabel(partida.getModo().getNombre()));
	        fila.add(new JLabel(partida.getFecha().toString()));
	        fila.add(new JLabel(String.valueOf(partida.getDuracion())));
	        panelTabla.add(fila); 
	        
	        if (i < partidas.size() - 1) {
	            JSeparator separador = new JSeparator(JSeparator.HORIZONTAL);
	            panelTabla.add(separador);
	        }
	        
	       
	    }

	    // Añadimos el panel al JScrollPane
	    scrollPane.setViewportView(panelTabla);
	}

	public void mostrarPartida(Partida partida, JPanel panelPartida) {
		  panelPartida.removeAll(); 
		  SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	        String fechaFormateada = formato.format(partida.getFecha());
		 JPanel fila = new JPanel(new GridLayout(1, 6));
		 fila.add(new JLabel(partida.victoria()));
	        fila.add(new JLabel(partida.getPersonaje().getName()));
	        fila.add(new JLabel(partida.getEstadisticas().toString()));
	        fila.add(new JLabel(partida.getModo().getNombre()));
	        fila.add(new JLabel(fechaFormateada));
	        fila.add(new JLabel(String.valueOf(partida.getDuracion())));
	        panelPartida.add(fila); 
	        
	        panelPartida.revalidate();
	        panelPartida.repaint();
	}


}
