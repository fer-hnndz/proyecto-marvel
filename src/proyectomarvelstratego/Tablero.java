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
import java.util.Random;
import java.util.Date;

public class Tablero extends JPanel{
    private boolean hayCasillaSeleccionada = false;
    private CasillaTablero casillaSeleccionada;
    private CasillaTablero[][] casillas;
    private boolean turnoHeroes = true;
    boolean esTutorial;
    boolean hayRetirado = false;
    boolean juegoTerminado = false;
    
    ArrayList<Personaje> heroesIniciales = Personaje.getPersonajesHeroes();
    ArrayList<Personaje> villanosIniciales = Personaje.getPersonajesVillanos();

    
    private ArrayList<Personaje> heroesEliminados = new ArrayList<Personaje>();
    private ArrayList<Personaje> villanosEliminados = new ArrayList<Personaje>();
    
    private JTextArea infoArea;
    private JTextArea heroesEliminatedArea;
    private JTextArea villanosEliminatedArea;
    
    SistemaUsuarios sistemaUsuarios;
    Stats stats;
    Usuario playerHeroes, playerVillanos;
    
    JFrame gameWindow;
    MenuInicio mainWindow;
    private Image imagenFondo;
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar la imagen de fondo
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
    }
    
    public Tablero(JTextArea infoArea, JTextArea heroesEliminadosArea, JTextArea villanosEliminadosArea, SistemaUsuarios sistemaUsuarios, Stats stats, Usuario playerHeroes, Usuario playerVillanos, boolean esTutorial, JFrame gameWindow, MenuInicio mainWindow) {
        // Cargar la imagen de fondo
        ImageIcon imagenIcono = new ImageIcon("src/img/tablero.png");
        imagenFondo = imagenIcono.getImage();
        
        // Agregar registro de partidas a los usuarios
        playerHeroes.partidasBuenos++;
        playerVillanos.partidasMalos++;
        
        sistemaUsuarios.actualizarUsuario(playerHeroes);
        sistemaUsuarios.actualizarUsuario(playerVillanos);
        
        this.stats = stats;
        this.mainWindow = mainWindow;
        this.sistemaUsuarios = sistemaUsuarios;
        this.playerHeroes = playerHeroes;
        this.playerVillanos = playerVillanos;
        this.infoArea = infoArea;
        this.heroesEliminatedArea = heroesEliminadosArea;
        this.villanosEliminatedArea = villanosEliminadosArea;
        this.esTutorial = esTutorial;
        this.gameWindow = gameWindow;
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
                
                // Todavia no se ha seleccionado una casilla
                if (!hayCasillaSeleccionada) {
                    // Obtener la posición de la etiqueta en la cuadrícula
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {
                            if (casillas[i][j].label == label) {
                                casillaSeleccionada = casillas[i][j];

                                
                                // VERIFICAR QUE SEA UNA FICHA DEL JUGADOR DEL TURNO ACTUAL Y TENGA UN PERSONAJE
                                if (casillaSeleccionada.personajeActual != null && 
                                        casillaSeleccionada.personajeActual.esHeroe == turnoHeroes) {
                                    hayCasillaSeleccionada = true;
                                  
                                    mostrarInformacionPersonaje();
                                    if (esTutorial) resaltarSiEsMovimientoValido();
                                    resaltarZonasProhibidas();
                                    casillaSeleccionada.setSelected(true);
                                    break;
                                } else {
                                    //  Reiniciar el valor de la casilla seleccionada ya que no cumple
                                    casillaSeleccionada = null;
                                    hayCasillaSeleccionada = false;
                                    break;
                                }
                            }
                        }
                    }
                } else { // ya hay una casilla seleccionada y se intentara mover
                    for (int i=0;i < 10; i++)  {
                        for (int j = 0;j<10;j++) {
                            
                            if (casillas[i][j].label == label) {
                                
                                // SI LA CASILLA CLICKEADA ES UNA FICHA DEL MISMO BANDO, CAMBIAR A ESA FICHA 
                                if (casillas[i][j].personajeActual != null) {
                                    if (casillas[i][j].personajeActual.esHeroe == turnoHeroes) {
                                        // Actualizar casillas  
                                        borrarResaltadoMovimientos();
                                        casillaSeleccionada.setSelected(false); // Deseleccionar la casilla anterior
                                        
                                        casillaSeleccionada = casillas[i][j];
                                        casillaSeleccionada.setSelected(true);
                                        if (esTutorial) resaltarSiEsMovimientoValido();
                                        resaltarZonasProhibidas();
                                        mostrarInformacionPersonaje();
                                        casillaSeleccionada.setSelected(true);
                                        
                                        break;
                                    }
                                }
                                
                                // Se dio click en una casilla con un rival o una casilla vacia
                                if (esMovimientoValido(i, j)) {
                                    moverPersonaje(i, j);
                                } else{
                                    if (esTutorial)
                                            JOptionPane.showMessageDialog(null, "MOVIMIENTO INVALIDO.\nTus movimientos validos estan coloreados de verde.");
                                    else
                                        JOptionPane.showMessageDialog(null, "ERROR. MOVIMIENTO INVALIDO.");
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

        // Establecer posiciones iniciales
        posicionarTodo();
        resaltarZonasProhibidas();
        
        if (!esTutorial) esconderPersonajes(); // Esconder personajes en caso de que no se este jugando en modo tutorial
        setVisible(true);
        repaint();
    }
    
    private void detectarGanador() {
        String mensajeLog = "";
        Date fecha = new Date();
        
        // Verificar si ambos se quedaron sin movimientos validos
        if (!tieneMovimientosValidos(turnoHeroes) && !tieneMovimientosValidos(!turnoHeroes)) {
           // Registrar ;
            
            
          mensajeLog = playerHeroes.getUsuario() + " usando HEROES ha empatado con " + playerVillanos.getUsuario() + " porque ambos se quedaron sin movimientos validos.";
          
          Partida partidaHeroes = new Partida(playerVillanos, false, "HEROES", 1.5);
          Partida partidaVillanos = new Partida(playerHeroes, false, "VILLANOS", 1.5);
          playerHeroes.addPartida(partidaHeroes);
          playerVillanos.addPartida(partidaVillanos);
          
          sistemaUsuarios.actualizarUsuario(playerHeroes);
          sistemaUsuarios.actualizarUsuario(playerVillanos);
          juegoTerminado = true;
        } else if (!tieneMovimientosValidos(turnoHeroes)) { // Verificar si el jugador actual no se quedo sin turnos

            
            Usuario ganador = (!turnoHeroes) ?playerHeroes:playerVillanos;
            Usuario perdedor = (!turnoHeroes) ?playerVillanos:playerHeroes;
            
            String bandoGanador = (!turnoHeroes) ?"HEROES":"VILLANOS";
            String bandoPerdedor = (!turnoHeroes) ?"VILLANOS":"HEROES";
            
            // Registrar la partida para los usuarios
            Partida partidaGanador = new Partida(perdedor, true, bandoGanador, 3);
            Partida partidaPerdedor = new Partida(ganador, false, bandoPerdedor, 0);
            
            ganador.addPartida(partidaGanador);
            perdedor.addPartida(partidaPerdedor);
            
            stats.addPartida(!turnoHeroes);
            // Registrar puntos y actualizar usuario
            sistemaUsuarios.actualizarUsuario(ganador);
            sistemaUsuarios.actualizarUsuario(perdedor);
            
            mensajeLog = perdedor.getUsuario() + 
                    " usando los " + bandoPerdedor + 
                    " ha perdido por no tener movimientos validos disponibles ante " + 
                    ganador.getUsuario() + " - " + fecha.toString();
            
            juegoTerminado = true;

            
        }  else if(!tieneMovimientosValidos(!turnoHeroes)) { // Verificar si el bando contrario se quedo sin movimientos
            Usuario ganador = (turnoHeroes) ?playerHeroes:playerVillanos;
            Usuario perdedor = (turnoHeroes) ?playerVillanos:playerHeroes;
            
            String bandoGanador = (turnoHeroes) ?"HEROES":"VILLANOS";
            String bandoPerdedor = (turnoHeroes) ?"VILLANOS":"HEROES";
            
            // Registrar la partida para los usuarios
            Partida partidaGanador = new Partida(perdedor, true, bandoGanador, 3);
            Partida partidaPerdedor = new Partida(ganador, false, bandoPerdedor, 0);
            
            ganador.addPartida(partidaGanador);
            perdedor.addPartida(partidaPerdedor);
            
            sistemaUsuarios.actualizarUsuario(ganador);
            sistemaUsuarios.actualizarUsuario(perdedor);
            
            mensajeLog = perdedor.getUsuario() + 
                    " usando los " + bandoPerdedor + 
                    " ha perdido por no tener movimientos validos disponibles ante " + 
                    ganador.getUsuario() + " - " + fecha.toString();
            
            stats.addPartida(turnoHeroes);
            juegoTerminado = true;
        } else {
            // Comprobar si la tierra de los heroes fue eliminada
            for (int i = 0;i<heroesEliminados.toArray().length;i++) {
                Personaje personajeActual = heroesEliminados.get(i);
                if (personajeActual.rango == -1 && personajeActual.esHeroe) {
                    juegoTerminado = true;
                    mensajeLog = playerVillanos.getUsuario() + " usando los VILLANOS ha CAPTURADO la TIERRA! Venciendo a " + playerHeroes.getUsuario() + " - " + new Date().toString();
                    
                    // Registrar partida para los jugadores y asignar puntos
                    
                    Partida partidaHeroes = new Partida(playerVillanos, false, "HEROES", 0);
                    Partida partidaVillanos = new Partida(playerHeroes, true, "VILLANOS", 3);
                    
                    playerHeroes.addPartida(partidaHeroes);
                    playerVillanos.addPartida(partidaVillanos);
                    
                    stats.addPartida(false);
                    sistemaUsuarios.actualizarUsuario(playerHeroes);
                    sistemaUsuarios.actualizarUsuario(playerVillanos);
                }
            }
            
            for (int i =0;i<villanosEliminados.toArray().length;i++) {
                Personaje personajeActual = villanosEliminados.get(i);
                if (personajeActual.rango == -1 && !personajeActual.esHeroe) {
                    juegoTerminado = true;
                    mensajeLog = playerHeroes.getUsuario() + " usando los HEROES ha SALVADO la TIERRA! Venciendo a " + playerVillanos.getUsuario() + " - " + new Date().toString();
                
                    Partida partidaHeroes = new Partida(playerVillanos, true, "HEROES", 3);
                    Partida partidaVillanos = new Partida(playerHeroes, false, "VILLANOS", 0);
                    
                    playerHeroes.addPartida(partidaHeroes);
                    playerVillanos.addPartida(partidaVillanos);
                    
                    stats.addPartida(true);
                    sistemaUsuarios.actualizarUsuario(playerHeroes);
                    sistemaUsuarios.actualizarUsuario(playerVillanos);
                }
            }
        }
        
        if (juegoTerminado)
        {
            mainWindow.setStats(stats);
            JOptionPane.showMessageDialog(null, mensajeLog);
            gameWindow.dispose();

        }
        
    }

    private boolean esMovimientoValido(int row, int column) {
        int currentRow = casillaSeleccionada.row;
        int currentColumn = casillaSeleccionada.column;
        
        
        // Son fichas que no se pueden mover
        if (casillaSeleccionada.personajeActual.rango == 0 || casillaSeleccionada.personajeActual.rango == -1) {
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
                        } else if ((currentRow >= 4 && currentRow <= 5) && ((i>=2 && i<=3) || (i>=6 && i<=7))) return false;
                    }
                    // Se hizo el recorrido sin encontrar ningun personaje en el camino
                    return true;
                } else if (column > currentColumn) { // iterar desde la col actual hacia el destino a la derecha
                for (int i = currentColumn+1; i <= column;i++) {
                        if (casillas[currentRow][i].personajeActual != null) {
                            if (casillas[currentRow][i].personajeActual.esHeroe != turnoHeroes) {
                                if (i == column) return true;
                                else return false;
                            } else return false;
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
    
    /**
     * Verifica si el jugador del turno actual tiene movimientos validos.
     * @return 
     */
    private boolean tieneMovimientosValidos(boolean bandoHeroes) {
        // Recorrer el array en busca de fichas que se pueden mover

        for (int r = 0;r<10;r++){
            for (int c = 0;c<10;c++) {
                CasillaTablero casillaActual = casillas[r][c];
                if (casillaActual.personajeActual == null) continue;
                if (casillaActual.personajeActual.esHeroe == bandoHeroes && casillaActual.personajeActual.rango > 0) return true;
            }
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
                casillas[row][column].label.setBackground(new Color(131, 52, 235, 255));
                casillas[row][column].label.repaint();
            }
            for (int column = 6; column <= 7; column++) {
                casillas[row][column].label.setOpaque(true);
                casillas[row][column].label.setBackground(Color.YELLOW);
                casillas[row][column].label.repaint();

            }
        }
    }
    
    private void moverPersonaje(int newRow, int newColumn) {
        
        // VERIFICAR QUE HAYA UN PERSONAJE EN LA NUEVA CASILLA PARA TOMAR EL COMBATE
        if (casillas[newRow][newColumn].personajeActual != null) {
            // Entrar en combate ya que esta restringido que piezas del mismo rango entren en combate.
            Personaje ganador = calcularCombate(casillaSeleccionada.personajeActual, casillas[newRow][newColumn].personajeActual);
            
            JPanel panel;
            
            // Mostrar mensaje del ganador/empate en pantalla
            if (ganador == casillaSeleccionada.personajeActual) {
                System.out.println("GANO LA FICHA DEL TURNO ACTUAL");
                panel = new VentanaCombate(ganador, casillas[newRow][newColumn].personajeActual, 1);
            } else if (ganador == null) {
                System.out.println("FUE UN EMPATE");
                panel = new VentanaCombate(casillaSeleccionada.personajeActual, casillas[newRow][newColumn].personajeActual, -1);
            } else {
                System.out.println("GANO LA FICHA DEL TURNO OPUESTO");
                panel = new VentanaCombate(casillaSeleccionada.personajeActual, ganador, 0);
            }
            
            JOptionPane.showMessageDialog(null, panel);
            
            // Eliminar pieza(s) derrotada(s).
            if (ganador == null) { // Ambas piezas fueron eliminadas porque eran del mismo rango
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
            
            detectarGanador();
            
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
        } if (defensor.rango == 0 && atacante.rango != 3) { // Combate entre bomba y otro personaje que no sea rango 3
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
        if (juegoTerminado) return;
        
        casillaSeleccionada = null;
        hayCasillaSeleccionada = false;
        
        turnoHeroes = !turnoHeroes;
        setVisible(false);
        if (!esTutorial) esconderPersonajes();
        infoArea.setText("");
        heroesEliminatedArea.setText("");
        
        String mensaje;
        if (!turnoHeroes) mensaje = "FIN DEL TURNO DE " + playerHeroes.getUsuario() + " DEJA QUE  " + playerVillanos.getUsuario() + " JUEGUE SU TURNO.";
        else mensaje = "FIN DEL TURNO DE " + playerVillanos.getUsuario() + " DEJA QUE  " + playerHeroes.getUsuario() + " JUEGUE SU TURNO.";
        JOptionPane.showMessageDialog(null, mensaje);
        
        String mensaje2 = MensajeTurnosJugador();
        
        Juego juego = (Juego) SwingUtilities.getWindowAncestor(this);
        
        borrarResaltadoMovimientos();
        resaltarZonasProhibidas();
        juego.actualizarTextoMensaje(mensaje2);// Actualizar el texto en jLabel3
        if (!esTutorial) esconderPersonajes();
        setVisible(true);
        mostrarPersonajesEliminados();
    }
    
    public void mostrarMensajeInicial() {
        String mensaje2 = MensajeTurnosJugador();
        Juego juego = (Juego) SwingUtilities.getWindowAncestor(this);
        juego.actualizarTextoMensaje(mensaje2);
    }
        
    private String MensajeTurnosJugador() {
        if (!turnoHeroes) {
            return playerVillanos.getUsuario() + " jugando, bando Villanos";
        } else {
            return playerHeroes.getUsuario() + " jugando, bando Héroes";
        }
    }
    
    public void surrender() {
        Usuario ganador, perdedor;
        String bandoGanador, bandoPerdedor;
        
        if (turnoHeroes) {
            ganador = playerVillanos;
            perdedor = playerHeroes;
            bandoGanador = "VILLANOS";
            bandoPerdedor = "HEROES";
        } else {
            ganador = playerHeroes;
            perdedor = playerVillanos;
            bandoGanador = "HEROES";
            bandoPerdedor = "VILLANOS";
        }
        
        String mensaje = ganador.getUsuario() + " usando " + 
                bandoGanador + " ha ganado ya que " + 
                perdedor.getUsuario() + " usando " + bandoPerdedor +
                " se ha retirado del juego. - " + new Date().toString();
        
        Partida partidaGanador = new Partida(perdedor, true, bandoGanador, 3);
        Partida partidaPerdedor = new Partida(ganador, false, bandoPerdedor, 0);
        
        // El bando opuesto gana la partida.
        stats.addPartida(!turnoHeroes);
        ganador.addPartida(partidaGanador);
        perdedor.addPartida(partidaPerdedor);
        
        sistemaUsuarios.actualizarUsuario(ganador);
        sistemaUsuarios.actualizarUsuario(perdedor);
        juegoTerminado = true;
        
        JOptionPane.showMessageDialog(null, mensaje);
        gameWindow.dispose();
        return;
        
    }
    public void borrarResaltadoMovimientos() {
        for (int i = 0; i<10;i++) {
            for (int j = 0; j<10;j++){
                casillas[i][j].label.setOpaque(false);
                casillas[i][j].label.repaint();

            }
        }
        resaltarZonasProhibidas();
    }
    
    public void mostrarInformacionPersonaje() {
        // Mostrar informacion en textarea
        Personaje ficha = casillaSeleccionada.personajeActual;
        
        String mensaje = "Ficha: " + ficha.nombre + "\n";
        mensaje += "Bando: " + ((ficha.esHeroe) ?"Heroes\n":"Villanos\n");
        
        if (ficha.rango == 0) {
            mensaje += """
                       Las bombas no se pueden mover.
                       Estas eliminan a cualquier ficha excepto a los de rango 3.
                       """;
        } else if (ficha.rango == -1) {
            mensaje += """
                       La Tierra no se puede mover.
                       Cualquier ficha que ataque a la tierra hara que pierdas la partida.
                       """;
        } else if (ficha.rango == 1) {
            mensaje += "Rango: " + ficha.rango + "\n";
            mensaje += """
                       Este personaje pierde contra todas las fichas excepto con las de rango 10.
                       """;
        } else if (ficha.rango == 10) {
            mensaje += "Rango: " + ficha.rango + "\n";
            mensaje += """
                       Este personaje gana con cualquier ficha que lo ataque exceptuando las fichas de rango 1.
                       """;
        }else {
            mensaje += "Rango: " + ficha.rango + "\n";
            mensaje += """
                       Esta ficha gana si su adversario es alguien de rango
                       menor.
                       Si ambas son de rango igual, ambas pierden.
                       """;
        }
        infoArea.setText(mensaje);
    }
    
    public void mostrarPersonajesEliminados() {
        String mensaje = "";
        
            
        for (int i = 0; i<heroesEliminados.toArray().length;i++) {
            Personaje personaje = heroesEliminados.get(i);
            if (personaje.rango != 0 || personaje.rango != -1) 
                mensaje += personaje.nombre + " (Rango " + personaje.rango + ")\n";
            else
                mensaje += personaje.nombre + "\n";
        }
        heroesEliminatedArea.setText(mensaje);
        mensaje = "";

        for (int i = 0; i<villanosEliminados.toArray().length;i++) {
            Personaje personaje = villanosEliminados.get(i);
            if (personaje.rango != 0 || personaje.rango != -1) 
                mensaje += personaje.nombre + " (Rango " + personaje.rango + ")\n";
            else
                mensaje += personaje.nombre + "\n";
        }
        villanosEliminatedArea.setText(mensaje);
        
    }

    public void posicionarPersonajesRango2() {
        
        // Iterar por toda la lista de heroes disponisbles
        
        Random random = new Random();
        // PERSONAJES RANGO 2 HEROES
        for (int i =0;i<heroesIniciales.toArray().length;i++) {
            Personaje personajeActual = heroesIniciales.get(i);
            int columnaAleatoria;
            int filaAleatoria;
            
            if (personajeActual.rango == 2) {
                // Elegir entre fila 6 y 7
                
                int filas[] = new int[2];
                filas[0] = 6;
                filas[1] = 7;
                filaAleatoria = filas[random.nextInt(0,2)];

                // Elegir columna aleatoria hasta que este libre ese espacio
                do {
                    columnaAleatoria = random.nextInt(0, 10);
                } while(casillas[filaAleatoria][columnaAleatoria].personajeActual != null);
                
                casillas[filaAleatoria][columnaAleatoria].setPersonaje(personajeActual);
                personajeActual.posicionado = true;
            }
        }
        
        for (int i =0;i<villanosIniciales.toArray().length;i++) {
            Personaje personajeActual = villanosIniciales.get(i);
            int columnaAleatoria;
            int filaAleatoria;
            
            if (personajeActual.rango == 2) {
                // Elegir entre fila 6 y 7
                
                int filas[] = new int[2];
                filas[0] = 2;
                filas[1] = 3;
                filaAleatoria = filas[random.nextInt(0,2)];

                // Elegir columna aleatoria hasta que este libre ese espacio
                do {
                    columnaAleatoria = random.nextInt(0, 10);
                } while(casillas[filaAleatoria][columnaAleatoria].personajeActual != null);
                
                casillas[filaAleatoria][columnaAleatoria].setPersonaje(personajeActual);
                personajeActual.posicionado = true;
            }
        }
        
    }
    
    public void posicionarTierras() {
        Random random = new Random();
        
        // TIERRA HEROES
        for (int i =0;i<heroesIniciales.toArray().length;i++){
            
            Personaje personajeActual= heroesIniciales.get(i);
            if (personajeActual.posicionado) continue;
            
            int columnaAleatoria;
            
            // Posicionar en la ultima fila de manera aleatoria la ficha de tipo tierra.
            if (personajeActual.rango == -1) {
                // Generar una columna aleatoria hasta que no este en los bordes del tablero
                do {
                    columnaAleatoria = random.nextInt(0, 10);
                } while (columnaAleatoria == 0 || columnaAleatoria == 9);
                
                casillas[9][columnaAleatoria].setPersonaje(personajeActual);
                
                // Agregas las bombas alrededor de la tierra
               Personaje bomba1 = new Personaje("Nova Blast", 0, true, null);
               Personaje bomba2 = new Personaje("Nova Blast", 0, true, null);
               Personaje bomba3 = new Personaje("Nova Blast", 0, true, null);
                
               bomba1.posicionado = true;
               bomba2.posicionado = true;
               bomba3.posicionado = true;

                casillas[8][columnaAleatoria].setPersonaje(bomba1);
                casillas[9][columnaAleatoria-1].setPersonaje(bomba2);
                casillas[9][columnaAleatoria+1].setPersonaje(bomba3);
                
                personajeActual.posicionado = true;
            }
        }
        
        // TIERRA VILLANOS
        
        for (int i =0; i<villanosIniciales.toArray().length;i++){
            Personaje personajeActual = villanosIniciales.get(i);
            
            if (personajeActual.posicionado) continue;
            
            int columnaAleatoria;
            
            // Posicionar en la ultima fila de manera aleatoria la ficha de tipo tierra.
            if (personajeActual.rango == -1) {
                // Generar una columna aleatoria hasta que no este en los bordes del tablero
                do {
                    columnaAleatoria = random.nextInt(0, 10);
                } while (columnaAleatoria == 0 || columnaAleatoria == 9);
                
                casillas[0][columnaAleatoria].setPersonaje(personajeActual);

                
                // Agregas las bombas alrededor de la tierra
                Personaje bomba1 = new Personaje("Pumpkin Bomb", 0, false, null);
                Personaje bomba2 = new Personaje("Pumpkin Bomb", 0, false, null);
                Personaje bomba3 = new Personaje("Pumpkin Bomb", 0, false, null);
                
                bomba1.posicionado = true;
                bomba2.posicionado = true;
                bomba3.posicionado = true;

                casillas[1][columnaAleatoria].setPersonaje(bomba1);
                casillas[0][columnaAleatoria-1].setPersonaje(bomba2);
                casillas[0][columnaAleatoria+1].setPersonaje(bomba3);
                
                personajeActual.posicionado = true;
            }
        }
    }
    
    public void posicionarBombas() {
        
        // Iterar por toda la lista de heroes disponisbles
        
        // BOMBAS HEROES
        Random random = new Random();
        for (int i =0;i<heroesIniciales.toArray().length;i++) {
            Personaje personajeActual = heroesIniciales.get(i);
            
            if (personajeActual.posicionado) continue;
            
            int columnaAleatoria;
            int filaAleatoria;
            
            if (personajeActual.rango == 0) {
                
                // Elegir una fila entre la 8 y 9
                int filas[] = new int[2];
                filas[0] = 8;
                filas[1] = 9;
                filaAleatoria = filas[random.nextInt(0,2)];
                
                // Generar una columna nueva hasta que esa casilla no tenga un personaje adentro
                do {
                    columnaAleatoria = random.nextInt(0, 10);
                } while(casillas[filaAleatoria][columnaAleatoria].personajeActual != null);
                
                casillas[filaAleatoria][columnaAleatoria].setPersonaje(personajeActual);
                personajeActual.posicionado = true;
            }
        }
        
        // BOMBAS VILLANOS
        for (int i =0;i<villanosIniciales.toArray().length;i++) {
            Personaje personajeActual = villanosIniciales.get(i);
            
            if (personajeActual.posicionado) continue;
            int columnaAleatoria;
            int filaAleatoria;
            
            if (personajeActual.rango == 0) {
                
                // Elegir una fila entre la 8 y 9
                int filas[] = new int[2];
                filas[0] = 0;
                filas[1] = 1;
                filaAleatoria = filas[random.nextInt(0,2)];
                
                // Generar una columna nueva hasta que esa casilla no tenga un personaje adentro
                do {
                    columnaAleatoria = random.nextInt(0, 10);
                } while(casillas[filaAleatoria][columnaAleatoria].personajeActual != null);
                
                casillas[filaAleatoria][columnaAleatoria].setPersonaje(personajeActual);
                personajeActual.posicionado = true;
            }
        }
    }
    
    public void posicionarPersonajesRestantes() {
        // Generar una ficha aleatoria y una columna aleatoria
        repaint();
        boolean placed = false;
        
        // Restantes HEROES
        for (int i = 0; i<heroesIniciales.toArray().length;i++) {
            
            Personaje personajeActual = heroesIniciales.get(i);
        
            for (int fila = 6;fila<10;fila++){
                for (int columna = 0; columna < 10; columna++){
                    if (!personajeActual.posicionado) {
                        placed = false;
                        if (casillas[fila][columna].personajeActual == null) {
                           casillas[fila][columna].setPersonaje(personajeActual);
                        
                            personajeActual.posicionado = true;
                            placed = true;
                            break;
                        }
                    }
                }
                
                if (placed) break;
            }
        } 
        
        // Restantes VILLANOS
        for (int i = 0; i<villanosIniciales.toArray().length;i++) {
            
            Personaje personajeActual = villanosIniciales.get(i);
        
            for (int fila = 0;fila<4;fila++){
                for (int columna = 0; columna < 10; columna++){
                    if (!personajeActual.posicionado) {
                        placed = false;
                        if (casillas[fila][columna].personajeActual == null) {
                           casillas[fila][columna].setPersonaje(personajeActual);
                        
                            personajeActual.posicionado = true;
                            placed = true;
                            break;
                        }
                    }
                }
                
                if (placed) break;
            }
        } 
    }

    public void posicionarTodo() {
        posicionarTierras();
        posicionarBombas();
        posicionarPersonajesRango2();
        posicionarPersonajesRestantes();
    }
}
