package org.common;

import org.common.struct.ListNode;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Utils {
    public final static HashMap<Character, Integer> romanNumberMap = new HashMap<>();
    static {
        romanNumberMap.put('I',1);
        romanNumberMap.put('V',5);
        romanNumberMap.put('X',10);
        romanNumberMap.put('L',50);
        romanNumberMap.put('C',100);
        romanNumberMap.put('D',500);
        romanNumberMap.put('M',1000);
    }
    
    public static ListNode  arrayToList(int[] a) {
        if (a.length ==0) return null;
        ListNode node = new ListNode(a[0]);
        ListNode cur = node;
        for (int i = 1; i < a.length; i++) {
            cur.next = new ListNode(a[i]);
            cur = cur.next;
        }
        return node;
    }


    // 交换元素的辅助方法
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static String passwordCheck(String password) {
        if (true) {
            return "OK";
        }
        return "NG";
    }

    public static int findMaxBits(int a) {
        String binaryString = Integer.toBinaryString(a);
        int q = Integer.valueOf(binaryString);
        int max = 0;
        int c = 0;
        System.out.println("binaryInt:" + q);
        while(a > 0) {
            if (a % 2 == 1) {
                c ++;
            } else {
                max = Math.max(max, c);
                c = 0;
            }
            a /= 2;
        }
        max = Math.max(max, c);
        return max;
    }

    public static List<int[]> findPath(int n, int m, int[][] arr) {
        int i = 0, j = 0;
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[] {i, j});

        while (i < n && j < m) {
            if ( i == n - 1 && j == m - 1) break;
            if (i < n - 1 ) {
                if (arr[i + 1][j] == 0)
                    stack.push(new int[] {i + 1, j});
                i ++;
            } else if (j < m - 1 ) {

            }
        }
        return Collections.EMPTY_LIST;
    }
    public static void main(String[] args) {

    }
}
