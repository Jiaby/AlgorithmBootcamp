package org.homework.thirdweek;

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
}
