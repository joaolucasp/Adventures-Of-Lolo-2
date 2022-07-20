package Models;

import Global.Constants;
import Global.Draw;
import Models.Entities.Shot;

public class StatueShot extends Shot {

    public StatueShot(int row, int column, Direction direction) {
        super(row, column, (direction == Direction.DOWN || direction == Direction.TOP) ?
                        Constants.STATUE_SHOT_VERTICAL_PNG_PATH : Constants.STATUE_SHOT_HORIZONTAL_PNG_PATH,
                direction);
    }

    @Override
    public void selfDraw() {
        if (!this.move(direction)) terminate();
        super.selfDraw();
    }

    @Override
    protected void terminate() {
        super.terminate();
        Draw.getScenery().reset();
    }
}
