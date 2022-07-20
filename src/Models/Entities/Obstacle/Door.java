package Models.Entities.Obstacle;

import Global.Constants;
import Global.Position;
import Models.Entity;

public class Door extends Entity {
    public Door(int row, int column) {
        super(new Position(row, column), Constants.DOOR_PNG_PATH, true, false);
    }
}

