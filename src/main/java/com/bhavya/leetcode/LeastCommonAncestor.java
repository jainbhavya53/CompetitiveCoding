package com.guavus.jobs2.bhavya.leetcode;

import com.guavus.jobs2.bhavya.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author bhavya.jain
 */
public class LeastCommonAncestor {
    // Version 2 faster than Version 3
    class Version1 {
        List<Integer> path1;
        List<Integer> path2;

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            path1 = new ArrayList<>();
            path2 = new ArrayList<>();
            getPath(root, p.val, 0);
            getPath(root, q.val, 1);
            return new TreeNode(getLCA());
        }

        int getLCA() {
            int min = Math.min(path1.size(), path2.size());
            int output = path1.get(path1.size() - 1);
            boolean result = false;
            for (int i = 0; i < min && !result; i++) {
                int first = path1.get(path1.size() - 1 - i);
                int second = path2.get(path2.size() - 1 - i);
                if (first != second) {
                    result = true;
                } else {
                    output = first;
                }
            }
            for (int i = 0; i < min && !result; i++) {
                int first = path1.get(path1.size() - 1 - i);
                int second = path2.get(path2.size() - 1 - i);
                if (first != second) {
                    result = true;
                } else {
                    output = first;
                }
            }
            return output;
        }

        boolean getPath(TreeNode root, int x, int index) {
            if (root == null) {
                return false;
            }
            if (root.val == x) {
                updateList(root.val, index);
                return true;
            }
            if (getPath(root.left, x, index) || getPath(root.right, x, index)) {
                updateList(root.val, index);
                return true;
            }
            return false;
        }

        void updateList(int value, int index) {
            switch (index) {
                case 0:
                    path1.add(value);
                    break;
                case 1:
                    path2.add(value);
                    break;
            }
        }
    }

    class Version2 {
        Stack<TreeNode> stack;
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            stack = new Stack<>();
            getPath(root,p);
            List<TreeNode> path1 = getPathList(stack);
            stack = new Stack<>();
            getPath(root,q);
            List<TreeNode> path2 = getPathList(stack);
            int index = -1;
            for(int i = 0;i<Math.min(path1.size(),path2.size());i++){
                if(path1.get(i).val == path2.get(i).val){
                    index = i;
                }
                else{
                    break;
                }
            }
            return path1.get(index);
        }

        public List<TreeNode> getPathList(Stack<TreeNode> stack){
            List<TreeNode> list = new ArrayList<>(stack.size());
            while(!stack.isEmpty()){
                list.add(stack.pop());
            }
            return list;
        }

        public boolean getPath(TreeNode root, TreeNode p){
            if(root == null){
                return false;
            }
            if(root.val == p.val){
                stack.add(root);
                return true;
            }
            if(getPath(root.left,p) || getPath(root.right,p)){
                stack.add(root);
                return true;
            }
            return false;
        }
    }

    class Version3 {
        TreeNode lca;

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            lca = null;
            if (root == null || p == null || q == null) {
                return lca;
            }
            lowestCommonAncestor(root, p.val, q.val);
            return lca;
        }

        public boolean lowestCommonAncestor(TreeNode root, int p, int q) {
            if (root == null) {
                return false;
            }
            boolean mid = false;
            boolean left = lowestCommonAncestor(root.left, p, q);
            boolean right = lowestCommonAncestor(root.right, p, q);
            if (root.val == p || root.val == q) {
                mid = true;
            }
            if ((left && right) || (left && mid) || (right && mid)) {
                lca = root;
                return true;
            } else if (left || right || mid) {
                return true;
            }
            return false;
        }
    }
}
