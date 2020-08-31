package com.bhavya.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author bhavya.jain
 */
public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        return paranthesis(n);
    }

    public List<String> paranthesis(int n) {
        Queue<Custom> queue = new LinkedList<>();
        queue.add(new Custom("", n, n));
        List<String> output = new ArrayList<>();
        while (!queue.isEmpty()) {
            Custom current = queue.remove();
            if (current.left == 0 && current.right == 0) {
                output.add(current.val);
            } else if (current.right == 0) {
                for (int i = 0; i < current.left; i++) {
                    current.val = "(" + current.val;
                }
                output.add(current.val);
            } else if (current.left > current.right) {
                Custom temp1 = new Custom(")" + current.val, current.left, current.right - 1);
                Custom temp2 = new Custom("(" + current.val, current.left - 1, current.right);
                queue.add(temp1);
                queue.add(temp2);
            } else {
                current.val = ")" + current.val;
                current.right = current.right - 1;
                queue.add(current);
            }
        }
        return output;
    }

    class Custom {
        String val;
        int left;
        int right;

        public Custom(String val, int left, int right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
