package UltimateAlgo2024.DynamicProgramming.TreeDP.Part2;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/minimum-fuel-cost-to-report-to-the-capital/
public class Code01_MinimumFuelCost {

    public long minimumFuelCost(int[][] roads, int seats) {
        // 节点数
        int n = roads.length + 1;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // 双向图
        for (int[] r : roads) {
            graph.get(r[0]).add(r[1]);
            graph.get(r[1]).add(r[0]);
        }
        int[] size = new int[n];  //此时有几人
        long[] cost = new long[n];
        f(graph, seats, 0, -1, size, cost);
        return cost[0];
    }

    public static void f(List<List<Integer>> graph, int seats, int cur, int parent, int[] size, long[] cost) {
        size[cur] = 1;
        for (int v : graph.get(cur)) {
            if (v != parent) {
                f(graph, seats, v, cur, size, cost);
                size[cur] += size[v];
                cost[cur] += cost[v];
                cost[cur] += (size[v] + seats - 1) / seats;
            }
        }
    }
}
