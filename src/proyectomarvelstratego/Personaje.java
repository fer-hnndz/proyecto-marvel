package proyectomarvelstratego;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jorge Hernandez
 */

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
public class Personaje {
    private BufferedImage icono;
    private int currentRow;
    private int currentColumn;
    String nombre;
    int rango;
    boolean esHeroe;
    
    public Personaje(String nombre, int rango, boolean esHeroe) {
       this.nombre = nombre;
       this.rango = rango;
       this.esHeroe = esHeroe;
    }
    
    public static Personaje[] getFichasHeroes() {
        Personaje personajes[] = new Personaje[40];
        
        personajes[0] = new Personaje("Mr. Fantastic", 10, true);
        personajes[1] = new Personaje("Captain America", 9, true);
        personajes[2] = new Personaje("Professor X", 8, true);
        personajes[3] = new Personaje("Nick Fury", 8, true);
        
        return personajes;
        
    }
}
