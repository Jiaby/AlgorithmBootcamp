package org.common.suanfa;

/**
 * 默认方法的参数数组是从小到大排列
 */
public class BinarySearch {

    /**
     * 基础版二分查找，数组默认有序
     */
    public static int primaryBinarySearch(int[] a, int n, int val) {
        int low = 0, high = n - 1, mid;
        int index = -1;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (a[mid] > val) {
                high = mid -1;
            } else if (a[mid] < val) {
                low = mid + 1;
            } else {
                index = mid;
                break;
            }
        }
        return index;
    }

    /*
    查找数组中等于val的第一个元素
     */
    public static int binarySearchFirst(int[] a, int n, int val) {
        int low = 0, high = n - 1, mid;
        int index = -1;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (a[mid] > val) {
                high = mid -1;
            } else if (a[mid] < val) {
                low = mid + 1;
            } else {
                if (mid == 0 || a[mid - 1] != val) {
                    index = mid;
                    break;
                } else {
                    high = mid - 1;
                }
            }
        }
        return index;
    }

    /**
     * 查找数组中等于val的最后一个元素
     */
    public static int binarySearchLast(int[] a, int n, int val) {
        int low = 0, high = n - 1, mid;
        int index = -1;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (a[mid] > val) {
                high = mid -1;
            } else if (a[mid] < val) {
                low = mid + 1;
            } else {
                if (mid == n - 1 || a[mid + 1] != val) {
                    index = mid;
                    break;
                } else {
                    low = mid + 1;
                }
            }
        }
        return index;
    }

    /**
     * 查找数组中第一个大于等于val的元素
     */
    public static int binarySearchFirstGreatAndEquals(int[] a, int n, int val) {
        int low = 0, high = n - 1, mid;
        int index = -1;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (a[mid] >= val) {
                if (mid == 0 || a[mid -1] < val) {
                    index = mid;
                    break;
                } else {
                    high = mid - 1;
                }
            } else if (a[mid] < val) {
                low = mid + 1;
            }
        }
        return index;
    }

    /**
     * 查找数组中最后一个小于等于val的元素
     */
    public static int binarySearchLastSmallAndEquals(int[] a, int n, int val) {
        int low = 0, high = n - 1, mid;
        int index = -1;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (a[mid] > val) {
                high = mid -1;
            } else if (a[mid] <= val) {
                if (mid == n - 1 || a[mid + 1] > val) {
                    index = mid;
                    break;
                } else {
                    low = mid + 1;
                }
            }
        }
        return index;
    }

    /**
     * 循环数组的二分查找
     * 所谓循环数组即：{4,5,6,1,2,3}这种
     * leetcode题目叫搜索旋转排序数组，地址https://leetcode.cn/leetbook/read/binary-search/xeog5j/
     * 本题难点在于数组进行了扭转整体无序但部分是有序的，所以当我们判断到mid值与target值不相等时，需要额外判断左右边界值与Target值关系来做边界移动。
     */
    public static int cycleBinarySearch(int[] nums, int target) {
        int l = 0, h = nums.length - 1, mid = l + (h - l) / 2;
        while (l <= h) {
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) {
                if (nums[l] > nums[mid] && nums[h] < target) {
                    h = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[h] < nums[mid] && nums[l] > target) {
                    l = mid + 1;
                } else {
                    h = mid - 1;
                }
            }
            mid = l + (h - l) / 2;
        }
        return -1;
    }

    /**
     * 求x的平方根
     */
    public static int mySqrt(int x) {
        if (x == 0 || x == 1) return x;
        int low = 0, high = x;
        int mid;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (mid > x / mid) {
                high = mid - 1;
            } else if (mid < x / mid) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return high;
    }

    /**
     * 峰值元素是指其值严格大于左右相邻值的元素。
     * 给你一个整数数组nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
     * 你可以假设nums[-1] = nums[n] = -∞ 。
     * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
     */
    public static int findPeakElement(int[] nums) {
         return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 1, 2, 3};
        int i = cycleBinarySearch(nums, 4);
        System.out.println(i);
    }
}
