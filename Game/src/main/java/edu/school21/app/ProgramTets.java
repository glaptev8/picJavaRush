package edu.school21.app;

import java.util.ArrayList;
import java.util.List;

public class ProgramTets {
    public static void main(String[] args) {

        int[][] arr = new int[11][11];
        List<Player> list = new ArrayList<Player>();
        Player player = new Shadower(10, 10);
        list.add(player);

        while (!(player.getX() == 0 && player.getY() == 0)) {
            player = NestStep.INSTANSE.getActualNextStep(list, arr, 0, 0);
            arr[player.getY()][player.getX()] = (char) 7;
            for (int[] line : arr) {
                for (int tmp : line) {
                    System.out.print(((int) tmp) + " ");
                }
                System.out.println();
            }
            arr[player.getY()][player.getX()] = (char) 0;

            System.out.println();
        }

    }
}
