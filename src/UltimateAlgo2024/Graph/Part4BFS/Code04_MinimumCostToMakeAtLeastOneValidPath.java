package UltimateAlgo2024.Graph.Part4BFS;

import java.util.ArrayDeque;

// https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/
public class Code04_MinimumCostToMakeAtLeastOneValidPath {

    public int minCost(int[][] grid) {
        // 格子的数值 :
        // 1 右
        // 2 左
        // 3 下
        // 4 上
        //                0      1         2          3         4
        int[][] move = { {}, { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        int n = grid.length;
        int m = grid[0].length;
        int[][] distance = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        ArrayDeque<int[]> deque = new ArrayDeque<>();
        deque.addFirst(new int[] {0, 0});
        distance[0][0] = 0;
        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int x = cur[0];
            int y = cur[1];
            if (x == n - 1 && y == m - 1) {
                return distance[x][y];
            }
            for (int i = 1; i < 5; i++) {
                int nx = x + move[i][0];
                int ny = y + move[i][1];
                int weight = grid[x][y] != i ? 1 : 0;
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && distance[nx][ny] > distance[x][y] + weight) {
                    distance[nx][ny] = distance[x][y] + weight;
                    if (weight == 0) {
                        deque.addFirst(new int[] {nx, ny});
                    } else {
                        deque.addLast(new int[] {nx, ny});
                    }
                }
            }
        }
        return -1;
    }
}
