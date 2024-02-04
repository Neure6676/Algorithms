package UltimateAlgo2024.Graph.Part3MST;

import java.util.Arrays;

// https://leetcode.com/problems/optimize-water-distribution-in-a-village/
public class Code03_OptimizeWaterDistribution {

    public static int MAXV = 10010;

    public static int[][] edges = new int[MAXV * 2][3];

    public static int[] father = new int[MAXV];

    // num of edges
    public static int cnt;

    public static void build(int n) {
        cnt = 0;
        for (int i = 0; i <= n; i++) {
            father[i] = i;
        }
    }

    public static int find(int i) {
        if (father[i] != i) {
            father[i] = find(father[i]);
        }
        return father[i];
    }

    public static boolean union(int i, int j) {
        int fi = find(i);
        int fj = find(j);
        if (fi != fj) {
            father[fi] = fj;
            return true;
        } else {
            return false;
        }
    }

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        build(n);
        for (int i = 0; i < n; i++, cnt++) {
            edges[cnt][0] = 0;
            edges[cnt][1] = i + 1;
            edges[cnt][2] = wells[i];
        }
        for (int i = 0; i < pipes.length; i++, cnt++) {
            edges[cnt][0] = pipes[i][0];
            edges[cnt][1] = pipes[i][1];
            edges[cnt][2] = pipes[i][2];
        }
        Arrays.sort(edges, 0, cnt, (a, b) -> a[2] - b[2]);
        int ans = 0;
        for (int i = 0; i < cnt; i++) {
            if (union(edges[i][0], edges[i][1])) {
                ans += edges[i][2];
            }
        }
        return ans;
    }
}
