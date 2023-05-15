package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.ConexionBD;
import utils.DBUtils;
import utils.RotatedIcon;

import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
import manager.GestionPartidas;
import manager.GestionPersonajes;
import manager.GestionUsuarios;
import modelo.Jugador;
import modelo.Modo;
import modelo.Partida;
import modelo.Personaje;
import modelo.Usuario;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Choice;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class MenuJugador extends JFrame {
	private MenuJugador frame;
	private ArrayList<ImageIcon> imagenes;
    private JLabel labelImagen;
	private JPanel contentPane;
	private  JPanel panelPersonajes;
	private  JPanel panelJugar;
	private JTabbedPane tabbedPane;
	MetodosVista metodosVista = new MetodosVista();
	GestionPersonajes gestionPJ = new GestionPersonajes();
	GestionPartidas gestionP = new GestionPartidas();
	GestionModos gestionM = new GestionModos();
	GestionUsuarios gestionU = new GestionUsuarios();
	Metodos metodos = new Metodos();
	private JScrollPane scrollPersonajes ;
	private  JScrollPane scrollModos;
	private JScrollPane scrollPartidas;
	private JLabel lblLvL;
	private JLabel lblRank;
	public static Jugador j1;
	Connection conexion;
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
		 try {
			 conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage("ImagenesAplicacion/ImagenesMenu/logoWR.png"));
		
		  j1 = (Jugador) usuario;
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
            	metodosVista.crearPanelesModos(scrollModos, gestionM.cargaInicialModos(conexion));
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
	            	lblLvL.setText(String.valueOf(gestionU.getNivelById(conexion, j1.getId())));
	            	 metodosVista.mostrarPartidas(gestionP.getPartidasByJugador(conexion, j1.getNombre()), scrollPartidas);
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
            	
            	metodosVista.crearCartas(gestionPJ.getPersonajeByJugadorLvL(conexion, gestionU.getNivelById(conexion, j1.getId())), scrollPersonajes);
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
		tabbedPane.addTab("Perfil", null, panelPerfil, null);
		

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
	        
	        JLabel lblSettings = new JLabel("");
	        lblSettings.setBounds(675, 41, 25, 25);
	        lblSettings.setIcon(MetodosVista.rescaleImage("ImagenesAplicacion/Utils/settings.png", 25, 25));
	        lblSettings.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	                // Abrir el JFrame de registro
	                SettingsFrame settingsFrame = new SettingsFrame();
	                settingsFrame.setVisible(true);
	               
	            }
	        });
	        panelPerfil.add(lblSettings);
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
	        
	         lblLvL = new JLabel(String.valueOf(j1.getNivel()));
	        lblLvL.setForeground(new Color(240, 230, 210));
	        lblLvL.setFont(new Font("Georgia", Font.BOLD, 15));
	        lblLvL.setHorizontalAlignment(SwingConstants.CENTER);
	        lblLvL.setBounds(67, 187, 45, 15);
	        panelPerfil.add(lblLvL);
	     
	        
	         scrollPartidas = new JScrollPane();
	        scrollPartidas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	        scrollPartidas.setEnabled(false);
	        scrollPartidas.setViewportBorder(null);
	        scrollPartidas.setBounds(216, 265, 465, 75);
	        panelPerfil.add(scrollPartidas);
	        metodosVista.mostrarPartidas(gestionP.getPartidasByJugador(conexion, j1.getNombre()), scrollPartidas);
	         
	        lblRank = new JLabel();
	        lblRank.setHorizontalAlignment(SwingConstants.CENTER);
	     
	       
	        lblRank.setBackground(Color.WHITE);
	      lblRank.setBounds(35, 212, 126, 128);
	        panelPerfil.add(lblRank);
	        metodosVista.cambiarIconoPorRango(j1.getRango(), lblRank);
	        
	        imageIcon = new ImageIcon("ImagenesAplicacion/ImagenesMenu/fondoMenu.jpg");
	         image = imageIcon.getImage().getScaledInstance(720,367, Image.SCALE_SMOOTH);
	      ImageIcon fondoPerfil = new ImageIcon(image);
	        
	        JLabel lblFondoPerfil = new JLabel();
	        lblFondoPerfil.setBounds(0, 0, 720, 367);
	        lblFondoPerfil.setIcon(fondoPerfil);
	        panelPerfil.add(lblFondoPerfil);
	        
	        
	       
	      
	     
	         panelPersonajes = new JPanel();
	        tabbedPane.addTab("Personajes", null, panelPersonajes, null);
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
	        tabbedPane.addTab("Jugar", null, panelJugar, null);
	        panelJugar.setLayout(null);
	        
	         scrollModos = new JScrollPane();
	         scrollModos.getViewport().setOpaque(false);
	         scrollModos.setOpaque(false);
	        scrollModos.setBounds(60, 60, 600, 160);
	        scrollModos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
	        panelJugar.add(scrollModos);
	        
	         JPanel panelPartida = new JPanel();
	         panelPartida.setBounds(71, 270, 578, 34);
	         panelPartida.setLayout(new BoxLayout(panelPartida, BoxLayout.Y_AXIS));
	         panelJugar.add(panelPartida);
	         
	        imageIcon = new ImageIcon("ImagenesAplicacion/ImagenesMenu/fondoMenu.jpg");
	         image = imageIcon.getImage().getScaledInstance(720,367, Image.SCALE_SMOOTH);
	      ImageIcon fondoJugar = new ImageIcon(image);
	        
	      
	      Choice choice = new Choice();
	        choice.setBounds(444, 318, 118, 20);
	        ArrayList<Personaje> p=gestionPJ.getPersonajeByJugadorLvL(conexion, gestionU.getNivelById(conexion, j1.getId()));
	      for (Personaje personaje : p) {
			choice.add(personaje.getName());
		}

	        panelJugar.add(choice);
	        Choice choice_1 = new Choice();
	        choice_1.setBounds(180, 318, 118, 20);
	        ArrayList<Modo> m=gestionM.cargaInicialModos(conexion);
	        for (Modo modo : m) {
				choice_1.add(modo.getNombre());
			}
	        panelJugar.add(choice_1);
	        
	        JButton btnJugarPartida = new JButton("Play");
	        btnJugarPartida.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		Partida partida =gestionP.crearPartidaAleatoria(conexion, j1, choice_1.getSelectedItem(), choice.getSelectedItem());
	        		if(partida.isResultado())
	        			gestionU.subirNivel(conexion, j1.getId());
	        		gestionP.insertarPartida(conexion, partida);
	        		metodosVista.mostrarPartida(partida, panelPartida);
	        	}
	        });
	        btnJugarPartida.setBounds(323, 315, 89, 23);
	        panelJugar.add(btnJugarPartida);
	        
	       
	        
	        JLabel lblFondoJugar = new JLabel();
	        lblFondoJugar.setBounds(0, 0, 720, 367);
	        lblFondoJugar.setIcon(fondoJugar);
	        panelJugar.add(lblFondoJugar);
	        
	        
	       
	        
	        
	       
	        
	        
	}
}
