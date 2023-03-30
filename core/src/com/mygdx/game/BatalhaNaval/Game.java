package com.mygdx.game.BatalhaNaval;

import java.util.List;

public class Game {
    private Tabuleiro tabuleiro;
    private List<Ship> navios; 

    public Game(int alturaTabuleiro, int larguraTabuleiro, List<Ship> navios) {
        this.tabuleiro = new Tabuleiro(larguraTabuleiro, alturaTabuleiro, navios);
        this.navios = navios;
    }

    public boolean isJogoTerminado(){
        for(Ship ship : navios){
            if(!ship.isAfundado()){
                return false;
            }
        }

        return true; 
    }

    public void atacar(int x, int y){
        Cell cell = tabuleiro.getCell(x, y);
        if(!cell.isHit()){
            cell.setHit(true);
        }
    }

    public Tabuleiro getTabuleiro(){
        return tabuleiro; 
    }
    
}
