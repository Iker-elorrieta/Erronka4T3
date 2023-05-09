package vista;

import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

import controlador.MetodosVista;
import controlador.Metodos;
import modelo.Usuario;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JViewport;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Button;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuAdministrador extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTabbedPane tabbedPane;
	private JPanel panelJugadores;
	private  JPanel panelPartidas ;
	private  JPanel panelPersonajes;
	private  JPanel panelHabilidades;
	private  JPanel panelModos;
	private JScrollPane scrollJugadores;
	private MetodosVista metodosVista = new MetodosVista();
	private Metodos metodos = new Metodos();
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
        		
        		
        		    boolean isEnabled ; 
        		    if(table.isEnabled())
        		    	  isEnabled = false; 
        		    else
        		    	  isEnabled = true; 
        		    table.setEnabled(isEnabled);
        		    table.clearSelection();
        		    
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
                    // Eliminar la fila seleccionada del modelo de tabla
                   DefaultTableModel modelo= (DefaultTableModel) table.getModel();
                   int rowCount = modelo.getRowCount();
                   if (rowCount == 0 || !metodosVista.isRowIncomplete(modelo, rowCount - 1)) {
                	    // La última fila está completa o la tabla está vacía,
                	    // por lo que podemos agregar una nueva fila.
                	    modelo.addRow(new Object[modelo.getColumnCount()]);
                	}


               
                }
        	}
        });
        toolBar.add(buttonAnadir);
        
        Button buttonGuardar = new Button("Guardar");
        buttonGuardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JTabbedPane tabbedPane = (JTabbedPane) getContentPane().getComponent(2);
        		JPanel selectedPanel = (JPanel) tabbedPane.getSelectedComponent();
        		int selectedPanelInt = tabbedPane.getSelectedIndex();
        		JScrollPane scroll = (JScrollPane) selectedPanel.getComponent(0);
        		JViewport vp = (JViewport) scroll.getComponent(0);
        		JTable table = (JTable) vp.getComponent(0);
        		
        		 int selectedRow = table.getSelectedRow();
        		// Verificar si hay alguna fila seleccionada
                if (selectedRow != -1) {
                    // Eliminar la fila seleccionada del modelo de tabla
                   DefaultTableModel modelo= (DefaultTableModel) table.getModel();
                  metodos.guardarCambios(modelo, selectedPanelInt);


               
                }
        	
        	}
        });
        toolBar.add(buttonGuardar);
        
         tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 712, 390);
        contentPane.add(tabbedPane);
        
        panelJugadores = new JPanel();
        panelJugadores.setBackground(new Color(225, 240, 255));
        tabbedPane.addTab("jugadores", null, panelJugadores, null);
        panelJugadores.setLayout(null);
        
        panelPartidas = new JPanel();
        panelPartidas.setBackground(new Color(225, 240, 255));
        tabbedPane.addTab("partidas", null, panelPartidas, null);
        panelPartidas.setLayout(null);
        
         panelPersonajes = new JPanel();
         panelPersonajes.setBackground(new Color(225, 240, 255));
        tabbedPane.addTab("personajes", null, panelPersonajes, null);
        panelPersonajes.setLayout(null);
        
         panelHabilidades = new JPanel();
         panelHabilidades.setBackground(new Color(225, 240, 255));
        tabbedPane.addTab("habilidades", null, panelHabilidades, null);
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
