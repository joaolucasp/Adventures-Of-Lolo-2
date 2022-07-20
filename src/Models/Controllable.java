package Models;

import Global.Position;

import java.util.List;
import java.util.Random;

public abstract class Controllable extends Character {

    private List<Direction> directions;
    private int directionIndex;

    private static final int TICKER = 7;

    private int tickerCounter = 0;

    protected Controllable(Position position, String imageSrc, boolean passable, boolean mortal) {
        super(position, imageSrc, passable, mortal);
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public void setDirections(List<Direction> directions) {
        this.directions = directions;
    }

    private int getDirectionIndex() {
        return directionIndex;
    }

    private void setDirectionIndex() {
        if (directionIndex == directions.size()-1) {
            this.directionIndex = 0;
        } else this.directionIndex++;
    }

    private Direction getDirection() {
        return directions.get(directionIndex);
    }


    public boolean shouldMove() {
        if (tickerCounter >= TICKER) {
            tickerCounter = 0;
            return true;
        }

        return false;
    }

    @Override
    public void selfDraw() {
        if (directions != null && this.shouldMove()) {
            switch (getDirection()) {
                case TOP -> this.move(Direction.TOP);
                case DOWN -> this.move(Direction.DOWN);
                case LEFT -> this.move(Direction.LEFT);
                case RIGHT -> this.move(Direction.RIGHT);
                default -> {
                }
            }
            setDirectionIndex();
        }
        tickerCounter++;
        super.selfDraw();
    }
}
