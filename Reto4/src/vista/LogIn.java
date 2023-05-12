package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exceptions.PlayerNotFoundException;
import manager.GestionUsuarios;
import utils.TextPrompt;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class LogIn extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField passwordField;
	GestionUsuarios gestionUsur = new GestionUsuarios();

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn frame = new LogIn();
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
	public LogIn() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("ImagenesAplicacion/ImagenesMenu/logoWR.png"));
		setResizable(false);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 430);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 200, 391);
		contentPane.add(panel);
		panel.setLayout(null);
		
		ImageIcon imageIcon = new ImageIcon("ImagenesAplicacion/ImagenesMenu/fondoInicio.jpg");
        Image image = imageIcon.getImage().getScaledInstance(728, 430, Image.SCALE_SMOOTH);
        ImageIcon fondo = new ImageIcon(image);
        
        JLabel lblFondo = new JLabel();
        lblFondo.setIcon(fondo);
		lblFondo.setBounds(0, 0, 712, 391);
		contentPane.add(lblFondo);
		
		 imageIcon = new ImageIcon("ImagenesAplicacion/ImagenesMenu/logoWR.png");
         image = imageIcon.getImage().getScaledInstance(40, 50, Image.SCALE_SMOOTH);
        ImageIcon logo = new ImageIcon(image);
		
		JLabel lblLogo = new JLabel();
		lblLogo.setIcon(logo);
		lblLogo.setBackground(new Color(0, 0, 0));
		lblLogo.setBounds(10, 11, 50, 50);
		panel.add(lblLogo);
		
		JLabel lblSignIn = new JLabel("Sign In");
		lblSignIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignIn.setFont(new Font("Segoe UI Black", Font.BOLD, 21));
		lblSignIn.setBounds(45, 111, 95, 45);
		panel.add(lblSignIn);
		
		 imageIcon = new ImageIcon("ImagenesAplicacion/Utils/mal.png");
	        image = imageIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	      ImageIcon mal = new ImageIcon(image);
	       
	        
	        JLabel lblError = new JLabel();
	        lblError.setIcon(mal);
	        lblError.setBackground(new Color(0, 128, 255));
	        lblError.setBounds(167, 185, 15, 15);
	        panel.add(lblError);
	        lblError.setVisible(false);
	        
	        JLabel lblError_1 = new JLabel();
	        lblError_1.setIcon(mal);
	        lblError_1.setBackground(new Color(0, 128, 255));
	        lblError_1.setBounds(167, 235, 15, 15);
	        panel.add(lblError_1);
	        lblError_1.setVisible(false);
		
		
		textUsuario = new JTextField();
		textUsuario.setBounds(37, 180, 120, 25);
		panel.add(textUsuario);
		textUsuario.setColumns(10);
		
		TextPrompt placeholderUsur = new TextPrompt(" Usuario", textUsuario);
		placeholderUsur.setForeground(Color.DARK_GRAY);
		placeholderUsur.setBackground(Color.ORANGE);
		placeholderUsur.changeAlpha(0.75f);
		placeholderUsur.changeStyle(Font.ITALIC);
		
		JLabel lblNewAcc = new JLabel("Create account");
		lblNewAcc.setForeground(new Color(160, 160, 160));
		lblNewAcc.setFont(new Font("SansSerif", Font.BOLD, 10));
		lblNewAcc.setBounds(14, 366, 126, 14);
		lblNewAcc.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Abrir el JFrame de registro
                RegisterFrame registerFrame = new RegisterFrame();
                registerFrame.setVisible(true);
               
            }
        });
		panel.add(lblNewAcc);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(37, 230, 120, 25);
		panel.add(passwordField);
		
		TextPrompt placeholderPass = new TextPrompt(" Password", passwordField);
		placeholderPass.setForeground(Color.DARK_GRAY);
		placeholderPass.changeAlpha(0.75f);
		placeholderPass.changeStyle(Font.ITALIC);
		
		JLabel lblerrorLogin = new JLabel();
		lblerrorLogin.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 10));
		lblerrorLogin.setForeground(new Color(255, 51, 51));
		lblerrorLogin.setHorizontalAlignment(SwingConstants.LEFT);
		lblerrorLogin.setBounds(10, 311, 172, 25);
		panel.add(lblerrorLogin);
	    
		JButton btnNewButton = new JButton("Iniciar");
		btnNewButton.setFont(new Font("Segoe UI Historic", Font.PLAIN, 11));
		btnNewButton.setForeground(SystemColor.inactiveCaptionText);
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
						
							try {
								gestionUsur.login(textUsuario.getText(), new String(passwordField.getPassword()));
								dispose();
							} catch (PlayerNotFoundException e1) {
								// TODO Auto-generated catch block
								 lblError.setVisible(true);
							 lblError_1.setVisible(true);
							 lblerrorLogin.setText(e1.getMessage());
							}
						
							
							
						
					
				
			}
		});
		btnNewButton.setBounds(51, 277, 89, 23);
		panel.add(btnNewButton);
		
		
		
		
	}
}
