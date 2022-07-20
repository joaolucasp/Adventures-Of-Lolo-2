package Models.Entities.Obstacle.Wall;

import Global.Constants;
import Global.Position;
import Models.Entity;

public class TopRight extends Entity {

    public TopRight() {
        super(new Position(0, Constants.RES-2), Constants.WALL_TOP_RIGHT_PNG_PATH, false, false);
    }

    public TopRight(int row, int column) {
        super(new Position(row, column), Constants.WALL_TOP_RIGHT_PNG_PATH, false, false);
    }
}
