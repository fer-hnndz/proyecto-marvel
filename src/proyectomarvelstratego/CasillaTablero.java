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
import java.awt.Font;

public class CasillaTablero {
    
    JLabel label;
    Personaje personajeActual;
    int row;
    int column;
    
    public CasillaTablero(int row, int column, Personaje personajeActual) {
        this.label = new JLabel();
        this.row = row;
        this.column = column;
        this.personajeActual = personajeActual;
                
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
       
    public void highlightMove(boolean activar) {
        if (activar) {
            label.setBackground(Color.green);
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
            label.setIcon(null);
        }
        else {
            
            if (personajeActual.icono != null) {
                label.setIcon(personajeActual.icono);
            }
            else
                label.setText(personajeActual.nombre);
        }
    }
    
    public void esconderCasilla(boolean esconder) {
        if (esconder) {
            label.setText("???");
            label.setIcon(null);
            label.repaint();
        } else {
            if (personajeActual.icono != null) {
                label.setIcon(personajeActual.icono);
            }
            else
                label.setText(personajeActual.nombre);
        }
    } 
    
   
    
    
    
}
