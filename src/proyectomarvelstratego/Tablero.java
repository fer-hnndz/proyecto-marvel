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
import java.util.ArrayList;

public class Tablero extends JPanel{
    private boolean hayCasillaSeleccionada = false;
    private CasillaTablero casillaSeleccionada;
    private CasillaTablero[][] casillas;
    private boolean turnoHeroes = true;
    
    private ArrayList<Personaje> heroesEliminados = new ArrayList<Personaje>();
    private ArrayList<Personaje> villanosEliminados = new ArrayList<Personaje>();
    private JTextArea infoArea;
    
    
    public Tablero(JTextArea infoArea) {
        this.infoArea = infoArea;
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
                                
                                if (casillaSeleccionada.personajeActual != null && 
                                        casillaSeleccionada.personajeActual.esHeroe == turnoHeroes) {
                                    casillas[i][j].setSelected(true);
                                    hayCasillaSeleccionada = true;
                                    System.out.println("Seleccionado correctamente");
                                    System.out.println("seleccionado: " + casillaSeleccionada.personajeActual.nombre);
                                   
                                    mostrarInformacionPersonaje();
                                    resaltarSiEsMovimientoValido();
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
                                // SI LA CASILLA CLICKEADA ES UNA FICHA DEL MISMO BANDO, CAMBIAR A ESA FICHA 
                                if (casillas[i][j].personajeActual != null) {
                                    if (casillas[i][j].personajeActual.esHeroe == turnoHeroes) {
                                        // Actualizar casillas  
                                        borrarResaltadoMovimientos();
                                        casillaSeleccionada.setSelected(false);
                                        
                                        casillaSeleccionada = casillas[i][j];
                                        casillaSeleccionada.setSelected(turnoHeroes);
                                        resaltarSiEsMovimientoValido();
                                        resaltarZonasProhibidas();
                                        
                                        mostrarInformacionPersonaje();
                                        
                                        break;
                                    }
                                }
                                
                                if (esMovimientoValido(i, j)) {
                                    moverPersonaje(i, j);
                                } else{
                                    JOptionPane.showMessageDialog(null, "MOVIMIENTO INVALIDO.\nTus movimientos validos estan coloreados de amarillo.");
                                }
                            }
                            
                        }
                    } 
                }
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
        casillas[8][6].setPersonaje(new Personaje("Iron Man", 7, true));
        casillas[8][8].setPersonaje(new Personaje("VILLANO", 7, false));
        casillas[1][8].setPersonaje(new Personaje("ROOK", 2, false));
        
        resaltarZonasProhibidas();
        esconderPersonajes();
        setVisible(true);
    }

    private boolean esMovimientoValido(int row, int column) {
        int currentRow = casillaSeleccionada.row;
        int currentColumn = casillaSeleccionada.column;
        
        
        // Son fichas que no se pueden mover
        if (casillaSeleccionada.personajeActual.rango == 0) {
            return false;
        }
        
        if (casillaSeleccionada.personajeActual.rango == 2) {
           
            // VERIFICAR QUE EL MOVIMIENTO SEA ORTOGONAL
            boolean isOrthogonal = (row == currentRow && Math.abs(column - currentColumn) > 0) ||
                    (column == currentColumn && Math.abs(row - currentRow) > 0);
            // Verificar si la nueva posición está dentro de los espacios restringidos
            boolean isRestricted = (row >= 4 && row <= 5 && column >= 2 && column <= 3) ||
                    (row >= 4 && row <= 5 && column >= 6 && column <= 7);
            
            if (isOrthogonal && !isRestricted) {
                if (column < currentColumn) { // iterar desde col actual hasta el destino a la izquierda
                    for (int i = currentColumn-1; i >= column ;i--){
                        if (casillas[currentRow][i].personajeActual != null) {
                            if (casillas[currentRow][i].personajeActual.esHeroe != turnoHeroes) { // EL PERSONAJE ES DEL BANDO OPUESTO
                                
                                if (i == column) return true; // SI ESA CASILLA ES MI DESTINO, PUEDO ATACAR AL PERSONAJE ENTONCES ES VALIDO
                                else return false; // ESTA INTERFIRIENDO EN EL DESTINO POR ENDE MI DESTINO NO ES VALIDO

                            } else return false;// PIEZA DEL MISMO BANDO INTERIFIENDO POR ENDE DESTINO INVALIDO
                        } else if ((currentRow >= 4 && currentRow <= 5) && ((i>=3 && i<=4) || (i>=6 && i<=7))) return false;
                    }
                    // Se hizo el recorrido sin encontrar ningun personaje en el camino
                    return true;
                } else if (column > currentColumn) { // iterar desde la col actual hacia el destino a la derecha
                for (int i = currentColumn+1; i <= column;i++) {
                        if (casillas[currentRow][i].personajeActual != null) {
                            if (casillas[currentRow][i].personajeActual.esHeroe != turnoHeroes) {
                                if (i == column) return true;
                                else return false;
                            }
                        } else if ((currentRow >= 4 && currentRow <= 5) && ((i>=3 && i<=4) || (i>=6 && i<=7))) return false;
                    }
                    
                    return true;
                } else if (row < currentRow) {
                    for (int i = currentRow-1; i >= row;i--){
                        if (casillas[i][currentColumn].personajeActual != null) {
                            if (casillas[i][currentColumn].personajeActual.esHeroe != turnoHeroes) {
                                
                                if (i == row) return true;
                                else return false;
                            } else return false;
                        } else if ((i >= 4 && i<=5) && ((currentColumn >= 2 && currentColumn <= 3) || (currentColumn >= 6 && currentColumn <= 7))) return false;
                    }
                    
                    return true;
                } else if (row > currentRow) {
                    for (int i = currentRow+1; i <= row;i++){
                        // Verificar si la nueva posición está dentro de los espacios restringidos
                        
                        if (casillas[i][currentColumn].personajeActual != null) {
                            if (casillas[i][currentColumn].personajeActual.esHeroe != turnoHeroes) {
                                
                                if (i == row) return true;
                                else return false;
                            } else return false;
                            
                            // HAY UNA CASILLA PROHIBIDA EN EL MEDIO
                        } else if ((i >= 4 && i<=5) && ((currentColumn >= 2 && currentColumn <= 3) || (currentColumn >= 6 && currentColumn <= 7))) return false;
                    }
                    
                    return true;
                }
                   
            } else return false;
        }
        
        // Fichas normales
        if (casillaSeleccionada.personajeActual.rango != 2) {
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
        
        return false;
    }
    
    private void resaltarSiEsMovimientoValido() {
        // VER DESPUES LAS FICHAS #2
        
        // Iterar por todas las casillas y verificar que sean un movimiento valido para resaltarlas
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j<10;j++) {
                casillas[i][j].highlightMove(esMovimientoValido(i, j));
                casillas[i][j].label.repaint();

            }
        }
        resaltarZonasProhibidas();
     }
    
    private void esconderPersonajes() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j<10;j++) {
                if (casillas[i][j].personajeActual != null) {
                    casillas[i][j].esconderCasilla(turnoHeroes != casillas[i][j].personajeActual.esHeroe);
                } 
            }
        }
    }
    
    public void resaltarZonasProhibidas() {
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

    private void moverPersonaje(int newRow, int newColumn) {
        
        // VERIFICAR QUE HAYA UN PERSONAJE EN LA NUEVA CASILLA PARA TOMAR EL COMBATE
        if (casillas[newRow][newColumn].personajeActual != null) {
            // Entrar en combate ya que esta restringido que piezas del mismo rango entren en combate.
            Personaje ganador = calcularCombate(casillaSeleccionada.personajeActual, casillas[newRow][newColumn].personajeActual);
            
            // Ambas piezas fueron eliminadas porque eran del mismo rango
            if (ganador == null) {
                casillaSeleccionada.setPersonaje(null);
                casillas[newRow][newColumn].setPersonaje(null);
            } else if (casillaSeleccionada.personajeActual == ganador) {
                // Mover el ganador a la casilla de la pieza derrotada
                casillaSeleccionada.setPersonaje(null);
                casillas[newRow][newColumn].setPersonaje(ganador);
            } else {
                // La pieza atacante perdio
                casillaSeleccionada.setPersonaje(null);
            }
            
            actualizarTurno();
            return;
        }
        
        
        // No habia un personaje en esa casilla asi que solo se actualiza la posicion
        Personaje personaje = casillaSeleccionada.personajeActual;
        casillaSeleccionada.setPersonaje(null);
        borrarResaltadoMovimientos();
        
        // Mover la imagen a la nueva posición
        casillas[newRow][newColumn].setPersonaje(personaje);
        
        actualizarTurno();
    }
    
    public Personaje calcularCombate(Personaje atacante, Personaje defensor) {
        
        // Los personajes rango 3 pueden vencer a las bombas (rango 0).
        if (atacante.rango == 3 && (defensor.rango == 0)) {
            if (defensor.esHeroe) heroesEliminados.add(defensor);
            else villanosEliminados.add(defensor);
            
            return atacante;
            
        // Las piezas rango 1 pueden vencer a las piezas de rango 10 y a las tierras (rango -1).
        } else if (atacante.rango == 1 && (defensor.rango == 10 || defensor.rango == -1)) {
            if (defensor.esHeroe) heroesEliminados.add(defensor);
            else villanosEliminados.add(defensor);
            
            return atacante;
        } if (defensor.rango == 3 && atacante.rango != 3) {
            if (atacante.esHeroe) heroesEliminados.add(atacante);
            else villanosEliminados.add(atacante);
            
            return defensor;
        } else if (atacante.rango > defensor.rango) {
            if (defensor.esHeroe) heroesEliminados.add(defensor);
            else villanosEliminados.add(defensor);
            
            return atacante;
        } else if (atacante.rango < defensor.rango) {
            if (atacante.esHeroe) heroesEliminados.add(atacante);
            else villanosEliminados.add(atacante);
            
            return defensor;
        } else {
            if (atacante.esHeroe) {
                heroesEliminados.add(atacante);
                villanosEliminados.add(defensor);
            } else {
                villanosEliminados.add(atacante);
                heroesEliminados.add(defensor);
            }
            
            return null;
        }
        
    
    }
    
    public void actualizarTurno() {
        casillaSeleccionada = null;
        hayCasillaSeleccionada = false;
        
        turnoHeroes = !turnoHeroes;
        setVisible(false);
        esconderPersonajes();
        infoArea.setText("");
        
        String mensaje;
        if (!turnoHeroes) mensaje = "FIN DEL TURNO DE LOS HEROES. DEJE A LOS VILLANOS JUGAR SU TURNO.";
        else mensaje = "FIN DEL TURNO DE LOS VILLANOS. DEJE A LOS HEROES JUGAR SU TURNO.";
        JOptionPane.showMessageDialog(null, mensaje);
        borrarResaltadoMovimientos();
        resaltarZonasProhibidas();
        setVisible(true);
        
    }
    public void borrarResaltadoMovimientos() {
        for (int i = 0; i<10;i++) {
            for (int j = 0; j<10;j++){
                casillas[i][j].setSelected(false);
                casillas[i][j].label.repaint();

            }
        }
        resaltarZonasProhibidas();
    }
    
    public void mostrarInformacionPersonaje() {
        // Mostrar informacion en textarea
        String mensaje = "Personaje: " + casillaSeleccionada.personajeActual.nombre + "\n";
        mensaje += (casillaSeleccionada.personajeActual.esHeroe) ?"Bando: Heroes\n":"Bando: Villanos\n";
        mensaje += "Rango: " + casillaSeleccionada.personajeActual.rango;
        infoArea.setText(mensaje);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Tablero(null);
                
            }
        });
    }
}
