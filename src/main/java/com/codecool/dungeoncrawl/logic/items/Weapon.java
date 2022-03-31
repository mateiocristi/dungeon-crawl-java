package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Weapon extends Item{

    private int damage;
    private CellType cellType;

    public Weapon(int damage, Cell cell, CellType itemType) {
        super(cell);
        this.damage = damage;
        this.cellType = itemType;
    }


    public int getDamage() {
        return damage;
    }

    public CellType getType() {
        return cellType;
    }

    @Override
    public String getTileName() {
        return cellType.getTileName();
    }
}
