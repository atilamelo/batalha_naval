package com.mygdx.game;

import java.util.ArrayList;
import com.badlogic.gdx.Game;
import com.mygdx.game.BatalhaNaval.LogicGame;
import com.mygdx.game.BatalhaNaval.Ship;

public class BattleShipGame extends Game{

    @Override
    public void create() {
        ArrayList<Ship> navios = new ArrayList<Ship>();
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
        setScreen(new MenuScreen(this));
    }
    
}
