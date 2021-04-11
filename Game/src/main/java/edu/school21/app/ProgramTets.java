package edu.school21.app;


import com.beust.jcommander.JCommander;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ProgramTets {

    static Settings settings = new Settings();


    public static void main(String[] args) {

        new JCommander(settings, args);

        if (settings.getEnemiesCount() + settings.getWallsCount() >= settings.getSize() - 3) {
            throw new IllegalParametersException("not valid arguments");
        }

        Cell[][] cells1 = generateMap();

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

    static public Cell[][] generateMap() {
        Cell[][] cells = new Cell[settings.getSize()][settings.getSize()];
        List<MapObject> list = new ArrayList<>();
        for (int i = 0; i < settings.getSize() * settings.getSize() - settings.getEnemiesCount() - settings.getWallsCount() - 2; i++) {
            list.add(MapObject.FREE);
        }
        for (int i = 0; i < settings.getWallsCount(); i++) {
            list.add(MapObject.WALL);
        }
        for (int i = 0; i < settings.getEnemiesCount(); i++) {
            list.add(MapObject.SHADOWER);
        }
        list.add(MapObject.GOAL);
        list.add(MapObject.PLAYER);

        for (int y = 0; y < settings.getSize(); y++) {
            for (int x = 0; x < settings.getSize(); x++) {
                cells[y][x] = new Cell();
                MapObject person = getPerson(list);
                while (!canStandPerson(cells, person, x, y)) {
                    person = getPerson(list);
                }
                cells[y][x].setMapObject(person);
                cells[y][x].setCordinates(x, y);
            }
        }
        return cells;
    }

    static public MapObject getPerson (List<MapObject> list) {
        int random = ThreadLocalRandom.current().nextInt(0, list.size());
        MapObject mapObject = list.get(random);
        list.remove(random);
        return mapObject;
    }

    static public boolean canStandPerson(Cell[][] cell, MapObject mapObject, int x, int y) {
        if (mapObject.equals(MapObject.FREE)) {
            return true;
        }
        if (mapObject.equals(MapObject.PLAYER)) {
            if (x == 0 && y == 0 && cell[x][y + 1] != null && cell[x][y + 1].getMapObject().equals(MapObject.WALL) && cell[x + 1][y] != null && cell[x + 1][y].getMapObject().equals(MapObject.WALL)) {
                return false;
            }
            if (x == 0 && y == settings.getSize() - 1 && cell[x][y - 1] != null && cell[x][y - 1].getMapObject().equals(MapObject.WALL) && cell[x + 1][y] != null && cell[x + 1][y].getMapObject().equals(MapObject.WALL)) {
                return false;
            }
            if (y == 0 && x == settings.getSize() - 1 && cell[x - 1][y] != null && cell[x - 1][y].getMapObject().equals(MapObject.WALL) && cell[x][y + 1] != null && cell[x][y + 1].getMapObject().equals(MapObject.WALL)) {
                return false;
            }
            if (y == settings.getSize() - 1 && x == settings.getSize() - 1 && cell[x - 1][y] != null && cell[x - 1][y].getMapObject().equals(MapObject.WALL) && cell[x][y - 1] != null && cell[x][y - 1].getMapObject().equals(MapObject.WALL)) {
                return false;
            }
            return cell[x + 1][y] == null || !cell[x + 1][y].getMapObject().equals(MapObject.WALL) ||
              cell[x - 1][y] == null || !cell[x - 1][y].getMapObject().equals(MapObject.WALL) ||
              cell[x][y + 1] == null || !cell[x][y + 1].getMapObject().equals(MapObject.WALL) ||
              cell[x][y - 1] == null || !cell[x][y - 1].getMapObject().equals(MapObject.WALL);
        }
        return true;
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
