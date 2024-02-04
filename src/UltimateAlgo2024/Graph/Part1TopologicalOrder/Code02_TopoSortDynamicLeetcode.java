package UltimateAlgo2024.Graph.Part1TopologicalOrder;

import java.util.ArrayList;

// https://leetcode.com/problems/course-schedule-ii/
public class Code02_TopoSortDynamicLeetcode {

    public int[] findOrder(int n, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // 入度表
        int[] indegree = new int[n];
        for (int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
            indegree[(edge[0])]++;
        }
        int[] queue = new int[n];
        int l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue[r++] = i;
            }
        }
        int cnt = 0;
        while (r > l) {
            int cur = queue[l++];
            cnt++;
            // 入度--
            for (int next : graph.get(cur)) {
                if (--indegree[next] == 0) {
                    queue[r++] = next;
                }
            }
        }
        return cnt == n ? queue : new int[0];
    }
}
