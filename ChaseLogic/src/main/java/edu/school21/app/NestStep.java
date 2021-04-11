package edu.school21.app;

import java.util.List;

public enum NestStep {

    INSTANSE;

    public Player getActualNextStep(List<Player> list, int[][] chars, int goalX, int goalY) {
        ThermCard thermCard = new ThermCard(chars, goalX, goalY);

        final int[][] newArrays = thermCard.getThermCard();
        int min = Character.MAX_VALUE;
        Player minPlayer = null;
        for (Player tmp : list) {
            int i;
            if ((i = getMin(newArrays, tmp.getX() + 1, tmp.getX() + 1)) < min) {
                minPlayer = tmp;
                min = i;
            }
        }

        return getStep(minPlayer, newArrays);
    }

    private Player getStep(Player player, int[][] chars) {
        int min = getMin(chars, player.getX() + 1, player.getY() + 1);

        if (min == chars[player.getY() + 2][player.getX() + 1]) {
            player.setY(player.getY() + 1);
            player.setX(player.getX());
        } else if (min == chars[player.getY() + 1][player.getX()]) {
            player.setY(player.getY());
            player.setX(player.getX() - 1);
        } else if (min == chars[player.getY() + 1][player.getX() + 2]) {
            player.setY(player.getY());
            player.setX(player.getX() + 1);
        } else if (min == chars[player.getY()][player.getX() + 1]) {
            player.setY(player.getY() - 1);
            player.setX(player.getX());
        }
        return player;
    }

    private int getMin(int[][] chars,int x, int y) {
        int minX = chars[y][x - 1] < chars[y][x + 1] ? chars[y][x - 1] : chars[y][x + 1];
        int minY = chars[y - 1][x] < chars[y + 1][x] ? chars[y - 1][x] : chars[y + 1][x];
        return minX < minY ? minX : minY;
    }
}
