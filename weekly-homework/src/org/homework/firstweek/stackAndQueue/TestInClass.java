package org.homework.firstweek.stackAndQueue;

import java.util.*;

/**
 * @author liuyue
 * @Description:
 * @date 2021/11/20 20:54
 */
public class TestInClass {

    public boolean isValid(String s) {
        if (s == null) return true;
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        stack.peek();
        for (char c : chars) {
            if (c == '(' || c == '{' || c == '[')
                stack.push(c);
            else {
                if (stack.isEmpty()) return false;
                Character left = stack.pop();
                switch (c) {
                    case ')' :
                        if (left != '(') return false;
                        break;
                    case '}' :
                        if (left != '{') return false;
                        break;
                    case ']' :
                        if (left != '[') return false;
                        break;
                }
            }
        }
        if (!stack.isEmpty()) return false;
        return true;
    }

    /**
     * 后缀表达式
     * @link https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/submissions/
     */
    public int evalRPN(String[] tokens) {
        Stack<String> strings = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (isOpChar(tokens[i])) {
                int x = Integer.parseInt(strings.pop());
                int y = Integer.parseInt(strings.pop());
                strings.push(String.valueOf(calc(y, x, tokens[i])));
            } else {
                strings.push(tokens[i]);
            }
        }
        return Integer.parseInt(strings.pop());
    }

    /**
     * 基本计算器I
     * @link https://leetcode-cn.com/problems/basic-calculator/
     */
    public int calculateOne(String s) {
        s += " ";
        Stack<Character> ops = new Stack<>();
        StringBuilder num = new StringBuilder();
        ArrayList<String> token = new ArrayList<>();
        boolean needZero = true;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                num.append(c);
                needZero = false;
                continue;
            } else {
                // 遇到符号先清空num里的数字让他们进token
                if (num.length() > 0) {
                    token.add(num.toString());
                    num.delete(0, num.length());
                }
            }
            if (c == ' ') continue;
            if (c == '(') {
                ops.push(c);
                needZero = true;
                continue;
            }
            if (c == ')') {
                while (ops.peek() != '(') {
                    token.add(String.valueOf(ops.pop()));
                }
                ops.pop();
                needZero = false;
                continue;
            }
            if ((c == '+' || c == '-') && needZero) {
                token.add("0");
            }
            int currRank = getRank(c);
            while (!ops.isEmpty() && getRank(ops.peek()) >= currRank) {
                Character pop = ops.pop();
                token.add(String.valueOf(pop));
            }
            ops.push(c);
            needZero = true;
        }
        while (!ops.isEmpty()) {
            token.add(String.valueOf(ops.pop()));
        }
        return evalRPN(token.toArray(new String[]{}));
    }

    /**
     * 基本计算器II
     * @link https://leetcode-cn.com/problems/basic-calculator/
     */
    public int calculateTwo(String s) {
        s += " ";
        Stack<Character> ops = new Stack<>();
        StringBuilder num = new StringBuilder();
        ArrayList<String> token = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                num.append(c);
                continue;
            } else {
                // 遇到符号先清空num里的数字让他们进token
                if (num.length() > 0) {
                    token.add(num.toString());
                    num.delete(0, num.length());
                }
            }
            if (c == ' ') continue;
            int currRank = getRank(c);
            while (!ops.isEmpty() && getRank(ops.peek()) >= currRank) {
                Character pop = ops.pop();
                token.add(String.valueOf(pop));
            }
            ops.push(c);
        }
        while (!ops.isEmpty()) {
            token.add(String.valueOf(ops.pop()));
        }
        return evalRPN(token.toArray(new String[]{}));
    }

    int getRank(char c) {
        if (c == '+' || c == '-') return 1;
        if (c == '*' || c == '/') return 2;
        return 0;
    }

    int calc(int x, int y, String op) {
        if (op.equals("+")) return x + y;
        if (op.equals("-")) return x - y;
        if (op.equals("*")) return x * y;
        return x / y;
    }

    boolean isOpChar(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    public static void main(String[] args) {
        TestInClass testInClass = new TestInClass();
        System.out.println(testInClass.calculateOne("(1+(4+5+2)-3)+(6+8)"));
    }
}

class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack;
    public MinStack() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }

    public void push(int val) {
        stack.push(val);
        if(minStack.isEmpty()){
            minStack.push(val);
        } else {
            minStack.push(Math.min(minStack.peek(), val));
        }

    }

    public void pop() {
        int i = stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
