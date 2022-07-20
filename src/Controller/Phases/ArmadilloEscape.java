package Controller.Phases;

import Controller.LivesListener;
import Controller.PhaseCompleteListener;
import Global.Phase;
import Models.Direction;
import Models.Entities.Collectible.EmptyChest;
import Models.Entities.Collectible.Heart;
import Models.Entities.Collectible.OpenChest;
import Models.Entities.Enemy.Armadillo;
import Models.Entities.Glass;
import Models.Entities.Lolo;
import Models.Entities.Obstacle.Bush;
import Models.Entities.Obstacle.Chest;
import Models.Entities.Obstacle.Door;
import Models.Entities.Obstacle.Tree;
import Models.Entities.Scenery.Water;

import java.util.ArrayList;
import java.util.Arrays;

public class ArmadilloEscape extends Phase {

    public ArmadilloEscape(PhaseCompleteListener phaseCompleteListener, LivesListener livesListener) {
        super(phaseCompleteListener, livesListener);
    }

    public void start() {
        super.startPhase();
        super.start();
    }

    @Override
    protected void createLolo() {
        this.lolo = new Lolo(11, 6);
        this.entities.add(lolo);
    }

    @Override
    protected void createEntities() {
        this.addEntity(new Bush(1, 4));
        this.addEntity(new Tree(1, 5));
        this.addEntity(new Tree(1, 6));
        this.addEntity(new Bush(1, 9));

        this.addEntity(new Bush(2, 4));
        this.addEntity(new Bush(2, 5));
        this.addEntity(new Tree(2, 6));
        this.addEntity(new Bush(2, 8));
        this.addEntity(new Bush(2, 9));

        this.addEntity(new Tree(4, 1));
        this.addEntity(new Bush(4, 2));
        this.addEntity(new Bush(4, 3));
        this.addEntity(new Bush(4, 4));
        this.addEntity(new Tree(4, 5));
        this.addEntity(new Tree(4, 6));
        this.addEntity(new Tree(4, 7));
        this.addEntity(new Tree(4, 9));
        this.addEntity(new Tree(4, 10));

        this.addEntity(new Bush(5, 5));
        this.addEntity(new Tree(5, 6));

        this.addEntity(new Tree(8, 9));

        this.addEntity(new Bush(9, 9));
        this.addEntity(new Tree(9, 6));

        this.addEntity(new Tree(10, 5));
        this.addEntity(new Bush(10, 9));

        this.addEntity(new Tree(11, 9));

        Glass glass = new Glass(7, 10);
        this.lolo.addPushable(glass);
        this.addEntity(glass);
        glass = new Glass(7,1);
        this.lolo.addPushable(glass);
        this.addEntity(glass);
        glass = new Glass(7,2);
        this.lolo.addPushable(glass);
        this.addEntity(glass);
        glass = new Glass(7,3);
        this.lolo.addPushable(glass);
        this.addEntity(glass);
        glass = new Glass(10,1);
        this.lolo.addPushable(glass);
        this.addEntity(glass);
        glass = new Glass(10,2);
        this.lolo.addPushable(glass);
        this.addEntity(glass);
        glass = new Glass(10,3);
        this.lolo.addPushable(glass);
        this.addEntity(glass);
    }

    @Override
    protected void createHearts() {
        final ArrayList<Heart> hearts = new ArrayList<>();

        hearts.add(new Heart(1, 8));
        hearts.add(new Heart(5, 1));
        hearts.add(new Heart(5, 3));
        hearts.add(new Heart(3, 4));
        hearts.add(new Heart(8, 2));
        hearts.add(new Heart(8, 10));

        this.hearts = hearts.size();
        this.entities.addAll(hearts);
    }

    @Override
    protected void createChest() {
        this.entities.add(new EmptyChest(5, 2));
        this.entities.add(new OpenChest(5, 2));
        this.entities.add(new Chest(5, 2));
    }

    @Override
    protected void createDoor() {
        this.door = new Door(0, 7);
        this.entities.add(door);
    }

    @Override
    protected void createEnemies() {
        Armadillo armadillo = new Armadillo(1, 1);

        this.lolo.addStalker(armadillo);

        this.addEntity(armadillo);
    }

    @Override
    protected void createScenery() {
        this.addEntity(new Water(5, 4));

        this.addEntity(new Water(6, 4));

        this.addEntity(new Water(7, 4));
        this.addEntity(new Water(7, 5));

        this.addEntity(new Water(8, 4));
        this.addEntity(new Water(8, 5));
        this.addEntity(new Water(8, 6));

        this.addEntity(new Water(9, 4));
        this.addEntity(new Water(9, 5));

        this.addEntity(new Water(10, 4));
    }
}
