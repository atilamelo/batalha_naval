package com.mygdx.game;

import com.badlogic.gdx.Game;

public class BattleShipGame extends Game{

    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
    
}
