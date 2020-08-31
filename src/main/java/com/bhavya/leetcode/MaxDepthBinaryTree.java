package com.guavus.jobs2.bhavya.leetcode;

/**
 * @author bhavya.jain
 */

import com.guavus.jobs2.bhavya.leetcode.tree.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

class MaxDepthBinaryTree {

    public int maxDepth(TreeNode root) {
        return getDepth(root, 0);
    }

    public int getDepth(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        return Math.max(getDepth(root.left, depth + 1), getDepth(root.right, depth + 1));
    }
}
