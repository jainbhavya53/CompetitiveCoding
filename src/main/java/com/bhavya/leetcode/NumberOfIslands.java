package com.bhavya.leetcode;

/**
 * @author bhavya.jain
 */
public class NumberOfIslands {
    boolean[][] checkArr;
    char[][] fGrid;

    public int numIslands(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        checkArr = new boolean[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            checkArr[i] = new boolean[grid[i].length];
        }
        fGrid = grid;
        int x = 0;
        int y = 0;
        int[] coor = getNextLandPos(x, y, fGrid);
        x = coor[0];
        y = coor[1];
        int count = 0;
        while (x != -1 || y != -1) {
            traverseIsland(x, y);
            coor = getNextLandPos(x, y, fGrid);
            x = coor[0];
            y = coor[1];
            count++;
        }
        return count;
    }

    public void traverseIsland(int x, int y) {
        if (x < 0 || x >= fGrid.length || y < 0 || y >= fGrid[x].length) {
            return;
        }
        if (fGrid[x][y] == '1' && !checkArr[x][y]) {
            checkArr[x][y] = true;
            traverseIsland(x + 1, y);
            traverseIsland(x - 1, y);
            traverseIsland(x, y + 1);
            traverseIsland(x, y - 1);
        }
        return;
    }

    public int[] getNextLandPos(int x, int y, char[][] grid) {
        int j = y;
        while (j < checkArr[x].length) {
            if (!checkArr[x][j]) {
                if (grid[x][j] == '1') {
                    return new int[]{x, j};
                } else {
                    checkArr[x][j] = true;
                }
            }
            j++;
        }
        for (int i = x + 1; i < checkArr.length; i++) {
            for (j = 0; j < checkArr[x].length; j++) {
                if (!checkArr[i][j]) {
                    if (grid[i][j] == '1') {
                        return new int[]{i, j};
                    } else {
                        checkArr[i][j] = true;
                    }
                }
            }
        }
        return new int[]{-1, -1};
    }
}
