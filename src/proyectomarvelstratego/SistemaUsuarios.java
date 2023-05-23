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

/*
    Esta clase administra el inicio de sesion y la informacion de los usuarios
*/
public class SistemaUsuarios {

    /**
     * Actualiza la ventana para mostrar el menu principal
     * @param ventana La ventana de la aplicacion
     */
    public SistemaUsuarios(JFrame ventana) {
        
        JPanel panel = new JPanel(new GridLayout());
        
        JLabel titulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        panel.add(titulo);
        
        ventana.getContentPane().removeAll();
        ventana.add(panel);
    }
}

