package Controller.Phases;

import Controller.LivesListener;
import Controller.PhaseCompleteListener;
import Global.Phase;
import Models.Entities.Collectible.EmptyChest;
import Models.Entities.Collectible.Heart;
import Models.Entities.Collectible.OpenChest;
import Models.Entities.Enemy.GreenMonster;
import Models.Entities.Lolo;
import Models.Entities.Obstacle.Chest;
import Models.Entities.Obstacle.Door;
import Models.Entities.Obstacle.Tree;
import Models.Entities.Scenery.DryGrass;
import Models.Entities.Scenery.Grass;

import java.util.ArrayList;

public class GreenSwarm extends Phase {
    public GreenSwarm(PhaseCompleteListener phaseCompleteListener, LivesListener livesListener) {
        super(phaseCompleteListener, livesListener);
    }

    public void start() {
        super.startPhase();
        super.start();
    }
    
    @Override
    protected void createLolo() {
        this.lolo = new Lolo(11, 10);
        this.addEntity(lolo);
    }

    @Override
    protected void createEntities() {
        this.addEntity(new Tree(1, 10));

        this.addEntity(new Tree(9, 8));

        this.addEntity(new Tree(11, 1));
    }

    @Override
    protected void createHearts() {
        final ArrayList<Heart> hearts = new ArrayList<>();

        hearts.add(new Heart(6, 6));
        hearts.add(new Heart(6, 10));

        hearts.add(new Heart(11, 6));

        this.hearts = hearts.size();
        this.entities.addAll(hearts);
    }

    @Override
    protected void createChest() {
        this.addEntity(new EmptyChest(1, 1));
        this.addEntity(new OpenChest(1, 1));
        this.addEntity(new Chest(1, 1));
    }

    @Override
    protected void createDoor() {
        this.door = new Door(0, 3);
    }

    @Override
    protected void createEnemies() {
        GreenMonster greenMonster = new GreenMonster(2, 8);
        this.addEntity(greenMonster);
        this.lolo.addStalker(greenMonster);

        greenMonster = new GreenMonster(4, 3);
        this.addEntity(greenMonster);
        this.lolo.addStalker(greenMonster);

        greenMonster = new GreenMonster(5, 7);
        this.addEntity(greenMonster);
        this.lolo.addStalker(greenMonster);

        greenMonster = new GreenMonster(5, 9);
        this.addEntity(greenMonster);
        this.lolo.addStalker(greenMonster);

        greenMonster = new GreenMonster(7, 5);
        this.addEntity(greenMonster);
        this.lolo.addStalker(greenMonster);

        greenMonster = new GreenMonster(8, 2);
        this.addEntity(greenMonster);
        this.lolo.addStalker(greenMonster);

        greenMonster = new GreenMonster(10, 5);
        this.addEntity(greenMonster);
        this.lolo.addStalker(greenMonster);
    }

    @Override
    protected void createScenery() {
        this.addEntity(new Grass(1, 1));
        this.addEntity(new Grass(1, 2));
        this.addEntity(new Grass(1, 3));
        this.addEntity(new Grass(1, 4));
        this.addEntity(new Grass(1, 5));
        this.addEntity(new Grass(1, 6));
        this.addEntity(new Grass(1, 7));
        this.addEntity(new Grass(1, 8));
        this.addEntity(new Grass(1, 9));

        this.addEntity(new Grass(2, 1));
        this.addEntity(new Grass(2, 2));
        this.addEntity(new Grass(2, 3));
        this.addEntity(new Grass(2, 4));
        this.addEntity(new DryGrass(2, 10));
        this.addEntity(new Grass(2, 5));

        this.addEntity(new Grass(3, 1));
        this.addEntity(new Grass(3, 2));
        this.addEntity(new Grass(3, 3));
        this.addEntity(new DryGrass(3, 10));

        this.addEntity(new DryGrass(4, 10));
        this.addEntity(new Grass(4, 1));
        this.addEntity(new Grass(4, 2));

        this.addEntity(new Grass(5, 1));
        this.addEntity(new Grass(5, 2));
        this.addEntity(new DryGrass(5, 10));

        this.addEntity(new Grass(6, 1));
        this.addEntity(new DryGrass(6, 7));
        this.addEntity(new DryGrass(6, 8));
        this.addEntity(new DryGrass(6, 9));

        this.addEntity(new Grass(7, 1));
        this.addEntity(new DryGrass(7, 6));
        this.addEntity(new DryGrass(7, 10));

        this.addEntity(new DryGrass(8, 6));
        this.addEntity(new DryGrass(8, 10));
        this.addEntity(new Grass(8, 1));

        this.addEntity(new Grass(9, 1));
        this.addEntity(new DryGrass(9, 6));
        this.addEntity(new DryGrass(9, 10));

        this.addEntity(new Grass(10, 1));
        this.addEntity(new DryGrass(10, 6));
        this.addEntity(new DryGrass(10, 10));

        this.addEntity(new DryGrass(11, 2));
        this.addEntity(new DryGrass(11, 3));
        this.addEntity(new DryGrass(11, 4));
        this.addEntity(new DryGrass(11, 5));
        this.addEntity(new DryGrass(11, 7));
        this.addEntity(new DryGrass(11, 8));
        this.addEntity(new DryGrass(11, 9));
    }
}
