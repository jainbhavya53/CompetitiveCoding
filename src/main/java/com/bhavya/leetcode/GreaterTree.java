package com.guavus.jobs2.bhavya.leetcode;

import com.guavus.jobs2.bhavya.leetcode.tree.TreeNode;

public class GreaterTree {

    public TreeNode convertBST(TreeNode root) {
        return convertBST(root, 0).node;
    }

    public Pair convertBST(TreeNode root, int total) {
        if (root == null) {
            return new Pair(root, total);
        }
        Pair right = convertBST(root.right, total);
        root.val = root.val + right.total;
        total = root.val;
        root.right = right.node;
        if (root.left != null) {
            Pair left = convertBST(root.left, total);
            root.left = left.node;
            total = left.total;
        }
        return new Pair(root, total);
    }

    class Pair {
        TreeNode node;
        int total;

        public Pair(TreeNode node, int total) {
            this.node = node;
            this.total = total;
        }
    }
}
