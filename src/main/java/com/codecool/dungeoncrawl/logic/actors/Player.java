package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {

    private final int MAX_ITEMS = 5;
    private List<Item> weapons;


    public Player(Cell cell) {
        super(cell);
        this.weapons = new ArrayList<>(MAX_ITEMS);
    }


    public String getTileName() {
        return "player";
    }

    public void pickWeapon(Item item) {
        System.out.println("will add " + item);
        weapons.add(item);
    }

    public List<Item> getWeapons() {
        return weapons;
    }

    private boolean isWeapon(Cell nextCell) {
        if (nextCell.getItem() != null) {
            return true;
        }
        return false;
    }
}
