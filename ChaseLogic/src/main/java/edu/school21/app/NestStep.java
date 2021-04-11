package edu.school21.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum NestStep {

    INSTANSE;

    public Cell getNextStep(Cell[][] map, int goalX, int goalY) {
        int[][] arr = new int[map.length][map[0].length];
        List<Cell> shadowerList = new ArrayList<>();

        Arrays.stream(map)
                .flatMap(x -> Arrays.stream(x))
                .forEach(x -> {
                    if (x.getMapObject() == MapObject.WALL) {
                        arr[x.getY()][x.getX()] = Integer.MAX_VALUE;
                    } else if (x.getMapObject() == MapObject.SHADOWER) {
                        shadowerList.add(x);
                    }
                });

        int[][] arrayWrap = new ThermCard(arr, goalX, goalY).getThermCard();

        Cell needMove = shadowerList.stream()
                .min((x, y) -> getMin(x.getX(), x.getY(), arrayWrap) < getMin(y.getX(), y.getY(), arrayWrap) ? 1 : 0)
                .orElse(null);

        if (needMove != null) {
            needMove.setMove(getStep(needMove.getX(), needMove.getY(), arrayWrap));
        }

        return needMove;
    }

    private int getMin(int x, int y, int[][] chars) {
        x++;
        y++;

        int minX = chars[y][x - 1] < chars[y][x + 1] ? chars[y][x - 1] : chars[y][x + 1];
        int minY = chars[y - 1][x] < chars[y + 1][x] ? chars[y - 1][x] : chars[y + 1][x];
        return minX < minY ? minX : minY;
    }

    private Move getStep(int x, int y, int[][] chars) {
        int min = getMin(x, y, chars);
        x++;
        y++;

        if (min == chars[y - 1][x]) {
            return Move.UP;
        } else if (min == chars[y][x - 1]) {
            return Move.LEFT;
        } else if (min == chars[y][x + 1]) {
            return Move.RIGHT;
        } else if (min == chars[y + 1][x]) {
            return Move.DOWN;
        }

        return null;
    }
}
