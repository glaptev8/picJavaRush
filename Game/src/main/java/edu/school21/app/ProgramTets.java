package edu.school21.app;

public class ProgramTets {
    public static void main(String[] args) {

        Cell[][] cells = new Cell[9][9];
        int[][] arr = new int[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                cells[y][x] = new Cell();
                cells[y][x].setCordinates(x, y);
            }
        }
        cells[8][8] = new Cell(MapObject.SHADOWER);
        Cell shadower = cells[8][8];
        shadower.setMove(Move.DOWN);
        shadower.setCordinates(8, 8);

        while (shadower != null && !(shadower.getX() == 4 && shadower.getY() == 4)) {
            shadower = NestStep.INSTANSE.getNextStep(cells, 4, 4);
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
