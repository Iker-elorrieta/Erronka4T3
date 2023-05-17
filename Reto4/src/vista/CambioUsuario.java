package vista;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.MetodosVista;
import manager.GestionUsuarios;
import modelo.Jugador;
import utils.ConexionBD;
import utils.DBUtils;
import utils.DateConverter;
import utils.TextPrompt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;

public class CambioUsuario extends JFrame {
	private JTextField textUsuario;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
    public GestionUsuarios gestionU= new GestionUsuarios();
    Connection conexion;
    private JButton btnCambiarDatos;
    private JLabel lblError;
   
    public CambioUsuario() {
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
        setTitle("EditarUsuario");
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
        
        JLabel lblRegistrarse = new JLabel("Editar");
        lblRegistrarse.setBounds(0, 23, 214, 52);
        getContentPane().add(lblRegistrarse);
        lblRegistrarse.setFont(new Font("Segoe UI Historic", Font.BOLD, 18));
        lblRegistrarse.setHorizontalAlignment(SwingConstants.CENTER);
        
      
        
        textUsuario = new JTextField();
        textUsuario.setBounds(48, 72, 120, 25);
        getContentPane().add(textUsuario);
        textUsuario.setColumns(10);
        
        TextPrompt placeholderUsur = new TextPrompt(" Usuario", textUsuario);
      	placeholderUsur.changeAlpha(0.75f);
      	placeholderUsur.changeStyle(Font.ITALIC);
      		
        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(48, 118, 120, 25);
        getContentPane().add(passwordField_1);
        
        TextPrompt placeholderContra1 = new TextPrompt(" Contraseña", passwordField_1);
        placeholderContra1.changeAlpha(0.75f);
        placeholderContra1.changeStyle(Font.ITALIC);
        
        passwordField_2 = new JPasswordField();
        passwordField_2.setBounds(48, 164, 120, 25);
        getContentPane().add(passwordField_2);
        
        TextPrompt placeholderContra2 = new TextPrompt(" Repetir Contraseña", passwordField_2);
        placeholderContra2.changeAlpha(0.75f);
        placeholderContra2.changeStyle(Font.ITALIC);
        
        imageIcon = new ImageIcon("ImagenesAplicacion/Utils/mal.png");
        image = imageIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
      ImageIcon mal = new ImageIcon(image);
       
        
        JLabel lblError = new JLabel();
        lblError.setIcon(mal);
        lblError.setBackground(new Color(0, 128, 255));
        lblError.setBounds(178, 77, 15, 15);
        getContentPane().add(lblError);
        lblError.setVisible(false);
        
        JLabel lblError_1 = new JLabel();
        lblError_1.setIcon(mal);
        lblError_1.setBackground(new Color(0, 128, 255));
        lblError_1.setBounds(178, 123, 15, 15);
        getContentPane().add(lblError_1);
        lblError_1.setVisible(false);
        
        JLabel lblError_2 = new JLabel();
        lblError_2.setIcon(mal);
        lblError_2.setBackground(new Color(0, 128, 255));
        lblError_2.setBounds(178, 169, 15, 15);
        getContentPane().add(lblError_2);
        lblError_2.setVisible(false);
        
        JButton btnNewButton = new JButton("OK");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		  // Verificar si el campo de usuario está vacío
        	      if (textUsuario.getText().isEmpty()) {
        	         // Mostrar un mensaje de error al usuario
        	    	  lblError.setVisible(true);
        	         
        	      }else {
        	    	  lblError.setVisible(false);
        	      }
        	      
        	      if (passwordField_1.getPassword().length == 0 ) {
        	          // Mostrar un mensaje de error al usuario
        	    	  lblError_1.setVisible(true);
        	       }else {
        	    	   lblError_1.setVisible(false);
				}
        	      
        	      if (passwordField_2.getPassword().length == 0) {
        	          // Mostrar un mensaje de error al usuario
        	    	  lblError_2.setVisible(true);
        	       }else {
         	    	  lblError_2.setVisible(false);
				}
        	      
        	      // Verificar si los campos de contraseña son iguales
        	      if (!Arrays.equals(passwordField_1.getPassword(), passwordField_2.getPassword())) {
        	         // Mostrar un mensaje de error al usuario
        	    	  
        	    	  lblError_2.setVisible(true);
        	      }else {
        	    	 
        	    	  lblError_2.setVisible(false);
        	      }
        	      try {
					if(gestionU.usuarioExistente(conexion, textUsuario.getText()))
						  lblError.setVisible(true);
					  else
						  lblError.setVisible(false);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
        	      
        	      // Si todo está bien, continua
        	      if(!lblError_2.isVisible()&&!lblError_1.isVisible()&&!lblError.isVisible()){
        	      Date fechareg = new Date();
        	     Jugador player=MenuJugador.j1;
        	      Jugador jugador = new Jugador(player.getId(), textUsuario.getText(),String.valueOf(passwordField_1.getPassword()), gestionU.getNivelById(conexion, player.getId()), player.getRango(),player.getFecha(), player.isBloqueado());
        	      try {
					gestionU.actualizarJugador(conexion, jugador);
					 dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	      }
        	   }
        	
        });
        btnNewButton.setBounds(10, 227, 75, 23);
        getContentPane().add(btnNewButton);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 dispose(); // Cierra el JFrame
        	}
        });
        btnCancelar.setBounds(123, 227, 81, 23);
        getContentPane().add(btnCancelar);
        
        
       
        
        // Hacer visible el JFrame de registro
        setVisible(true);
    }
}