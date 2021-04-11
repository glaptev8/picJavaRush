package edu.school21.app;

public class Cell {

    private final MapObject mapObject;

    private Move move;

    private int x;

    private int y;

    public Cell(MapObject mapObject) {
        this.mapObject = mapObject;
    }

    public int getX() {
        return x;
    }
    public void setNewX(int newX) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }
}
