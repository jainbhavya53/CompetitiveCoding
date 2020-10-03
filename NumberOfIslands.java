/**
 * @author bhavya.jain
 */
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        boolean[][] visited = new boolean[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            visited[i] = new boolean[grid[i].length];
        }
        int count = 0;
        int[] unvisited = getNextUnvisitedLand(grid, visited, 0, 0);
        while (unvisited[0] != -1 && unvisited[1] != -1) {
            count++;
            performDfs(grid, visited, unvisited[0], unvisited[1]);
            unvisited = getNextUnvisitedLand(grid, visited, unvisited[0], unvisited[1]);
        }
        return count;
    }

    public void performDfs(char[][] grid, boolean[][] visited, int x, int y) {
        /** Return in following scenarios :
         *
         * Goes out of boundary
         * Water tile encountered
         * Already visited
         *
         */
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[x].length || grid[x][y] == '0' || visited[x][y]) {
            return;
        }
        visited[x][y] = true;
        performDfs(grid, visited, x - 1, y);
        performDfs(grid, visited, x + 1, y);
        performDfs(grid, visited, x, y - 1);
        performDfs(grid, visited, x, y + 1);
    }

    // Returns (x,y) coordinates of next unvisited land tile  
    public int[] getNextUnvisitedLand(char[][] grid, boolean[][] visited, int x, int y) {
        for (int c = y; c < grid[x].length; c++) {
            if (grid[x][c] == '1' && !visited[x][c]) {
                return new int[]{x, c};
            }
        }
        for (int r = x + 1; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == '1' && !visited[r][c]) {
                    return new int[]{r, c};
                }
            }
        }
        return new int[]{-1, -1};
    }
}

