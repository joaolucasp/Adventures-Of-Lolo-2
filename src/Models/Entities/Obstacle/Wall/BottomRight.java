package Models.Entities.Obstacle.Wall;

import Global.Constants;
import Global.Position;
import Models.Entity;

public class BottomRight extends Entity {

    public BottomRight() {
        super(new Position(Constants.RES-1, Constants.RES-2), Constants.WALL_BOTTOM_RIGHT_PNG_PATH, false, false);
    }

    public BottomRight(int row, int column) {
        super(new Position(row, column), Constants.WALL_BOTTOM_RIGHT_PNG_PATH, false, false);
    }
}
