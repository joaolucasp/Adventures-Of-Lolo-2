package Models.Entities.Obstacle;

import Global.Constants;
import Global.Position;
import Models.Entity;

public class Bush extends Entity {
    public Bush(int row, int column) {
        super(new Position(row, column), Constants.BUSH_PNG_PATH, false, false);
    }
}
