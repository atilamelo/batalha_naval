package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.game.BatalhaNaval.*;

public class Main {
    public static void main(String[] args) {
        List<Ship> navios = new ArrayList<Ship>();
        navios.add(new Ship(5));
        navios.add(new Ship(4));
        navios.add(new Ship(4));
        navios.add(new Ship(3));
        navios.add(new Ship(3));
        navios.add(new Ship(3));
        navios.add(new Ship(2));
        navios.add(new Ship(2));
        navios.add(new Ship(2));
        navios.add(new Ship(2));

        // Imprime as letras na parte superior do tabuleiro
        Tabuleiro tabuleiro = new Tabuleiro(10, 10, navios);
        
        tabuleiro.printTabuCells();

    }
    
}
