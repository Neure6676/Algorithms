package UltimateAlgo2024.Graph.Part1TopologicalOrder;

import java.util.ArrayList;
import java.util.Arrays;

// https://leetcode.com/problems/stamping-the-sequence/
public class Code04_StampingTheSequence {

    public int[] movesToStamp(String stamp, String target) {
        char[] s = stamp.toCharArray();
        char[] t = target.toCharArray();
        int m = s.length;
        int n = t.length;
        // 错误的点数量
        int[] indegree = new int[n - m + 1];
        Arrays.fill(indegree, m);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        int[] queue = new int[n - m + 1];
        int l = 0, r = 0;
        for (int i = 0; i <= n - m; i++) {
            // 依次在i位置上放stamp
            for (int j = 0; j < m; j++) {
                if (t[i + j] == s[j]) {
                    if (--indegree[i] == 0) {
                        queue[r++] = i;
                    }
                } else {
                    // 错误的位置影响的是哪个开头
                    // from : 错误的位置
                    // to : i开头的下标
                    graph.get(i + j).add(i);
                }
            }
        }
        // 同一个位置取消错误不要重复统计
        boolean[] visited = new boolean[n];
        int[] path = new int[n - m + 1];
        int size = 0;
        while (l < r) {
            int cur = queue[l++];
            path[size++] = cur;
            for (int i = 0; i < m; i++) {
                // cur : 开头位置
                // 能取消哪些点 cur + 0 cur + 1 cur + 2 ... cur + m - 1
                if (!visited[cur + i]) {
                    visited[cur + i] = true;
                    // 这个位置影响哪些开头的点
                    for (int next : graph.get(cur + i)) {
                        if (--indegree[next] == 0) {
                            queue[r++] = next;
                        }
                    }
                }
            }
        }
        if (size != n - m + 1) {
            return new int[0];
        }
        for (int i = 0, j = size - 1; i < j; i++, j--) {
            int temp = path[i];
            path[i] = path[j];
            path[j] = temp;
        }
        return path;
    }
}
