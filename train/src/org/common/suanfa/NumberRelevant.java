package org.common.suanfa;

import org.common.Utils;

import java.util.HashMap;

/**
 * 有关数字的算法题
 */
public class NumberRelevant {

    /**
     * 判断一个数字是否为回文数字，比如121是回文数字，123不是回文数字。
     */
    public boolean isPalindrome(int x) {
        // 负数肯定不是回文数字
        if (x < 0) {
            return false;
        }
        int l = 1;
        int tmp = x;
        while (tmp >= 10) {
            l *= 10;
            tmp /= 10;
        }
        while (x!=0) {
            // 最高位与个位不相等则不是回文数字
            if (x % 10 != x / l) {
                return false;
            }
            // 为了去掉数字的最高位和最低位
            x = x % l / 10;
            // 去掉两位后l除以100，l代表数字的位数*10
            l /= 100;
        }
        return true;
    }

    /**
     * 罗马数字转阿拉伯数字，罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
     * 同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
     * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
     * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
     * C可以放在D(500) 和M(1000) 的左边，来表示400和900。
     */



    public static int romanToInt(String s) {
        if (s == null || s.length() == 0) return 0;
        HashMap<Character, Integer> map = Utils.romanNumberMap;
        char[] chs = s.toCharArray();
        int result = 0;
        for (int i = 0; i < chs.length;) {
            // 如果是那六种特殊情况 则两个罗马数字改为一个罗马数字,六种特殊情况都是左边的数字小于右边
            if (i < chs.length - 1 && map.get(chs[i + 1]) > map.get(chs[i])) {
                char romanChar = chs[i + 1];
                if (romanChar == 'V') {
                    result += 4;
                } else if (romanChar == 'X') {
                    result += 9;
                } else if (romanChar == 'L') {
                    result += 40;
                } else if (romanChar == 'C') {
                    result += 90;
                } else if (romanChar == 'D') {
                    result += 400;
                } else if (romanChar == 'M') {
                    result += 900;
                }
                i += 2;
            } else {
                result = result + map.get(chs[i]);
                i ++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("IVXXCM"));
    }
}
