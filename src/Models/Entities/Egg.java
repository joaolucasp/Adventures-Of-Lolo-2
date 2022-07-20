package Models.Entities;

import Global.Constants;
import Global.Draw;
import Global.Position;
import Models.*;

import java.util.Optional;

public class Egg extends Shot implements Pushable {

    private Entity trapped;
    private static final short TIMER = 100;
    private short ticker;
    public Egg(int row, int column, Direction direction) {
        super(row, column, Constants.EGG_PNG_PATH, direction);
    }

    @Override
    public void selfDraw() {
        boolean move = this.move(direction);
        this.verifyCollision();
        this.incrementTimer();
        super.selfDraw();

        if (!move && this.getTrapped().isEmpty()) {
            this.terminate();
            Draw.getScenery().removeEntity(this);

        } else {
            if (this.getTrapped().isPresent()) {
                if (this.ticker == TIMER / 2) this.setImage(Constants.BROKEN_EGG_PNG_PATH);
                if (this.ticker == TIMER) {
                    this.getTrapped().ifPresent(o -> {
                        if (o instanceof Movable) {
                            ((Movable) o).setInert(false);
                        }
                        ((Trappable) o).setIsTrapped(false);
                    });
                    this.terminate();
                }
            }

        }
    }

    @Override
    protected void terminate() {
        super.terminate();
        Draw.getScenery().lolo.removePushable(this);
    }

    protected void verifyCollision() {
        if (this.getTrapped().isEmpty()) {
            Optional<Entity> trapped = Draw.getScenery().getMoveManager().collision(this);
            trapped.ifPresent(o -> {
                if (o instanceof Trappable) {
                    this.getPosition().setPosition(o.getPosition().getRow(), o.getPosition().getColumn());
                    super.selfDraw();

                    if (((Trappable) o).isTrapped()) {
                        ((Trappable) o).getTrapper().ifPresent(trapper -> Draw.getScenery().removeEntity(trapper));
                        Draw.getScenery().removeEntity(o);
                        Draw.getScenery().removeEntity(this);
                    }

                    this.setTrapped(o);
                }
            });
        }
    }

    @Override
    public void onTryToBePush(Position pusherPosition) {

        int pusherColumn = pusherPosition.getColumn();
        int pusherRow = pusherPosition.getRow();

        int row = this.getPosition().getRow();
        int column = this.getPosition().getColumn();

        boolean inRow = Math.abs(row - pusherRow) == 1 && column - pusherColumn == 0;
        boolean inColumn = Math.abs(column - pusherColumn) == 1 && row - pusherRow == 0;

        boolean pusherDidNotMove = pusherPosition.isEqual(pusherPosition.getPreviousPosition());
        boolean pusherTriedToPushThis = pusherPosition.getNextPosition(pusherPosition.getLastMove()).isEqual(this.position);

        if (pusherDidNotMove && pusherTriedToPushThis && (inRow || inColumn)) {
            if (this.move(pusherPosition.getLastMove())) {
                pusherPosition.setPosition(this.getPosition().getPreviousRow(), this.getPosition().getPreviousColumn());
                this.getTrapped().ifPresent(o -> o.getPosition().setPosition(this.position.getRow(), this.position.getColumn()));
            }
        }
    }

    public Optional<Entity> getTrapped() {
        return Optional.ofNullable(trapped);
    }

    public void setTrapped(Entity trapped) {
        this.trapped = trapped;
        if (trapped != null) {
            if (trapped instanceof Movable) ((Movable) trapped).setInert(true);
            ((Trappable) trapped).setIsTrapped(true);
            ((Trappable) trapped).setTrapper(this);
        }
        this.setDirection(Direction.NONE);
    }

    private void incrementTimer() {
        this.ticker++;
    }

}
