package Models.Entities.Obstacle;

import Global.Constants;
import Global.Position;
import Models.Entity;

public class Chest extends Entity {
    public Chest(int row, int column) {
        super(new Position(row, column), Constants.CHEST_PNG_PATH, true, false);
    }
}
