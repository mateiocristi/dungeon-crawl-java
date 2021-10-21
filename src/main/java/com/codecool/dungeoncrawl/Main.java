package com.codecool.dungeoncrawl;

import animatefx.animation.Bounce;
import animatefx.animation.Shake;
import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Armor;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Weapon;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {



    GameUI inventoryMap = new GameUI(5, 19, CellType.EMPTY);
    Canvas inventoryCanvas = new Canvas(
            inventoryMap.getWidth() * Tiles.TILE_WIDTH,
            inventoryMap.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext inventoryCanvasContext = inventoryCanvas.getGraphicsContext2D();

    GameMap map = MapLoader.loadMap();
    List<Item> weapons = map.getPlayer().getWeapons();
    List<Item> armors = map.getPlayer().getArmors();

    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Button pickItemButton = new Button("Pick item");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));
        handleUIElements(pickItemButton);

//        ui.add(new Label("Health: "), 0, 0);
//        ui.add(healthLabel, 1, 0);
        ui.add(pickItemButton, 0, 10);
        ui.add(inventoryCanvas, 0, 2);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();

    }

    private void handleUIElements(Button pickItemButton) {
        pickItemButton.setFocusTraversable(false);
        pickItemButton.setDisable(true);
        pickItemButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("clicked");
                if (map.getPlayer().getCurrentCell().getItem().getTileName().equals(CellType.ARMOR.getTileName())) {
                    map.getPlayer().pickArmor(map.getPlayer().getCurrentCell().getItem());
                    map.getPlayer().setArmor(map.getPlayer().getArmor() + ((Armor)map.getPlayer().getCurrentCell().getItem()).getResistance());
                } else  {
                    map.getPlayer().pickWeapon(map.getPlayer().getCurrentCell().getItem());
                    map.getPlayer().setDamage(map.getPlayer().getDamage() + ((Weapon)map.getPlayer().getCurrentCell().getItem()).getDamage());
                }
                map.getPlayer().getCurrentCell().setItem(null);
                refresh();
            }
        });
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                if (map.getPlayer().getCurrentCell().isItem()) {
                    pickItemButton.setDisable(false);
                } else {
                    pickItemButton.setDisable(true);
                }
                if (map.getPlayer().getCurrentCell().getType() == CellType.KEY) {
                    map.setHasKey(true);
                    map.getPlayer().getCurrentCell().setType(CellType.EMPTY);
                }
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                if (map.getPlayer().getCurrentCell().isItem()) {
                    pickItemButton.setDisable(false);
                } else {
                    pickItemButton.setDisable(true);
                }
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                if (map.getPlayer().getCurrentCell().isItem()) {
                    pickItemButton.setDisable(false);
                } else {
                    pickItemButton.setDisable(true);
                }
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1,0);
                if (map.getPlayer().getCurrentCell().isItem()) {
                    pickItemButton.setDisable(false);
                } else {
                    pickItemButton.setDisable(true);
                }
                refresh();
                break;
        }
    }

    private void refresh() {

        inventoryCanvasContext.setFill(Color.BLACK);
        inventoryCanvasContext.fillRect(0, 0, inventoryCanvas.getWidth(), inventoryCanvas.getHeight());

        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int health = map.getPlayer().getHealth();
        System.out.println("health is " + health);
        for (int x = 0; x < canvas.getWidth() / 4; x++) {
            for (int y = 0; y < canvas.getHeight() / 4; y++) {
                // display the ui
                if (x < 5 && y < 19) {
                    Cell cell = inventoryMap.getCell(x, y);;
                    // health bar
                    if (y == 0 && health > 0) {
                        cell.setType(CellType.HEALTH);
                        health--;
                    } else if (y == 0 && health == 0) {
                        cell.setType(CellType.EMPTY);
                    }
                    // display weapon
                    if (y == 1) {
                        if (x < weapons.size()) {
                            cell.setType(((Weapon)weapons.get(x)).getType());
                        } else {
                            cell.setType(CellType.EMPTY);
                        }

                    }
                    // display armor
                    if (y == 2) {
                        if (x < armors.size()) {
                            cell.setType(CellType.ARMOR);
                        }
                    }
                    // display other items
                    if ( y == 3 && x == 0) {
                        if (map.isHasKey()) {
                            cell.setType(CellType.KEY);
                        }
                    }
                    Tiles.drawTile(inventoryCanvasContext, cell, x, y);
                }
                // display the board
                if (x < map.getWidth() && y < map.getHeight()) {
                    Cell cell = map.getCell(x, y);
                    if (cell.getActor() != null) {
                        Tiles.drawTile(context, cell.getActor(), x, y);
                    } else if (cell.getItem() != null){
                        Tiles.drawTile(context, cell.getItem(), x, y);
                    } else if(cell.getType() == CellType.KEY) {
                        Tiles.drawTile(context, cell, x, y);
                    } else {
                        Tiles.drawTile(context, cell, x, y);
                    }
                    if (cell.getType() == CellType.DOOR && map.isHasKey()) {
                        cell.setType(CellType.OPEN);
                        Tiles.drawTile(context, cell, x, y);
                    }

                }

            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
    }
}
