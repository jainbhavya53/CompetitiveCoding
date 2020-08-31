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

// Version 1 and 2 have the same logic, its just different way of implementing the same thing.
// Very interesting that version 2 has lesser memory requirement than version 1. Need to look into it.
public class UnivaluedBinaryTree {
    // Version 1
    public boolean isUnivalTree2(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = true;
        boolean right = true;
        if (root.left != null) {
            if (root.val == root.left.val) {
                left = isUnivalTree(root.left);
            } else {
                return false;
            }
        }
        if (root.right != null) {
            if (root.val == root.right.val) {
                right = isUnivalTree(root.right);
            } else {
                return false;
            }
        }
        return (left && right);
    }


    // Version 2
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        return check(root, root.val);
    }

    public boolean check(TreeNode root, int val) {
        if (root == null) {
            return true;
        }
        if (root.val != val) {
            return false;
        }
        return check(root.left, val) && check(root.right, val);
    }
}
