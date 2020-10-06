package com.bhavya.leetcode;

public class WordSearch {
    public boolean exist(char[][] board, String word) {
        if (word == null) {
            return false;
        }
        boolean[][] visited = new boolean[board.length][];
        for (int i = 0; i < board.length; i++) {
            visited[i] = new boolean[board[i].length];
        }
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (performDfs(board, word, x, y, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    // We can change this method so that it takes the current index of the word, rather than the whole string itself in order to save memory
    // but did not observe any significant difference in memory consumption by using it. Tested on leetcode
    public boolean performDfs(char[][] board, String word, int x, int y, boolean[][] visited) {

        /**
         * Return true if
         * Empty
         * Traversed the whole word
         * */

        if (word.length() == 0) {
            return true;
        }

        /**
         * Return false if
         * Out of bounds
         * Not desired character
         * Already visi¬ted
         * */

        if (x < 0 || y < 0 || x >= board.length || y >= board[x].length || word.charAt(0) != board[x][y] || visited[x][y]) {
            return false;
        }
        visited[x][y] = true;
        String nextWord = word.substring(1);
        if (performDfs(board, nextWord, x - 1, y, visited) || performDfs(board, nextWord, x + 1, y, visited) || performDfs(board, nextWord, x, y - 1, visited) || performDfs(board, nextWord, x, y + 1, visited)) {
            return true;
        }
        visited[x][y] = false;
        return false;
    }
}