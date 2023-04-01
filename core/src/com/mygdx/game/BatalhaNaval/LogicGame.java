package com.mygdx.game.BatalhaNaval;

import java.util.List;

import com.mygdx.game.Dificuldade;

public class LogicGame {
    private Tabuleiro tabuleiro;
    private List<Ship> navios; 
    private Player jogador; 
    private Dificuldade dificuldade; 

    public LogicGame(int alturaTabuleiro, int larguraTabuleiro, List<Ship> navios, Dificuldade dificuldade) {
        this.tabuleiro = new Tabuleiro(larguraTabuleiro, alturaTabuleiro, navios);
        this.navios = navios;
        this.jogador = new Player();
        this.dificuldade = dificuldade; 

        switch(dificuldade){
            case FACIL:
                jogador.qntdBombas = Integer.MAX_VALUE; 
                break; 
            case MEDIO: 
                jogador.qntdBombas = 80; 
                break;
            case DIFICIL:
                jogador.qntdBombas = 40; 
                break;
        }
    }

    public boolean isJogoTerminado(){
        if(jogador.qntdBombas == 0){
            return true;
        }

        for(Ship ship : navios){
            if(!ship.isAfundado()){
                return false;
            }
        }

        return true; 
    }

    public boolean jogadorVenceu(){
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
    
    public int getQntdBombas(){
        return jogador.qntdBombas;
    }

    public void atacar(){
        if(jogador.qntdBombas != Integer.MAX_VALUE){
            jogador.qntdBombas--;
        }
    }
    
}
