package org.common.struct;

import java.util.*;

/**
 * 二叉树
 */
public class BinaryTree {


    public static List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return Collections.EMPTY_LIST;
        LinkedList<Integer> result = new LinkedList<>();
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            result.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return Collections.EMPTY_LIST;
        LinkedList<Integer> result = new LinkedList<>();
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        TreeNode cur = root;
        while (!stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;

            }
            TreeNode node = stack.pop();
            result.add(node.val);
            while (node.right != null) {
                cur = node.right;
                stack.push(cur);
            }
        }
        return result;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return Collections.EMPTY_LIST;
        LinkedList<Integer> result = new LinkedList<>();
        ArrayDeque<TreeNode> stack1 = new ArrayDeque<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            result.addFirst(node.val);
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }
        return result;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return Collections.EMPTY_LIST;
        LinkedList<List<Integer>> res = new LinkedList<>();
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            LinkedList<Integer> list = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(list);
        }
        return res;
    }
    public List<List<Integer>> levelOrder(TreeNode root, int level, List<List<Integer>> res) {
        if (root == null) return res;
        if (res.size() == level) {
            res.add(level, new LinkedList<>());
        }
        res.get(level).add(root.val);
        levelOrder(root.left, level + 1, res);
        levelOrder(root.right, level + 1, res);
        return res;
    }

    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        if (n <= 1) return n;
        int maxLen = 1;

        //左、右指针
        int left = 0, right = 0;

        Set<Character> window = new HashSet<>();
        while (right < n) {
            char rightChar = s.charAt(right);
            while (window.contains(rightChar)) {
                window.remove(s.charAt(left));
                left++;
            }
            //最大长度对比
            maxLen = Math.max(maxLen, right - left + 1);
            window.add(rightChar);
            right++;
        }

        return maxLen;
    }

    public static void main(String[] args) {
        int i = lengthOfLongestSubstring2("abcrfewscoplik");
        System.out.println(i);
    }

}
