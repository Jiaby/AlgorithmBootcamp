package org.train.thirdweek;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author liuyue
 * @Description: 递归训练
 * @date 2021/12/4 14:17
 */
public class RecursionTest {
    private List<Integer> chosen = new ArrayList<>();
    private List<List<Integer>> ans = new LinkedList<>();
    /**
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * @link https://leetcode-cn.com/problems/subsets/
     * @return 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     */
    public List<List<Integer>> subsets(int[] nums) {
        subsetsRecursionHelp(nums, 0);
        return ans;
    }

    private void subsetsRecursionHelp(int[] nums, int idx) {
        if (idx == nums.length) {
            ans.add(new ArrayList<>(chosen));
            return;
        }
        subsetsRecursionHelp(nums, idx + 1);
        chosen.add(nums[idx]);
        subsetsRecursionHelp(nums, idx + 1);
        chosen.remove(chosen.size() - 1);
    }


    /**
     * 给定两个整数 n 和 k
     * @link https://leetcode-cn.com/problems/combinations/
     * @return 返回范围 [1, n] 中所有可能的 k 个数的组合。
     */
    public List<List<Integer>> combine(int n, int k) {
        combineRecursionHelp(n, 1, k);
        return ans;
    }

    private void combineRecursionHelp(int n, int i, int k) {
        // 剪枝，发现已经不满足返回条件时就直接中断递归
        if (chosen.size() > k || chosen.size() + n - i + 1 < k) return;
        if (i == n + 1) {
            ans.add(new ArrayList<>(chosen));
            return;
        }
        // 不选当前
        combineRecursionHelp(n, i + 1, k);
        // 选当前
        chosen.add(i);
        combineRecursionHelp(n, i + 1, k);
        chosen.remove(chosen.size() - 1);
    }

    /**
     * @link https://leetcode-cn.com/problems/permutations/
     * @param nums 给定一个不含重复数字的数组 nums
     * @return 返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     */
    boolean[] used;
    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
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
            if (!used[i]) {
                used[i] = true;
                // 这里先进行将元素放入
                chosen.add(nums[i]);
                permuteRecursionHelp(nums, depth + 1);
                // 移除之后待下一次递归循环会继续放入但放入的位置会发生变化
                chosen.remove(chosen.size() - 1);
                used[i] = false;
            }
        }
    }


    public static void main(String[] args) {
        RecursionTest recursionTest = new RecursionTest();
        recursionTest.permute(new int[]{1,2,3});
    }
}
