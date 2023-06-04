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


    public static ListNode addTwoNumbersTwo(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        ListNode cur = null;
        int j = 0;
        while(l1 != null || l2 != null) {
            int i1 = l1 == null ? 0 : l1.value;
            int i2 = l2 == null ? 0 : l2.value;
            int r = i1 + i2 + j;
            j = r / 10;
            ListNode node = new ListNode(r % 10);
            node.next = cur ;
            cur = node;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        if(j > 0) {
            cur = new ListNode(j,cur);
        }
        return cur;
    }

    /**
     * 判断链表是否为回文链表
     * 思路：利用快慢指针来解决
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // fast不为null说明链表个数为奇数个.slow.next才是后半段的开始结点
        if (fast != null) {
            slow = slow.next;
        }
        // 反转链表
        ListNode  r = slow.next;
        while (r != null) {
            ListNode next = r.next;
            r.next = slow;
            slow = r;
            r = next;
        }
        fast = head;
        while (slow != null) {
            if (fast.value != slow.value) {
                return false;
            };
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    /**
     * 判断链表是否有环
     * 思路：快慢指针，如果有环两个指针一定会相遇
     */
    public static boolean hasCycle(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode fast = head, slow = dummy;
        while (fast != null && fast.next != null) {
            if (fast == slow) {
                return true;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }


    /**
     * 合并K个升序链表,迭代解法
     * 内存消耗小但时间复杂度高
     */
    public ListNode mergeKListsIterator(ListNode[] lists) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (lists != null && lists.length > 0) {
            int min = -1;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) continue;
                if (min == -1 || lists[i].value < lists[min].value) {
                    min = i;
                }
            }
            if (min == -1) break;
            cur.next = lists[min];
            cur = cur.next;
            if (lists[min].next != null) {
                lists[min] = lists[min].next;
            } else {
                lists[min] = null;
            }

        }
        return dummy.next;

    }

    /**
     * 合并k个升序列表递归解法
     * 递归解法采用分治思想，将k个列表分治，然后两两合并
     */
    public ListNode mergeKListsRecursion(ListNode[] lists) {
        if (lists == null ) return null;
        return mergeKListsHelp(lists, 0, lists.length - 1);
    }

    public ListNode mergeKListsHelp(ListNode[] lists, int left, int right) {
        if (left == right) return  lists[left];
        int mid = left + ((right - left) >> 1);
        ListNode leftNode = mergeKListsHelp(lists, left, mid);
        ListNode rightNode = mergeKListsHelp(lists, mid + 1, right);
        return mergeTwoList(leftNode, rightNode);
    }

    public ListNode mergeTwoList(ListNode left, ListNode right) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (left != null && right != null) {
            if (left.value < right.value) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        cur.next = left == null ? right : left;
        return dummy.next;
    }



    public static void main(String[] args) {
        ListNode l1 = Utils.arrayToList(new int[]{7, 2, 4, 3});
        ListNode l2 = Utils.arrayToList(new int[]{5, 6, 4});
        ListNode node = addTwoNumbersTwo(l1, l2);
        System.out.println(node);
    }
}
