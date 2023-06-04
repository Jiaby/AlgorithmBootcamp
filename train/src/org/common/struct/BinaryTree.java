package org.common.struct;

import com.sun.source.tree.Tree;

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


    /**
     * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗二叉树。
     * 思路：后序遍历数组的最后一个数组就是根节点，我们以这个结点为边界就可以把中序数组分成左右子树两部分。之后递归查询就可以
     * @param inorder 中序遍历数组
     * @param postorder 后序遍历数组
     */
    public TreeNode buildTreeByInorderAndPostOrder(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        return buildTreeHelp(0, inorder.length - 1, 0, postorder.length - 1);
    }
    int[] inorder;
    int[] postorder;
    int[] preorder;
    private TreeNode buildTreeHelp(int i, int i1, int i2, int i3) {
        if (i > i1) return null;
        int mid = i;
        TreeNode root = new TreeNode(postorder[i3]);
        while (postorder[i3] != inorder[mid]) {
            mid ++;
        }
        root.left = buildTreeHelp(i, mid - 1, i2, i2 + mid - i - 1);
        root.right = buildTreeHelp(mid + 1, i1,  i2 + mid - i, i3 - 1);
        return root;
    }

    /**
     * 给定两个整数数组preorder 和 inorder，其中preorder 是二叉树的先序遍历， inorder是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     */
    public TreeNode buildTreeByPreorderAndInOrder(int[] preOrder, int[] inorder) {
        this.inorder = inorder;
        this.preorder = preOrder;
        return buildTreeHelpByPreAndIn(0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelpByPreAndIn(int i, int i1, int i2, int i3) {
        if (i > i1 || i2 > i3) return null;
        int mid = i2;
        TreeNode root = new TreeNode(preorder[i]);
        while (root.val != inorder[mid]) {
            mid ++;
        }
        root.left = buildTreeHelpByPreAndIn(i + 1, i + mid - i2, i2, mid - 1);
        root.right = buildTreeHelpByPreAndIn(i + mid - i2 + 1, i1, mid + 1, i3);
        return root;
    }


    /**
     * 给定一个二叉树填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL
     * @param root
     * @return
     */
    public TreeNode connect(TreeNode root) {
        if (root == null) return null;
        TreeNode cur = root;
        while (cur != null) {
            TreeNode dummy = new TreeNode(0);
            TreeNode pre = dummy;
            while (cur != null) {
                if (cur.left != null) {
                    pre.next = cur.left;
                    pre = pre.next;
                }
                if (cur.right != null) {
                    pre.next = cur.right;
                    pre = pre.next;
                }
                cur = cur.next;
            }
            cur = dummy.next;
        }
        return root;
    }

    public TreeNode connectFunc2(TreeNode root) {
        if (root == null) return null;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode pre = null;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (pre != null) {
                    pre.next = node;
                    pre = pre.next;
                } else {
                    pre = node;
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return root;
    }

    /**
     * 寻找公共父节点
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q || root == null) return root;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        HashMap<TreeNode, TreeNode> parents = new HashMap<>();
        parents.put(root, null);
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                parents.put(node.left, node);
                queue.offer(node.left);
            }
            if (node.right != null) {
                parents.put(node.right, node);
                queue.offer(node.right);
            }
        }
        HashSet<TreeNode> qParents = new HashSet<>();
        while (q != null) {
            qParents.add(q);
            q = parents.get(q);
        }
        while (!qParents.contains(p)) {
            p = parents.get(p);
        }
        return p;
//      递归写法
//        if (root == p || root == q || root ==null) return root;
//        TreeNode left = lowestCommonAncestor(root.left,p,q);
//        TreeNode right = lowestCommonAncestor(root.right,p,q);
//        return left == null ? right : right == null ? left : root;
    }

    /**
     * 判断一棵树是否是二叉搜索树，即结点左子树的值都小于它右子树的值都大于它
     */
    public boolean isValidBST(TreeNode root) {
        return helpIsValidBST(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }

    public boolean helpIsValidBST(TreeNode node, long max, long min) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return helpIsValidBST(node.left, node.val, min) && helpIsValidBST(node.right, max, node.val);
    }


    public static void main(String[] args) {
        int i = lengthOfLongestSubstring2("abcrfewscoplik");
        System.out.println(i);
    }

}
