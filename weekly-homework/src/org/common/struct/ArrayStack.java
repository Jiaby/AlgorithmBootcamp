package org.common.struct;

import java.util.Arrays;

/**
 * 数组方式实现的栈
 */
public class ArrayStack<T> {
    private Object[] item;
    private static final int INIT_SIZE = 8;      // 初始化大小
    private static final int DEFAULT_LOAD_FACTOR = 2;        //扩容因子
    private int size;       // 数组大小
    private int count;      // 元素个数
    public ArrayStack() {
        this.size = INIT_SIZE;
    }

    public ArrayStack(int initCap){
        if (initCap <= 0)
            initCap = INIT_SIZE;
        this.size = initCap;
        this.count = 0;
    }

    public void push(T e) {
        if (item == null) {
            item = new Object[size];
        }
        item[count] = e;
        if (item.length * 0.75 <= count) {
            grow();
        }
        count ++;
    }

    public T pop() {
        if (count == 0) return null;
        Object e = item[count - 1];
        item[count - 1] = null;
        count --;
        return (T) e;
    }

    private void grow() {
        Object[] newItems = Arrays.copyOf(item, size * 2);
        item = newItems;
        size = newItems.length;
    }

    @Override
    public String toString() {
        return "ArrayStack{" +
                "item=" + Arrays.toString(item) +
                ", size=" + size +
                ", count=" + count +
                '}';
    }
}
