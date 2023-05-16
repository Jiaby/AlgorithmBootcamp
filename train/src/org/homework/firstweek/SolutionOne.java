package org.homework.firstweek;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author liuyue
 * @date 2021/11/16 22:20
 */
public class SolutionOne {
    /**
     * 加一
     * @link https://leetcode-cn.com/problems/plus-one/
     */
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] = digits[i] + 1;
                break;
            }
        }
        if (digits[0] == 0) {
            int[] result = new int[digits.length + 1];
            System.arraycopy(digits,0, result, 1, digits.length);
            result[0] = 1;
            return result;
        }
        return digits;
    }

    /**
     *合并有序链表
     * @link https://leetcode-cn.com/problems/merge-two-sorted-lists/
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            if (l1 == null)
                return l2;
            else
                return l1;
        }
        ListNode head = new ListNode();
        ListNode node = head;
        while (l1 !=  null && l2 != null) {
            if (l1.val <= l2.val) {
                node.next = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }
        node.next = l1 == null ? l2 : l1;
        return head.next;
    }

    /**
     * 最大矩形
     * @link https://leetcode-cn.com/problems/maximal-rectangle/
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int[] heights = new int[matrix[0].length];
        int maxArea = 0;
        for (int row = 0; row < matrix.length; row++) {
            //遍历每一列，更新高度
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == '1') {
                    heights[col] += 1;
                } else {
                    heights[col] = 0;
                }
            }
            //利用单调栈， 求条面积
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }

    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        int p = 0;
        while (p < heights.length) {
            //栈空入栈
            if (stack.isEmpty()) {
                stack.push(p);
                p++;
            } else {
                int top = stack.peek();
                //当前高度大于栈顶，入栈
                if (heights[p] >= heights[top]) {
                    stack.push(p);
                    p++;
                } else {
                    //保存栈顶高度
                    int height = heights[stack.pop()];
                    //左边第一个小于当前柱子的下标
                    int leftLessMin = stack.isEmpty() ? -1 : stack.peek();
                    //右边第一个小于当前柱子的下标
                    int RightLessMin = p;
                    //计算面积
                    int area = (RightLessMin - leftLessMin - 1) * height;
                    maxArea = Math.max(area, maxArea);
                }
            }
        }
        while (!stack.isEmpty()) {
            //保存栈顶高度
            int height = heights[stack.pop()];
            //左边第一个小于当前柱子的下标
            int leftLessMin = stack.isEmpty() ? -1 : stack.peek();
            //右边没有小于当前高度的柱子，所以赋值为数组的长度便于计算
            int RightLessMin = heights.length;
            int area = (RightLessMin - leftLessMin - 1) * height;
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }

    /**
     * 循环双端链表
     * @Link https://leetcode-cn.com/problems/design-circular-deque/
     */

    class MyCircularDeque {
        Deque<Integer> deque =new LinkedList<>();
        int capacity;
        public MyCircularDeque(int k) {
            capacity = k;
        }

        public boolean insertFront(int value) {
            if (isFull())
                return false;
            return deque.offerFirst(value);
        }

        public boolean insertLast(int value) {
            if (isFull())
                return false;
            return deque.offerLast(value);
        }

        public boolean deleteFront() {
            if (isEmpty()) return false;
            deque.removeFirst();
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) return false;
            deque.removeLast();
            return true;
        }

        public int getFront() {
            if (isEmpty()) return -1;
            return deque.peekFirst();
        }

        public int getRear() {
            if (isEmpty()) return -1;
            return deque.peekLast();
        }

        public boolean isEmpty() {
            return deque.isEmpty();
        }

        public boolean isFull() {
            return deque.size() == capacity;
        }
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode pre;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        public ListNode(int val, ListNode next, ListNode pre) {
            this.val = val;
            this.next = next;
            this.pre = pre;
        }
    }
}





