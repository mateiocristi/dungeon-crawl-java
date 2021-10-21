package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Weapon;

import java.util.ArrayList;
import java.util.List;

public class GameMap extends CellsMap{

    private Player player;
    private List<Skeleton> skeletons;
    private boolean hasKey;

    public boolean isHasKey() {
        return hasKey;
    }

    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    public GameMap(int width, int height, CellType defaultCellType) {
        super(width, height, defaultCellType);
        setHasKey(false);
        this.skeletons = new ArrayList<>(1);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Skeleton> getSkeletons() {
        return skeletons;
    }

    public void addSkeleton(Skeleton skeleton) {
        skeletons.add(skeleton);
    }

    public boolean areSkeletonsDead() {
        for (Skeleton skeleton: skeletons) {
            if (skeleton != null) {
                return true;
            }
        }
        return false;
    }

}
