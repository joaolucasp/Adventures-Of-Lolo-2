package Models.Entities.Obstacle.Wall;

import Global.Constants;
import Global.Position;
import Models.Entity;

public class Bottom extends Entity {
    public Bottom(int row, int column) {
        super(new Position(row, column), Constants.WALL_BOTTOM_PNG_PATH, false, false);
    }
}
