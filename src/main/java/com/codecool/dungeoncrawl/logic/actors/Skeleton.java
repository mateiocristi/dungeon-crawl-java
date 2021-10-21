package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor {

    public Skeleton(Cell cell) {
        super(cell);
        setArmor(3);
        setDamage(3);
        setPlayer(false);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

}
