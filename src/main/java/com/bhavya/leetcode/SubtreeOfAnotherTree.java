package com.guavus.jobs2.bhavya.leetcode;

import com.guavus.jobs2.bhavya.leetcode.tree.TreeNode;

// Runtime - 5 ms faster than 97.34 % solutions. I think we can further optimize it by calculating height of the tree but haven't tried it yet.
public class SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        if (s.val == t.val && isSame(s, t)) {
            return true;
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    public boolean isSame(TreeNode p, TreeNode t) {
        if (p == null && t == null) {
            return true;
        }
        if (p != null && t != null && p.val == t.val && isSame(p.left, t.left) && isSame(p.right, t.right)) {
            return true;
        }
        return false;
    }
}
