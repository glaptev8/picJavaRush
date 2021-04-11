package edu.school21.app;

public class Cell {

    private MapObject mapObject;

    private Move move = null;

    private int x;

    private int y;

    public Cell() {
        this.mapObject = MapObject.FREE;
    }

    public Cell(MapObject mapObject) {
        this.mapObject = mapObject;
    }

    public MapObject getMapObject() {
        return mapObject;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public void setCordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
