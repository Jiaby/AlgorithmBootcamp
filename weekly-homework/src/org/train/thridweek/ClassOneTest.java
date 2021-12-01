package org.train.thridweek;

import java.util.Arrays;
import java.util.List;

public class ClassOneTest {
    /**
     * 子集
     * @link  https://leetcode-cn.com/problems/subsets/
     */
    public List<List<Integer>> subsets(int[] nums) {

    }

    private void subsetsHelp(int[] nums, int idx, List<List<Integer>> list) {
        if (idx == nums.length - 1) {
            list.add(Arrays.asList(nums[idx]));
            return;
        }
        subsetsHelp(nums, idx + 1, list);

    }
}
