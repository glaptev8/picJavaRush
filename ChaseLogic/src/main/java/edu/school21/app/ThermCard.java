package edu.school21.app;

public class ThermCard {

    private static final int GOAL_NUMBER = 1;
    private int[][] card;
    private int goalX;
    private int goalY;

    public ThermCard(int[][] card, int goalX, int goalY) {
        this.card = new IntArrayWrapper(card).getArrayWrapper();
        this.goalX = goalX;
        this.goalY = goalX;
    }

    public int[][] getThermCard() {
        card[goalY + 1][goalX + 1] = GOAL_NUMBER;
        int currentElement = GOAL_NUMBER;
        int rows = card.length;
        int columns = card[0].length;

        boolean needIterationWave = true;
        boolean needIteration = true;

        while (needIteration) {
            needIteration = false;
            while (needIterationWave) {
                needIterationWave = false;
                for (int y = 1; y < rows; y++) {
                    for (int x = 0; x < columns; x++) {
                        if (card[y][x] == 0) {
                            int min = getMin(x, y);
                            if (min == currentElement) {
                                card[y][x] = (char) (min + 0x1);
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

        return card;
    }

    private int getMin(int x, int y) {
        int min = Integer.MAX_VALUE;
        for (int i = y - 1; i < y + 2; i++) {
            for (int z = x - 1; z < x + 2; z++) {
                if (card[i][z] < min && card[i][z] != 0) {
                   min = card[i][z];
                }
            }
        }
        return min;
    }
}
