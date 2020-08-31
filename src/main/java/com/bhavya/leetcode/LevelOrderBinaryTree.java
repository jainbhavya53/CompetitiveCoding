package com.bhavya.leetcode;

/**
 * @author bhavya.jain
 */

import com.bhavya.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *      3
 *     / \
 *    9  20
 *      /  \
 *     15   7
 * return its level order traversal as:
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 */
class LevelOrderBinaryTree {

    /**
     * Definition for a binary tree node.
     */

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> outputList = new ArrayList<>();
        Queue<Pair> queue = new LinkedList<>();
        if (root == null) {
            return outputList;
        }
        queue.add(new Pair(0, root));
        int currentLevel = 0;
        int previousLevel = 0;
        List<Integer> currentList = new ArrayList<>();
        while (!queue.isEmpty()) {
            Pair currentPair = queue.remove();
            currentLevel = currentPair.getLevel();
            TreeNode currentNode = currentPair.getNode();
            if (currentLevel != previousLevel) {
                outputList.add(currentList);
                currentList = new ArrayList<>();
            }
            currentList.add(currentNode.val);
            if (currentNode.left != null) {
                queue.add(new Pair(currentLevel + 1, currentNode.left));
            }
            if (currentNode.right != null) {
                queue.add(new Pair(currentLevel + 1, currentNode.right));
            }
            previousLevel = currentLevel;
        }
        outputList.add(currentList);
        return outputList;
    }

    class Pair {
        TreeNode node;
        int level;

        public Pair(int level, TreeNode node) {
            this.node = node;
            this.level = level;
        }

        public int getLevel() {
            return this.level;
        }

        public TreeNode getNode() {
            return this.node;
        }
    }
}
