package org.train.firstweek.linkedList;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author liuyue
 * @Description: 练习
 * @date 2021/11/18 23:21
 */
public class TestInClass {

    /**
     * 反转链表
     * @link https://leetcode-cn.com/problems/reverse-linked-list/submissions/
     */
    public ListNode reverseList(ListNode head) {
        ListNode last = null;
        while (head != null) {
            ListNode nextNode = head.next;
            head.next = last;
            last = head;
            head = nextNode;
        }
        return last;
    }

    /**
     * k个一组反转链表
     * @link https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode protect = new ListNode();
        ListNode last = protect;
        // 1.分组遍历
        while (head != null) {
            // 一组的开头head 结尾end
            ListNode end = getEnd(head, k);
            if (end == null) break;
            ListNode nextGroupHead = end.next;
            // 2.一组内部反转
            reverseList(head, nextGroupHead);
            // 3.更新组之间的指向关系
            // 因为在第二步进行了组内反转，所以head此时是组内最后一个元素，end是组内第一个元素，last代表上一组内的最后一个元素
            last.next = end;
            // 反转链表固定模版(注意此时的head其实是tail)
            head.next = nextGroupHead;
            last = head;
            head = nextGroupHead;
        }
        return protect.next;
    }

    // 返回走k - 1步后的点
    // null代表这一组不够k个
    private ListNode getEnd(ListNode head, int k) {
        while (head != null) {
            k --;
            if (k == 0) return head;
            head = head.next;
        }
        return null;
    }

    // 反转链表在stop停止
    public void reverseList(ListNode head, ListNode stop) {
        ListNode last = null;
        while (head != stop) {
            ListNode nextNode = head.next;
            head.next = last;
            last = head;
            head = nextNode;
        }
    }


    // 判断链表是否有环
    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }


    /**
     * 判断是否有环且返回环的第一个结点
     * meet = 相遇结点， st = 环的第一个结点，l = head -> st, p = st -> meet, r = 环长
     * 因为快指针速度是慢指针2倍， 慢指针走的距离为 l+p 那么快指针就是 2(l+p)
     * 快指针速度还可以表示为 l + p + k * r 得 l = k * r - p  = (k - 1) r + (r - p) 得出 l 与 (r-p) 对于整圈数同余
     * r - p 又刚好是 慢指针未走完的环形距离
     * 相遇时让慢指针 继续， head开始走，slow = head时即是st结点
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode pre = head;
                while (pre != slow) {
                    slow = slow.next;
                    pre = pre.next;
                }
                return pre;
            }
        }
        return null;
    }

    /**
     * 邻值查找
     * @link https://www.acwing.com/problem/content/description/138/
     */
    public int[][] neighborLookup(int n, int[] num) {
        Integer[] rk = new Integer[n];
        for (int i = 0; i < rk.length; i++) {
            rk[i] = i;
        }
        // 对rk排序，以num[rk[i]]的大小来排序
        Arrays.sort(rk, Comparator.comparingInt(a -> num[a]));
        ListNode[] pos = new ListNode[n];
        ListNode head = new ListNode();
        ListNode tail = new ListNode();
        head.next = tail;
        head.value = Integer.MIN_VALUE;
        tail.pre = head;
        tail.value = Integer.MAX_VALUE;
        for (int i = 0; i < pos.length; i++) {
            pos[rk[i]] = addNode(tail.pre, num[rk[i]], rk[i]);
        }
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i --) {
            ListNode curr = pos[i];
            if (num[i] - curr.pre.value <= curr.next.value - num[i]) {
                ans[i] = curr.pre.idx;
            } else {
                ans[i] = curr.next.idx;
            }
            deleteNode(curr);
        }
        for (int i = 0; i < n; i ++){
            System.out.println(Math.abs(num[ans[i]] - num[i]) + " " + ans[i]);
        }
        return null;
    }

    private void deleteNode(ListNode node) {
        ListNode preNode = node.pre;
        ListNode nextNode = node.next;
        preNode.next = nextNode;
        nextNode.pre = preNode;
        node.next = null;
        node.pre = null;
    }

    private ListNode addNode(ListNode node, int val, int idx) {
        ListNode listNode = new ListNode(val, node.next, node);
        listNode.idx = idx;
        node.next.pre = listNode;
        node.next = listNode;
        return listNode;
    }

    public static void main(String[] args) {
        int[] num = new int[]{1,3,4,5,6,2,8,7};
        TestInClass testInClass = new TestInClass();
        testInClass.neighborLookup(8, num);
    }
}
