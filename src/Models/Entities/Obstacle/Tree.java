package Models.Entities.Obstacle;

import Global.Constants;
import Global.Position;
import Models.Entity;

import java.io.Serializable;

public class Tree extends Entity implements Serializable {
    public Tree(int row, int column) {
        super(new Position(row, column), Constants.TREE_PNG_PATH, false, false);
    }
}
