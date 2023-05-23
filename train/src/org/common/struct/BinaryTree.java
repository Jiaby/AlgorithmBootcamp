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

    //求树的最大深度
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return countDepth(root, 0);
    }

    public int countDepth(TreeNode node, int preDepth) {
        if (node == null) return preDepth;
        int curDepth = preDepth + 1;
        int leftDepth = countDepth(node.left, curDepth);
        int rightDepth = countDepth(node.right, curDepth);
        return Math.max(leftDepth, rightDepth);
    }

    /**
     * 是否对称二叉树
     * 解决这个问题要知道一个关键点，对称二叉树中一个非叶子结点的左子树等于该兄弟们结点的右子树，右子树等于该兄弟节点的左子树
     */

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left == null && right == null)
                continue;
            if (left == null || right ==null || left.val != right.val)
                return false;
            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }

    // 是否对称二叉树的递归解法
    public boolean isSymmetricHelp(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null || left.val != right.val) return false;
        return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left);
    }

    /**
     * 给你二叉树的根节点root 和一个表示目标和的整数targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，
     * 这条路径上所有节点值相加等于目标和targetSum 。如果存在，返回 true ；否则，返回 false 。
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null)
            return root.val == targetSum;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }


    public static void main(String[] args) {
        int i = lengthOfLongestSubstring2("abcrfewscoplik");
        System.out.println(i);
    }

}
