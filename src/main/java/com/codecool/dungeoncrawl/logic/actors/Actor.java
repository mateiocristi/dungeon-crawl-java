package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {

    private Cell currentCell;
    private int health = 5;
    private int damage;
    private int armor;
    private boolean isPlayer;

    public Actor(Cell cell) {
        this.currentCell = cell;
        this.currentCell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = currentCell.getNeighbor(dx, dy);

        // check for wall
        if (isWall(nextCell)) {
            return;
        }
        // check for monster
        if (isActor(nextCell)) {
            // monster have more damage than player's armor
            if (nextCell.getActor().getDamage() > currentCell.getActor().getArmor()) {
                currentCell.getActor().setHealth(currentCell.getActor().getHealth() - 1);
                System.out.println("monster has more damage than ur armor");
                return;
            }
            // player has less damage than monster's armor
            if (nextCell.getActor().getArmor() > currentCell.getActor().getDamage()) {
                System.out.println("monster has more armor than ur damage");
                return;
            }
        }
        // good to move
        this.currentCell.setActor(null);
        nextCell.setActor(this);
        this.currentCell = nextCell;
    }

    public boolean isWall(Cell nextCell) {
        // wall and door
        if (nextCell.getType() == CellType.WALL || nextCell.getType() ==  CellType.DOOR) {
            return true;
        }
        return false;
    }

    public boolean isActor(Cell nextCell) {
        // not null and not player
        if (nextCell.getActor() != null && !nextCell.getActor().isPlayer()) {
            return true;
        }
        return false;
    }

    public int getHealth() {
        return health;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public int getX() {
        return currentCell.getX();
    }

    public int getY() {
        return currentCell.getY();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayer(boolean player) {
        isPlayer = player;
    }
}
