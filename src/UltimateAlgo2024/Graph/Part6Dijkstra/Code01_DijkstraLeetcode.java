package UltimateAlgo2024.Graph.Part6Dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// https://leetcode.com/problems/network-delay-time
public class Code01_DijkstraLeetcode {

    public int networkDelayTime(int[][] times, int n, int s) {
        // build graph
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] time : times) {
            graph.get(time[0]).add(new int[] {time[1], time[2]});
        }
        // 下标1到n，长度1到n+1
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[s] = 0;
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        heap.add(new int[] {s, 0});
        while (!heap.isEmpty()) {
            int u = heap.poll()[0];
            if (visited[u]) {
                continue;
            }
            visited[u] = true;
            for (int[] edge : graph.get(u)) {
                int v = edge[0];
                int w = edge[1];
                if (!visited[v] && distance[v] > w + distance[u]) {
                    distance[v] = w + distance[u];
                    heap.add(new int[] {v, w + distance[u]});
                }
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, distance[i]);
        }
        return ans;
    }
}
