/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomarvelstratego;

/**
 *
 * @author Jorge Hernandez
 */

import java.awt.*;
import javax.swing.*;
public class CasillaTablero {
    
    JLabel label;
    Personaje personajeActual;
    int row;
    int column;
    boolean selected = false;
   
    public CasillaTablero(int row, int column, Personaje personajeActual) {
        this.label = new JLabel();
        this.row = row;
        this.column = column;
        this.personajeActual = personajeActual;
        
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
    public void setSelected(boolean selected) {
        
        this.selected = selected;
        if (selected == true) {
            // Colorear de distinta manera si es heroe o villano
            
            if (personajeActual.esHeroe) {
                label.setBackground(Color.BLUE);
            } else { // ES Villano
                label.setBackground(Color.RED);
            }
            label.setOpaque(true);  
        } else {
            label.setOpaque(false);
        }
    }
    
    public void highlightMove(boolean activar) {
        if (activar) {
            label.setBackground(Color.yellow);
            label.setOpaque(true);
        } else label.setOpaque(false);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    
    public void setPersonaje(Personaje personaje) {
        personajeActual = personaje;
        
        if (personaje == null) {
            label.setText("");
        } else {
            label.setText(personaje.nombre);
            label.setBackground(Color.red);
        }
    }
    
   
    
    
    
}
