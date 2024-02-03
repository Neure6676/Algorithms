package UltimateAlgo2024.Graph.Part2;

import java.util.ArrayList;

// https://leetcode.com/problems/loud-and-rich/
public class Code02_LoudAndRich {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        int[] indegree = new int[n];
        for (int[] r : richer) {
            graph.get(r[0]).add(r[1]);
            indegree[r[1]]++;
        }
        int[] queue = new int[n];
        int l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue[r++] = i;
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = i;
        }
        while (l < r) {
            int cur = queue[l++];
            // cur试图把自己的消息推给next
            for (int next : graph.get(cur)) {
                if (quiet[ans[cur]] < quiet[ans[next]]) {
                    ans[next] = ans[cur];
                }
                if (--indegree[next] == 0) {
                    queue[r++] = next;
                }
            }
        }
        return ans;
    }

}
