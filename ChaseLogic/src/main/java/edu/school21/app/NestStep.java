package edu.school21.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum NestStep {

    INSTANSE;

    public Cell getNextStep(Cell[][] map) {
        int[][] arr = new int[map.length][map[0].length];
        List<Cell> goalList = new ArrayList<>();
        List<Cell> shadowerList = new ArrayList<>();

        Arrays.stream(map)
                .flatMap(x -> Arrays.stream(x))
                .forEach(x -> {
                    if (x.getMapObject() == MapObject.WALL) {
                        arr[x.getY()][x.getX()] = Integer.MAX_VALUE;
                    } else if (x.getMapObject() == MapObject.SHADOWER) {
                        shadowerList.add(x);
                    } else if (x.getMapObject() == MapObject.PLAYER || x.getMapObject() == MapObject.GOAL) {
                        goalList.add(x);
                    }
                });

        int[][] arrayWrap = new ThermCard(arr, goalList).getThermCard();

        Cell needMove = shadowerList.stream()
                .min((x, y) -> getMin(x.getX(), x.getY(), arrayWrap) < getMin(y.getX(), y.getY(), arrayWrap) ? 1 : 0)
                .orElse(null);
        Cell player = goalList.stream()
                .filter(x -> x.getMapObject() == MapObject.PLAYER)
                .findFirst()
                .orElse(null);

        if (needMove != null) {
            needMove.setMove(getStep(needMove.getX(), needMove.getY(), arrayWrap, player));
        }

        return needMove;
    }

    private int getMin(int x, int y, int[][] arrayWrap) {
        x++;
        y++;

        int minX = Math.min(arrayWrap[y][x - 1], arrayWrap[y][x + 1]);
        int minY = Math.min(arrayWrap[y - 1][x], arrayWrap[y + 1][x]);
        return Math.min(minX, minY);
    }

    private Move getStep(int x, int y, int[][] arrayWrap, Cell player) {
        int min = getMin(x, y, arrayWrap);
        x++;
        y++;

        int minX = Math.min(arrayWrap[y][x - 1], arrayWrap[y][x + 1]);
        int minY = Math.min(arrayWrap[y - 1][x], arrayWrap[y + 1][x]);

        if (minX == minY && minX == min) {
            if (Math.abs(x - player.getX()) > Math.abs(y - player.getY())) {
                return x - player.getX() > 0 ? Move.LEFT : Move.RIGHT;
            } else {
                return y - player.getY() > 0 ? Move.UP : Move.DOWN;
            }
        }
        if (min == arrayWrap[y - 1][x]) {
            return Move.UP;
        }else  if (min == arrayWrap[y + 1][x]) {
            return Move.DOWN;
        } else if (min == arrayWrap[y][x - 1]) {
            return Move.LEFT;
        } else if (min == arrayWrap[y][x + 1]) {
            return Move.RIGHT;
        }

        return null;
    }
}
