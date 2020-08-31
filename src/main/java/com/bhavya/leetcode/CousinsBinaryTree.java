package com.guavus.jobs2.bhavya.leetcode;

import com.guavus.jobs2.bhavya.leetcode.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;
/**
 * @author bhavya.jain
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class CousinsBinaryTree {

    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        int[] result1 = getAncestorDepth(root, root.val, x, 0);
        int[] result2 = getAncestorDepth(root, root.val, y, 0);
        if (result1[1] == -1 || result2[1] == -1) {
            return false;
        }
        if (result1[1] == result2[1] && result1[0] != result2[0]) {
            return true;
        } else {
            return false;
        }
    }

    public int[] getAncestorDepth(TreeNode root, int parent, int input, int depth) {
        int output[] = new int[2];
        output[0] = -1;
        output[1] = -1;
        if (root == null) {
            return output;
        }
        if (root.val == input) {
            output[0] = parent;
            output[1] = depth;
            return output;
        }
        int[] leftResult = getAncestorDepth(root.left, root.val, input, depth + 1);
        if (leftResult[1] != -1) {
            return leftResult;
        }
        int[] rightResult = getAncestorDepth(root.right, root.val, input, depth + 1);
        if (rightResult[1] != -1) {
            return rightResult;
        }
        return output;
    }
}
