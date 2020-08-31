package com.guavus.jobs2.bhavya.leetcode;

import com.guavus.jobs2.bhavya.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bhavya.jain
 */

public class DiameterBinaryTree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */

    int sum;

    List<Integer> list = new ArrayList<>();
    public int diameterOfBinaryTree(TreeNode root) {
        sum = -1;
        if (root == null) {
            return 0;
        }
        findDepth(root);
        return sum;
    }

    public int findDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int lDepth = findDepth(node.left);
        int rDepth = findDepth(node.right);
        if (lDepth + rDepth > sum) {
            sum = lDepth + rDepth;
        }
        return Math.max(lDepth, rDepth) + 1;
    }
}
