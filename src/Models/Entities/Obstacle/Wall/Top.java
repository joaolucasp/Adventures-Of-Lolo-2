package Models.Entities.Obstacle.Wall;

import Global.Constants;
import Global.Position;
import Models.Entity;

public class Top extends Entity {
    public Top(int row, int column) {
        super(new Position(row, column), Constants.WALL_TOP_PNG_PATH, false, false);
    }
}
