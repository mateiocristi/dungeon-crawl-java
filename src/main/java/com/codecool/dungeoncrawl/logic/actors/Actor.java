package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {

    private Cell currentCell;
    private int health = 2;

    public Actor(Cell cell) {
        this.currentCell = cell;
        this.currentCell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = currentCell.getNeighbor(dx, dy);

        // check for walls
        if (isWall(nextCell)) {
            return;
        }
        // good to move
        this.currentCell.setActor(null);
        nextCell.setActor(this);
        this.currentCell = nextCell;
    }

    public boolean isWall(Cell nextCell) {
        if (nextCell.getType() == CellType.WALL || nextCell.getActor() != null) {
            return true;
        }
        return false;
    }

//    public boolean isActor(Cell nextCell) {
//        if (nextCell.getType() == CellType.)
//    }

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
}
