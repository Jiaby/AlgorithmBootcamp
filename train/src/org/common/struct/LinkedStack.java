package org.common.struct;

import java.util.Arrays;

/**
 * 栈的链表实现
 */
public class LinkedStack<T> {

    class LinkedNode{
        T value;
        LinkedNode next;

        LinkedNode(T value, LinkedNode next) {
            this.value = value;
            this.next = next;
        }
    }
    private int count;      // 元素个数

    private LinkedNode top; //栈顶元素

    public void push(T e) {
        top = new LinkedNode(e, top);
        count ++;


    }

    public T pop() {
        if (top == null) return null;
        T value = top.value;
        LinkedNode next = top.next;
        top.next = null;
        top = next;
        count --;
        return value;
    }

    public boolean isEmpty() {
        return count <= 0;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        LinkedNode n = top;
        while (n != null) {
            sb.append(n.value).append(',');
            n = n.next;
        }
        if (sb.length() > 1)
            sb.setLength(sb.length() - 1);
        sb.append(']');
        return sb.toString();
    }


}
