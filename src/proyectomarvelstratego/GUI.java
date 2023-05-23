/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomarvelstratego;

/**
 *
 * @author Jorge Hernandez
 */

import java.awt.GridLayout;
import javax.swing.*;
public class GUI {
    JFrame ventana;
    
    // Puede que tengamos que quitar que sea una constante.
    
    /**
     * Inicia la interfaz del usuario.
     */
    public GUI() {
        ventana = new JFrame();
        
        // Modificar valores
        ventana.setTitle("Marvel Stratego");
        ventana.setSize(800, 500); // Estos valores deberian de poder
        
        new SistemaUsuarios(ventana);
        
        // POr defecto se inicia en la pantalla con el menu principal.
        ventana.setVisible(true);
    }
    
    /**
     * Cambia la interfaz al menu principal
     */
    public void cambiarMenuPrincipal() {
        JPanel panel = new JPanel(new GridLayout());
        
        JLabel titulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        panel.add(titulo);
        
        ventana.getContentPane().removeAll();
        ventana.add(panel);
    }
    
}
