package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {

    private final int MAX_ITEMS = 5;
    private List<Item> weapons;
    private List<Item> armors;
    private int currentLevel;

    public Player(Cell cell) {
        super(cell);
        setArmor(2);
        setDamage(2);
        setPlayer(true);
        this.weapons = new ArrayList<>(MAX_ITEMS);
        this.armors = new ArrayList<>(MAX_ITEMS);
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public String getTileName() {
        return "player";
    }

    public void pickWeapon(Item item) {
        System.out.println("will add " + item);
        weapons.add(item);
    }

    public void pickArmor(Item item) {
        System.out.println("will add " + item);
        armors.add(item);
    }

    public List<Item> getArmors() {
        return armors;
    }

    public List<Item> getWeapons() {
        return weapons;
    }

}
