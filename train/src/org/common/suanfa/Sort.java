package org.common.suanfa;

import org.common.struct.Node;

import java.util.ArrayList;
import java.util.Arrays;

public class Sort {

    // 交换元素的辅助方法
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    /**
     * 插入排序，特点：原地排序算法，稳定排序、平均时间复杂度O(n)
     * 插入排序在数据量不大的情况下要比快速排序更快一些。。
     */
    public static int[] insertSort(int[] arr){
        if (arr.length <= 1) return arr;
        for (int i = 0; i < arr.length; i++) {
            // i的下标位于未排序区间的第一位, j位于已排序区间的最后一位
            int j = i - 1;
            int value = arr[i]; // value就是本次要放到插入正确位置的元素
            for (; j >= 0; j --) {
                // 发现区间元素比待插入元素大，则依次往后移动，最开始时候的j+1位置的元素就是value。这次要写>如果写成>=就破坏了插入排序的稳定性
                if (arr[j] > value) {
                    arr[j+1] = arr[j];
                } else break; //前半部分区间已经排好序了，如果j处元素没有i大，那么它前面的元素也不可能比i大
            }
            arr[j + 1] = value; //将value放入比它小的元素后面。上面遍历完后j指向的要么是 -1 要么就是比value小的元素下标
        }
        return arr;
    }

    // 冒泡排序
    public static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length <= 1) return arr;
        for (int i = 0; i < arr.length; i++) {
            boolean flag = true;
            // 初学容易搞不清楚的一个点就是这里为什么是j<arr.length-i-1。冒泡排序的核心思想是每次都将最大的元素顶到数组最后面。所以经过i次变历后，后面的i个元素已经是排好序的了，没必要再进行遍历比较
            for (int j = 0; j < arr.length - i - 1; j++) {
                // 只要发现前面的大于后面的就交换
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                    flag = false;
                }
            }
            if (flag) break;
        }
        return arr;
    }

    /**
     * 快速排序的核心思想是分区排序，每次找一个基准值，小于这个值的和大于这个值的分布在两侧区间内，然后将小区间递归进行排序
     * 这个是双指针排序的方法
     * @param l 左边界
     * @param r 右边界
     * @return
     */
    public static int[] quickSortWithTwoPoint(int[] a, int l, int r) {
        if (l >= r) return a;
        // 选最后一个元素做基准值
        int pv = a[r];
        int ll = l;
        int rr = r;
        // 这种实现方法类似于拔萝卜。先把基准值这个萝卜A拔起来，这样就空出来了一个坑A
        // 然后从另一边开始遍历，找到 大于基准值的萝卜B，把这个萝卜放到原来基准值的位置A
        // 接着从尾部开始遍历，找到小于基准值的萝卜C，把这个萝卜拔出来放到萝卜B原来的位置B
        // 直到两个指针相遇，结束循环，此时剩下的那个坑位C，就把基准萝卜A放进去，这样它的左边都是比它小的，右边都是比它大的
//        while (ll < rr) {
//            while (ll < rr && a[ll] <= pv) {
//                ll ++;
//            }
//            a[rr] = a[ll];
//            while (ll < rr && a[rr] >= pv) {
//                rr --;
//            }
//            a[ll] = a[rr];
//
//        }
//        a[ll] = pv;
        // 还有一种两个指针同时遍历，然后直接进行交换，最后两个指针相遇的位置就可以放基准值A
        while (ll < rr) {
            while (ll < rr && a[ll] <= pv) {
                ll ++;
            }
            while (ll < rr && a[rr] >= pv) {
                rr --;
            }
            // ll < rr 说明两个指针还没相遇，可以直接交换然后接着遍历。
            // 如果相遇了，证明这个坑上得值要么大于等于基准值，要么这里就是基准值
            if (ll < rr) swap(a, ll, rr);
        }
        // 把基准值和相遇位置的值进行交换
        swap(a, r, ll);

        quickSortWithTwoPoint(a, l, ll - 1);
        quickSortWithTwoPoint(a, ll + 1, r);
        return a;
    }

    // 这种快排的做法是单边移动，以i作为基准值的下标位置，如果发现有比基准值小值就放到i位置，
    // 将i后移，这样一次遍历之后所有小于基准值的元素都在i元素的前面，再将i处元素 与基准元素交换，就完成了区间的划分
    public static int[] quickSort(int[] a, int p, int r) {
        if (p >= r) return a;
        // 选择pivot
        int value = a[r];
        // 创建游标i,用以区分小于value区间与大于value区间
        int i = p;
        //此处为节省内存空间利用了类似选择排序的思想，对于元素的插入操作用交换来代替搬移。
        for (int j = p; j <= r -1; j ++){
            //小于value 则代表应放入i游标前中
            if (a[j] <= value){
                // 进行交换然后i++
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i ++;
            }
            // 大约value则应在大于value区间故i不移动
        }
        //将分区点的元素放到i的位置
        swap(a, i, r);
        // 递归继续
        quickSort(a, p , i-1);
        quickSort(a, i+1, r);
        return a;
    }

    // 选择排序，思路类似插入排序，也是将数组分为待排序区间和已排序区间，但不同的是，插入排序时将比已排序区间最大值小的元素插入到已排序区间中，
    // 而选择排序是每次都将未排序区间最小的元素放到已排序区间的末尾
    public static int[] selectSort(int[] a) {
        if (a == null) return a;
        // i作为已排序区间的末尾只要遍历到a.length-2这个位置就可以了,最后一个位置由j进行判断
        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = i;
            // 找出最小值
            for (int j = i + 1; j < a.length; j ++){
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            swap(a, i, minIndex);
        }
        return a;
    }

    /**
     * 归并排序，将数组分成多个小数组，小数组排序后返回给上一层进行合并
     */
    public static int[] mergeSort(int[] a, int l, int r) {
        if (l >= r) return a;
        int mid = (l + r) / 2;
        mergeSort(a, l, mid);
        mergeSort(a, mid + 1, r);
        return mergeArray(a, l, mid, r);

    }

    private static int[] mergeArray(int[] a, int l, int mid, int r) {
        int[] tempArray = new int[r - l + 1];
        int ll = l;
        int rr = mid + 1;
        for (int i = 0; i < tempArray.length; i++) {
            if (rr > r || (ll <= mid && a[ll] <= a[rr])) {
                tempArray[i] = a[ll];
                ll ++;
            } else if (ll > mid || (rr <= r && a[rr] <= a[ll])) {
                tempArray[i] = a[rr];
                rr ++;
            }
        }
        int i = l;
        for (int t : tempArray) {
            a[i] = t;
            i ++;
        }
        return a;
    }

    public static void main(String[] args) {
        int[] params = {1, 3, 4, 2, 6, 4, 5, 9 ,12, 10, 254, 16};
//        int[] params = {1, 3, 2, 4, 11, 6};
//        int[] sorted = insertSort(params);
//        int[] sorted = bubbleSort(params);
//        int[] sorted = quickSortWithTwoPoint(params, 0, params.length - 1);
//        int[] sorted = quickSort(params, 0, params.length - 1);
//        int[] sorted = selectSort(params);
        int[] sorted = mergeSort(params, 0, params.length - 1);
        System.out.println(Arrays.toString(sorted));

    }
}
