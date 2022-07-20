package Models.Entities.Enemy;
import Global.Constants;
import Global.Position;
import Models.*;
import Models.Character;

import java.util.Optional;

public class Snake extends Entity implements Trappable, Enemy {

    private boolean trapped = false;
    private Entity trapper;

    public Snake(int row, int column) {
        super(new Position(row, column), Constants.SNAKE_PNG_PATH, false, false);
    }

    @Override
    public boolean isTrapped() {
        return trapped;
    }

    @Override
    public void setIsTrapped(boolean isTrapped) {
        trapped = isTrapped;
    }

    @Override
    public Optional<Entity> getTrapper() {
        return Optional.ofNullable(this.trapper);
    }

    @Override
    public void setTrapper(Entity entity) {
        this.trapper = entity;
    }
}
