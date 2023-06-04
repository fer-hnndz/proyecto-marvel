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
        personajes[4] = new Personaje("Spider-Man", 7, true);
        personajes[5] = new Personaje("Wolverine", 7, true);
        personajes[6] = new Personaje("Namor", 7, true);
        personajes[7] = new Personaje("Daredevil", 6, true);
        personajes[8] = new Personaje("Silver Surfer", 6, true);
        personajes[9] = new Personaje("Hulk", 6, true);
        personajes[10] = new Personaje("Iron Man", 6, true);
        return personajes;
        
    }
}
