package Models.Entities.Enemy;

import Global.Constants;
import Global.Draw;
import Global.Position;
import Models.*;

import java.util.Random;

public class Armadillo extends Stalker implements Enemy {
    public Armadillo(int row, int column) {
        super(new Position(row, column), Constants.ARMADILLO_PNG_PATH, false, false);
    }

    @Override
    protected boolean shouldMove() {
        return (new Random().nextInt(0, 99) >= 90);
    }
}
