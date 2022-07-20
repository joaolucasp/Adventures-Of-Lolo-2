/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Models.Entities;

import Global.Constants;
import Global.Draw;
import Global.Position;
import Models.*;
import Models.Character;
import Models.Entities.Obstacle.Tree;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Lolo extends Character implements
        Serializable, Stalkable, Pushes, Aimable, Shooter {

    public Lolo(int row, int column) {
        super(new Position(row, column), Constants.LOLO_PNG_PATH, true, false);
    }

    @Override
    public void onChangePosition(Position position) {
        stalkers.forEach(stalker -> stalker.onNewPosition(this.getPosition()));
    }

    @Override
    public void onPusherPosition(Position position) {
        pushableList.forEach(pushable -> pushable.onTryToBePush(this.getPosition()));
    }

    @Override
    public void addPushable(Pushable pushable) {
        pushableList.add(pushable);
    }

    public void addAllPushable(ArrayList<Pushable> pushable) {
        pushableList.addAll(pushable);
    }

    @Override
    public void removePushable(Pushable pushable) {
        pushableList.remove(pushable);
    }

    @Override
    public void addStalker(Stalker stalker) {
        stalkers.add(stalker);
    }

    @Override
    public void onAimableMove(Position aimable) {
        aimsList.forEach(aims -> aims.onTargetMove(this));
    }

    @Override
    public void addAim(Aims aim) {
        aimsList.add(aim);
    }

    @Override
    public void selfDraw() {
        onChangePosition(this.getPosition());
        onPusherPosition(this.getPosition());
        onAimableMove(this.getPosition());

        super.selfDraw();
    }

    @Override
    public void shot(Direction direction) {
        Egg egg = new Egg(this.getPosition().getRow(), this.getPosition().getColumn(), direction);
        Draw.getScenery().addEntity(egg);
        this.addPushable(egg);
    }

    @Override
    public void setInert(boolean inert) {
        super.setInert(inert);
    }

    public void kill() {
        this.setInert(true);
        setImage(Constants.DEAD_LOLO_PNG_PATH);
        Draw.getScenery().onHitDetected();
    }
}
