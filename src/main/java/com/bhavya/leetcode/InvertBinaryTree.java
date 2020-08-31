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
public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode temp = node.right;
        node.right = invertTree(node.left);
        node.left = invertTree(temp);
        return node;
    }
}
