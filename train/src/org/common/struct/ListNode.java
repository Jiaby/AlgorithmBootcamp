package org.common.struct;

import java.util.Objects;

/**
 * @author liuyue
 * @Description: 链表节点类
 * @date 2021/11/18 23:22
 */
public class ListNode {
    public int value;
    public ListNode next;
    public ListNode pre;
    public int idx;

    public ListNode() { }

    public ListNode(int value) {
        this.value = value;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListNode listNode = (ListNode) o;

        if (value != listNode.value) return false;
        if (idx != listNode.idx) return false;
        if (!Objects.equals(next, listNode.next)) return false;
        return Objects.equals(pre, listNode.pre);
    }

    @Override
    public int hashCode() {
        int result = value;
        result = 31 * result + (next != null ? next.hashCode() : 0);
        result = 31 * result + (pre != null ? pre.hashCode() : 0);
        result = 31 * result + idx;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("ListNode{");
        ListNode cur = this;
        while (cur != null) {
            s.append(cur.value);
            s.append(",");
            cur = cur.next;
        }
        s.replace(s.length() - 1, s.length(), "}");
        return s.toString();
    }
}
