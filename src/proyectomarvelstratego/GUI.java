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
import java.awt.event.*;
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
        
        botonRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                cambiarCrearUsuario();
            }
        });
        
        panel.add(botonRegistro);
        
        
        panel.setAlignmentX(SwingConstants.CENTER);
        
        ventana.add(panel);
        ventana.setVisible(true);
    }
    
    public void cambiarCrearUsuario() {
        
        // Eliminar los contenidos de la ventana
        ventana.getContentPane().removeAll();
        //ventana.setVisible(false);
        
        JPanel panel = new JPanel(); // TODO: BUSCAR MEJOR LAYOUT PARA ORGANIZAR LOS COMPONENTES
        ventana.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel titulo = new JLabel("Crear Usuario");
        titulo.setFont(new Font("Sans-Serif", Font.BOLD, 62));
        titulo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panel.add(titulo);
        
        JButton botonAtras = new JButton("Volver al Menú Principal");
        botonAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                cambiarMenuPrincipal();
            }
        });
        
        panel.add(botonAtras);
        // Agregar espaciado
        
        // Texto que se mostrara en caso de que ocurra un error o se haya registrado correctamente.
        JLabel textoMensaje = new JLabel("");
        
        
        panel.add(Box.createRigidArea(new Dimension(0, 32)));
        
        JLabel textoNombreUsuario = new JLabel("Nombre de Usuario");
        textoNombreUsuario.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        JTextField campoNombreUsuario = new JTextField();
        campoNombreUsuario.setSize(250, 40);
        
        panel.add(textoNombreUsuario);
        panel.add(campoNombreUsuario);
        
        JLabel textoContrasena = new JLabel("Contraseña");
        JTextField campoContrasena = new JTextField();
        
        panel.add(textoContrasena);
        panel.add(campoContrasena);
        
        // Espaciado
        panel.add(Box.createRigidArea(new Dimension(0, 62)));
        
        JButton botonRegistrar = new JButton("Crear Usuario");
        panel.add(botonRegistrar);
        
        ventana.add(panel);
        ventana.setVisible(true);
    }
    
    
}
