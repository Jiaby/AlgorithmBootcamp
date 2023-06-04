package org.common.suanfa;

import java.util.ArrayDeque;

/**
 * 单调栈相关算法
 * 所谓单调栈即栈中元素单调递增或递减
 */
public class MonotonicStack {

    /**
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     * 解题思路：
     * 1.暴力解法：每个柱子都向左向右扩展直至遇到比它低的柱子或者边界，然后算出面积更新答案。n个柱子每个柱子最坏情况扩展n下，时间复杂度为O(n2)
     * 2.借助单调性。从第一个柱子开始，如果第二个柱子比它高，那么第一个柱子的宽度就可以进行扩展，如果比它低则代表第一个柱子能扩展的宽度已经确定，那么以它为
     * 高度的矩形面积也就确定了。这个时候我们就可以得到一个备选答案。那么试想如果柱子是单调递增的情况下，前面的柱子宽度可以一直向右扩展，直到数组边界或者遇到低于等于它的柱子。我们称为达到扩展边界
     * 当达到扩展边界时我们可以从右到左依次求出之前柱子所代表高度的对应区域面积。比如最右边柱子I所勾勒矩形的宽度就是1，面积就是它的高度乘1，它左边的柱子勾勒矩形宽度就是2，高度是其本身高度，再左边宽度就是3以此类推……
     * 然后将新柱子在当前所勾勒的矩形放入栈中，它的高度是柱子的高度，宽度是前面所有高于等于它的柱子的数量。
     * 这种从左到右遍历数组，从右往左计算面积很符合栈的先进后出特性，所以我们可以使用一个栈来保存还未达到扩展边界的柱子所勾勒的区域，当达到扩展边界时从栈顶依次弹出并计算勾勒出的最大面积，求出其中的最大值。最后将最大面积返回
     * 补充说明：为什么是遇到低于等于它的柱子时就算达到扩展边界而不只是低于它的柱子，明明和它高度相同的柱子是可以扩展矩形的。因为我们在栈中放入的是一个个矩形，遇到边界情况时我们其实是将前面的柱子删除只保留了不高于新柱子的高度
     * 来扩充新柱子的宽度。当两个柱子高度相等时，删掉前一个柱子让后面的新柱子扩充宽度，才能求出该高度可勾勒的最大矩形面积。
     *
     * 从本题理解到的单调栈的解法思想：单调栈的核心就在于其单调二字，我们算法的目的就是维护栈的单调性，当遇到破坏单调性的情况时，我们采用出栈操作直至栈可以保持其单调性。在维护栈单调性的过程中求得我们需要的结果
     * @url <a href="https://leetcode.cn/problems/largest-rectangle-in-histogram/"/>
     */
    public int largestRectangleArea(int[] heights) {
        // 入参判断
        if (heights == null || heights.length == 0) return 0;
        // 单调栈，这个问题我们是要求面积，那么栈中就存放矩形的高度与宽度
        ArrayDeque<Rect> stack = new ArrayDeque<>();
        // 最终答案
        int ans = 0;
        for (int height : heights) {
            // 累加宽度，用来计算新柱子向左可扩展的宽度
            int accumulatedWidth = 0;
            // 栈不为空且无法维持单调递增，就出栈，并更新面积答案
            while (!stack.isEmpty() && stack.peek().height >= height) {
                Rect rect = stack.pop();
                // rect.width代表的是这个柱子向左可以扩展的最大宽度，而accumulated代表的是它向右可以扩展的最大宽度，两者相加就是这个最大矩形的宽度。
                // 这也是为什么遇到相等柱子是会出栈用新柱子的原因。这里需要好好理解
                accumulatedWidth += rect.width;
                // 更新答案，求当前柱子代表面积与之前的最大面积的最大值
                ans = Math.max(ans, rect.height * accumulatedWidth);
            }
            // 新矩形入栈
            stack.push(new Rect(accumulatedWidth + 1, height));
        }
        // 遍历完成后可能还会有一些元素没有出栈，因为它们还没有遇到比它们矮的柱子，所以需要继续遍历一下，并计算对应面积
        int accumulatedWidth = 0;
        while (!stack.isEmpty()) {
            Rect rect = stack.pop();
            accumulatedWidth += rect.width;
            ans = Math.max(ans, rect.height * accumulatedWidth);
        }
        return ans;
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
