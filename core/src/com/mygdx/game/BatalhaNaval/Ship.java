package com.mygdx.game.BatalhaNaval;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Classe que representa um navio em um jogo de batalha naval.
 * 
 * Um navio tem um tamanho e é composto por uma lista de células.
 */
public class Ship {
    private int size;
    private List<Cell> cells;

    /**
     * 
     * Cria um novo navio com o tamanho especificado.
     * 
     * @param size tamanho do navio
     */
    public Ship(int size) {
        this.size = size;
        this.cells = new ArrayList<>();
    }

    /**
     * 
     * Retorna o tamanho do navio.
     * 
     * @return tamanho do navio
     */
    public int getSize() {
        return size;
    }

    /**
     * 
     * Retorna a lista de células ocupadas pelo navio.
     * 
     * @return lista de células ocupadas pelo navio
     */
    public List<Cell> getCells() {
        return cells;
    }

    /**
     * 
     * Adiciona uma célula à lista de células ocupadas pelo navio.
     * 
     * @param cell célula a ser adicionada
     */
    public void addCell(Cell cell) {
        this.cells.add(cell);
    }

    /**
     * 
     * Verifica se o navio está afundado.
     * 
     * @return true se todas as células do navio estiverem atingidas, false caso
     *         contrário
     */
    public boolean isAfundado() {
        for (Cell cell : cells) {
            if (!cell.isHit()) {
                return false;
            }
        }

        return true;
    }
}