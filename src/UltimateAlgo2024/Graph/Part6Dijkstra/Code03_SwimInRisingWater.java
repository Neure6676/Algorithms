package UltimateAlgo2024.Graph.Part6Dijkstra;

import java.util.PriorityQueue;

// https://leetcode.com/problems/swim-in-rising-water/
public class Code03_SwimInRisingWater {

    public static int[] move = {-1, 0, 1, 0, -1};

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] distance = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        distance[0][0] = grid[0][0];
        boolean[][] visited = new boolean[n][m];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        heap.add(new int[] { 0, 0, grid[0][0]});
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int x = cur[0];
            int y = cur[1];
            int c = cur[2];
            if (visited[x][y]) {
                continue;
            }
            visited[x][y] = true;
            if (x == n - 1 && y == m - 1) {
                return c;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + move[i];
                int ny = y + move[i + 1];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny]) {
                    int nc = Math.max(c, grid[nx][ny]);
                    if (nc < distance[nx][ny]) {
                        distance[nx][ny] = nc;
                        heap.add(new int[] {nx, ny, nc});
                    }
                }
            }
        }
        return -1;
    }
}
