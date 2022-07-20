package Models.Entities.Enemy;

import Global.Constants;
import Global.Draw;
import Global.Position;
import Models.*;

public class Statue extends Entity implements Aims, Shooter, Enemy {

    private static final short RANGE = 4;
    private boolean hasShot = false;

    public Statue(int row, int column) {
        super(new Position(row, column), Constants.STATUE_PNG_PATH, false, false);
    }
    @Override
    public void onTargetMove(Movable target) {
        if (!hasShot && !this.isDead()) {
            if (target.getPosition().getColumn() == this.getPosition().getColumn() &&
                    distanceInRange(this.getPosition().getRow() - target.getPosition().getRow())) {
                this.setImage(Constants.PISSED_OF_STATUE_PNG_PATH);
                if (target.getPosition().getRow() > this.getPosition().getRow()) {
                    if (!this.hasObstacle(target, Direction.DOWN)) {
                        this.shot(Direction.DOWN);
                        target.setInert(true);
                    }
                } else {
                    if (!this.hasObstacle(target, Direction.TOP)) {
                        this.shot(Direction.TOP);
                        target.setInert(true);
                    }
                }
                return;
            }

            if (target.getPosition().getRow() == this.getPosition().getRow() &&
                    distanceInRange(this.getPosition().getColumn() - target.getPosition().getColumn())) {
                this.setImage(Constants.PISSED_OF_STATUE_PNG_PATH);
                if (target.getPosition().getColumn() > this.getPosition().getColumn()) {
                    if (!this.hasObstacle(target, Direction.RIGHT)) {
                        this.shot(Direction.RIGHT);
                        target.setInert(true);
                    }
                } else {
                    if (!this.hasObstacle(target, Direction.LEFT)) {
                        this.shot(Direction.LEFT);
                        target.setInert(true);
                    }
                }
            }
        }
        this.setImage(Constants.STATUE_PNG_PATH);
    }

    @Override
    public void shot(Direction direction) {
        hasShot = true;
        Draw.getScenery().addEntity(new StatueShot(this.getPosition().getRow(), this.getPosition().getColumn(), direction));
    }

    private boolean hasObstacle(Movable target, Direction direction) {

        switch (direction) {
            case TOP -> {
                return Draw.getScenery().getEntitiesInColumn(this.getPosition().getColumn()).stream().anyMatch(entity ->
                        entity instanceof Pushable && entity.getPosition().getRow() < this.getPosition().getRow()
                                && entity.getPosition().getRow() > target.getPosition().getRow());
            }
            case DOWN -> {
                return Draw.getScenery().getEntitiesInColumn(this.getPosition().getColumn()).stream().anyMatch(entity ->
                        entity instanceof Pushable && entity.getPosition().getRow() > this.getPosition().getRow()
                                && entity.getPosition().getRow() < target.getPosition().getRow());
            }
            case RIGHT -> {
                return Draw.getScenery().getEntitiesInRow(this.getPosition().getRow()).stream().anyMatch(entity ->
                        entity instanceof Pushable && entity.getPosition().getColumn() > this.getPosition().getColumn()
                                && entity.getPosition().getColumn() < target.getPosition().getColumn());
            }
            case LEFT -> {
                return Draw.getScenery().getEntitiesInRow(this.getPosition().getRow()).stream().anyMatch(entity ->
                        entity instanceof Pushable && entity.getPosition().getColumn() < this.getPosition().getColumn()
                        && entity.getPosition().getColumn() > target.getPosition().getColumn());
            }
            default -> {
                return true;
            }
        }
    }

    private boolean distanceInRange(int distance) {
        return Math.abs(distance) <= RANGE;
    }
}
