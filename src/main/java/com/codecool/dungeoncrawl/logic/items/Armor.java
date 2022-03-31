package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Armor extends Item{

    private int resistance;

    public Armor(int resistance, Cell cell) {
        super(cell);
        this.resistance = resistance;
    }

    public int getResistance() {
        return resistance;
    }

    @Override
    public String getTileName() {
        return "armor";
    }
}
