package org.common.suanfa;

import org.common.Utils;
import org.common.struct.ListNode;

public class ListRelevant {

    /**
     * 反转单链表
     * @param head 头结点
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode l = head;
        ListNode r = l.next;
        l.next = null;
        while (r != null) {
            ListNode next = r.next;
            r.next = l;
            l = r;
            r = next;
        }
        return l;
    }

    /**
     * 要求反转left位置到right位置的链表结点，用头插法来解决
     * @param head 头结点
     * @param left 左边界位置
     * @param right 右边界位置
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        // 题目要求是单链表，所以按单链表去操作
        if (head == null ) return head;
        // 定义一个前置结点解决left是头结点的情况
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode leftNode = pre.next;
        for (int i = 0; i <  right - left; i++) {
            ListNode next = leftNode.next;
            leftNode.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }

        return dummy.next;
    }

    /**
     * 两数相加，两个链表逆序存储了对应数字，每个结点存储一位数字，头结点是个位数字，依次向上
     * @param l1 数字1，举例：2->4->3 代表数字342
     * @param l2 数字2，举例：5->6->4 代表数字465
     * @return 按上面举例数字返回应该是 7->0->8 代表两个数字相加结果为807
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode n1 = l1;
        ListNode n2 = l2;
        // 上一个结点计算结果的进位值
        int m = 0;
        ListNode cur = result;
        while (n1 != null || n2 != null) {
            int i1 = 0, i2 = 0;
            if (n1 != null) {
                i1 = n1.value;
                n1 = n1.next;
            }
            if (n2 != null) {
                i2 = n2.value;
                n2 = n2.next;
            }
            int r = i1 + i2 + m;
            m = r/10;
            cur.next = new ListNode(r % 10, null);
            cur = cur.next;
        }
        if (m > 0) {
            cur.next = new ListNode(m, null);
        }
        return result.next;
    }




    public static void main(String[] args) {
        ListNode l1 = Utils.arrayToList(new int[]{2, 4, 3});
        ListNode l2 = Utils.arrayToList(new int[]{5, 6, 4});
        ListNode node = addTwoNumbers(l1, l2);
        System.out.println(node);
    }
}
