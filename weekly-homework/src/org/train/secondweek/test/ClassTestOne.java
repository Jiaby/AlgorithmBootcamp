package org.train.secondweek.test;

import java.util.*;

/**
 * @author liuyue
 * @Description: 随课练习1
 * @date 2021/11/24 22:18
 */
public class ClassTestOne {

    /**
     * 两数之和
     * @link https://leetcode-cn.com/problems/two-sum/description/
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            int y = target - x;
            if (map.containsKey(y)) {
                return new int[] {map.get(y), i};
            }
            // 如果x和之前放入的数字a相等这里会把之前a的下标更新为x的下标，
            // 但因为题目是两数相加，假设x+z=target，那么返回(xi,zi)或(ai,zi)都正确，所以下标覆盖不会影响题目的解决
            map.put(x, i);
        }
        return new int[0];
    }

    /**
     * 模拟行走机器人
     * @link https://leetcode-cn.com/problems/walking-robot-simulation/
     */
    public int robotSim(int[] commands, int[][] obstacles) {
        HashSet<List<Integer>> set = new HashSet<>();
        // 障碍物坐标放入set中以便O(1)复杂度查询
        for (int[] obstacle : obstacles) {
            set.add(Arrays.asList(obstacle[0], obstacle[1]));
        }
        // 方向数组来代替if判断
        int[] xn = {0,1,0,-1};
        int[] yn = {1,0,-1,0};
        int x = 0;
        int y = 0;
        int dir = 0; // 北=0 东=1 南=2 西=3
        int ans = 0;
        for (int i = 0; i < commands.length; i++) {
            if (commands[i] > 0 && commands[i] <= 9) {
                for (int j = 1; j <= commands[i]; j ++) {
                    int nx = x + xn[dir];
                    int ny = y + yn[dir];
                    // 遇到障碍物指令就没必要继续执行了
                    if (set.contains(Arrays.asList(nx, ny))) {
                        break;
                    } else {
                        x = nx;
                        y = ny;
                    }
                }
            //下面两个dir的操作是经常用到的取模来防止数组下标溢出操作(循环队列中也有使用)
            } else if (commands[i] == -1) {
                dir = (dir + 1) % 4;
            } else {
                // dir + 3是由dir - 1 + 4得来，+4的目的是为了避免负数取模
                dir = (dir + 3) % 4;
            }
            // 更新答案
            ans = Math.max(ans, x * x + y * y);
        }
        return ans;
    }

    //字母异位词分组
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = calHash(str);
            if (map.containsKey(key)) {
                map.get(key).add(str);
            } else {
                ArrayList<String> li = new ArrayList<>();
                li.add(str);
                map.put(key, li);
            }
        }
        List<List<String>> result = new LinkedList<>();
        map.forEach((k,v) -> result.add(v));
        return result;
    }

    private String calHash(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }


    public static void main(String[] args) {
        ClassTestOne classTestOne = new ClassTestOne();
        classTestOne.robotSim(new int[]{4, -1, 3},new int[][]{});
    }
}
