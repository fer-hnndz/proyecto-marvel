/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomarvelstratego;

/**
 *
 * @author Jorge Hernandez
 */

import java.awt.Dimension;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
public class GUI {
    JFrame ventana;
    
    // Puede que tengamos que quitar que sea una constante.
    
    /**
     * Inicia la interfaz del usuario.
     */
    public GUI() {
        ventana = new JFrame();
        // Hacer que la ventana no sea mas pequena que una resolucion de 800 x 500 pixeles;
        ventana.setMinimumSize(new Dimension(800, 500));
        ventana.setSize(800, 500);
        ventana.setTitle("Marvel Stratego");
        
        /*
        Inicializar el sistema de usuarios que administra toda la infraestructura para poder guardar
        informacion de lo usuarios.
        */
        
        SistemaUsuarios sistemaUsuarios = new SistemaUsuarios(ventana);
        
        // Por defecto se inicia en la pantalla con el menu principal.
        ventana.setVisible(true);
        cambiarMenuPrincipal();
    }
    
    /**
     * 
     * Devuelve la coordenada X donde el componente debe estar para aparecer centrado en el eje X.
     * @param anchoComponente La anchura del componente en pixeles.
     * @return La coordenada X donde el componente va a aparecer centrado.
     */
    public int centrarComponenteX(int anchoComponente) {
        return (ventana.getWidth() / 2) - (anchoComponente/2);
    }
    
    /**
     * Cambia la interfaz al menu principal
     */
    public void cambiarMenuPrincipal() {
        JPanel panel = new JPanel(); // TODO: BUSCAR MEJOR LAYOUT PARA ORGANIZAR LOS COMPONENTES
        panel.setBackground(new Color(2, 6, 23, 255));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //panel.setSize(800, 500);

        // Eliminar todos los contenidos anteriores en la ventana.
        ventana.getContentPane().removeAll();
        
        JLabel titulo = new JLabel("Menú Principal");
        titulo.setFont(new Font("Sans-Serif", Font.BOLD, 60));
       
        //titulo.setBounds(centrarComponenteX(250), 5, 250, 120);
        titulo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        titulo.setForeground(Color.WHITE);
        panel.add(titulo);
       
        // Agregar un espaciado entre titulo y botones
        Color MARVEL_RED = new Color(127, 29, 29, 255);
        panel.add(Box.createRigidArea(new Dimension(0, 32)));
        
        
        JButton botonLogin = new JButton("Iniciar Sesion");
        botonLogin.setSize(200, 40);
        botonLogin.setBackground(MARVEL_RED);
        botonLogin.setForeground(Color.WHITE);
        botonLogin.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        botonLogin.setFont(new Font("Sans-Serif", Font.ITALIC, 35));

        panel.add(botonLogin);
        
        // Agregar espaciado entre los dos botones
        panel.add(Box.createRigidArea(new Dimension(0, 8)));

        JButton botonRegistro = new JButton("Crear Jugador");
        botonRegistro.setSize(200, 40);
        botonRegistro.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        botonRegistro.setBackground(MARVEL_RED);
        botonRegistro.setForeground(Color.WHITE);
        botonRegistro.setFont(new Font("Sans-Serif", Font.ITALIC, 35));
        panel.add(botonRegistro);
        panel.setAlignmentX(SwingConstants.CENTER);
        
        ventana.add(panel);
    }
    
    
}
