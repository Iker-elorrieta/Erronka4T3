package vista;



import javax.swing.JFrame;

import manager.GestionEstadisticas;
import manager.GestionHabilidades;
import manager.GestionModos;
import manager.GestionPartidas;
import manager.GestionPersonajes;
import manager.GestionUsuarios;
import modelo.Habilidad;
import modelo.Jugador;
import modelo.Modo;
import modelo.Partida;
import modelo.Personaje;
import modelo.Usuario;
import utils.ConexionBD;
import utils.DBUtils;
import utils.DateConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class AddRowFrame extends JFrame implements ActionListener {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel[] columnLabels;
    private JTextField[] columnFields;
    private JButton saveButton, cancelButton;
    private int numColumns;
    private  String tabla;;
    GestionModos gestionM = new GestionModos();
    GestionHabilidades gestionH = new GestionHabilidades();
    GestionPersonajes gestionC = new GestionPersonajes();
    GestionUsuarios gestionU = new GestionUsuarios();
    GestionPartidas gestionP = new GestionPartidas();
    GestionEstadisticas gestionE = new GestionEstadisticas();
	private Connection conexion;
    
    public AddRowFrame(String[] columnNames, String tabla) {
    	try {
			 conexion = ConexionBD.obtenerConexion(DBUtils.URL, DBUtils.USERADMIN, DBUtils.PASS);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        this.numColumns = columnNames.length;
        this.columnLabels = new JLabel[numColumns];
        this.columnFields = new JTextField[numColumns];
        this.tabla= tabla;
        
        // Set frame properties
        this.setTitle("Add Row");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 200);
        this.setLayout(new GridLayout(numColumns+1, 2));
        
        // Create column labels and fields
        for (int i = 0; i < numColumns; i++) {
            columnLabels[i] = new JLabel(columnNames[i] + ":");
            this.add(columnLabels[i]);
            columnFields[i] = new JTextField();
            this.add(columnFields[i]);
        }
        
        // Create buttons
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        this.add(saveButton);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        this.add(cancelButton);
        
        // Make the frame visible
        this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            ArrayList<Object> columnValues = new ArrayList<>();
            for (JTextField field : columnFields) {
                String fieldValue = field.getText();
                columnValues.add(fieldValue);
            }
            
            switch (tabla) {
                case "modos":
                    Modo modo = new Modo();
                    modo.setId(Integer.parseInt(columnFields[0].getText()));
                    modo.setNombre(columnFields[1].getText());
                    gestionM.insertarModo(conexion, modo);
                    break;
                case "abilities":
                    Habilidad habilidad = new Habilidad();
                    habilidad.setCod(Integer.parseInt(columnFields[0].getText()));
                    habilidad.setCod_personaje(Integer.parseInt(columnFields[1].getText()));
                    habilidad.setNombre(columnFields[2].getText());
                    habilidad.setDescripcion(columnFields[3].getText());
                    gestionH.insertarHabilidad(conexion, habilidad);
                    break;
                case "champions":
                    Personaje personaje = new Personaje();
                    personaje.setId(Integer.parseInt(columnFields[0].getText()));
                    personaje.setName(columnFields[1].getText());
                    personaje.setRole(columnFields[2].getText());
                    personaje.setDifficulty(Integer.parseInt(columnFields[3].getText()));
                    personaje.setAttackDamage(Integer.parseInt(columnFields[4].getText()));
                    personaje.setAbilityPower(Integer.parseInt(columnFields[5].getText()));
                    personaje.setHealth(Integer.parseInt(columnFields[6].getText()));
                    personaje.setMana(Integer.parseInt(columnFields[7].getText()));
                    gestionC.insertarPersonaje(conexion, personaje);
                    break;
                case "players":
                    Jugador jugador = new Jugador();
                    jugador.setId(Integer.parseInt(columnFields[0].getText()));
                    jugador.setNombre(columnFields[1].getText());
                    jugador.setContrasenya(columnFields[2].getText());
                  
                    jugador.setNivel(Integer.parseInt(columnFields[4].getText()));
                    jugador.setRango(columnFields[5].getText());   
                    String fechaTexto = columnFields[3].getText();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        java.util.Date utilDate = dateFormat.parse(fechaTexto);
                        jugador.setFecha(DateConverter.toSqlDate(utilDate));
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    jugador.setBloqueado(Boolean.parseBoolean(columnFields[6].getText()));
                    try {
                        gestionU.insertarJugador(conexion, jugador);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "matches":
                    Partida partida = new Partida();
                    partida.setCod_partida(Integer.parseInt(columnFields[0].getText()));
                    partida.setJugador(gestionU.getJugadorByNombre(conexion, columnFields[1].getText())); // Asigna el jugador correspondiente
                    partida.setModo(gestionM.getModoByName(conexion, columnFields[2].getText())); // Asigna el modo correspondiente
                    partida.setPersonaje(gestionC.getPersonajeByNombre(conexion, columnFields[3].getText())); // Asigna el personaje correspondiente
                    partida.setEstadisticas(gestionE.obtenerEstadistica(columnFields[4].getText())); // Asigna las estadísticas correspondientes
                    partida.setResultado(Boolean.parseBoolean(columnFields[5].getText())); // Asigna el resultado correspondiente
                    
                    // Asigna la fecha
                     fechaTexto = columnFields[6].getText();
                     dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        java.util.Date utilDate = dateFormat.parse(fechaTexto);
                        partida.setFecha(DateConverter.toSqlDate(utilDate));
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    
                    partida.setDuracion(Integer.parseInt(columnFields[7].getText())); // Asigna la duración
                    
                    gestionP.insertarPartida(conexion, partida);
                    break;

                default:
                    System.out.println("Table not supported");
                    break;
            }

            // Close the frame
            this.dispose();
        } else if (e.getSource() == cancelButton) {
            // Close the frame
            this.dispose();
        }
    }

         
}



