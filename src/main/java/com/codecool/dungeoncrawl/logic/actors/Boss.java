package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Boss extends Actor{

    public Boss(Cell cell) {
        super(cell);
        setArmor(10);
        setDamage(9);
        setPlayer(false);
    }

    @Override
    public String getTileName() {
        return CellType.BOSS.getTileName();
    }
}
