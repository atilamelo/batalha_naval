package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.Screens.MenuScreen;


public class BattleShipGame extends Game{

    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
    
}
