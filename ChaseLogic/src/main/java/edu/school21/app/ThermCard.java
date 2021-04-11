package edu.school21.app;

import java.util.ArrayList;
import java.util.List;

public class ThermCard {

    private static final int GOAL_NUMBER = 1;
    private int[][] card;
    List<Cell> goalList;
    List<int[][]> cardList = new ArrayList<>();

    public ThermCard(int[][] card, List<Cell> goalList) {
        this.card = card;
        this.goalList = goalList;
    }

    public int[][] getThermCard() {
        for (Cell tmp : goalList) {
            int[][] newArr = new IntArrayWrapper(card).getArrayWrapper();
            newArr[tmp.getY() + 1][tmp.getX() + 1] = GOAL_NUMBER;

            int currentElement = GOAL_NUMBER;
            int rows = newArr.length;
            int columns = newArr[0].length;

            boolean needIterationWave = true;
            boolean needIteration = true;

            while (needIteration) {
                needIteration = false;
                while (needIterationWave) {
                    needIterationWave = false;
                    for (int y = 1; y < rows; y++) {
                        for (int x = 0; x < columns; x++) {
                            if (newArr[y][x] == 0) {
                                int min = getMin(x, y, newArr);
                                if (min == currentElement) {
                                    newArr[y][x] = (char) (min + 0x1);
                                    needIterationWave = true;
                                    needIteration = true;
                                }
                            }
                        }
                    }
                }
                currentElement++;
                needIterationWave = true;
            }
            newArr[tmp.getY() + 1][tmp.getX() + 1] = Integer.MAX_VALUE;
            cardList.add(newArr);
        }

        return sumOfCard();
    }

    private int getMin(int x, int y, int[][] newCard) {
        int minLeftX = newCard[y][x - 1] == 0 ? Integer.MAX_VALUE : newCard[y][x - 1];
        int minRightX = newCard[y][x + 1] == 0 ? Integer.MAX_VALUE : newCard[y][x + 1];
        int minUpY = newCard[y - 1][x] == 0 ? Integer.MAX_VALUE : newCard[y - 1][x];
        int minDownY = newCard[y + 1][x] == 0 ? Integer.MAX_VALUE : newCard[y + 1][x];

        int minX = Math.min(minLeftX, minRightX);
        int minY = Math.min(minUpY, minDownY);
        return Math.min(minX, minY);
    }

    private int[][] sumOfCard() {
        int[][] res = new IntArrayWrapper(card).getArrayWrapper();
        for (int[][] tmp : cardList) {
            res = sumMatrix(tmp, res);
        }
        return res;
    }

    private int[][] sumMatrix(int[][] src, int[][] dest) {

        for (int y = 1; y < src.length - 1; y++) {
            for (int x = 1; x < src[0].length - 1; x++) {
                if (dest[y][x] == Integer.MAX_VALUE || src[y][x] == Integer.MAX_VALUE) {
                    dest[y][x] = Integer.MAX_VALUE;
                } else {
                    dest[y][x] += src[y][x];
                }
            }
        }
        return dest;
    }
}
