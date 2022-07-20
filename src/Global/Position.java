package Global;

import Models.Direction;

import java.io.Serializable;

public final class Position implements Serializable {
    private int	row;
    private int column;
    private Direction lastMove;
    private int previousRow;
    private int previousColumn;

    public Position(int row, int column){
        this.setPosition(row, column);
    }

    public boolean setPosition(int row, int column) {
        if(row < 0 || row >= Global.Constants.RES)
            return false;
        this.row = row;
        
        if(column < 0 || column >= Global.Constants.RES)
            return false;
        this.column = column;

        return true;
    }
    
    public int getRow(){
        return row;
    }
   
    public int getColumn(){
        return column;
    }

    public int getPreviousRow(){
        return previousRow;
    }

    public int getPreviousColumn() {
        return previousColumn;
    }
    public boolean goToPreviousPosition(){
        return this.setPosition(previousRow, previousColumn);
    }

    public boolean isEqual(Position position){
        return (row == position.getRow() && column == position.getColumn());
    }

    public boolean copy(Position position){
        return this.setPosition(position.getRow(),position.getColumn());
    }

    public Position getNextPosition(Direction direction) {
        switch (direction) {
            case TOP -> {
                return this.getUpPosition();
            }
            case DOWN -> {
                return this.getDownPosition();
            }
            case LEFT -> {
                return this.getLeftPosition();
            }
            case RIGHT -> {
                return this.getRightPosition();
            }
            default -> {
                return this;
            }
        }
    }

    public Position getUpPosition() {
        return new Position(this.getRow()-1, this.getColumn());
    }

    public Position getDownPosition() {
        return new Position(this.getRow()+1, this.getColumn());
    }

    public Position getRightPosition() {
        return new Position(this.getRow(), this.getColumn()+1);
    }

    public Position getLeftPosition() {
        return new Position(this.getRow(), this.getColumn()-1);
    }

    public boolean moveUp() {
        setPreviousPosition();
        setLastMove(Direction.TOP);
        Position newPosition = getUpPosition();

        if (Draw.getScenery() != null && !Draw.getScenery().moveManager.isPositionValid(newPosition)) return false;

        return this.setPosition(newPosition.getRow(), newPosition.getColumn());
    }
    
    public boolean moveDown() {
        setLastMove(Direction.DOWN);
        setPreviousPosition();
        Position newPosition = getDownPosition();

        if (Draw.getScenery() != null && !Draw.getScenery().moveManager.isPositionValid(newPosition)) return false;

        return this.setPosition(newPosition.getRow(), newPosition.getColumn());
    }
    
    public boolean moveRight() {
        setLastMove(Direction.RIGHT);
        setPreviousPosition();
        Position newPosition = getRightPosition();

        if (Draw.getScenery() != null && !Draw.getScenery().moveManager.isPositionValid(newPosition)) return false;

        return this.setPosition(newPosition.getRow(), newPosition.getColumn());
    }
    
    public boolean moveLeft() {
        setLastMove(Direction.LEFT);
        setPreviousPosition();
        Position newPosition = getLeftPosition();

        if (Draw.getScenery() != null && !Draw.getScenery().moveManager.isPositionValid(newPosition)) return false;

        return this.setPosition(newPosition.getRow(), newPosition.getColumn());
    }

    public Direction getLastMove() {
        return lastMove == null ? Direction.NONE : lastMove;
    }

    public void setLastMove(Direction lastMove) {
        this.lastMove = lastMove;
    }

    private void setPreviousPosition() {
        this.previousColumn = this.column;
        this.previousRow = this.row;
    }

    public Position getPreviousPosition() {
        return new Position(this.getPreviousRow(), this.getPreviousColumn());
    }

    public boolean move(Direction direction) {
        switch (direction) {
            case TOP -> {
                return this.moveUp();
            }
            case DOWN -> {
                return this.moveDown();
            }
            case LEFT -> {
                return this.moveLeft();
            }
            case RIGHT -> {
                return this.moveRight();
            }
            default -> {
                return false;
            }
        }
    }
}
