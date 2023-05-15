package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import modelo.Personaje;

public class CartaMouseListener extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        // Obtener el índice de la tarjeta haciendo uso del método getComponentZOrder()
       JPanel panel = (JPanel) e.getComponent();
        System.out.println(panel.getToolTipText());
     }
}
