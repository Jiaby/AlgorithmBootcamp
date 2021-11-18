package org.homework.firstweek.array;

import java.util.Arrays;

/**
 * @author jiaby
 * @Title: 变长数组
 * @date 2021/11/18 22:56
 */
public class ResizableArray {
    private int[] arr;
    private int size;
    private int capacity;
    private int index;

    public ResizableArray() {
        arr = new int[16];
        size = 0;
        capacity = 16;
    }

    public ResizableArray(int capacity) {
        arr = new int[capacity];
        size = 0;
        this.capacity = capacity;
    }

    public ResizableArray(int[] arr) {
        this.arr = Arrays.copyOf(arr, arr.length);
        size = this.arr.length;
        index = size - 1;
        capacity = this.arr.length;
    }


    public void pushBack(int a) {
        if (size == capacity) {
            expansion();
        }
        arr[++index] = a;
        size ++;
    }

    public int popBack() {
        int a = arr[index];
        size --;
        if (index >= 0)
            index --;
        if (size <= capacity / 2) {
            shrink();
        }
        return a;
    }

    private void expansion() {
        int[] newArr = new int[capacity * 2];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        arr = newArr;
        capacity = arr.length;
    }

    private void shrink() {
        int[] newArr = new int[capacity / 2];
        System.arraycopy(arr, 0, newArr, 0, newArr.length);
        arr = newArr;
        capacity = arr.length;
    }

    public static void main(String[] args) {
        int[] s = new int[] {1,2,3,4,5};
        ResizableArray resizableArray = new ResizableArray(s);
        int p = resizableArray.popBack();
        int q = resizableArray.popBack();
        int x = resizableArray.popBack();
        resizableArray.pushBack(5);
        resizableArray.pushBack(6);
    }
}
