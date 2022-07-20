package Models.Entities.Obstacle.Wall;

import Global.Constants;
import Global.Position;
import Models.Entity;

public class Side extends Entity {
    public Side(int row, int column) {
        super(new Position(row, column), Constants.WALL_SIDE_PNG_PATH, false, false);
    }
}
