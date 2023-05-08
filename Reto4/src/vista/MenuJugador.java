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
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import modelo.Usuario;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

public class MenuJugador extends JFrame {
	private MenuJugador frame;
	private ArrayList<ImageIcon> imagenes;
    private JLabel labelImagen;
	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public MenuJugador(Usuario UsurJugador) {
		
		frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Muestra un mensaje preguntando si está seguro de cerrar sesión
                int respuesta = JOptionPane.showConfirmDialog(frame, "¿Estás seguro de cerrar sesión?",
                        "Cerrar sesión", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                // Si el usuario confirma que quiere cerrar sesión, cierra la ventana
                if (respuesta == JOptionPane.YES_OPTION) {
                    e.getWindow().dispose();
                }
            }
        });
		
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
		
		 ImageIcon imageIcon = new ImageIcon("ImagenesAplicacion/Utils/boton.png");
	        Image image = imageIcon.getImage().getScaledInstance(380,220, Image.SCALE_SMOOTH);
	      ImageIcon jugar = new ImageIcon(image);
	      
        JLabel lblJugar = new JLabel();
        lblJugar.setFocusCycleRoot(true);
        lblJugar.setBounds(10, 0, 170, 56);
        lblJugar.setIcon(jugar);
        lblJugar.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblJugar);
        
		   // Crear la barra de navegación
        JToolBar navBar = new JToolBar();
        navBar.setBounds(0, 0, 712, 56);
        contentPane.add(navBar);
        
        navBar.setFloatable(false);
        navBar.setForeground(Color.WHITE);
        navBar.setBackground(new Color(0, 67, 100));
        // Configurar el administrador de diseño
        navBar.setLayout(new BoxLayout(navBar, BoxLayout.X_AXIS));
		
		
		
		Label lblPlaceholder = new Label("");
		navBar.add(lblPlaceholder);
		lblPlaceholder.setForeground(Color.WHITE);
		lblPlaceholder.setAlignment(SwingConstants.CENTER);
		lblPlaceholder.setFont(new Font("Georgia", Font.BOLD, 15));
		
		Label lblPerfil = new Label("Perfil");
		lblPerfil.setForeground(Color.WHITE);
		lblPerfil.setFont(new Font("Georgia", Font.BOLD, 15));
		lblPerfil.setAlignment(Label.CENTER);
		navBar.add(lblPerfil);
		
		Label lblPersonajes = new Label("Personajes");
		lblPersonajes.setForeground(Color.WHITE);
		lblPersonajes.setAlignment(Label.CENTER);
		lblPersonajes.setFont(new Font("Georgia", Font.BOLD, 15));
		lblPersonajes.setBounds(209, 20, 103, 20);
		navBar.add(lblPersonajes);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(-5, 0, 725, 395);
		tabbedPane.setBackground(Color.WHITE);
		contentPane.add(tabbedPane);
		
		JPanel panelPerfil = new JPanel();
		panelPerfil.setBackground(new Color(0, 41, 62));
		tabbedPane.addTab("New tab", null, panelPerfil, null);
		
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
	        panelPerfil.setLayout(null);
	        panelPerfil.add(labelImagen);
	        
	        JLabel lblBienvenido = new JLabel("Bienvenido *");
	        lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
	        lblBienvenido.setForeground(new Color(255, 255, 255));
	        lblBienvenido.setFont(new Font("Georgia", Font.BOLD, 19));
	        lblBienvenido.setBounds(164, 55, 426, 20);
	        panelPerfil.add(lblBienvenido);
	        
	         imageIcon = new ImageIcon("ImagenesAplicacion/Utils/nivel.png");
	         image = imageIcon.getImage().getScaledInstance(45,50, Image.SCALE_SMOOTH);
	      ImageIcon nivel = new ImageIcon(image);
	        
	        JLabel lblLvL = new JLabel("1");
	        lblLvL.setForeground(Color.WHITE);
	        lblLvL.setFont(new Font("Georgia", Font.BOLD, 15));
	        lblLvL.setHorizontalAlignment(SwingConstants.CENTER);
	        lblLvL.setBounds(67, 187, 45, 14);
	        panelPerfil.add(lblLvL);
	        
	        JLabel lblNivel = new JLabel();
	        lblNivel.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNivel.setBackground(Color.RED);
	        lblNivel.setIcon(new RotatedIcon(nivel, 180));
	        lblNivel.setBounds(67, 173, 45, 40);
	        panelPerfil.add(lblNivel);
	        
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
	        scrollPane.setBounds(216, 265, 465, 75);
	        panelPerfil.add(scrollPane);
	        
	        JLabel lblRank = new JLabel("");
	        lblRank.setOpaque(true);
	        lblRank.setBackground(Color.WHITE);
	        lblRank.setBounds(55, 240, 73, 80);
	        panelPerfil.add(lblRank);
	        
	     
	        
	        JPanel panelPersonajes = new JPanel();
	        tabbedPane.addTab("New tab", null, panelPersonajes, null);
	        
	        JPanel panelJugar = new JPanel();
	        tabbedPane.addTab("New tab", null, panelJugar, null);
		
		
	}
}
