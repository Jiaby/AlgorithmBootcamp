package org.homework.thirdweek;

import org.common.struct.ListNode;
import org.common.struct.TreeNode;
import org.homework.firstweek.SolutionOne;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author liuyue
 * @Description: homework-thirdWeek
 * @date 2021/12/4 15:00
 */
public class Solution {

    /**
     * 全排列II
     * @link https://leetcode-cn.com/problems/permutations-ii/
     */
    private List<Integer> chosen = new ArrayList<>();
    private List<List<Integer>> ans = new LinkedList<>();
    boolean[] used;
    public List<List<Integer>> permuteUnique(int[] nums) {
        used = new boolean[nums.length];
        // 对原数组排序，保证相同的数字都相邻
        Arrays.sort(nums);
        permuteRecursionHelp(nums, 0);
        return ans;
    }

    private void permuteRecursionHelp(int[] nums, int depth) {
        if (depth == nums.length) {
            ans.add(new ArrayList<>(chosen));
            return;
        }
        // 循环的目的是每一次递归都会从头将未放入chosen的元素放入（虽然一开始会将下标为0的元素放入，但后续它会移除）
        for (int i = 0; i < nums.length; i++) {
            // 每次填入的数一定是这个数所在重复数集合中「从左往右第一个未被填过的数字」
            // 即对相邻的重复数字只有一种排列方式被填入，其他情况都被舍弃
            // 比如{1,1,1} 只有下标{0,1,2}这一种排列方式被填入结果集，{1,0,2}{2,0,1}···这些都被舍弃
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]))  continue;
            used[i] = true;
            // 这里先进行将元素放入
            chosen.add(nums[i]);
            permuteRecursionHelp(nums, depth + 1);
            // 移除之后待下一次递归循环会继续放入但放入的位置会发生变化
            chosen.remove(chosen.size() - 1);
            used[i] = false;

        }
    }

    /**
     * 从中序与后序遍历序列构造二叉树
     * @link https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
     * @param inorder 中序遍历数组
     * @param postorder 后序遍历数组
     * @return 二叉树节点
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return null;
    }


    /**
     * 合并K个升序链表
     * @link https://leetcode-cn.com/problems/merge-k-sorted-lists/
     * @param lists 链表数组,每个链表都已经按升序排列。
     * @return 合并后的链表
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeListsHelp(lists, 0, lists.length - 1);
    }

    private ListNode mergeListsHelp(ListNode[] lists, int leftIdx, int rightIdx) {
        if (leftIdx == rightIdx) return lists[leftIdx];
        int midIdx = (leftIdx + rightIdx) / 2;
        ListNode leftNode = mergeListsHelp(lists, leftIdx, midIdx);
        ListNode rightNode = mergeListsHelp(lists, midIdx + 1, rightIdx);
        return mergeTwoLists(leftNode, rightNode);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            if (l1 == null) return l2;
            return l1;
        }
        ListNode head = new ListNode();
        ListNode node = head;
        while (l1 != null && l2 != null) {
            if (l1.value <= l2.value) {
                node.next = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }
        node.next = l1 == null ? l2 : l1;
        return head.next;
    }

    public static void main(String[] args) {
        int left = 0, right = 9;
        int mid = (left + right) / 2;
        System.out.println(mid);
    }
}
