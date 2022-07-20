/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import Controller.Game;
import Global.Menu;
import Models.Direction;
import Models.Entities.Enemy.GreenMonster;
import Models.Entities.Enemy.RockMonster;
import Models.Entities.Enemy.Skull;
import Models.Entities.Enemy.Snake;
import Models.Entities.Lolo;
import Models.Entities.Obstacle.Bush;
import Models.Entities.Obstacle.Tree;
import Models.Entities.Obstacle.Wall.Side;
import Models.Entities.Scenery.Water;
import Models.Entity;

import java.io.*;
import java.util.Arrays;

/**
 *
 * @author junio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        serializeAll();
        Menu menu = new Menu();
        menu.start();
    }

    private static void serializeAll() {
        RockMonster monster = new RockMonster(0, 0);
        monster.setDirections(Arrays.asList(Direction.RIGHT, Direction.DOWN, Direction.LEFT, Direction.TOP));

        serializeEntity(monster);
        serializeEntity(new Tree(0, 0));
        serializeEntity(new Bush(0, 0));
        serializeEntity(new Side(0, 0));
        serializeEntity(new Water(0, 0));
        serializeEntity(new Skull(0, 0));
        serializeEntity(new Snake(0, 0));
        serializeEntity(new GreenMonster(0, 0));
    }

    private static void serializeEntity(Entity toBeSerialized) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./Serialized/"+toBeSerialized.getClass().getSimpleName()+".dat");
            ObjectOutputStream serializer = new ObjectOutputStream(fileOutputStream);
            serializer.writeObject(toBeSerialized);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
