package org.homework.firstweek.stackAndQueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author liuyue
 * @Description:
 * @date 2021/11/21 13:18
 */
public class TestInClassTwo {

    /**
     * 柱状图中最大的矩形
     * @link https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
     */
    public int largestRectangleArea(int[] heights) {
        int ans = 0;
        int[] newHeights = new int[heights.length + 1];
        System.arraycopy(heights, 0, newHeights, 0, heights.length);
        Stack<Rect> stack = new Stack<>();
        // for遍历每个元素
        for (int height : newHeights) {
            int accumulateWidth = 0;
            // 遇到破坏单调性的元素时，更新答案
            while (!stack.isEmpty() && stack.peek().height >= height) {
                // 不符合单调性的元素出栈
                Rect rect = stack.pop();
                // 保留它的宽度
                accumulateWidth += rect.width;
                // 更新答案
                ans = Math.max(ans, rect.height * accumulateWidth);
            }
            // 新元素入栈
            stack.push(new Rect(accumulateWidth + 1, height));
        }
        return ans;
    }

    /**
     * 接雨水
     * @link https://leetcode-cn.com/problems/trapping-rain-water/submissions/
     */
    public int trap(int[] height) {
        int ans = 0;
        Stack<Rect> stack = new Stack<>();
        for (int h : height) {
            int accumulateWidth = 0;
            // 单调递减性遇到破坏
            while (!stack.isEmpty() && stack.peek().height <= h) {
                // 这个re是作为底的矩形
                Rect re = stack.pop();
                int bottomHeight = re.height;
                accumulateWidth += re.width;
                if (stack.isEmpty()) continue; // 左边没有柱子无法留住水
                // 以bottom为底，一条一条计算
                Rect left = stack.peek();
                int up = Math.min(left.height, h);
                ans += accumulateWidth * (up - bottomHeight);
            }
            stack.push(new Rect(accumulateWidth + 1, h));
        }
        return ans;
    }

    /**
     * 滑动窗口最大值
     * @link https://leetcode-cn.com/problems/sliding-window-maximum/
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int[] ans = new int[nums.length - k + 1];
        int j = 0;
        // 下标递增
        for (int i = 0; i < nums.length; i++) {
            // 不在滑动窗口出队
            while (!deque.isEmpty() && deque.getFirst() <= i - k) deque.pollFirst();
            // 下标小且值小的直接出队
            while (!deque.isEmpty() && nums[deque.getLast()] <= nums[i]) deque.pollLast();
            deque.offerLast(i);
            if (i >= k - 1) {
                ans[j ++] = nums[deque.getFirst()];
            }
        }
        return ans;

    }

    public static void main(String[] args) {
        TestInClassTwo testInClassTwo = new TestInClassTwo();
        testInClassTwo.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);
    }

    class Rect {
        int width;
        int height;

        public Rect(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
}
