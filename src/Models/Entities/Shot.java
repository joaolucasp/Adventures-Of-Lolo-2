package Models.Entities;
import Global.Draw;
import Global.Position;
import Models.Direction;
import Models.Movable;

public class Shot extends Movable {

    public Direction direction;

    public Shot(int row, int column, String imageSrc, Direction direction) {
        super(new Position(row, column), imageSrc, false);

        setDirection(direction);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    protected void terminate() {
        Draw.getScenery().removeEntity(this);
    }
}
