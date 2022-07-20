package Models.Entities.Scenery;

import Global.Constants;
import Global.Position;
import Models.Entity;

public class DryGrass extends Entity {
    public DryGrass(int row, int column) {
        super(new Position(row, column), Constants.DRY_GRASS_PNG_PATH, true, false);
    }
}
