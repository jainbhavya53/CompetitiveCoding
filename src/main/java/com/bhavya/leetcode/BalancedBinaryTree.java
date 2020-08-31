package com.guavus.jobs2.bhavya.leetcode;

import com.guavus.jobs2.bhavya.leetcode.tree.TreeNode;

// Approach 2 is much faster than 1, as it eliminates the overhead of creating Pair object again and again.
public class BalancedBinaryTree {
    class Approach1 {
        public boolean isBalanced(TreeNode root) {
            Pair result = check(root);
            return result.flag;
        }

        public Pair check(TreeNode root) {
            if (root == null) {
                return new Pair(-1, true);
            }
            Pair left = check(root.left);
            Pair right = check(root.right);
            if (left.flag && right.flag && Math.abs(left.height - right.height) <= 1) {
                int height = Math.max(left.height, right.height) + 1;
                return new Pair(height, true);
            }
            return new Pair(-1, false);
        }

        class Pair {
            int height;
            boolean flag;

            public Pair(int height, boolean flag) {
                this.height = height;
                this.flag = flag;
            }
        }
    }

    class Approach2 {
        public boolean isBalanced(TreeNode root) {
            int val = check(root);
            if (val == -2) {
                return false;
            }
            return true;
        }

        public int check(TreeNode root) {
            if (root == null) {
                return -1;
            }
            int left = check(root.left);
            int right = check(root.right);
            if (left != -2 && right != -2 && Math.abs(left - right) <= 1) {
                int height = Math.max(left, right) + 1;
                return height;
            }
            return -2;
        }
    }
}
