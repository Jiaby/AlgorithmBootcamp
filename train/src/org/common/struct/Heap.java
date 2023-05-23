package org.common.struct;


import org.common.Utils;

public class Heap {

    public static void buildHeap(int[] a, int n) {
        for (int i = n / 2; i >= 1; -- i) {
            heapify(a, n, i);
        }
    }

    public static void heapify (int[] a, int n, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 < n && a[i*2] > a[maxPos]) maxPos = i * 2;
            if (i * 2 + 1 < n && a[i * 2 + 1] > a[maxPos]) maxPos = i * 2 + 1;
            if (maxPos == i) break;
            Utils.swap(a, i, maxPos);
            i = maxPos;
        }
    }
}
