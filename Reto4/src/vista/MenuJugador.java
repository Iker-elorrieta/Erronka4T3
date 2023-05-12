package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.RotatedIcon;

import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTree;
import controlador.Metodos;
import controlador.MetodosVista;
import manager.GestionModos;
import manager.GestionPersonajes;
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
	MetodosVista metodosVista = new MetodosVista();
	GestionPersonajes gestionP = new GestionPersonajes();
	GestionModos gestionM = new GestionModos();
	Metodos metodos = new Metodos();
	private JScrollPane scrollPersonajes ;
	private  JScrollPane scrollModos;

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
	public MenuJugador(Usuario usuario){
		setIconImage(Toolkit.getDefaultToolkit().getImage("ImagenesAplicacion/ImagenesMenu/logoWR.png"));
		Jugador j1 = (Jugador) usuario;
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
            	metodosVista.crearPanelesModos(scrollPersonajes, gestionM.cargaInicialModos());
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
            	
            	metodosVista.crearCartas(gestionP.getPersonajeByJugadorLvL(j1.getNivel()), scrollPersonajes);
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
	        
	        JLabel lblBienvenido = new JLabel("Bienvenido "+j1.getNombre());
	        lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
	        lblBienvenido.setForeground(new Color(240, 230, 210));
	        lblBienvenido.setFont(new Font("Georgia", Font.BOLD, 19));
	        lblBienvenido.setBounds(164, 55, 426, 20);
	        panelPerfil.add(lblBienvenido);
	        
	         imageIcon = new ImageIcon("ImagenesAplicacion/Utils/nivel.png");
	         image = imageIcon.getImage().getScaledInstance(45,50, Image.SCALE_SMOOTH);
	      ImageIcon nivel = new ImageIcon(image);
	        
	        JLabel lblLvL = new JLabel(String.valueOf(j1.getNivel()));
	        lblLvL.setForeground(new Color(240, 230, 210));
	        lblLvL.setFont(new Font("Georgia", Font.BOLD, 15));
	        lblLvL.setHorizontalAlignment(SwingConstants.CENTER);
	        lblLvL.setBounds(67, 187, 45, 14);
	        panelPerfil.add(lblLvL);
	        
	    /*    JLabel lblNivel = new JLabel();
	        lblNivel.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNivel.setBackground(Color.RED);
	        lblNivel.setIcon(new RotatedIcon(nivel, 180));
	        lblNivel.setBounds(67, 173, 45, 40);
	        panelPerfil.add(lblNivel);
	        */
	      
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
	        
	        JLabel lblRank = new JLabel();
	        lblRank.setHorizontalAlignment(SwingConstants.CENTER);
	        System.out.println(metodosVista.cambiarIconoPorRango(j1.getRango()));
	        lblRank.setIcon(metodosVista.cambiarIconoPorRango(j1.getRango()));
	        lblRank.setBackground(Color.WHITE);
	      lblRank.setBounds(35, 212, 126, 128);
	        panelPerfil.add(lblRank);
	      
	        
	        imageIcon = new ImageIcon("ImagenesAplicacion/ImagenesMenu/fondoMenu.jpg");
	         image = imageIcon.getImage().getScaledInstance(720,367, Image.SCALE_SMOOTH);
	      ImageIcon fondoPerfil = new ImageIcon(image);
	        
	        JLabel lblFondoPerfil = new JLabel();
	        lblFondoPerfil.setBounds(0, 0, 720, 367);
	        lblFondoPerfil.setIcon(fondoPerfil);
	        panelPerfil.add(lblFondoPerfil);
	      
	     
	         panelPersonajes = new JPanel();
	        tabbedPane.addTab("New tab", null, panelPersonajes, null);
	        panelPersonajes.setLayout(null);
	      
	        
	         scrollPersonajes = new JScrollPane();
	         scrollPersonajes.getViewport().setOpaque(false);
	         scrollPersonajes.setOpaque(false);
	        scrollPersonajes.setBounds(0, 30, 720, 337);
	        scrollPersonajes.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	        panelPersonajes.add(scrollPersonajes);
	        
	        imageIcon = new ImageIcon("ImagenesAplicacion/ImagenesMenu/fondoMenu.jpg");
	         image = imageIcon.getImage().getScaledInstance(720,367, Image.SCALE_SMOOTH);
	      ImageIcon fondoPersonaje = new ImageIcon(image);
	        
	        JLabel lblFondoPersonajes = new JLabel();
	        lblFondoPersonajes.setBounds(0, 0, 720, 367);
	        lblFondoPersonajes.setIcon(fondoPersonaje);
	        panelPersonajes.add(lblFondoPersonajes);
	         
	      
	         panelJugar = new JPanel();
	        tabbedPane.addTab("New tab", null, panelJugar, null);
	        panelJugar.setLayout(null);
	        
	         scrollModos = new JScrollPane();
	         scrollModos.getViewport().setOpaque(false);
	         scrollModos.setOpaque(false);
	        scrollModos.setBounds(60, 60, 600, 160);
	        scrollModos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
	        panelJugar.add(scrollModos);
	        
	        imageIcon = new ImageIcon("ImagenesAplicacion/ImagenesMenu/fondoMenu.jpg");
	         image = imageIcon.getImage().getScaledInstance(720,367, Image.SCALE_SMOOTH);
	      ImageIcon fondoJugar = new ImageIcon(image);
	        
	        JLabel lblFondoJugar = new JLabel();
	        lblFondoJugar.setBounds(0, 0, 720, 367);
	        lblFondoJugar.setIcon(fondoJugar);
	        panelJugar.add(lblFondoJugar);
	        
	       
	        
	        
	}
}
