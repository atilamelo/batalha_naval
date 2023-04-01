package com.mygdx.game.Logic;

public class Player {
    private int qntdBombas;

    public Player(int qntdBombas) {
        this.qntdBombas = qntdBombas;
    }

    public int getQntdBombas() {
        return qntdBombas;
    }

    public void atacar() {
        qntdBombas--;
    }
}
