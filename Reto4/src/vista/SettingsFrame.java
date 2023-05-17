package vista;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import manager.GestionUsuarios;
import modelo.Jugador;
import utils.ConexionBD;
import utils.DBUtils;
import utils.DateConverter;
import utils.TextPrompt;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

class SettingsFrame extends JFrame {
	
	private JTextField textUsuario;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
    public GestionUsuarios gestionU= new GestionUsuarios();
    Connection conexion;
    public SettingsFrame() {
    	 try {
 			 conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERVISITANTE, DBUtils.PASS);
 		} catch (SQLException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
    	setIconImage(Toolkit.getDefaultToolkit().getImage("ImagenesAplicacion/ImagenesMenu/logoWR.png"));
    	setType(Type.UTILITY);
    	setResizable(false);
    	setBackground(new Color(192, 192, 192));
    	getContentPane().setBackground(new Color(255, 255, 255));
        // Configurar el JFrame de registro
        setTitle("Settings");
        setSize(230, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(47, 151, 255));
        panel.setBounds(0, 0, 214, 35);
        getContentPane().add(panel);
        
        ImageIcon imageIcon = new ImageIcon("ImagenesAplicacion/ImagenesMenu/logoWR.png");
        Image image = imageIcon.getImage().getScaledInstance(20, 27, Image.SCALE_SMOOTH);
       ImageIcon logo = new ImageIcon(image);
        panel.setLayout(null);
       
        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(21, 0, 35, 35);
        lblLogo.setIcon(logo);
        panel.add(lblLogo);
        
        JButton btnEditar = new JButton("Editar Usuario");
        btnEditar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 CambioUsuario frame = new CambioUsuario();
        		 frame.setVisible(true);
        		 dispose();
        	}
        });
       
        btnEditar.setBounds(46, 151, 122, 23);
        btnEditar.setBackground(new Color(47, 151, 255));
        getContentPane().add(btnEditar);
        
        JButton btnEliminar = new JButton("Eliminar Usuario");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(SettingsFrame.this, "¿Estás seguro de que quieres borrar tu cuenta?", "Confirmación de borrado", JOptionPane.YES_NO_OPTION);

                if (respuesta == JOptionPane.YES_OPTION) {
                    // Lógica para borrar la cuenta
                    System.out.println("La cuenta ha sido borrada exitosamente.");
                } else {
                    System.out.println("La cuenta no ha sido borrada.");
                }
            }
        });

        btnEliminar.setBounds(46, 91, 122, 23);
        btnEliminar.setBackground(new Color(47, 151, 255));
        getContentPane().add(btnEliminar);
        
      
        
       
        
        
       
        

        setVisible(true);
    }
}