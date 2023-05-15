package vista;



import javax.swing.JFrame;

import manager.GestionEstadisticas;
import manager.GestionHabilidades;
import manager.GestionModos;
import manager.GestionPartidas;
import manager.GestionPersonajes;
import manager.GestionUsuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddRowFrame extends JFrame implements ActionListener {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel[] columnLabels;
    private JTextField[] columnFields;
    private JButton saveButton, cancelButton;
    private int numColumns;
    GestionModos gestionM = new GestionModos();
    GestionHabilidades gestionH = new GestionHabilidades();
    GestionPersonajes gestionC = new GestionPersonajes();
    GestionUsuarios gestionU = new GestionUsuarios();
    GestionPartidas gestionP = new GestionPartidas();
    
    public AddRowFrame(String[] columnNames) {
        this.numColumns = columnNames.length;
        this.columnLabels = new JLabel[numColumns];
        this.columnFields = new JTextField[numColumns];
        
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
        	model
            // (you would need to implement this)
            System.out.println("Data saved!");
            
            // Close the frame
            this.dispose();
        } else if (e.getSource() == cancelButton) {
            // Close the frame
            this.dispose();
        }
    }
}



