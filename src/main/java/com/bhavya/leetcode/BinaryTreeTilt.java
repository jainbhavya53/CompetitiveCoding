package com.bhavya.leetcode;

/**
 * @author bhavya.jain
 */

import com.bhavya.leetcode.tree.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class BinaryTreeTilt {

    int tilt;

    public int findTilt(TreeNode root) {
        tilt = 0;
        getAnswer(root);
        return tilt;
    }

    public int getAnswer(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        int lSum = getAnswer(root.left);
        int rSum = getAnswer(root.right);
        tilt = tilt + Math.abs(lSum - rSum);
        return lSum + rSum + root.val;
    }
}
