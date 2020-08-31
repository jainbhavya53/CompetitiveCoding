package com.guavus.jobs2.bhavya.leetcode;

import com.guavus.jobs2.bhavya.leetcode.tree.TreeNode;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class ZigzagBinaryTreeTraversal {
    // Using two 2 Stack
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> output = new ArrayList<>();
        Stack<Point> left = new Stack<>();
        Stack<Point> right = new Stack<>();
        if (root != null) {
            left.add(new Point(root, 1));
        }
        List<Integer> levelList = new ArrayList<>();
        int prevLevel = 1;
        while (!left.isEmpty() || !right.isEmpty()) {
            Point point;
            if (prevLevel % 2 == 1) {
                if (!left.isEmpty()) {
                    point = left.pop();
                } else {
                    output.add(levelList);
                    levelList = new ArrayList();
                    point = right.pop();
                }
            } else {
                if (!right.isEmpty()) {
                    point = right.pop();
                } else {
                    output.add(levelList);
                    levelList = new ArrayList();
                    point = left.pop();
                }
            }
            int level = point.level;
            TreeNode node = point.node;
            levelList.add(node.val);
            if (level % 2 == 1) {
                if (node.left != null) {
                    right.push(new Point(node.left, level + 1));
                }
                if (node.right != null) {
                    right.push(new Point(node.right, level + 1));
                }
            } else {
                if (node.right != null) {
                    left.push(new Point(node.right, level + 1));
                }
                if (node.left != null) {
                    left.push(new Point(node.left, level + 1));
                }
            }
            prevLevel = level;
        }
        if (levelList.size() > 0) {
            output.add(levelList);
        }
        return output;
    }

    // Using Queue and revering the list
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> output = new ArrayList<>();
        Queue<Point> queue = new LinkedList<>();
        if (root != null) {
            queue.add(new Point(root, 1));
        }
        int prevLevel = 1;
        List<Integer> levelList = new ArrayList<>();
        while (!queue.isEmpty()) {
            Point curr = queue.remove();
            TreeNode node = curr.node;
            int level = curr.level;
            if (node.left != null) {
                queue.add(new Point(node.left, level + 1));
            }
            if (node.right != null) {
                queue.add(new Point(node.right, level + 1));
            }
            if (prevLevel != level) {
                if (prevLevel % 2 == 0) {
                    Collections.reverse(levelList);
                }
                output.add(levelList);
                levelList = new ArrayList<>();
            }
            levelList.add(node.val);
            prevLevel = level;
        }
        if (levelList.size() > 0) {
            if (prevLevel % 2 == 0) {
                Collections.reverse(levelList);
            }
            output.add(levelList);
        }
        return output;
    }

    class Point {
        TreeNode node;
        int level;

        public Point(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }
}

