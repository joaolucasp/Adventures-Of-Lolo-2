package Models;
import Global.Position;

public abstract class Movable extends Entity  {

    protected boolean inert = false;

    protected Movable(Position position, String imageSrc, boolean passable) {
        super(position, imageSrc, passable, false);
    }

    public boolean move(Direction direction) {
        if (this.isInert()) return false;

        return this.getPosition().move(direction);
    }

    public boolean moveUp() {
        return this.position.moveUp();
    }

    public boolean moveDown() {
        return this.position.moveDown();
    }

    public boolean moveRight() {
        return this.position.moveRight();
    }

    public boolean moveLeft() {
        return this.position.moveLeft();
    }

    public boolean isInert() {
        return inert;
    }

    @Override
    public void setDead(boolean status) {
        super.setDead(status);
        this.setInert(true);
    }

    public boolean hasMoved() {
        return !this.getPosition().isEqual(this.getPosition().getPreviousPosition());
    }

    public void setInert(boolean inert) {
        this.inert = inert;
    }
}
