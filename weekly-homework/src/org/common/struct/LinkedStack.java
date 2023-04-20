package org.common.struct;

import java.util.Arrays;

/**
 * 栈的链表实现
 */
public class LinkedStack<T> {

    class LinkedNode{
        T value;
        LinkedNode prev;
        LinkedNode next;

        LinkedNode(T value) {
            this.value = value;
        }
    }
    private int count;      // 元素个数

    private LinkedNode head;

    private LinkedNode tail;

    public void push(T e) {
        LinkedNode cur = new LinkedNode(e);
        if (tail == null) {
            tail = cur;
            tail.prev = head;
        }
        if (head == null) {
            head = new LinkedNode(null);
            head.next = cur;
        } else {
            tail.next = cur;
            cur.prev = tail;
            tail = cur;
        }
    }

    public T pop() {
       if (tail == null) return null;
       T value = tail.value;
        LinkedNode n = tail;
        tail = tail.prev;
        tail.next = null;
        n.prev = null;
        return value;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        LinkedNode n = head.next;
        while (n != null) {
            sb.append(n.value).append(',');
        }
        if (sb.length() > 1)
            sb.deleteCharAt(sb.length() - 1);
        sb.append(']');
        return sb.toString();
    }


}
