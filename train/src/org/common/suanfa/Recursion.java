package org.common.suanfa;

/**
 * 递归算法练习题
 */
public class Recursion {
    /**
     * 斐波那契数列求解
     */
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }
}
