package Models.Entities.Enemy;

import Global.Constants;
import Global.Position;
import Models.Stalker;

import java.util.Random;

public class GreenMonster extends Stalker implements Enemy {

    public GreenMonster(int row, int column) {
        super(new Position(row, column), Constants.GREEN_MONSTER_PNG_PATH, false, true);
    }

    @Override
    protected boolean shouldMove() {
        return (new Random().nextInt(0, 99) >= 95);
    }

    @Override
    protected void onCollided() {
        this.setInert(true);
    }

    @Override
    public void setInert(boolean inert) {
        this.setImage(Constants.GREEN_MONSTER_SLEEPING_PNG_PATH);
        super.setInert(inert);
    }
}
