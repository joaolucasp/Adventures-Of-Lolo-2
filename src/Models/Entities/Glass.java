package Models.Entities;

import Global.Constants;
import Global.Position;
import Models.Movable;
import Models.Pushable;

public class Glass extends Movable implements Pushable {
    public Glass(int row, int column) {
        super(new Position(row, column), Constants.GLASS_PNG_PATH, false);
    }

    @Override
    public void onTryToBePush(Position pusherPosition) {
        int pusherColumn = pusherPosition.getColumn();
        int pusherRow = pusherPosition.getRow();

        int row = this.getPosition().getRow();
        int column = this.getPosition().getColumn();

        boolean inRow = Math.abs(row - pusherRow) == 1 && column - pusherColumn == 0;
        boolean inColumn = Math.abs(column - pusherColumn) == 1 && row - pusherRow == 0;

        boolean pusherDidNotMove = pusherPosition.isEqual(pusherPosition.getPreviousPosition());
        boolean pusherTriedToPushThis = pusherPosition.getNextPosition(pusherPosition.getLastMove()).isEqual(this.position);

        if (pusherDidNotMove && pusherTriedToPushThis && (inRow || inColumn)) {
            if (this.move(pusherPosition.getLastMove()))
                pusherPosition.setPosition(this.getPosition().getPreviousRow(), this.getPosition().getPreviousColumn());
        }
    }
}
