package Models.Entities.Collectible;

import Global.Constants;
import Global.Position;
import Models.Entity;

public class OpenChest extends Entity {
    public OpenChest(int row, int column) {
        super(new Position(row, column), Constants.OPEN_CHEST_PNG_PATH, true, true);
    }
}
