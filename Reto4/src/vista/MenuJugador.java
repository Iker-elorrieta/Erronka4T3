package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.RotatedIcon;

import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTree;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import modelo.Jugador;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;

public class MenuJugador extends JFrame {
	private ArrayList<ImageIcon> imagenes;
    private JLabel labelImagen;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuJugador frame = new MenuJugador();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuJugador(Jugador jugador) {
		
		  // Obtener imágenes de la carpeta
        imagenes = new ArrayList<>();
        File carpeta = new File("ImagenesAplicacion/IconosPerfil");
        File[] archivos = carpeta.listFiles();
        for (File archivo : archivos) {
            if (archivo.isFile() && archivo.getName().endsWith(".jpg")) {
                ImageIcon imagen = new ImageIcon(archivo.getPath());
                Image Img = imagen.getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH);
      	      ImageIcon ImgRescalated = new ImageIcon(Img);
                imagenes.add(ImgRescalated);
            }
        }
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 430);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 67, 100));
		panel.setBounds(0, 0, 720, 56);
		contentPane.add(panel);
		
		 ImageIcon imageIcon = new ImageIcon("ImagenesAplicacion/Utils/boton.png");
	        Image image = imageIcon.getImage().getScaledInstance(380,220, Image.SCALE_SMOOTH);
	      ImageIcon jugar = new ImageIcon(image);
		
		JLabel lblJugar = new JLabel();
		lblJugar.setFocusCycleRoot(true);
		lblJugar.setBounds(10, 0, 170, 56);
		lblJugar.setIcon(jugar);
		lblJugar.setHorizontalAlignment(SwingConstants.CENTER);
		panel.setLayout(null);
		panel.add(lblJugar);
		
		JLabel lblPersonajes = new JLabel("Personajes");
		lblPersonajes.setForeground(Color.WHITE);
		lblPersonajes.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonajes.setFont(new Font("Georgia", Font.BOLD, 15));
		lblPersonajes.setBounds(209, 20, 103, 20);
		panel.add(lblPersonajes);
		
		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setForeground(Color.WHITE);
		lblPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerfil.setFont(new Font("Georgia", Font.BOLD, 15));
		lblPerfil.setBounds(334, 20, 103, 20);
		panel.add(lblPerfil);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(-10, -30, 730, 429);
		tabbedPane.setBackground(Color.WHITE);
		contentPane.add(tabbedPane);
		
		JTabbedPane tabbedPerfil = new JTabbedPane(JTabbedPane.TOP);
		tabbedPerfil.setBackground(Color.WHITE);
		tabbedPane.addTab("New tab", null, tabbedPerfil, null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 41, 62));
		tabbedPerfil.addTab("New tab", null, panel_1, null);
		
		 JPanel panelImagen = new JPanel();
	        labelImagen = new JLabel(imagenes.get(0));
	        labelImagen.setBackground(Color.GREEN);
	        labelImagen.setOpaque( true);
	        labelImagen.setForeground(Color.CYAN);
	        labelImagen.setBounds(50, 100, 80, 80);
	        labelImagen.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	                // Mostrar la siguiente imagen
	                int indiceActual = imagenes.indexOf(labelImagen.getIcon());
	                int indiceSiguiente = (indiceActual + 1) % imagenes.size();
	                ImageIcon imagenSiguiente = imagenes.get(indiceSiguiente);
	                labelImagen.setIcon(imagenSiguiente);
	            }
	        });
	        panel_1.setLayout(null);
	        panel_1.add(labelImagen);
	        
	        JLabel lblBienvenido = new JLabel("Bienvenido *");
	        lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
	        lblBienvenido.setForeground(new Color(255, 255, 255));
	        lblBienvenido.setFont(new Font("Georgia", Font.BOLD, 19));
	        lblBienvenido.setBounds(164, 55, 426, 20);
	        panel_1.add(lblBienvenido);
	        
	         imageIcon = new ImageIcon("ImagenesAplicacion/Utils/nivel.png");
	         image = imageIcon.getImage().getScaledInstance(45,50, Image.SCALE_SMOOTH);
	      ImageIcon nivel = new ImageIcon(image);
	        
	        JLabel lblLvL = new JLabel("1");
	        lblLvL.setForeground(Color.WHITE);
	        lblLvL.setFont(new Font("Georgia", Font.BOLD, 15));
	        lblLvL.setHorizontalAlignment(SwingConstants.CENTER);
	        lblLvL.setBounds(67, 185, 45, 14);
	        panel_1.add(lblLvL);
	        
	        JLabel lblNivel = new JLabel();
	        lblNivel.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNivel.setBackground(Color.RED);
	        lblNivel.setIcon(new RotatedIcon(nivel, 180));
	        lblNivel.setBounds(67, 173, 45, 40);
	        panel_1.add(lblNivel);
	        
	        // Crear un JPanel para contener los JLabels
	        JPanel panelPartidas = new JPanel();
	       
	        panelPartidas.setBackground(new Color(0, 67, 100));
	        panelPartidas.setLayout(new BoxLayout(panelPartidas, BoxLayout.Y_AXIS));
	        for (int i = 1; i <= 20; i++) {
	            JLabel label = new JLabel("Label " + i);
	            label.setForeground(Color.WHITE);
	            panelPartidas.add(label);
	        }
	        
	        JScrollPane scrollPane = new JScrollPane(panelPartidas);
	        scrollPane.setBounds(152, 265, 529, 75);
	        panel_1.add(scrollPane);
		
		JTabbedPane tabbedPersonajes = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("New tab", null, tabbedPersonajes, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPersonajes.addTab("New tab", null, panel_2, null);
		
		JTabbedPane tabbedJugar = new JTabbedPane(JTabbedPane.TOP);
		tabbedJugar.setBackground(Color.WHITE);
		tabbedPane.addTab("New tab", null, tabbedJugar, null);
		
		JPanel panel_3 = new JPanel();
		tabbedJugar.addTab("New tab", null, panel_3, null);
		
		
	}
}
