package UltimateAlgo2024.Graph.Part4BFS;

import java.util.ArrayDeque;

// https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner/
public class Code03_MinimumObstacleRemovalToReachCorner {

    public int minimumObstacles(int[][] grid) {
        int[] move = {-1, 0, 1, 0, -1};
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
            // top right down left
            for (int i = 0; i < 4; i++) {
                int nx = x + move[i], ny = y + move[i + 1];
                // 1. 不越界 2. 能更新（路权值更小）
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && distance[nx][ny] > distance[x][y] + grid[nx][ny]) {
                    distance[nx][ny] = distance[x][y] + grid[nx][ny];
                    if (grid[nx][ny] == 0) {
                        // 从头进
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
