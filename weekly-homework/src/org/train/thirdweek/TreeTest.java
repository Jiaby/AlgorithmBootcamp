package org.train.thirdweek;

import org.common.struct.Node;
import org.common.struct.TreeNode;


import java.util.*;

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

    /**
     * @link https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
     * @param root
     * @return 二叉树的中序遍历结果
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if (root != null) {
            inorderHelp(root, ans);
        }
        return ans;
    }

    private void inorderHelp(TreeNode root, List<Integer> ans) {
        if (root == null) return ;
        inorderHelp(root.left, ans);
        ans.add(root.val);
        inorderHelp(root.right, ans);
    }

    /**
     * @link https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/submissions/
     * @desc 多叉树的前序遍历
     */
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new LinkedList<>();
        if (root == null) return ans;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            ans.add(node.val);
            for (int i = node.children.size() - 1; i >= 0 ; i--) {
                stack.push(node.children.get(i));
            }
        }
        return ans;
    }


    /**
     * @link https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
     * @desc 多叉树的层序遍历
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) return ans;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> nodes = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node == null) continue;
                nodes.add(node.val);
                if (node.children != null && node.children.size() > 0) {
                    for (Node child : node.children) {
                        queue.offer(child);
                    }
                }
            }
            ans.add(nodes);
        }
        return ans;
    }

    /**
     * @link https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
     * @desc 从前序与中序遍历序列构造二叉树
     */
    int[] preorder;
    int[] inorder;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.inorder = inorder;
        this.preorder = preorder;
        return buildHelp(0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode buildHelp(int l1, int r1, int l2, int r2) {
        if (l1 > r1) return null;
        TreeNode root = new TreeNode(preorder[l1]);
        int mid = l2;
        // 此时mid是root在中序数组中的下标
        while (inorder[mid] != root.val) mid++;
        // 左子树的个数就是mid - 1 - l2 + 1 = mid - l2(mid此时是root的位置，所以要减一才能是区间l~r中r的位置）
        // 左子树的中序就是l2~mid-1, 前序就是l1+1~l1+(mid-1 - l2 + 1)
        root.left = buildHelp(l1 + 1, l1 + mid - l2, l2, mid - 1);
        root.right = buildHelp(l1 + mid - l2 + 1, r1, mid + 1, r2);
        return root;
    }

    /**
     * @link https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
     * 二叉树的序列化
     */
    public String serialize(TreeNode root) {
        return null;
    }

    /**
     * @link https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
     * 二叉树的反序列化
     */
    public TreeNode deserialize(String data) {
        return null;
    }





    public static void main(String[] args) {
        TreeTest treeTest = new TreeTest();
        System.out.println(treeTest.isValidBST(new TreeNode(2147483647)));
    }
}
