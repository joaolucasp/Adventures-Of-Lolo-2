package Controller.Phases;

import Controller.LivesListener;
import Controller.PhaseCompleteListener;
import Global.Phase;
import Models.Entities.Collectible.EmptyChest;
import Models.Entities.Collectible.OpenChest;
import Models.Entities.Enemy.Snake;
import Models.Entities.Enemy.Statue;
import Models.Entities.Glass;
import Models.Entities.Obstacle.Bush;
import Models.Entities.Obstacle.Chest;
import Models.Entities.Obstacle.Door;
import Models.Entities.Obstacle.Tree;
import Models.Entities.Collectible.Heart;
import Models.Entities.Lolo;
import Models.Entities.Scenery.DryGrass;
import Models.Entities.Scenery.Grass;

import java.util.ArrayList;

public class SnakeGarden extends Phase {

    public SnakeGarden(PhaseCompleteListener phaseCompleteListener, LivesListener livesListener) {
        super(phaseCompleteListener, livesListener);
    }

    @Override
    public void start() {
        super.startPhase();
        super.start();
    }

    @Override
    protected void createLolo() {
        this.lolo = new Lolo(8, 6);
        this.addEntity(lolo);
    }

    @Override
    protected void createEntities() {
        this.addEntity(new Bush(1, 1));
        this.addEntity(new Tree(2, 3));
        this.addEntity(new Tree(2, 4));
        this.addEntity(new Tree(3, 3));
        this.addEntity(new Tree(3, 4));
        this.addEntity(new Bush(3, 1));
        this.addEntity(new Bush(2, 7));
        this.addEntity(new Bush(3, 7));
        this.addEntity(new Bush(3, 8));
        this.addEntity(new Bush(4, 8));

        this.addEntity(new Tree(1, 8));
        this.addEntity(new Tree(1, 9));
        this.addEntity(new Tree(2, 8));
        this.addEntity(new Tree(2, 9));
        this.addEntity(new Tree(3, 9));

        this.addEntity(new Tree(4, 1));
        this.addEntity(new Tree(7, 1));
        this.addEntity(new Tree(8, 1));
        this.addEntity(new Tree(9, 1));
        this.addEntity(new Tree(8, 2));
        this.addEntity(new Tree(9, 2));
        this.addEntity(new Bush(10, 1));
        this.addEntity(new Bush(11, 1));
        this.addEntity(new Bush(11, 4));
        this.addEntity(new Bush(10, 5));
        this.addEntity(new Bush(11, 5));
        this.addEntity(new Bush(10, 6));
        this.addEntity(new Tree(11, 6));
        this.addEntity(new Tree(9, 7));
        this.addEntity(new Tree(10, 7));
        this.addEntity(new Tree(11, 7));
        this.addEntity(new Tree(11, 9));
        this.addEntity(new Bush(10, 9));
        this.addEntity(new Tree(10, 10));
        this.addEntity(new Bush(9, 10));
        this.addEntity(new Tree(11, 9));
        this.addEntity(new Tree(11, 10));

        Glass glass = new Glass(6, 9);
        this.lolo.addPushable(glass);
        this.addEntity(glass);
    }

    @Override
    protected void createHearts() {
        final ArrayList<Heart> hearts = new ArrayList<>();

        hearts.add(new Heart(1, 7));
        hearts.add(new Heart(1, 10));
        hearts.add(new Heart(2, 1));
        hearts.add(new Heart(11, 2));
        hearts.add(new Heart(10, 8));
        hearts.add(new Heart(11, 8));

        this.hearts = hearts.size();
        this.entities.addAll(hearts);
    }

    @Override
    protected void createChest() {
        this.addEntity(new EmptyChest(3, 6));
        this.addEntity(new OpenChest(3, 6));
        this.addEntity(new Chest(3, 6));
    }

    @Override
    protected void createDoor() {
        this.door = new Door(0, 6);
    }

    @Override
    protected void createEnemies() {
        this.addEntity(new Snake(2, 2));
        this.addEntity(new Snake(6, 4));
        this.addEntity(new Snake(6, 8));
    }

    @Override
    protected void createScenery() {
        this.addEntity(new Grass(5, 1));

        this.addEntity(new Grass(6, 1));
        this.addEntity(new Grass(6, 2));
        this.addEntity(new Grass(6, 10));

        this.addEntity(new Grass(7, 2));
        this.addEntity(new Grass(7, 10));

        this.addEntity(new Grass(8, 9));
        this.addEntity(new Grass(8, 10));

        this.addEntity(new Grass(9, 9));
    }
}
