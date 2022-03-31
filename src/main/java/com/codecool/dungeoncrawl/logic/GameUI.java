package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.items.Item;

public class GameUI extends CellsMap{

    private Item item;

    public GameUI(int width, int height, CellType defaultCellType) {
        super(width, height, defaultCellType);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
