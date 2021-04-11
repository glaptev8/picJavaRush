package edu.school21.app;

public enum MapObject {

    PLAYER("P"),

    SHADOWER("S"),

    GOAL("G"),

    WALL("X"),

    FREE("0"),
    ;

    private String symbol;

    MapObject(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
