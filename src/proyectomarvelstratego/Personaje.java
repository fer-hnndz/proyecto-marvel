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
import java.util.ArrayList;
public class Personaje {
    private BufferedImage icono;
    private int currentRow;
    private int currentColumn;
    String nombre;
    int rango;
    boolean esHeroe;
    boolean posicionado = false;
    
    public Personaje(String nombre, int rango, boolean esHeroe) {
       this.nombre = nombre;
       this.rango = rango;
       this.esHeroe = esHeroe;
    }
    
    public static ArrayList<Personaje> getPersonajesHeroes() {
        ArrayList<Personaje> personajes = new ArrayList<Personaje>();
        
        personajes.add(new Personaje("Mr. Fantastic", 10, true));
        personajes.add(new Personaje("Captain America", 9, true));
        personajes.add(new Personaje("Professor X", 8, true));

        personajes.add(new Personaje("Nick Fury", 8, true));
        personajes.add(new Personaje("Spider-Man", 7, true));
        personajes.add(new Personaje("Wolverine", 7, true));
        personajes.add(new Personaje("Namor", 7, true));
        personajes.add(new Personaje("Daredevil", 6, true));
        personajes.add(new Personaje("Silver Surfer", 6, true));
        personajes.add(new Personaje("Hulk", 6, true));
        personajes.add(new Personaje("Iron Man", 6, true));
        personajes.add(new Personaje("Thor", 5, true));
        personajes.add(new Personaje("Human Torch", 5, true));
        personajes.add(new Personaje("Cyclops", 5, true));
        personajes.add(new Personaje("Invisible Woman", 5, true));
        personajes.add(new Personaje("Ghost Rider", 4, true));
        personajes.add(new Personaje("Punisher", 4, true));
        personajes.add(new Personaje("Blade", 4, true));
        personajes.add(new Personaje("Thing", 4, true));
        personajes.add(new Personaje("Emma Frost", 3, true));
        personajes.add(new Personaje("She-Hulk", 3, true));
        personajes.add(new Personaje("Giant Man", 3, true));
        personajes.add(new Personaje("Beast", 3, true));
        personajes.add(new Personaje("Colossus", 3, true));
        personajes.add(new Personaje("Gambit", 2, true));
        personajes.add(new Personaje("Spider-Girl", 2, true));
        personajes.add(new Personaje("Ice Man", 2, true));
        personajes.add(new Personaje("Storm", 2, true));
        personajes.add(new Personaje("Phoenix", 2, true));
        personajes.add(new Personaje("Dr. Strange", 2, true));
        personajes.add(new Personaje("Elektra", 2, true));
        personajes.add(new Personaje("Nightcrawler", 2, true));
        personajes.add(new Personaje("Black Widow", 1, true));
        
        // Bombas
        personajes.add(new Personaje("Nova Blast", 0, true));
        personajes.add(new Personaje("Nova Blast", 0, true));
        personajes.add(new Personaje("Nova Blast", 0, true));
        //personajes.add(36, new Personaje("Nova Blast", 0, true));
        //personajes.add(37, new Personaje("Nova Blast", 0, true));
        //personajes.add(38, new Personaje("Nova Blast", 0, true));
        
        // Estas 3 bombas se agregan manualmente cuando la tierra es agregada al tablero.
        
        personajes.add(new Personaje("Planet Earth", -1, true));
        return personajes;
        
    }
    
    public static ArrayList<Personaje> getPersonajesVillanos() {
        ArrayList<Personaje> personajes = new ArrayList<Personaje>();
        
        personajes.add(new Personaje("Dr. Doom", 10, false));
        personajes.add(new Personaje("Galactus", 9, false));
        personajes.add(new Personaje("Kingpin", 8, false));
        personajes.add(new Personaje("Magneto", 8, false));
        personajes.add(new Personaje("Apocalypse", 7, false));
        personajes.add(new Personaje("Green Goblin", 7, false));
        personajes.add(new Personaje("Venom", 7, false));
        personajes.add(new Personaje("Bullseye", 6, false));
        personajes.add(new Personaje("Omega Red", 6, false));
        personajes.add(new Personaje("Onslaught", 6, false));
        personajes.add(new Personaje("Red Skull", 6, false));
        personajes.add(new Personaje("Mystique", 5, false));
        personajes.add(new Personaje("Mysterio", 5, false));
        personajes.add(new Personaje("Dr. Octopus", 5, false));
        personajes.add(new Personaje("Deadpool", 5, false));
        personajes.add(new Personaje("Abomination", 4, false));
        personajes.add(new Personaje("Thanos", 4, false));
        personajes.add(new Personaje("Black Cat", 4, false));
        personajes.add(new Personaje("Sabretooth", 4, false));
        personajes.add(new Personaje("Juggernaut", 3, false));
        personajes.add(new Personaje("Rhino", 3, false));
        personajes.add(new Personaje("Carnage", 3, false));
        personajes.add(new Personaje("Mole Man", 3, false));
        personajes.add(new Personaje("Lizard", 3, false));
        personajes.add(new Personaje("Mr. Sinister", 2, false));
        personajes.add(new Personaje("Sentinel 1", 2, false));
        personajes.add(new Personaje("Ultron", 2, false));
        personajes.add(new Personaje("Sandman", 2, false));
        personajes.add(new Personaje("Leader", 2, false));
        personajes.add(new Personaje("Viper", 2, false));
        personajes.add(new Personaje("Sentinel 2", 2, false));
        personajes.add(new Personaje("Electro", 2, false));
        personajes.add(new Personaje("Black Widow", 1, false));
        
        // Bombas
        // Solo se agregan 3 porque las otras tres son agregadas de manera manual
        
        personajes.add(new Personaje("Pumpkin Bomb", 0, false));
        personajes.add(new Personaje("Pumpkin Bomb", 0, false));
        personajes.add(new Personaje("Pumpkin Bomb", 0, false));
        
        personajes.add(new Personaje("Planet Earth", -1, false));


        
        return personajes;
    }
}
