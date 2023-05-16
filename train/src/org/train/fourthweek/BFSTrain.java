package org.train.fourthweek;

import java.util.*;

/**
 * @author liuyue
 * @Description: 广搜训练
 * @date 2021/12/12 16:03
 */
public class BFSTrain {

    /**
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * @link https://leetcode-cn.com/problems/number-of-islands/
     */
    public int numIslands(char[][] grid) {
        int ans = 0, m = grid.length, n = grid[0].length;
        vis = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !vis[i][j]) {
                    ans ++;
                    numIsLandsBfsHelp(i, j, m, n, grid);
                }
            }
        }
        return ans;
    }

    private boolean[][] vis;
    private void numIsLandsBfsHelp(int i, int j, int m, int n, char[][] grid) {
        int[] dx = {-1,0,0,1};
        int[] dy = {0,1,-1,0};
        Queue<List<Integer>> queue = new ArrayDeque<>();
        queue.offer(Arrays.asList(i,j));
        while (!queue.isEmpty()) {
            List<Integer> integers = queue.poll();
            int x = integers.get(0);
            int y = integers.get(1);
            for (int k = 0; k < 4; k ++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (nx < 0 || nx >= m || ny <0 || ny >= n) continue;
                if (vis[nx][ny] || grid[nx][ny] != '1') continue;
                vis[nx][ny] = true;
                queue.offer(Arrays.asList(nx,ny));
            }
        }
    }


    /**
     * 一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
     * 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化
     * @link https://leetcode-cn.com/problems/minimum-genetic-mutation/
     * @param start 起始基因序列
     * @param end 目标基因序列
     * @param bank 基因库
     * @return 请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数 如果无法实现目标变化，请返回 -1
     */
    public int minMutation(String start, String end, String[] bank) {
        // Set集合方便判断是否存在某个元素
        hasBank = new HashSet<>(Arrays.asList(bank));
        if (!hasBank.contains(end)) return -1;
        // key->字符，value->层数
        depth = new HashMap<>(bank.length);
        // 最开始层数为0，变化次数也等于层数
        depth.put(start, 0);
        Queue<String> queue = new ArrayDeque<>();
        // 初始入队
        queue.offer(start);
        // 4个字符的常量数组
        char[] gene = {'A','C','G','T'};
        while (!queue.isEmpty()) {
            String poll = queue.poll();
            // 字符串由8个字符组成的
            for (int i = 0; i < 8; i ++){
                // 每个字符可以4选一
                for (int j = 0; j < 4; j ++) {
                    // 变化自然是字符不同才算
                    if (poll.charAt(i) != gene[j]) {
                        StringBuilder ns = new StringBuilder(poll);
                        ns.replace(i, i + 1, String.valueOf(gene[j]));
                        // 不允许出现不在bank中的基因，所以不存在直接进行下一次
                        if (!hasBank.contains(ns.toString())) continue;
                        // 避免重复访问
                        if (depth.containsKey(ns.toString())) continue;
                        // Depth++
                        depth.put(ns.toString(), depth.get(poll) + 1);
                        // 是end则直接返回结果
                        if (ns.toString().equals(end)) {
                            return depth.get(ns.toString());
                        }
                        // 入队
                        queue.offer(ns.toString());
                    }
                }
            }
        }
        // 不存在返回-1
        return -1;
    }

    Set<String> hasBank;
    Map<String, Integer> depth;
}
  