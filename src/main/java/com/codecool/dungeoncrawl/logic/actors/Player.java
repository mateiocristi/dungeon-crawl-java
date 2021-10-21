package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Weapon;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {

    private final int MAX_ITEMS = 5;
    private List<Item> weapons;
    private Cell cell;

    public Player(Cell cell) {
        super(cell);
        this.cell = cell;
        weapons = new ArrayList<>();
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

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);

        // check for walls
        if (isWall(nextCell)) {
            return;
        }
        // good to move
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    private boolean isWeapon(Cell nextCell) {
        if (nextCell.getItem() != null) {
            return true;
        }
        return false;
    }
}
