package Models.Entities.Scenery;

import Global.Constants;
import Global.Position;
import Models.Entity;

public class Water extends Entity {
    public Water(int row, int column) {
        super(new Position(row, column), Constants.WATER_PNG_PATH, false, false);
    }
}
