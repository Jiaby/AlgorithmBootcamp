package org.common.struct;

import java.util.Arrays;

/**
 * 数组实现循环队列带自动扩容
 */
public class ArrayQueue<T> {
    private Object[] items;
    private int size = INIT_SIZE;
    private int count;
    private int head;
    private int tail ;

    private static final int INIT_SIZE = 6; // 写小点方便测试扩容

    public void enqueue(T t) {
        if (items == null) items = new Object[INIT_SIZE];
        if (count == size) grow();
        items[tail] = t;
        if (tail == size - 1) tail = 0;
        else tail += 1;
        count ++;
    }

    public T dequeue() {
        if (count == 0) return null;
        T value = (T)items[head];
        items[head] = null;
        if (head == size - 1) head = 0;
        else head ++;
        count --;
        return value;
    }

    private void grow() {
        Object[] newItems = new Object[size * 2];
        int i = 0;
        do {
            newItems[i] = items[head];
            if (head == size - 1) head = 0;
            else head ++;
            i ++;
        } while (head != tail);
        head = 0;
        tail = count;
        size = newItems.length;
        items = newItems;
    }

    @Override
    public String toString() {
        return "ArrayQueue{" +
                "items=" + Arrays.toString(items) +
                ", size=" + size +
                ", count=" + count +
                ", head=" + head +
                ", tail=" + tail +
                '}';
    }
}
