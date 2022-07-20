package Models.Entities.Obstacle.Wall;

import Global.Constants;
import Global.Position;
import Models.Entity;

public class BottomLeft extends Entity {

    public BottomLeft() {
        super(new Position(Constants.RES-1, 0), Constants.WALL_BOTTOM_LEFT_PNG_PATH, false, false);
    }

    public BottomLeft(int row, int column) {
        super(new Position(row, column), Constants.WALL_BOTTOM_LEFT_PNG_PATH, false, false);
    }
}
