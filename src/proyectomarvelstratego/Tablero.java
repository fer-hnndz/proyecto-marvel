/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomarvelstratego;

/**
 *
 * @author Gab
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tablero extends JFrame{
    private boolean hayCasillaSeleccionada = false;
    private CasillaTablero casillaSeleccionada;
    private CasillaTablero[][] casillas;
    private BufferedImage image;
    private boolean turnoHeroes = true;

    public Tablero() {
        setTitle("Image Grid");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new GridLayout(10, 10));

        // Crear las etiquetas para representar la cuadrícula del tablero
        casillas = new CasillaTablero[10][10];
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                CasillaTablero casilla = new CasillaTablero(row, column, null); // RELLENAR PERSONAJES DESPUES
                casillas[row][column] = casilla;
                add(casillas[row][column].label);
            }
        }
        
        // Manejar eventos de clic en las etiquetas
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel label = (JLabel) e.getSource();
                
                // Tendran valores negativos en caso de que el click no fue en una label valida
                int row = -1, column = -1;

                // Se dio click en una casilla que tiene un personaje
                if (!hayCasillaSeleccionada) {
                    System.out.println("No habia casilla seleccionada");
                    // Obtener la posición de la etiqueta en la cuadrícula
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {
                            if (casillas[i][j].label == label) { // La label clickeada es de la casilla actual
                                
                                // VERIFICAR QUE SEA UNA FICHA DEL JUGADOR Y TENGA UN PERSONAJE
                                casillaSeleccionada = casillas[i][j];
                                
                                if (casillaSeleccionada.personajeActual != null && casillaSeleccionada.personajeActual.esHeroe == turnoHeroes) {
                                    casillas[i][j].setSelected(true);
                                    hayCasillaSeleccionada = true;
                                    System.out.println("Seleccionado correctamente");
                                    System.out.println("seleccionado: " + casillaSeleccionada.personajeActual.nombre);
                                   
                                    highlightIfValidMove();
                                    break;
                                } else {
                                    casillaSeleccionada = null;
                                    hayCasillaSeleccionada = false;
                                    break;
                                }
                            }
                        }
                    }
                } else { // ya hay una casilla seleccionada y se intentara mover
                    System.out.println("HAY CASILLA SELECCIONADA");
                    for (int i=0;i < 10; i++)  {
                        for (int j = 0;j<10;j++) {
                            if (casillas[i][j].label == label) {
                                if (isValidMove(i, j)) {
                                    moveCharacter(i, j);
                                }
                            }
                            
                        }
                    }
                    
                }
                
                
                /*
                if (row != -1 && column != -1) {
                    // Verificar si el movimiento es válido
                    if (isValidMove(row, column)) {
                        // Mover la imagen a la nueva posición
                        moveImage(row, column);
                    } else {
                        // Mostrar mensaje de movimiento inválido solo si es diagonal
                        if (row != currentRow && column != currentColumn) {
                            JOptionPane.showMessageDialog(Tablero.this, "Movimiento inválido", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
*/
            }
        };

        // Agregar el manejador de eventos a las etiquetas
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                casillas[row][column].label.addMouseListener(mouseAdapter);
            }
        }

        // Establecer la posición inicial de la imagen
        casillas[2][2].setPersonaje(new Personaje("Captain America", 9, true));
        casillas[1][0].setPersonaje(new Personaje("Iron Man", 7, true));
        
        highlightForbiddenZones();
        
        setVisible(true);
    }

    private boolean isValidMove(int row, int column) {
        int currentRow = casillaSeleccionada.row;
        int currentColumn = casillaSeleccionada.column;
        
        // Verificar si el movimiento es ortogonal
        boolean isOrthogonal = (row == currentRow && Math.abs(column - currentColumn) == 1) ||
                (column == currentColumn && Math.abs(row - currentRow) == 1);

        // Verificar si la nueva posición está dentro de los espacios restringidos
        boolean isRestricted = (row >= 4 && row <= 5 && column >= 2 && column <= 3) ||
                (row >= 4 && row <= 5 && column >= 6 && column <= 7);

        // verificar si hay un personaje en la casilla
        boolean hasCharacter = (casillas[row][column].personajeActual != null);

        // VERIFICAR QUE SI HAY UN PERSONAJE, QUE ESTE SEA DEL MISMO BANDO
        if (hasCharacter) { 
            hasCharacter = (casillas[row][column].personajeActual.esHeroe == casillaSeleccionada.personajeActual.esHeroe);
        }
        
        // El movimiento es válido solo si es ortogonal y no está en un espacio restringido (zonas prohibidas) y no tiene otra ficha del mismo bando
        return isOrthogonal && !isRestricted && !hasCharacter;
    }
    
    private void highlightIfValidMove() {
        // VER DESPUES LAS FICHAS #2
        
        // Iterar por todas las casillas y verificar que sean un movimiento valido para resaltarlas
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j<10;j++) {
                casillas[i][j].highlightMove(isValidMove(i, j));
                casillas[i][j].label.repaint();

            }
        }
        highlightForbiddenZones();
     }
    
    public void highlightForbiddenZones() {
        // Resaltar las zonas restringidas en color negro
        for (int row = 4; row <= 5; row++) {
            for (int column = 2; column <= 3; column++) {
                casillas[row][column].label.setOpaque(true);
                casillas[row][column].label.setBackground(Color.BLACK);
                casillas[row][column].label.repaint();
            }
            for (int column = 6; column <= 7; column++) {
                casillas[row][column].label.setOpaque(true);
                casillas[row][column].label.setBackground(Color.BLACK);
                casillas[row][column].label.repaint();

            }
        }
    }

    private void moveCharacter(int newRow, int newColumn) {
        // Eliminar la imagen de la posición actual
        Personaje personaje = casillaSeleccionada.personajeActual;
        
        casillaSeleccionada.setPersonaje(null);
        unhighlightAllValidMoves();
        
        // Mover la imagen a la nueva posición
        casillas[newRow][newColumn].setPersonaje(personaje);
        casillaSeleccionada = null;
        hayCasillaSeleccionada = false;        
    }
    
    public void unhighlightAllValidMoves() {
        for (int i = 0; i<10;i++) {
            for (int j = 0; j<10;j++){
                casillas[i][j].setSelected(false);
                casillas[i][j].label.repaint();

            }
        }
        highlightForbiddenZones();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Tablero();
                
            }
        });
    }
}
