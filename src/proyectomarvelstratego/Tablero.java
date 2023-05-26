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
     private JLabel[][] gridLabels;
    private BufferedImage image;
    private int currentRow;
    private int currentColumn;

    public Tablero() {
        setTitle("Image Grid");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new GridLayout(10, 10));

        // Crear las etiquetas para representar la cuadrícula
        gridLabels = new JLabel[10][10];
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                JLabel label = new JLabel();
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                gridLabels[row][column] = label;
                add(label);
            }
        }

        // Cargar la imagen desde un archivo local
        try {
            image = ImageIO.read(new File("src/img/capitan-america.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Manejar eventos de clic en las etiquetas
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel label = (JLabel) e.getSource();
                int row = -1, column = -1;

                // Obtener la posición de la etiqueta en la cuadrícula
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (gridLabels[i][j] == label) {
                            row = i;
                            column = j;
                            break;
                        }
                    }
                }

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
            }
        };

        // Agregar el manejador de eventos a las etiquetas
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                gridLabels[row][column].addMouseListener(mouseAdapter);
            }
        }

        // Establecer la posición inicial de la imagen
        currentRow = 1;
        currentColumn = 1;
        gridLabels[currentRow][currentColumn].setIcon(new ImageIcon(image));

        // Resaltar las zonas restringidas en color negro
        for (int row = 4; row <= 5; row++) {
            for (int column = 2; column <= 3; column++) {
                gridLabels[row][column].setOpaque(true);
                gridLabels[row][column].setBackground(Color.BLACK);
            }
            for (int column = 6; column <= 7; column++) {
                gridLabels[row][column].setOpaque(true);
                gridLabels[row][column].setBackground(Color.BLACK);
            }
        }

        setVisible(true);
    }

    private boolean isValidMove(int row, int column) {
        // Verificar si el movimiento es ortogonal
        boolean isOrthogonal = (row == currentRow && Math.abs(column - currentColumn) == 1) ||
                (column == currentColumn && Math.abs(row - currentRow) == 1);

        // Verificar si la nueva posición está dentro de los espacios restringidos
        boolean isRestricted = (row >= 4 && row <= 5 && column >= 2 && column <= 3) ||
                (row >= 4 && row <= 5 && column >= 6 && column <= 7);

        // El movimiento es válido solo si es ortogonal y no está en un espacio restringido
        return isOrthogonal && !isRestricted;
    }

    private void moveImage(int row, int column) {
        // Eliminar la imagen de la posición actual
        gridLabels[currentRow][currentColumn].setIcon(null);

        // Mover la imagen a la nueva posición
        gridLabels[row][column].setIcon(new ImageIcon(image));

        // Actualizar la posición actual
        currentRow = row;
        currentColumn = column;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Tablero();
            }
        });
    }
}
