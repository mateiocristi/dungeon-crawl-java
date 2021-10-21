package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Weapon;

public class GameMap extends CellsMap{

    private Player player;

    public GameMap(int width, int height, CellType defaultCellType) {
        super(width, height, defaultCellType);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }


}
