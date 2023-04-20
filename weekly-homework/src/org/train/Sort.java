package org.train;

import java.util.Arrays;

public class Sort {
    public static int[] insertSort(int[] arr){
        if (arr.length <= 1) return arr;
        int n = arr.length;
        for (int i  = 0; i < n; i ++){
            int value = arr[i];
            int j = i - 1;
            for (; j >= 0; j --) {
                if (arr[j] > value) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = value;
        }
        return arr;
    }



    public static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length <= 1) return arr;
        for (int i = 0; i < arr.length; i ++ ){
            boolean flag = true;
            for (int j = 0; j < arr.length -i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    arr[j] = arr[j] ^ arr[j+1];
                    arr[j+1] = arr[j] ^ arr[j+1];
                    arr[j] = arr[j] ^ arr[j+1];
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        return arr;
    }

    public static int[] quickSortTwo(int[] a, int l, int r) {
        if (l < r) {
            int i,j,x;
            i = l;
            j = r;
            x = a[i];
            while (i < j) {
                while(i < j && a[j] > x)
                    j--; // 从右向左找第一个小于x的数
                if(i < j)
                    a[i++] = a[j];
                while(i < j && a[i] < x)
                    i++; // 从左向右找第一个大于x的数
                if(i < j)
                    a[j--] = a[i];
            }
            a[i] = x;
            quickSort(a, l, i-1); /* 递归调用 */
            quickSort(a, i+1, r); /* 递归调用 */
        }
        return a;
    }

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
        int temp = a[i];
        a[i] = a[r];
        a[r] = temp;
        // 递归继续
        quickSort(a, p , i-1);
        quickSort(a, i+1, r);
        return a;
    }

    public static void main(String[] args) {
        int[] params = {1, 3, 4, 2, 6, 12, 8};
//        int[] sorted = insertSort(params);
//        int[] sorted = bubbleSort(params);
        int[] sorted = quickSort(params, 0, params.length - 1);
//        Arrays.sort(params);
        System.out.println(Arrays.toString(sorted));
    }
}
