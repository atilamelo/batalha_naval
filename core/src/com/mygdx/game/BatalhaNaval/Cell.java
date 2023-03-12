package com.mygdx.game.BatalhaNaval;

import java.util.List;

/**
 * Classe que representa uma célula no tabuleiro de jogo do tipo batalha naval.
 */
public class Cell {
    private boolean isHit; // indica se a célula já foi atingida
    private Ship ship = null; // ponteiro para o navio que ocupa a célula
    private int x, y; // Index da célula

    /**
     * Construtor da classe Cell.
     * 
     * @param x a posição x da célula no tabuleiro
     * @param y a posição y da célula no tabuleiro
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retorna o navio que ocupa a célula.
     * 
     * @return o navio que ocupa a célula, null se a célula não possui navio
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Retorna se a célula está ocupada por um navio.
     * 
     * @return true se a célula possui um navio, false caso contrário
     */
    public boolean estaOcupado(){
        return ship != null;
    }

    /**
     * Retorna a posição x da célula no tabuleiro.
     * 
     * @return a posição x da célula no tabuleiro
     */
    public int getX() {
        return x;
    }

    /**
     * Retorna a posição y da célula no tabuleiro.
     * 
     * @return a posição y da célula no tabuleiro
     */
    public int getY() {
        return y;
    }

    /**
     * Define o navio que ocupa a célula.
     * 
     * @param ship o navio que ocupa a célula
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    /**
     * Define se a célula já foi atingida.
     * 
     * @param hit true se a célula já foi atingida, false caso contrário
     */
    public void setHit(boolean hit) {
        isHit = hit;
    }

    /**
     * Retorna se a célula já foi atingida.
     * 
     * @return true se a célula já foi atingida, false caso contrário
     */
    public boolean isHit() {
        return isHit;
    }

    /**
     * Retorna uma representação em String da célula.
     * 
     * @return a representação em String da célula
     */
    @Override
    public String toString() {
        return ship == null ? " " : Integer.toString(ship.getSize());
    }
}
