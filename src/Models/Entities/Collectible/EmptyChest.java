package Models.Entities.Collectible;

import Global.Constants;
import Global.Position;
import Models.Entity;

public class EmptyChest extends Entity {
    public EmptyChest(int row, int column) {
        super(new Position(row, column), Constants.EMPTY_CHEST_PNG_PATH, true, false);
    }
}
