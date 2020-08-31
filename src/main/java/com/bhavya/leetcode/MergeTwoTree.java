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
class MergeTwoTree {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        return merge(t1, t2);
    }

    public TreeNode merge(TreeNode first, TreeNode second) {
        if (first == null && second == null) {
            return null;
        }

        if (first == null || second == null) {
            if (first == null) {
                return second;
            }
            return first;
        }
        TreeNode newNode = new TreeNode(first.val + second.val);
        newNode.left = merge(first.left, second.left);
        newNode.right = merge(first.right, second.right);
        return newNode;
    }
}
