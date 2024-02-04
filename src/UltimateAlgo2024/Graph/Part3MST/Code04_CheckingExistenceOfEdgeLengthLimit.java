package UltimateAlgo2024.Graph.Part3MST;

import java.util.Arrays;

// https://leetcode.com/problems/checking-existence-of-edge-length-limited-paths/
public class Code04_CheckingExistenceOfEdgeLengthLimit {
    public static int MAXK = 100001;

    public static int[][] questions = new int[MAXK][4];

    public static int[] father = new int[MAXK];

    public static void build(int n) {
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }

    public static int find(int i) {
        if (i != father[i]) {
            father[i] = find(father[i]);
        }
        return father[i];
    }

    public static boolean isSameSet(int x, int y) {
        return find(x) == find(y);
    }

    public static void union(int x, int y) {
        father[find(x)] = find(y);
    }

    public boolean[] distanceLimitedPathsExist(int n, int[][] edges, int[][] queries) {
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);
        int m = edges.length;
        int k = queries.length;
        for (int i = 0; i < k; i++) {
            questions[i][0] = queries[i][0];
            questions[i][1] = queries[i][1];
            questions[i][2] = queries[i][2];
            questions[i][3] = i;
        }
        Arrays.sort(questions, 0, k, (a, b) -> a[2] - b[2]);
        build(n);
        boolean[] ans = new boolean[k];
        for (int i = 0, j = 0; i < k; i++ ) {
            for (; j < m && edges[j][2] < questions[i][2]; j++) {
                union(edges[j][0], edges[j][1]);
            }
            ans[questions[i][3]] = isSameSet(questions[i][0], questions[i][1]);
        }
        return ans;
    }
}
