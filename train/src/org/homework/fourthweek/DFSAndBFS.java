package org.homework.fourthweek;

import org.common.struct.CommonUti;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyue
 * @Description: 深搜广搜作业
 * @date 2021/12/14 17:45
 */
public class DFSAndBFS {

    /**
     * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充
     * @param board 一个 m*n 的矩阵 board ，由若干字符 'X' 和 'O'
     * @link https://leetcode-cn.com/problems/surrounded-regions/
     */
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        vis = new boolean[m][n];
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (board[i][j] == 'O' && !vis[i][j])
                solveBfsHelp(i,j,m,n,board);
            }
        }
    }

    private void solveBfsHelp(int i, int j, int m, int n, char[][] board) {
        int[] dx = {-1,0,0,1};
        int[] dy = {0,1,-1,0};
        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(i,j));
        List<Pair> pairs = new ArrayList<>(200);
        boolean flag = true;
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            pairs.add(pair);
            for (int k = 0; k < 4; k ++) {
                int nx = pair.x + dx[k];
                int ny = pair.y + dy[k];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    flag = false;
                    continue;
                }
                if (vis[nx][ny] || board[nx][ny] == 'X') continue;
                vis[nx][ny] = true;
                q.offer(new Pair(nx, ny));
            }
        }
        if (!flag) return;
        for (Pair pair : pairs) {
            board[pair.x][pair.y] = 'X';
        }
    }

    boolean[][] vis;

    static class Pair{
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static void main(String[] args) {
        DFSAndBFS dfsAndBFS = new DFSAndBFS();
        char[][] chars = CommonUti.leetCodeStringToCharArray("[[\"X\",\"O\",\"X\",\"O\",\"O\",\"O\",\"O\"],[\"X\",\"O\",\"O\",\"O\",\"O\",\"O\",\"O\"],[\"X\",\"O\",\"O\",\"O\",\"O\",\"X\",\"O\"],[\"O\",\"O\",\"O\",\"O\",\"X\",\"O\",\"X\"],[\"O\",\"X\",\"O\",\"O\",\"O\",\"O\",\"O\"],[\"O\",\"O\",\"O\",\"O\",\"O\",\"O\",\"O\"],[\"O\",\"X\",\"O\",\"O\",\"O\",\"O\",\"O\"]]");

        dfsAndBFS.solve(chars);
    }
}
