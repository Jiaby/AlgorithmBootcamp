package org.homework.firstweek.array;

/**
 * @author jiaby
 * @desc 练习
 * @date 2021/11/18 22:20
 */
public class TestInClass {

    /**
     * 移除数组中的重复项
     * @link https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
     * @return 新数组长度
     */
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        int n = 1;
        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != pre) {
                pre = nums[i];
                nums[n] = nums[i];
                n ++;
            }
        }
        return n;
    }

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * @link https://leetcode-cn.com/problems/move-zeroes/
     */
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0)
            return;
        int n = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[n] = nums[i];
                n ++;
            }
        }
        while (n < nums.length) {
            nums[n] = 0;
            n ++;
        }
    }

    /**
     * 合并两个有序数组 放入nums1
     * @link https://leetcode-cn.com/problems/merge-sorted-array/
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        for (int k = m + n - 1; k >= 0; k --) {
            if (j < 0 || (i >= 0 && nums1[i] >= nums2[j])) {
                nums1[k] = nums1 [i];
                i --;
            } else {
                nums1[k] = nums2[j];
                j --;
            }
        }
    }
}
