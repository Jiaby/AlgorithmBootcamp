package org.train.fourthweek;

import org.common.struct.ListNode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liuyue
 * @Description: 深度优先遍历
 * @date 2021/12/11 19:13
 */
public class DFSTrain {

    /**
     * @link https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回
     * 给出数字到字母的映射同九键键盘
     */
    public List<String> letterCombinations(String digits) {
        // 关注本题中的状态，变得是选取哪一个字符，即Index
        this.digits = digits;
        if (digits == null || digits.equals("")) return ans;
        dfs(0, "");
        return ans;
    }

    private void dfs(int index, String str) {
        if (index == digits.length()) {
            ans.add(str);
            return;
        }
        char c = digits.charAt(index);
        for (String s : keyboard.get(c)) {
            str += s;
            dfs(index + 1, str);
            str = str.substring(0, index);
        }
    }

    private String digits;
    private List<String> ans = new LinkedList<>();
    static Map<Character, List<String>> keyboard = new HashMap<>();
    static {
        keyboard.put('2', Arrays.asList("a","b","c"));
        keyboard.put('3',Arrays.asList("d","e","f"));
        keyboard.put('4',Arrays.asList("g","h","i"));
        keyboard.put('5',Arrays.asList("j","k","l"));
        keyboard.put('6',Arrays.asList("m","n","o"));
        keyboard.put('7',Arrays.asList("p","q","r","s"));
        keyboard.put('8',Arrays.asList("t","u","v"));
        keyboard.put('9',Arrays.asList("w","x","y","z"));
    }


    /**
     * N 皇后
     * @link https://leetcode-cn.com/problems/n-queens/
     */
    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        this.used = new boolean[n];
        permutation = new ArrayList<>(n);
        dfsSolveQueen(0);
        List<List<String>> result = new ArrayList<>();
        for (List<Integer> list : ansTwo) {
            List<String> str = new ArrayList<>(list.size());
            list.forEach(i -> {
                char[] chars = new char[n];
                Arrays.fill(chars, '.');
                chars[i] = 'Q';
                str.add(new String(chars));
            });
            result.add(str);
        }
        return result;
    }

    private void dfsSolveQueen(int row) {
        if (row == n) {
            ansTwo.add(new ArrayList<>(permutation));
            return;
        }
        for (int col = 0; col < n; col++) {
            // used确保行列不重复，usedPlus和usedMinus确保对角线
            if(!used[col] && usedPlus.get(row - col) == null && usedMinus.get(row + col) == null) {
                used[col] = true;
                usedPlus.put(row - col, true);
                usedMinus.put(row + col, true);
                permutation.add(col);
                dfsSolveQueen(row + 1);
                used[col] = false;
                usedPlus.remove(row - col);
                usedMinus.remove(row + col);
                permutation.remove(permutation.size() - 1);
            }
        }
    }

    private int n;
    private boolean[] used;
    private Map<Integer, Boolean> usedPlus = new HashMap<>();
    private Map<Integer, Boolean> usedMinus = new HashMap<>();
    private List<List<Integer>> ansTwo = new LinkedList<>();
    private List<Integer> permutation;


    public static void main(String[] args) {
        DFSTrain dfsTrain = new DFSTrain();
        dfsTrain.solveNQueens(4);
    }
}
