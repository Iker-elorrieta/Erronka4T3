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
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTree;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import modelo.Jugador;
import modelo.Usuario;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

public class MenuJugador extends JFrame {
	private MenuJugador frame;
	private ArrayList<ImageIcon> imagenes;
    private JLabel labelImagen;
	private JPanel contentPane;
	private  JPanel panelPersonajes;
	private  JPanel panelJugar;
	private JTabbedPane tabbedPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuJugador frame = new MenuJugador(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public MenuJugador(Usuario UsurJugador){
		
	/*	addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(MenuJugador.this,
                        "¿Estás seguro de que deseas cerrar sesión?", "Confirmar cierre de sesión",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Cerrar la ventana actual
                    dispose();

                    // Abrir la ventana de inicio de sesión
                    LogIn loginFrame = new LogIn();
                    loginFrame.setVisible(true);
                }
            }
        });
    
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);*/
		setBounds(100, 100, 728, 430);
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
        lblJugar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	tabbedPane.setSelectedIndex(2);
            }
        });
        contentPane.add(lblJugar);
        
		   // Crear la barra de navegación
        JToolBar navBar = new JToolBar();
        navBar.setFloatable(false);
        navBar.setBounds(0, 0, 712, 56);
        contentPane.add(navBar);
        navBar.setForeground(Color.BLACK);
        navBar.setBackground(new Color(9, 20, 40));
        // Configurar el administrador de diseño
        navBar.setLayout(new BoxLayout(navBar, BoxLayout.X_AXIS));
		
		
		
		Label lblPlaceholder = new Label("");
		navBar.add(lblPlaceholder);
		lblPlaceholder.setForeground(Color.WHITE);
		lblPlaceholder.setAlignment(SwingConstants.CENTER);
		lblPlaceholder.setFont(new Font("Georgia", Font.BOLD, 15));
		
		Label lblPerfil = new Label("Perfil");
		lblPerfil.setForeground(new Color(240, 230, 210));
		lblPerfil.setFont(new Font("Georgia", Font.BOLD, 15));
		lblPerfil.setAlignment(Label.CENTER);
		lblPerfil.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	            	tabbedPane.setSelectedIndex(0);
	            }
	        });
		navBar.add(lblPerfil);
		
		Label lblPersonajes = new Label("Personajes");
		lblPersonajes.setForeground(new Color(240, 230, 210));
		lblPersonajes.setAlignment(Label.CENTER);
		lblPersonajes.setFont(new Font("Georgia", Font.BOLD, 15));
		lblPersonajes.setBounds(209, 20, 103, 20);
		lblPersonajes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	tabbedPane.setSelectedIndex(1);
            }
        });
		navBar.add(lblPersonajes);
		
		 tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		 tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
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
	        lblBienvenido.setForeground(new Color(240, 230, 210));
	        lblBienvenido.setFont(new Font("Georgia", Font.BOLD, 19));
	        lblBienvenido.setBounds(164, 55, 426, 20);
	        panelPerfil.add(lblBienvenido);
	        
	         imageIcon = new ImageIcon("ImagenesAplicacion/Utils/nivel.png");
	         image = imageIcon.getImage().getScaledInstance(45,50, Image.SCALE_SMOOTH);
	      ImageIcon nivel = new ImageIcon(image);
	        
	        JLabel lblLvL = new JLabel("1");
	        lblLvL.setForeground(new Color(240, 230, 210));
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
	            label.setForeground(new Color(240, 230, 210));
	            panelPartidas.add(label);
	        }
	        
	        JScrollPane scrollPane = new JScrollPane(panelPartidas);
	        scrollPane.setEnabled(false);
	        scrollPane.setViewportBorder(null);
	        scrollPane.setBounds(216, 265, 465, 75);
	        panelPerfil.add(scrollPane);
	        
	        JLabel lblRank = new JLabel("");
	        lblRank.setOpaque(true);
	        lblRank.setBackground(Color.WHITE);
	        lblRank.setBounds(55, 240, 73, 80);
	        panelPerfil.add(lblRank);
	        
	        imageIcon = new ImageIcon("ImagenesAplicacion/ImagenesMenu/fondoMenu.jpg");
	         image = imageIcon.getImage().getScaledInstance(720,367, Image.SCALE_SMOOTH);
	      ImageIcon fondo = new ImageIcon(image);
	        
	        JLabel lblFondo = new JLabel();
	        lblFondo.setBounds(0, 0, 720, 367);
	        lblFondo.setIcon(fondo);
	        panelPerfil.add(lblFondo);
	      
	     
	         panelPersonajes = new JPanel();
	        tabbedPane.addTab("New tab", null, panelPersonajes, null);
	        
	        JLabel lblFondoPersonajes = new JLabel();
	        lblFondoPersonajes.setBounds(0, 0, 720, 367);
	        lblFondoPersonajes.setIcon(fondo);
	        panelPersonajes.add(lblFondoPersonajes);
	         
	      
	         panelJugar = new JPanel();
	        tabbedPane.addTab("New tab", null, panelJugar, null);
	        panelJugar.setLayout(null);
	        
	     
	      
	        JPanel panelModosNombres = new JPanel();
	        panelModosNombres.setOpaque(false);
	        panelModosNombres.setBounds(40, 180, 640, 49);
	        panelJugar.add(panelModosNombres);
	        panelModosNombres.setLayout(new GridLayout(1, 3));
	   
	
	        
	        JLabel lblClasifi_1 = new JLabel();
	        panelModosNombres.add(lblClasifi_1);
	        
	        JLabel lblARAM_1 = new JLabel("New label");
	        lblARAM_1.setForeground(new Color(240, 230, 210));

	        lblARAM_1.setHorizontalAlignment(SwingConstants.CENTER);
	        lblARAM_1.setBackground(new Color(10, 20, 40));
	        panelModosNombres.add(lblARAM_1);
	        
	        JLabel lblNormal_1 = new JLabel("New label");
	        lblNormal_1.setForeground(new Color(240, 230, 210));
	      
	        lblNormal_1.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNormal_1.setBackground(new Color(10, 20, 40));
	        panelModosNombres.add(lblNormal_1);
	        
	        JPanel panelModos = new JPanel();
	        panelModos.setOpaque(false);
	        panelModos.setBounds(40, 70, 640, 140);
	        panelJugar.add(panelModos);
	        panelModos.setLayout(new BorderLayout(0, 0));
	        
	        JLabel lblClasifi = new JLabel("New label");
	        lblClasifi.setHorizontalAlignment(SwingConstants.CENTER);
	        lblClasifi.setOpaque(true);
	        lblClasifi.setBackground(Color.CYAN);
	        panelModos.add(lblClasifi);
	        
	        JLabel lblARAM = new JLabel("New label");
	        lblARAM.setHorizontalAlignment(SwingConstants.CENTER);
	        lblARAM.setOpaque(true);
	        lblARAM.setBackground(Color.RED);
	        panelModos.add(lblARAM, BorderLayout.EAST);
	        
	      
	        
	        JLabel lblNormal = new JLabel("New label");
	        lblNormal.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNormal.setOpaque(true);
	        lblNormal.setBackground(Color.PINK);
	        panelModos.add(lblNormal, BorderLayout.WEST);
	        
	        JLabel lblFondoJugar = new JLabel();
	        lblFondoJugar.setBounds(0, 0, 720, 367);
	        lblFondoJugar.setIcon(fondo);
	        panelJugar.add(lblFondoJugar);
	        
	       
	        
	        
	}
}
