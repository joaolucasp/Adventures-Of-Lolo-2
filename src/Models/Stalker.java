package Models;

import Global.Draw;
import Global.Position;
import Models.Entities.Enemy.RockMonster;

import java.util.Random;

public abstract class Stalker extends Character {

    protected Stalker(Position position, String imageSrc, boolean passable, boolean mortal) {
        super(position, imageSrc, passable, mortal);
    }

    protected boolean shouldMove() {
        return (new Random().nextInt(0, 99) >= 80);
    }

    protected void onCollided() {
        Draw.getScenery().onHitDetected();
    }

    public void onNewPosition(Position stalkablePosition) {
        if (shouldMove()) {
            int stalkableColumn = stalkablePosition.getColumn();
            int stalkerColumn = this.getPosition().getColumn();
            int stalkerRow = this.getPosition().getRow();
            int stalkableRow = stalkablePosition.getRow();

            if(stalkablePosition.isEqual(this.getPosition()) && !this.isDead()) {
                this.onCollided();
            }

            if (stalkableColumn > stalkerColumn) {
                this.move(Direction.RIGHT);
            }
            if (stalkableColumn < stalkerColumn) {
                this.move(Direction.LEFT);
            }
            if (stalkableRow > stalkerRow) {
                this.move(Direction.DOWN);
            }
            if (stalkableRow < stalkerRow) {
                this.move(Direction.TOP);
            }
        }
    }
}
