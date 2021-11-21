package org.homework.firstweek.linkedList;

/**
 * @author liuyue
 * @Description: 链表节点类
 * @date 2021/11/18 23:22
 */
public class ListNode {
    public int value;
    public ListNode next;
    public ListNode pre;

    public ListNode() { }

    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }

    public ListNode(int value, ListNode next, ListNode pre) {
        this.value = value;
        this.next = next;
        this.pre = pre;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public ListNode getPre() {
        return pre;
    }

    public void setPre(ListNode pre) {
        this.pre = pre;
    }

}
