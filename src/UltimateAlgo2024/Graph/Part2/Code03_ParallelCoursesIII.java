package UltimateAlgo2024.Graph.Part2;

import java.util.ArrayList;

// https://leetcode.com/problems/parallel-courses-iii/
public class Code03_ParallelCoursesIII {

    public int minimumTime(int n, int[][] relations, int[] time) {
        int[] indegree = new int[n + 1];
        int[] res = new int[n + 1];
        int ans = 0;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        // 更新入度和图
        for (int i = 0; i < relations.length; i++) {
            // 1.更新图
            graph.get(relations[i][0]).add(relations[i][1]);
            indegree[relations[i][1]]++;
        }

        int[] queue = new int[n];
        int l = 0, r = 0;
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue[r++] = i;
            }
        }
        while (l < r) {
            int cur = queue[l++];
            res[cur] += time[cur - 1];
            ans = Math.max(ans, res[cur]);
            for (int next : graph.get(cur)) {
                res[next] = Math.max(res[next], res[cur]);
                // 2. 维护入度表，继续进队列
                if (--indegree[next] == 0) {
                    queue[r++] = next;
                }
            }
        }
        return ans;
    }
}
