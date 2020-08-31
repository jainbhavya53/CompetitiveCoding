package com.bhavya.leetcode;

/**
 * @author bhavya.jain
 */
public class FloodFill {
    int[][] output;
    int color;
    int checkColor;
    boolean[][] result;

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        result = new boolean[image.length][];
        for (int i = 0; i < image.length; i++) {
            result[i] = new boolean[image[i].length];
        }
        this.output = image;
        this.checkColor = image[sr][sc];
        this.color = newColor;
        fillColor(sr, sc);
        return output;
    }

    public void fillColor(int x, int y) {
        if (x < 0 || x > output.length - 1 || y < 0 || y > output[x].length - 1) {
            return;
        }
        if (result[x][y]) {
            return;
        }
        result[x][y] = true;
        if (output[x][y] == checkColor) {
            output[x][y] = color;
        } else {

            return;
        }
        fillColor(x - 1, y);
        fillColor(x + 1, y);
        fillColor(x, y + 1);
        fillColor(x, y - 1);
    }
}
