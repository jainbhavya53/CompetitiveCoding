package com.guavus.jobs2.bhavya.leetcode;

import com.guavus.jobs2.bhavya.leetcode.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

class SymmetricTree {
    class RecursiveApproach {
        Queue<Pair> queue = new LinkedList<>();

        public boolean isSymmetric(TreeNode root) {
            if (root != null) {
                queue.add(new Pair(root, root));
            }
            while (!queue.isEmpty()) {
                Pair pair = queue.remove();
                TreeNode left = pair.left;
                TreeNode right = pair.right;
                if (left.val != right.val) {
                    return false;
                }
                if (!getPair(left, right) || !getPair(right, left)) {
                    return false;
                }
            }
            return true;
        }

        public boolean getPair(TreeNode left, TreeNode right) {
            if (left.left == null && right.right == null) {
                return true;
            }
            if (left.left == null || right.right == null) {
                return false;
            }
            queue.add(new Pair(left.left, right.right));
            return true;
        }

        class Pair {
            TreeNode left;
            TreeNode right;

            public Pair(TreeNode left, TreeNode right) {
                this.left = left;
                this.right = right;
            }
        }
    }

    class IterativeApproach {
        public boolean isSymmetric(TreeNode root) {
            return checkSymmetric(root, root);
        }

        public boolean checkSymmetric(TreeNode left, TreeNode right) {
            if (left == null && right == null) {
                return true;
            } else if (left == null || right == null) {
                return false;
            }
            if (left.val == right.val && checkSymmetric(left.left, right.right) && checkSymmetric(left.right, right.left)) {
                return true;
            }
            return false;
        }
    }
}
