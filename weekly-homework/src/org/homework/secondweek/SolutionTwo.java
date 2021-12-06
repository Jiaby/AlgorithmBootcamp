package org.homework.secondweek;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author liuyue
 * @Description:
 * @date 2021/11/27 14:51
 */
public class SolutionTwo {
    /**
     * 子域名访问次数统计
     * 给定一个带访问次数和域名的组合，要求分别计算每个域名被访问的次数。其格式为访问次数+空格+地址，例如："9001 discuss.leetcode.com"。
     * @link https://leetcode-cn.com/problems/subdomain-visit-count/
     */
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        for (String cpdomain : cpdomains) {
            String[] countDomain = cpdomain.split(" ");
            int count = Integer.parseInt(countDomain[0]);
            String[] domains = splitDomain(countDomain[1]);
            for (String domain : domains) {
                if (map.containsKey(domain)) {
                    map.put(domain, map.get(domain) + count);
                } else {
                    map.put(domain, count);
                }
            }
        }
        ArrayList<String> list = new ArrayList<>();
        map.forEach((k,v) -> list.add(v + " " + k));
        return list;
    }

    private String[] splitDomain(String domain) {
        String[] split = domain.split("\\.");
        String[] s = new String[split.length];
        StringBuilder preStr = new StringBuilder();
        for (int i = split.length - 1; i >= 0; i--) {
            String string = preStr.insert(0, split[i]).toString();
            s[i] = string;
            preStr.insert(0, ".");
        }
        return s;
    }

    /**
     * 数组的度
     * 给定一个非空且只包含非负数的整数数组nums，数组的度的定义是指数组里任一元素出现频数的最大值。
     *
     * 你的任务是在 nums 中找到与nums拥有相同大小的度的最短连续子数组，返回其长度。
     * @link https://leetcode-cn.com/problems/degree-of-an-array/
     */
    public int findShortestSubArray(int[] nums) {
        // 求出数组的度，即任一元素出现频数的最大值
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        // 哪些数字出现的频次最大
        Map<Integer, Integer> max = new HashMap<>();
        map.forEach((k1, v1) -> {
            Iterator<Map.Entry<Integer, Integer>> iterator = max.entrySet().iterator();
            if (iterator.hasNext()) {
                Map.Entry<Integer, Integer> next = iterator.next();
                if (next.getValue() < v1) {
                    max.clear();
                    max.put(k1,v1);
                } if (next.getValue().equals(v1)) {
                    max.put(k1,v1);
                }
            } else {
                max.put(k1,v1);
            }
        });
        IntValue result = new IntValue();
        result.value = nums.length;
        max.forEach((k,v) -> {
            int start = 0; int end = nums.length - 1;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == k) {
                    start = i;
                    break;
                }
            }
            for (int i = nums.length - 1; i >= 0; i--) {
                if (nums[i] == k) {
                    end = i;
                    break;
                }
            }
            result.value = Math.min(result.value, end - start + 1);
        });
        return result.value;
    }

    class IntValue {
        int value;
    }

    private int findDegree(int[] nums, int start, int end) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = start; i <= end; i++) {
            int num = nums[i];
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int max = 0;
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            max = Math.max(max, next.getValue());
        }
        return max;
    }


    /**
     * 元素和为目标值的子矩阵数量
     * 子矩阵x1, y1, x2, y2是满足 x1 <= x <= x2且y1 <= y <= y2的所有单元matrix[x][y]的集合。
     * 如果(x1, y1, x2, y2) 和(x1', y1', x2', y2')两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
     * @link https://leetcode-cn.com/problems/number-of-submatrices-that-sum-to-target/
     * @param matrix 二维矩阵
     * @param target 目标值
     * @return 元素总和等于目标值的非空子矩阵的数量
     */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        // 本题要求子矩阵的和核心思路为子矩阵的求和公式，即：
        // 以(p,q)为左上角，(i,j)为右下角的子矩阵中数的和为：SUM(p,q,i,j)=S[i][j]-S[i][q-1]-S[p-1][j]+S[p-1][q-1]
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] s = new int[n + 1][m + 1];
        // 前缀和数组s的求和公式为：s[i][j] = s[i-1][j]+s[i][j-1]-s[i-1][j-1]+a[i][j]
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                s[i][j] = s[i-1][j] + s[i][j-1] - s[i-1][j-1] + matrix[i-1][j-1];
            }
        }
        int cnt = 0;
        // 先固定上下两条边
        for (int top = 1; top <= n; top++) {
            for (int bot = top; bot <= n; bot++) {
                int cur;
                // map用来存放{面积:次数}
                HashMap<Integer, Integer> map = new HashMap<>();
                //再从左到右移动右边界来形成一个子矩阵
                for (int right = 1; right <= m; right++) {
                    // 这个地方是求的以top为上边，以bot为下边，0号列为左边，right为右边的矩阵内数的和
                    cur = s[bot][right] - s[top - 1][right];
                    if (cur == target) cnt ++;
                    // 如果map记录过面积为cur-target的子矩阵(即曾经以x号列为右边的面积为cur-targer)，那么现在遍历到的子矩阵只要从以0号列为左边改为以x边为左边那么面积就是target
                    if (map.containsKey(cur - target)) cnt+=map.get(cur - target);
                    map.put(cur, map.getOrDefault(cur, 0) + 1);
                }
            }
        }
        return cnt;
    }

    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
     * @link https://leetcode-cn.com/problems/subarray-sum-equals-k/
     */
    public int subarraySum(int[] nums, int k) {
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
            if (sum[i] == k) ans ++;
            if (map.containsKey(sum[i] - k)) ans += map.get(sum[i] - k);
            map.put(sum[i], map.getOrDefault(sum[i], 0) + 1);
        }
        return ans;

    }

    public static void main(String[] args) {
        SolutionTwo solutionTwo = new SolutionTwo();
        int[][] matrix= {{0,1,0},{1,0,1},{0,1,0}};
        int target = 0;
        solutionTwo.numSubmatrixSumTarget(matrix, target);
    }

}
