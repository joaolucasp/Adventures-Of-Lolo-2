package Controller.Phases;

import Controller.LivesListener;
import Controller.PhaseCompleteListener;
import Global.Phase;
import Models.Direction;
import Models.Entities.Collectible.EmptyChest;
import Models.Entities.Collectible.Heart;
import Models.Entities.Collectible.OpenChest;
import Models.Entities.Enemy.RockMonster;
import Models.Entities.Enemy.Statue;
import Models.Entities.Glass;
import Models.Entities.Lolo;
import Models.Entities.Obstacle.Chest;
import Models.Entities.Obstacle.Door;
import Models.Entities.Obstacle.Tree;
import Models.Entities.Scenery.DryGrass;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class RocksAndShots extends Phase {
    public RocksAndShots(PhaseCompleteListener phaseCompleteListener, LivesListener livesListener) {
        super(phaseCompleteListener, livesListener);
    }

    public void start() {
        super.startPhase();
        super.start();
    }
    
    @Override
    protected void createLolo() {
        this.lolo = new Lolo(11, 6);
        this.addEntity(lolo);
    }

    @Override
    protected void createEntities() {
        this.addEntity(new Tree(2, 3));
        this.addEntity(new Tree(2, 8));

        this.addEntity(new Tree(6, 2));
        this.addEntity(new Tree(6, 9));

        this.addEntity(new Tree(10, 3));
        this.addEntity(new Tree(10, 9));

        Glass glass = new Glass(4, 6);
        this.lolo.addPushable(glass);
        this.addEntity(glass);
        glass = new Glass(4,9);
        this.lolo.addPushable(glass);
        this.addEntity(glass);
        glass = new Glass(8,5);
        this.lolo.addPushable(glass);
        this.addEntity(glass);
        glass = new Glass(8,7);
        this.lolo.addPushable(glass);
        this.addEntity(glass);
        glass = new Glass(8,9);
        this.lolo.addPushable(glass);
        this.addEntity(glass);
    }

    @Override
    protected void createHearts() {
        final ArrayList<Heart> hearts = new ArrayList<>();

        hearts.add(new Heart(5, 4));
        hearts.add(new Heart(5, 6));
        hearts.add(new Heart(5, 8));

        hearts.add(new Heart(7, 4));
        hearts.add(new Heart(7, 6));
        hearts.add(new Heart(7, 8));

        this.hearts = hearts.size();
        this.entities.addAll(hearts);
    }

    @Override
    protected void createChest() {
        this.addEntity(new EmptyChest(1, 10));
        this.addEntity(new OpenChest(1, 10));
        this.addEntity(new Chest(1, 10));
    }

    @Override
    protected void createDoor() {
        this.door = new Door(0, 6);
    }

    @Override
    protected void createEnemies() {
        Statue statue = new Statue(6, 5);
        this.lolo.addAim(statue);
        this.addEntity(statue);
        statue = new Statue(6, 7);
        this.addEntity(statue);
        this.lolo.addAim(statue);
        this.addEntity(statue);

        RockMonster rockMonster = new RockMonster(5, 4);
        rockMonster.setDirections(Arrays.asList(
                Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT,
                Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN,
                Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT,
                Direction.TOP, Direction.TOP, Direction.TOP, Direction.TOP));

        this.addEntity(rockMonster);

        rockMonster = new RockMonster(5, 8);
        rockMonster.setDirections(Arrays.asList(
                Direction.DOWN, Direction.DOWN,
                Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT,
                Direction.TOP, Direction.TOP,
                Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT));

        this.addEntity(rockMonster);
    }

    @Override
    protected void createScenery() {
        this.addEntity(new DryGrass(2, 9));

        this.addEntity(new DryGrass(3, 2));
        this.addEntity(new DryGrass(3, 3));
        this.addEntity(new DryGrass(3, 4));
        this.addEntity(new DryGrass(3, 5));
        this.addEntity(new DryGrass(3, 7));
        this.addEntity(new DryGrass(3, 8));
        this.addEntity(new DryGrass(3, 9));

        this.addEntity(new DryGrass(4, 2));
        this.addEntity(new DryGrass(5, 2));
        this.addEntity(new DryGrass(7, 2));
        this.addEntity(new DryGrass(8, 2));

        this.addEntity(new DryGrass(9, 2));
        this.addEntity(new DryGrass(9, 3));
        this.addEntity(new DryGrass(9, 4));
        this.addEntity(new DryGrass(9, 5));
        this.addEntity(new DryGrass(9, 7));
        this.addEntity(new DryGrass(9, 8));
        this.addEntity(new DryGrass(9, 9));

        this.addEntity(new DryGrass(10, 4));
        this.addEntity(new DryGrass(10, 5));
        this.addEntity(new DryGrass(10, 7));
        this.addEntity(new DryGrass(10, 8));
    }
}
