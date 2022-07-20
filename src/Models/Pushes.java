package Models;

import Global.Position;

import java.util.ArrayList;
import java.util.List;

public interface Pushes {
    List<Pushable> pushableList = new ArrayList<>();

    public void onPusherPosition(Position position);
    public void addPushable(Pushable pushable);
    public void addAllPushable(ArrayList<Pushable> pushable);
    public void removePushable(Pushable pushable);

}
