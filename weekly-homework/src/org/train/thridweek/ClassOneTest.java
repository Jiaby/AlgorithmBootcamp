package org.train.thridweek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ClassOneTest {
    List<List<Integer>> ans = new ArrayList<>();
    /**
     * 子集
     * @link  https://leetcode-cn.com/problems/subsets/
     */
    public List<List<Integer>> subsets(int[] nums) {
        subsetsHelp(nums, 0, new LinkedList<>());
        return ans;
    }

    private void subsetsHelp(int[] nums, int idx, LinkedList<Integer> list) {
        if (idx == nums.length) {
            ans.add(list);
            return;
        }
        subsetsHelp(nums, idx + 1, list);
        list.push(nums[idx]);
        subsetsHelp(nums, idx + 1, list);
        list.pop();
    }
}
