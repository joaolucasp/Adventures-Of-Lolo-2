package Models;

import Global.Position;

import java.util.ArrayList;
import java.util.List;

public interface Stalkable {

    List<Stalker> stalkers = new ArrayList<>();

    public void onChangePosition(Position position);
    public void addStalker(Stalker stalker);
}
