package org.train.secondweek.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassTestThree {
    /**
     * 两数之和 II - 输入有序数组
     * @link https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
     */
    public int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;
        int[] result = new int[2];
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                result[0] = i + 1;
                result[1] = j + 1;
                return result;
            }
            if (sum > target) {
                j --;
            } else {
                i ++;
            }
        }
        return result;
    }

    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
     * 请你找出所有和为 0 且不重复的三元组。
     * @link https://leetcode-cn.com/problems/3sum/
     */

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            List<List<Integer>> list = twoSum(nums, i + 1, -nums[i]);
            for (List<Integer> l : list) {
                ans.add(Arrays.asList(nums[i], l.get(0), l.get(1)));
            }
        }
        return ans;
    }

    private List<List<Integer>> twoSum(int[] numbers, int start, int target) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = start; i < numbers.length; i++) {
            if (i > start && numbers[i] == numbers[i - 1]) continue;
            int j = numbers.length - 1;
            while (j > i && numbers[j] + numbers[i] > target) {
                j --;
            }
            if (i < j && numbers[i] + numbers[j] == target) {
                result.add(Arrays.asList(numbers[i],numbers[j]));
            }
        }
        return result;
    }

    /**
     * 盛最多水的容器
     * @link https://leetcode-cn.com/problems/container-with-most-water/
     * 本质就找冗余去掉
     */
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int ans = 0;
        while (i < j) {
            ans = Math.max(ans, Math.min(height[i], height[j]) * (j - i));
            if (height[i] < height [j]) i ++;
            else j --;
        }
        return ans;
    }

    public static void main(String[] args) {
        ClassTestThree classTestThree = new ClassTestThree();
//        int[] ints = classTestThree.twoSum(new int[]{2, 7, 11, 15}, 9);

//        classTestThree.threeSum(new int[]{0,0,0,0});
        classTestThree.maxArea(new int[]{1,1});
    }

}
