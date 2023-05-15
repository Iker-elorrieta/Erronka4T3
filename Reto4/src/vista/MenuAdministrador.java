package vista;

import java.awt.EventQueue;

import javax.swing.BoxLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.table.DefaultTableModel;

import controlador.MetodosVista;
import manager.GestionHabilidades;
import manager.GestionModos;
import manager.GestionPartidas;
import manager.GestionPersonajes;
import manager.GestionUsuarios;
import controlador.Metodos;
import modelo.Usuario;
import utils.ConexionBD;
import utils.DBUtils;

import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JViewport;
import javax.swing.JLabel;
import java.awt.Color;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import java.awt.Button;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuAdministrador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panelJugadores;
	private  JPanel panelPartidas ;
	private  JPanel panelPersonajes;
	private  JPanel panelHabilidades;
	private  JPanel panelModos;
	private MetodosVista metodosVista = new MetodosVista();
	private Metodos metodos = new Metodos();
	private List<String> sentenciasSQL = new ArrayList<>();
	 GestionModos gestionM = new GestionModos();
	    GestionHabilidades gestionH = new GestionHabilidades();
	    GestionPersonajes gestionC = new GestionPersonajes();
	    GestionUsuarios gestionU = new GestionUsuarios();
	    GestionPartidas gestionP = new GestionPartidas();
	    GestionPersonajes gestionPJ = new GestionPersonajes();

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
		setIconImage(Toolkit.getDefaultToolkit().getImage("ImagenesAplicacion/ImagenesMenu/logoWR.png"));
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(MenuAdministrador.this,
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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 728, 430);

		contentPane = new JPanel();
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
            	
            	try {
					metodosVista.mostrarTabla(1, panelJugadores);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
               try {
				metodosVista.mostrarTabla(2, panelPartidas);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
               tabbedPane.setSelectedIndex(1);
               contentPane.updateUI();
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
            	
            	try {
					metodosVista.mostrarTabla(3, panelPersonajes);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
            	try {
					metodosVista.mostrarTabla(4, panelHabilidades);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
            	try {
					metodosVista.mostrarTabla(5, panelModos);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
        buttonEliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JTabbedPane tabbedPane = (JTabbedPane) getContentPane().getComponent(2);
        		JPanel selectedPanel = (JPanel) tabbedPane.getSelectedComponent();
        		JScrollPane scroll = (JScrollPane) selectedPanel.getComponent(0);
        		JViewport vp = (JViewport) scroll.getComponent(0);
        		JTable table = (JTable) vp.getComponent(0);
        		
        		 int selectedRow = table.getSelectedRow();
        		// Verificar si hay alguna fila seleccionada
                if (selectedRow != -1) {
                    // Eliminar la fila seleccionada del modelo de tabla
                   DefaultTableModel modelo= (DefaultTableModel) table.getModel();
                   modelo.removeRow(selectedRow);
                   String nombre = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
                  if(nombre.equalsIgnoreCase("players")){
                	   try {
						gestionU.eliminarJugador(selectedRow);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                   }else if(nombre.equalsIgnoreCase("matches")){
                	  
                   }else if(nombre.equalsIgnoreCase("champions")){
                	  
                   }else if(nombre.equalsIgnoreCase("abilities")){
                	  
                   }else if(nombre.equalsIgnoreCase("modos")) {
                	   
                   }
                }
        	}
        });
        toolBar.add(buttonEliminar);
        
        Button buttonEditar = new Button("Editar");
        buttonEditar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JTabbedPane tabbedPane = (JTabbedPane) getContentPane().getComponent(2);
        		JPanel selectedPanel = (JPanel) tabbedPane.getSelectedComponent();
        		JScrollPane scroll = (JScrollPane) selectedPanel.getComponent(0);
        		JViewport vp = (JViewport) scroll.getComponent(0);
        		JTable table = (JTable) vp.getComponent(0);
        		 DefaultTableModel modelo= (DefaultTableModel) table.getModel();
        		 int selectedRow = table.getSelectedRow();
        		 if (selectedRow != -1) {
        		
        		  EditRowFrame editarFila = null;
				try {
					editarFila = new EditRowFrame(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()),selectedRow ,ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERADMIN, DBUtils.PASS));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		    editarFila.setVisible(true);
        		 }
        	}
        });
        toolBar.add(buttonEditar);
        
        Button buttonAnadir = new Button("Añadir");
        buttonAnadir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JTabbedPane tabbedPane = (JTabbedPane) getContentPane().getComponent(2);
        		JPanel selectedPanel = (JPanel) tabbedPane.getSelectedComponent();
        		JScrollPane scroll = (JScrollPane) selectedPanel.getComponent(0);
        		JViewport vp = (JViewport) scroll.getComponent(0);
        		JTable table = (JTable) vp.getComponent(0);
        		
        		 int selectedRow = table.getSelectedRow();
        		// Verificar si hay alguna fila seleccionada
                if (selectedRow != -1) {
                
                   DefaultTableModel modelo= (DefaultTableModel) table.getModel();
                   int numeroColumnas = modelo.getColumnCount();

                   String[] columnas = new String[numeroColumnas];
                   for (int i = 0; i < numeroColumnas; i++) {
                       columnas[i] = modelo.getColumnName(i);
                   }
                   AddRowFrame anadirFila = new AddRowFrame(columnas);
                   anadirFila.setVisible(true);
               
                }
        	}
        });
        toolBar.add(buttonAnadir);
        
       
        
         tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 712, 390);
        contentPane.add(tabbedPane);
        
        panelJugadores = new JPanel();
        panelJugadores.setBackground(new Color(225, 240, 255));
        tabbedPane.addTab("players", null, panelJugadores, null);
        panelJugadores.setLayout(null);
        
        panelPartidas = new JPanel();
        panelPartidas.setBackground(new Color(225, 240, 255));
        tabbedPane.addTab("matches", null, panelPartidas, null);
        panelPartidas.setLayout(null);
        
         panelPersonajes = new JPanel();
         panelPersonajes.setBackground(new Color(225, 240, 255));
        tabbedPane.addTab("champions", null, panelPersonajes, null);
        panelPersonajes.setLayout(null);
        
         panelHabilidades = new JPanel();
         panelHabilidades.setBackground(new Color(225, 240, 255));
        tabbedPane.addTab("abilities", null, panelHabilidades, null);
        panelHabilidades.setLayout(null);
        
         panelModos = new JPanel();
         panelModos.setBackground(new Color(225, 240, 255));
        tabbedPane.addTab("modos", null, panelModos, null);
        panelModos.setLayout(null);
        
        JLabel lblIntroduccion = new JLabel("New label");
        lblIntroduccion.setBounds(220, 150, 220, 54);
        contentPane.add(lblIntroduccion);
        
     
		
	}
}
