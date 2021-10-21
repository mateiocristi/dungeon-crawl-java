package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    HEALTH("health"),
    SKELETON("skeleton"),
    ARMOR("armor"),
    SWORD("sword"),
    PISTOL("pistol"),
    AXE("axe");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
