package org.train.thirdweek;

import org.common.struct.TreeNode;

/**
 * @author liuyue
 * @Description: 树结构练习
 * @date 2021/12/4 15:43
 */
public class TreeTest {

    /**
     * 翻转二叉树
     * @link https://leetcode-cn.com/problems/invert-binary-tree/description/
     */
    public TreeNode invertTree(TreeNode root) {
        invertTreeHelp(root);
        return root;
    }

    private void invertTreeHelp(TreeNode node) {
        if (node == null) return;
        invertTree(node.left);
        invertTree(node.right);
        TreeNode left = node.left;
        node.left = node.right;
        node.right = left;
        return;
    }


    /**
     * 验证二叉搜索树
     * @link https://leetcode-cn.com/problems/validate-binary-search-tree/
     */
    public boolean isValidBST(TreeNode root) {
        return check(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean check(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return check(node.left, min, node.val) && check(node.right, node.val, max);
    }

    /**
     * 二叉树的最大深度
     * @link https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.right), maxDepth(root.left)) + 1;
    }

    /**
     * 二叉树的最小深度
     * @link https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) {
            return 1;
        }

        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left), min_depth);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }

        return min_depth + 1;
    }

    public static void main(String[] args) {
        TreeTest treeTest = new TreeTest();
        System.out.println(treeTest.isValidBST(new TreeNode(2147483647)));
    }
}
