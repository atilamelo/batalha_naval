package com.mygdx.game.BatalhaNaval;

import java.util.List;

public class LogicGame {
    private Tabuleiro tabuleiro;
    private List<Ship> navios; 

    public LogicGame(int alturaTabuleiro, int larguraTabuleiro, List<Ship> navios) {
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

    public Tabuleiro getTabuleiro(){
        return tabuleiro; 
    }

    public List<Ship> getNavios() {
        return navios;
    }
    
}
