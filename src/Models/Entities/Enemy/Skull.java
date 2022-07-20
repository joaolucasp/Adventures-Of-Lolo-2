package Models.Entities.Enemy;

import Global.Constants;
import Global.Draw;
import Global.Position;
import Models.*;
import Models.Character;

import java.util.Optional;
import java.util.Scanner;

public class Skull extends Stalker implements Enemy, Trappable {

    private boolean trapped = false;
    private Entity trapper;

    public Skull(int row, int column) {
        super(new Position(row, column), Constants.SKULL_PNG_PATH, false, true);
        this.setInert(true);
    }

    @Override
    public void setInert(boolean inert) {
        if (!inert) {
            this.setImage(Constants.PISSED_OFF_SKULL_PNG_PATH);
        }
        super.setInert(inert);
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
