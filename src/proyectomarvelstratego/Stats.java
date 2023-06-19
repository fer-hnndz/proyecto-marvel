/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomarvelstratego;

/**
 *
 * @author Jorge Hernandez
 */
public class Stats {
    private static int partidasJugadas = 0;
    private static int victoriasHeroes = 0;
    private static int victoriasVillanos = 0;
    
    public static int getPartidasJugadas() {
        return partidasJugadas;
    }
    
    public static int getVictoriasHeroes() {
        return victoriasHeroes;
    }
    
    public static int getVictoriasVillanos() {
        return victoriasVillanos;
    }
    
    void addPartida(boolean gananHeroes) {
        partidasJugadas++;
        if (gananHeroes) victoriasHeroes++;
        else victoriasVillanos++;
    }
}
