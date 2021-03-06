package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Boss;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Armor;
import com.codecool.dungeoncrawl.logic.items.Weapon;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(InputStream is) {

        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);

        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            Skeleton skeleton = new Skeleton(cell);
                            cell.setActor(skeleton);
                            map.addSkeleton(skeleton);
                            break;
                        case 'w':
                            cell.setType(CellType.FLOOR);
                            cell.setItem(new Weapon(10, cell, CellType.SWORD));
                            break;
                        case 'x':
                            cell.setType(CellType.FLOOR);
                            cell.setItem(new Weapon(12, cell, CellType.AXE));
                            break;
                        case 'a':
                            cell.setType(CellType.FLOOR);
                            cell.setItem(new Armor(10, cell));
                            break;
                        case 'd':
                            cell.setType(CellType.DOOR);
                            break;
                        case 'k':
                            cell.setType(CellType.KEY);
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            Boss boss = new Boss(cell);
                            cell.setActor(boss);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
