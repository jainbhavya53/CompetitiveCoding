package com.bhavya.leetcode;

import com.bhavya.leetcode.tree.TreeNode;

public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return check(root).flag;
    }

    public Info check(TreeNode root) {
        int min = root.val;
        int max = root.val;
        if (root.left != null) {
            // This step can be avoided, but to identify an invalid early we need this step otherwise we would go all the way down to the leaf node before discovering an invald node
            if (root.val > root.left.val) {
                Info left = check(root.left);
                if (left.flag && root.val > left.max) {
                    min = left.min;
                } else {
                    return new Info(false, -1, -1);
                }
            } else {
                return new Info(false, -1, -1);
            }
        }
        if (root.right != null) {
            // This step can be avoided, but to identify an invalid early we need this step otherwise we would go all the way down to the leaf node before discovering an invald node
            if (root.val < root.right.val) {
                Info right = check(root.right);
                if (right.flag && root.val < right.min) {
                    max = right.max;
                } else {
                    return new Info(false, -1, -1);
                }
            } else {
                return new Info(false, -1, -1);
            }
        }
        return new Info(true, min, max);
    }

    class Info {
        boolean flag;
        int min;
        int max;

        public Info(boolean flag, int min, int max) {
            this.flag = flag;
            this.min = min;
            this.max = max;
        }
    }
}
