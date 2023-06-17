/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomarvelstratego;

/**
 *
 * @author Jorge Hernandez
 */
public class Partida {
    Usuario contrincante;
    boolean victoria;
    String bandoUsado;
    double puntosGanados;

    public Partida(Usuario contrincante, boolean victoria, String bandoUsado, double puntosGanados) {
        this.contrincante = contrincante;
        this.victoria = victoria;
        this.bandoUsado = bandoUsado;
        this.puntosGanados = puntosGanados;
    }
    
    
}
