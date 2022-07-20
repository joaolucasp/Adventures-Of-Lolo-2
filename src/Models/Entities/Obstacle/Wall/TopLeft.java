package Models.Entities.Obstacle.Wall;

import Global.Constants;
import Global.Position;
import Models.Entity;

public class TopLeft extends Entity {

    public TopLeft() {
        super(new Position(0, 0), Constants.WALL_TOP_LEFT_PNG_PATH, false, false);
    }

    public TopLeft(int row, int column) {
        super(new Position(row, column), Constants.WALL_TOP_LEFT_PNG_PATH, false, false);
    }
}
