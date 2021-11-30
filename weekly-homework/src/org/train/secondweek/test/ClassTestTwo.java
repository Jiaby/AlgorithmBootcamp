package org.train.secondweek.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuyue
 * @Description: 随课练习2
 * @date 2021/11/24 22:18
 */
public class ClassTestTwo {

    /**
     * 串联所有单词的子串
     * @link https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/
     */

    public List<Integer> findSubstring(String s, String[] words) {
        int k = words[0].length();
        int total = k * words.length;
        HashMap<String, Integer> wordsMap = new HashMap<>();
        for (String word : words) {
            wordsMap.put(word, wordsMap.containsKey(word) ? wordsMap.get(word) + 1 : 1);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (i + total > s.length()) break;
            String subStr = s.substring(i, i + total);
            if (valid(subStr, wordsMap, k)) {
                list.add(i);
            }
        }
        return list;
    }

    private boolean valid(String s, Map<String, Integer> wordsMap, int k) {
        HashMap<String, Integer> mapA = new HashMap<>();

        for (int i = 0; i < s.length(); i += k) {
            String substring = s.substring(i, i + k);
            mapA.put(substring, mapA.containsKey(substring) ? mapA.get(substring) + 1 : 1);
        }
        return mapA.equals(wordsMap);
    }


    public static void main(String[] args) {
        ClassTestTwo classTestTwo = new ClassTestTwo();
        int[][] bookings = new int[][]{{1,2,10},{2,3,20},{2,5,25}};
        classTestTwo.corpFlightBookings(bookings, 5);
    }

    /**
     * 航班预计统计
     * @link https://leetcode-cn.com/problems/corporate-flight-bookings/
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] delta = new int[n + 2];
        Arrays.fill(delta, 0);
        // 原数组字段l~r的加、减一个数，等于其差分数组下标l处加、减一个数和r+1处减、加一个数
        for (int[] booking : bookings) {
            int first = booking[0], last = booking[1], seats = booking[2];
            delta[first] = delta[first] + seats;
            delta[last + 1] = delta[last + 1] - seats;
        }
        int[] sum = new int[n];
        sum[0] = delta[1];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = delta[i + 1] + sum[i - 1];
        }
        return sum;
    }
}

/**
 * @link https://leetcode-cn.com/problems/range-sum-query-2d-immutable/
 * 二维区域和检索 - 矩阵不可变
 */
class NumMatrix {
    int[][] s;

    public NumMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        s = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 二维数组前缀和公式
                s[i][j] = s[i-1][j] + s[i][j-1] - s[i-1][j-1] + matrix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        row1 ++; col1 ++; row2 ++; col2 ++;
        return s[row2][col2] - s[row2][col1 - 1] - s[row1 - 1][col2] + s[row1 - 1][col1 - 1];
    }
}
