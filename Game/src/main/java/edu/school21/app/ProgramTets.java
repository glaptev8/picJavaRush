package edu.school21.app;

public class ProgramTets {
    private static final int SIZE = 9;

    private static final int SHADOWER_X = 0;
    private static final int SHADOWER_Y = 0;

    private static final int GOAL_X = 4;
    private static final int GOAL_Y = 4;

    private static final int PLAYER_X = 8;
    private static final int PLAYER_Y = 8;


    public static void main(String[] args) {

        Cell[][] cells = new Cell[SIZE][SIZE];
        int[][] arr = new int[SIZE][SIZE];
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                cells[y][x] = new Cell();
                cells[y][x].setCordinates(x, y);
            }
        }
        cells[SHADOWER_Y][SHADOWER_X] = new Cell(MapObject.SHADOWER);
        cells[SHADOWER_Y][SHADOWER_X].setCordinates(SHADOWER_X,SHADOWER_Y);

        cells[GOAL_Y][GOAL_X] = new Cell(MapObject.GOAL);
        cells[GOAL_Y][GOAL_X].setCordinates(GOAL_X,GOAL_Y);

        cells[PLAYER_Y][PLAYER_X] = new Cell(MapObject.PLAYER);
        cells[PLAYER_Y][PLAYER_X].setCordinates(PLAYER_X,PLAYER_Y);

        Cell shadower = cells[SHADOWER_Y][SHADOWER_X];

        while (shadower != null && !(shadower.getX() == GOAL_X && shadower.getY() == GOAL_Y)) {
            shadower = NestStep.INSTANSE.getNextStep(cells);
            if (shadower != null && shadower.getMove() != null) {
                swapCell(cells, shadower);
            }
            for (Cell[] tmp : cells) {
                for (Cell tmp2 : tmp) {
                    System.out.print(tmp2.getMapObject().getSymbol() + " ");
                }
                System.out.println();
            }
        System.out.println();
        }
    }

    public static void swapCell(Cell[][] map, Cell shadower) {
        if (shadower.getMove() == Move.UP) {
            swap(map, shadower.getX(), shadower.getY(), shadower.getX(), shadower.getY() - 1);
            shadower.setMove(null);
        } else if (shadower.getMove() == Move.LEFT) {
            swap(map, shadower.getX(), shadower.getY(), shadower.getX() - 1, shadower.getY());
            shadower.setMove(null);
        } else if (shadower.getMove() == Move.RIGHT) {
            swap(map, shadower.getX(), shadower.getY(), shadower.getX() + 1, shadower.getY());
            shadower.setMove(null);
        } else if (shadower.getMove() == Move.DOWN) {
            swap(map, shadower.getX(), shadower.getY(), shadower.getX(), shadower.getY() + 1);
            shadower.setMove(null);
        }
    }

    public static void swap(Cell[][] map, int scrX, int scrY, int desX, int destY) {
        Cell tmp = map[scrY][scrX];
        map[scrY][scrX] = map[destY][desX];
        map[destY][desX] = tmp;
        map[scrY][scrX].setCordinates(scrX, scrY);
        map[destY][desX].setCordinates(desX, destY);
    }
}
