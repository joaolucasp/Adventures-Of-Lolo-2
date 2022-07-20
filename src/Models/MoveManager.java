package Models;

import Global.Position;
import Models.Entities.Enemy.Skull;
import Models.Entities.Glass;
import Models.Entities.Shot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MoveManager {

    private List<Entity> entities;

    public MoveManager(ArrayList<Entity> entities) {
        setEntities(entities);
    }

    public boolean isPositionValid(Position position) {
        for (Entity entity : entities) {
            if (!entity.isPassable() && entity.getPosition().isEqual(position)) {
                return false;
            }
        }
        return true;
    }

    public Optional<Entity> collision(Movable movable) {
        for (Entity entity : entities) {
            if (entity.getPosition().isEqual(movable.getPosition().getNextPosition(movable.getPosition().getLastMove())) && !movable.equals(entity)) {
                return Optional.of(entity);
            }
        }

        return Optional.empty();
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
}
