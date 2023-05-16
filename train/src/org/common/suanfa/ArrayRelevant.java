package org.common.suanfa;

import java.util.Arrays;

/**
 * 数组相关算法题
 */
public class ArrayRelevant {

    /**
     * 移除数组中的目标元素，且要将后续元素迁移不能有空位，返回值为新数组长度
     * 题目要求必须原地修改
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        int i = -1;
        for (int m = 0; m < nums.length; m++) {
            if (nums[m] == val) {
                i = i == -1 ? m : i;
            } else if (i > -1) {
                nums[i] = nums[m];
                i ++;
            }
        }
        return i == -1 ? nums.length : i;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 3, 2, 3, 3};
        int i = removeElement(nums, 1);
        System.out.println(i);
        System.out.println(Arrays.toString(nums));
    }
}
