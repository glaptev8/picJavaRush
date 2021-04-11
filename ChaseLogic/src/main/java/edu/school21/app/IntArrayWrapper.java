package edu.school21.app;

public class IntArrayWrapper {

    private int[][] initialArray;
    private int[][] arrayWrapper;

    public IntArrayWrapper(int[][] array) {
        this.initialArray = array;
    }

    public int[][] getInitialArray() {
        return initialArray;
    }

    public int[][] getArrayWrapper() {
        if (arrayWrapper == null) {
            arrayWrapper = setArrayToWrapper(initialArray);
        }
        return arrayWrapper;
    }

    private int[][] setArrayToWrapper(int[][] arr) {
        int[][] wrap = new int[arr.length + 2][arr[0].length + 2];

        for (int y = 0; y < wrap.length; y += wrap.length - 1) {
            for (int x = 0; x < wrap[0].length; x++) {
                wrap[y][x] = Integer.MAX_VALUE;
            }
        }

        for (int x = 0; x < wrap[0].length; x += wrap[0].length - 1) {
            for (int y = 0; y < wrap.length; y++) {
                wrap[y][x] = Integer.MAX_VALUE;
            }
        }
        for (int y = 0; y < arr.length; y++) {
            System.arraycopy(arr[y], 0, wrap[y + 1], 1, arr[y].length);
        }
        return wrap;
    }
}
