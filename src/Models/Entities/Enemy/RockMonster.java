package Models.Entities.Enemy;

import Global.Constants;
import Global.Draw;
import Global.Position;
import Models.Controllable;
import Models.Stalker;

import java.util.Random;

public class RockMonster extends Controllable implements Enemy {
    public RockMonster(int row, int column) {
        super(new Position(row, column), Constants.MONSTER_ROCK_PNG_PATH, false, false);
    }
}
