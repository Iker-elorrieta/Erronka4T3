package vista;

import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Usuario;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Button;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;

public class MenuAdministrador extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable tablePartidas;
	private JTabbedPane tabbedPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAdministrador frame = new MenuAdministrador(null);
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
	public MenuAdministrador(Usuario UsurJugador) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
        contentPane.setLayout(null);
		
		// Crear la barra de navegación
        JToolBar navBar = new JToolBar();
        navBar.setBounds(0, 0, 712, 46);
        navBar.setFloatable(false);
        navBar.setForeground(Color.WHITE);
        navBar.setBackground(new Color(0, 69, 138));
        // Configurar el administrador de diseño
        navBar.setLayout(new BoxLayout(navBar, BoxLayout.X_AXIS));
        contentPane.add(navBar);
        
        Label labelJugadores = new Label("Jugadores");
        labelJugadores.setFont(new Font("Georgia", Font.BOLD, 12));
        labelJugadores.setBackground(new Color(0, 66, 132));
        labelJugadores.setAlignment(Label.CENTER);
        labelJugadores.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	tabbedPane.setSelectedIndex(0);
               
            }
        });
        navBar.add(labelJugadores);
        navBar.addSeparator();
        
        Label labelPartidas = new Label("Partidas");
        labelPartidas.setFont(new Font("Georgia", Font.BOLD, 12));
        labelPartidas.setBackground(new Color(0, 66, 132));
        labelPartidas.setAlignment(Label.CENTER);
        labelPartidas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	tabbedPane.setSelectedIndex(1);
               
            }
        });
        navBar.add(labelPartidas);
        navBar.addSeparator();
        
        Label labelPersonajes = new Label("Personajes");
        labelPersonajes.setFont(new Font("Georgia", Font.BOLD, 12));
        labelPersonajes.setBackground(new Color(0, 66, 132));
        labelPersonajes.setAlignment(Label.CENTER);
        labelPersonajes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	tabbedPane.setSelectedIndex(2);
               
            }
        });
        navBar.add(labelPersonajes);
        navBar.addSeparator();
        
        Label labelHabilidades = new Label("Habilidades");
        labelHabilidades.setFont(new Font("Georgia", Font.BOLD, 12));
        labelHabilidades.setBackground(new Color(0, 66, 132));
        labelHabilidades.setAlignment(Label.CENTER);
        labelHabilidades.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	tabbedPane.setSelectedIndex(3);
               
            }
        });
        navBar.add(labelHabilidades);
        navBar.addSeparator();
        
        Label labelModos = new Label("Modos");
        labelModos.setAlignment(Label.CENTER);
        labelModos.setBackground(new Color(0, 66, 132));
        labelModos.setFont(new Font("Georgia", Font.BOLD, 12));
        labelModos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	tabbedPane.setSelectedIndex(4);
               
            }
        });
        navBar.add(labelModos);
        
        JToolBar toolBar = new JToolBar();
        toolBar.setBounds(2, 345, 710, 45);
        toolBar.setFloatable(false);
        toolBar.setBackground(Color.LIGHT_GRAY);
        contentPane.add(toolBar);
        
        // Configurar el administrador de diseño
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));
        
        Button buttonEliminar = new Button("Eliminar");
        toolBar.add(buttonEliminar);
        
        Button buttonEditar = new Button("Editar");
        toolBar.add(buttonEditar);
        
        Button buttonAnadir = new Button("Añadir");
        toolBar.add(buttonAnadir);
        
         tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 712, 390);
        contentPane.add(tabbedPane);
        
        JPanel panelJugadores = new JPanel();
        panelJugadores.setBackground(new Color(225, 240, 255));
        tabbedPane.addTab("1", null, panelJugadores, null);
        panelJugadores.setLayout(null);
        
        JScrollPane scrollJugadores = new JScrollPane();
        scrollJugadores.setBounds(155, 75, 400, 200);
        panelJugadores.add(scrollJugadores);
        
        tablePartidas = new JTable();
        scrollJugadores.setViewportView(tablePartidas);
        
        JPanel panelPartidas = new JPanel();
        tabbedPane.addTab("2", null, panelPartidas, null);
        panelPartidas.setLayout(null);
        
        JPanel panelPersonajes = new JPanel();
        tabbedPane.addTab("3", null, panelPersonajes, null);
        panelPersonajes.setLayout(null);
        
        JPanel panelHabilidades = new JPanel();
        tabbedPane.addTab("4", null, panelHabilidades, null);
        panelHabilidades.setLayout(null);
        
        JPanel panelModos = new JPanel();
        tabbedPane.addTab("5", null, panelModos, null);
        panelModos.setLayout(null);
        
     
		
	}
}
