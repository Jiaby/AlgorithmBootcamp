package org.common.suanfa;

public class Dtguihua {
    /**
     * 爬楼梯，一共n阶，每次可爬一阶或二阶，一共多少种爬法
     * 思路：爬第n阶楼梯的方法数=第n-1阶楼梯方法数+第n-2阶方法数
     */
    public static int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int[] a = new int[n + 1];
        a[0] = 1;
        a[1] = 1;
        for (int i = 2; i <= n; i++) {
            a[i] = a[i - 1] + a[i - 2];
        }
        return a[n];
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(3));
    }

}
