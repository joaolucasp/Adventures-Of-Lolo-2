package Controller.Phases;

import Controller.LivesListener;
import Controller.PhaseCompleteListener;
import Global.Phase;
import Models.Entities.Collectible.EmptyChest;
import Models.Entities.Collectible.OpenChest;
import Models.Entities.Enemy.Skull;
import Models.Entities.Enemy.Snake;
import Models.Entities.Obstacle.Bush;
import Models.Entities.Obstacle.Chest;
import Models.Entities.Obstacle.Door;
import Models.Entities.Obstacle.Tree;
import Models.Entities.Collectible.Heart;
import Models.Entities.Lolo;
import java.util.ArrayList;

public class SkeletalLabyrinth extends Phase {

    public SkeletalLabyrinth(PhaseCompleteListener phaseCompleteListener, LivesListener livesListener) {
        super(phaseCompleteListener, livesListener);
    }
    @Override
    public void start() {
        super.startPhase();
        super.start();
    }

    @Override
    protected void createLolo() {
        this.lolo = new Lolo(11, 10);
        this.entities.add(lolo);
    }

    @Override
    protected void createEntities() {
        this.entities.add(new Tree(10, 9));
        this.entities.add(new Tree(10, 6));
        this.entities.add(new Tree(10, 7));
        this.entities.add(new Bush(10, 2));
        this.entities.add(new Tree(10, 4));

        this.entities.add(new Tree(11, 9));
        this.entities.add(new Bush(11, 8));
        this.entities.add(new Bush(11, 1));
        this.entities.add(new Tree(11, 2));
        this.entities.add(new Bush(11, 4));

        this.entities.add(new Bush(8, 3));
        this.entities.add(new Tree(8, 4));
        this.entities.add(new Bush(8, 5));
        this.entities.add(new Bush(8, 6));
        this.entities.add(new Tree(8, 7));
        this.entities.add(new Bush(8, 8));
        this.entities.add(new Tree(8, 9));

        this.entities.add(new Tree(7, 1));
        this.entities.add(new Tree(7, 10));

        this.entities.add(new Tree(6, 3));
        this.entities.add(new Bush(6, 4));
        this.entities.add(new Tree(6, 5));
        this.entities.add(new Tree(6, 7));

        this.entities.add(new Bush(5, 1));
        this.entities.add(new Tree(5, 2));
        this.entities.add(new Bush(5, 6));

        this.entities.add(new Bush(4, 2));
        this.entities.add(new Bush(4, 4));
        this.entities.add(new Bush(4, 9));

        this.entities.add(new Tree(3, 2));
        this.entities.add(new Tree(3, 7));
        this.entities.add(new Tree(3, 9));

        this.entities.add(new Tree(2, 4));
        this.entities.add(new Tree(2, 6));
        this.entities.add(new Bush(2, 9));

        this.entities.add(new Tree(1, 2));
        this.entities.add(new Tree(1, 4));
        this.entities.add(new Tree(1, 5));
        this.entities.add(new Tree(1, 6));
    }

    @Override
    protected void createHearts() {
        final ArrayList<Heart> hearts = new ArrayList<>();

        hearts.add(new Heart(1, 3));
        hearts.add(new Heart(1, 7));

        hearts.add(new Heart(6, 1));
        hearts.add(new Heart(6, 6));

        hearts.add(new Heart(8, 10));

        hearts.add(new Heart(9, 7));

        hearts.add(new Heart(10, 1));

        this.hearts = hearts.size();
        this.entities.addAll(hearts);
    }

    @Override
    protected void createChest() {
        this.entities.add(new EmptyChest(1, 1));
        this.entities.add(new OpenChest(1, 1));
        this.entities.add(new Chest(1, 1));
    }

    @Override
    protected void createDoor() {
        this.door = new Door(0, 7);
        this.entities.add(door);
    }

    @Override
    protected void createEnemies() {
        this.entities.add(new Snake(7, 5));
        Skull skullInSixthRow = new Skull(6, 8);
        this.lolo.addStalker(skullInSixthRow);
        this.entities.add(skullInSixthRow);
        Skull skullInThirdRow = new Skull(3, 5);
        this.lolo.addStalker(skullInThirdRow);
        this.entities.add(skullInThirdRow);
    }

    @Override
    protected void createScenery() {

    }

    @Override
    protected void onCollectedAllHearts() {
        super.onCollectedAllHearts();

        this.entities.forEach(o -> {
            if (o instanceof Skull) ((Skull) o).setInert(false);
        });
    }
}
