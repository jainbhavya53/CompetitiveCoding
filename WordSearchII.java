import java.util.*;
public class WordSearchII {
    public List < String > findWords(char[][] board, String[] words) {
        Map < Character, List < Point >> map = getMap(board);
        List < String > output = new ArrayList < > ();
        boolean[][] visited = new boolean[board.length][];
        for (int x = 0; x < board.length; x++) {
            visited[x] = new boolean[board[x].length];
        }
        for (String word: words) {
            if (word == null) {
                continue;
            }
            if (word.length() == 0) {
                output.add(word);
                continue;
            }
            if (!map.containsKey(word.charAt(0))) {
                continue;
            }
            List < Point > pointList = map.get(word.charAt(0));
            boolean result = false;
            for (int i = 0; i < pointList.size() && !result; i++) {
                if (performDfs(board, word, pointList.get(i).x, pointList.get(i).y, visited)) {
                    output.add(word);
                    result = true;
                }
            }
        }
        return output;
    }

    public boolean performDfs(char[][] board, String word, int x, int y, boolean[][] visited) {
        // Return True if
        // Empty word
        if (word.length() == 0) {
            return true;
        }
        // Return False if
        // Out of bounds
        // Already visited
        // Not the desired character
        if (x < 0 || y < 0 || x >= board.length || y >= board[x].length || word.charAt(0) != board[x][y] || visited[x][y]) {
            return false;
        }
        visited[x][y] = true;
        if (performDfs(board, word.substring(1), x - 1, y, visited) || performDfs(board, word.substring(1), x + 1, y, visited) || performDfs(board, word.substring(1), x, y - 1, visited) || performDfs(board, word.substring(1), x, y + 1, visited)) {
            visited[x][y] = false;
            return true;
        }
        visited[x][y] = false;
        return false;
    }

    public Map < Character, List < Point >> getMap(char[][] board) {
        Map < Character, List < Point >> map = new HashMap < > ();
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                List < Point > list;
                if (map.containsKey(board[x][y])) {
                    list = map.get(board[x][y]);
                } else {
                    list = new ArrayList < > ();
                }
                list.add(new Point(x, y));
                map.put(board[x][y], list);
            }
        }
        return map;
    }

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
