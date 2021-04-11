package edu.school21.app;

import java.util.ArrayList;
import java.util.List;

public class ProgramTets {
    public static void main(String[] args) {

        int[][] arr = new int[9][9];
        List<Player> list = new ArrayList<Player>();
        Player player = new Shadower(8, 8);
        list.add(player);

        while (!(player.getX() == 4 && player.getY() == 4)) {
            player = NestStep.INSTANSE.getActualNextStep(list, arr, 4, 4);
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
