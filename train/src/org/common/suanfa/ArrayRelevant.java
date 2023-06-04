package org.common.suanfa;

import org.common.Utils;

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

    /**
     * 求出数组中出现次数大于n/2的元素
     * 解题思路：
     * 1.利用map存储元素及其出现次数，出现最多的元素就是多数元素
     * 2.先对数组排序，数组中间的元素一定是多数元素
     * 3.投票法，选定nums[0]为候选人，票数初始化为1，遇到相同元素票数+1，不同元素票数-1，票数为0时更换候选人，最后的候选人就是多数元素
     * 投票法的核心就是将不同的元素两两抵消，最后剩下的一定是多数元素
     * @param nums 给定非空数组，长度为n，数组中一定含有多数元素
     */
    public int majorityElement(int[] nums) {
        int c = nums[0], count = 1;
        for (int num : nums) {
            if (num == c) {
                count ++;
            } else if (-- count == 0) {
                // 更换候选人,票数重置为1
                c = num;
                count = 1;
            }
        }
        return c;
    }


    /**
     * 搜索二维矩阵,该矩阵特点是从左到右升序，从上到下升序
     * 思路：这个矩阵是一个有序矩阵，所以首先想到用二分法来解决
     * 1.在每个数组中进行二分查找，同时对两种情况进行优化，1）如果数组第一个元素大于目标值那么后面的元素可以不用查找；2）如果数组最后一个元素小于目标值那么该数组可以不用查找
     * 2.二分查找的进阶办法，二维数组的二分查找。一维数组的mid点很容易算出，但二维数组的中间点需要多考虑一些条件，即整个矩阵的中间点，它将矩阵分为四块，其中左上角矩阵的元素小于中间点，右下角矩阵的元素
     * 大于中间值，所以每次查找我们可以排除掉一块矩阵，然后对剩下的三块矩阵进行分治二分，最后如果触发边界条件返回false
     * 3.从矩阵右上角的元素出发，左边元素一直小于它右边元素一直大于它，所以从右上角元素开始遍历查找
     * @url <a href="https://leetcode.cn/problems/search-a-2d-matrix-ii/"/>
     * 下面是解法1的实现（基本的二分查找实现）
     */
    public boolean searchMatrixFunc1(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] > target) return false;
            if (matrix[i][n - 1] < target) continue;
            int mid = n / 2;
            int l = 0;
            int r = n - 1;
            while (l <= r) {
                if (matrix[i][mid] == target) return true;
                if (matrix[i][mid] < target) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
                mid = (l + r) / 2;
            }
        }
        return false;
    }

    /**
     * 搜索二维矩阵，用二维数组的二分查找解决
     */
    public boolean searchMatrixFunc2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int x1 = 0, x2 = matrix[0].length - 1;
        int y1 = 0, y2 = matrix.length - 1;
        return searchMatrixFunc2Help(matrix, x1, y1, x2, y2, target);
    }

    public boolean searchMatrixFunc2Help(int[][] matrix, int x1, int y1, int x2, int y2, int target) {
        // 边界条件
        if (x1 > x2 || y1 > y2) return false;
        // 数组就剩一个元素了，如果不做判断会陷入死循环
        if (x1 == x2 && y1 == y2) return matrix[y1][x1] == target;
        int mx = (x1 + x2) / 2;
        int my = (y1 + y2) / 2;
        if (matrix[my][mx] == target) return true;
        // 中间点小于目标值，丢弃左上角的矩阵
        else if (matrix[my][mx] < target) {
            return searchMatrixFunc2Help(matrix, mx + 1, y1, x2, my, target) || // 右上角矩阵
                    searchMatrixFunc2Help(matrix, x1, my + 1, mx, y2, target) || // 左下角矩阵
                    searchMatrixFunc2Help(matrix, mx + 1, my + 1, x2, y2, target); // 右下角矩阵
        } else { // 中间值大于目标值，丢弃右下角矩阵
            return searchMatrixFunc2Help(matrix, mx + 1, y1, x2, my, target) || // 右上角矩阵
                    searchMatrixFunc2Help(matrix, x1, my + 1, mx, y2, target) || // 左下角矩阵
                    searchMatrixFunc2Help(matrix, x1, y1, mx , my, target); // 左上角矩阵
        }
    }


    /**
     * 特殊解法，从右上角出发求解
     */
    public boolean searchMatrixFunc3(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = 0, n = matrix[0].length - 1;
        while (n >= 0 && m < matrix.length) {
            if (matrix[m][n] == target) return true;
            else if (matrix[m][n] < target) m ++;
            else n --;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[1][2];
        matrix[0] = new int[]{-1, 3};
        ArrayRelevant arrayRelevant = new ArrayRelevant();
        boolean b = arrayRelevant.searchMatrixFunc2(matrix, 3);
        System.out.println(b);
    }
}
