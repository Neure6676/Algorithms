package UltimateAlgo2024.Graph.Part4BFS;

import java.util.PriorityQueue;

// https://leetcode.com/problems/trapping-rain-water-ii/
public class Code05_TrappingRainWaterII {

    public int trapRainWater(int[][] heightMap) {
        int[] move = {-1, 0, 1, 0, -1};
        int n = heightMap.length;
        int m = heightMap[0].length;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] isVisited = new boolean[n][m];
        // 将边界加入
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0 || i == n - 1 || j == m -1) {
                    heap.add(new int[] {i, j, heightMap[i][j]});
                    isVisited[i][j] = true;
                } else {
                    isVisited[i][j] = false;
                }
            }
        }
        int ans = 0;
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int r = cur[0];
            int c = cur[1];
            int w = cur[2];
            ans += w - heightMap[r][c];
            for (int i = 0, nx, ny; i < 4; i++) {
                nx = r + move[i];
                ny = c + move[i + 1];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !isVisited[nx][ny]) {
                    heap.add(new int[] {nx, ny, Math.max(w, heightMap[nx][ny])});
                    isVisited[nx][ny] = true;
                }
            }
        }
        return ans;
    }
}
