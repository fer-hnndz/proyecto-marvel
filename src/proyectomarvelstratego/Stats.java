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
    int partidasJugadas = 0;
    int victoriasHeroes = 0;
    int victoriasVillanos = 0;
    
    void addPartida(boolean gananHeroes) {
        partidasJugadas++;
        if (gananHeroes) victoriasHeroes++;
        else victoriasVillanos++;
    }
}
