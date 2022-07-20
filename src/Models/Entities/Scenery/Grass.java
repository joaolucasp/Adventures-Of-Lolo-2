package Models.Entities.Scenery;

import Global.Constants;
import Global.Position;
import Models.Entity;

public class Grass extends Entity {
    public Grass(int row, int column) {
        super(new Position(row, column), Constants.GRASS_PNG_PATH, true, false);
    }
}
