package com.guavus.jobs2.bhavya.leetcode;

/**
 * @author bhavya.jain
 */

import com.guavus.jobs2.bhavya.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class LeafSimilarTrees {

    List<Integer> list1;
    List<Integer> list2;

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        list1 = new ArrayList<Integer>();
        list2 = new ArrayList<Integer>();
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        getLeafNodes(root1, 0);
        getLeafNodes(root2, 1);
        if (list1.equals(list2)) {
            return true;
        } else {
            return false;
        }
    }

    private void getLeafNodes(TreeNode node, int listUse) {
        if (node.left == null && node.right == null) {
            if (listUse == 0) {
                list1.add(node.val);
                return;
            } else {
                list2.add(node.val);
                return;
            }
        }
        if (node.left != null) {
            getLeafNodes(node.left, listUse);
        }
        if (node.right != null) {
            getLeafNodes(node.right, listUse);
        }
    }
}
