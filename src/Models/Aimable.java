package Models;

import Global.Position;

import java.util.ArrayList;
import java.util.List;

public interface Aimable {
    List<Aims> aimsList = new ArrayList<>();

    public void onAimableMove(Position aimable);
    public void addAim(Aims aim);
}
