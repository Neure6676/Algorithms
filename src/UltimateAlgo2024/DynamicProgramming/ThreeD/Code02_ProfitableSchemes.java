package UltimateAlgo2024.DynamicProgramming.ThreeD;

// https://leetcode.com/problems/profitable-schemes/
public class Code02_ProfitableSchemes {

    public static int mod = 1000000007;

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int[][][] dp = new int[group.length][n + 1][minProfit + 1];
        for (int i = 0; i < group.length; i++) {
            for (int r = 0; r <= n; r++) {
                for (int s = 0; s <= minProfit; s++) {
                    dp[i][r][s] = -1;
                }
            }
        }
        return f(group, profit, 0, n, minProfit, dp);
    }

    // i: current idx
    // r: how many people are there
    // s: how much profit we neet to get
    public static int f(int[] g, int[] p, int i, int r, int s, int[][][] dp) {
        if (r <= 0) {
            return s == 0 ? 1 : 0;
        }
        if (i == g.length) {
            return s == 0 ? 1 : 0;
        }
        if (dp[i][r][s] != -1) {
            return dp[i][r][s];
        }
        int p1 = f(g, p, i + 1, r, s, dp);
        int p2 = 0;
        if (g[i] <= r) {
            p2 = f(g, p, i + 1, r - g[i], Math.max(0, s - p[i]), dp);
        }
        int ans = (p1 + p2) % mod;
        dp[i][r][s] = ans;
        return ans;
    }

    public int profitableSchemes2(int n, int minProfit, int[] group, int[] profit) {
        int[][][] dp = new int[group.length + 1][n + 1][minProfit + 1];
        for (int r = 0; r <= n; r++) {
            dp[group.length][r][0] = 1;
        }
        for (int i = group.length - 1; i >= 0; i--) {
            for (int r = 0; r <= n; r++) {
                for (int s = 0; s <= minProfit; s++) {
                    int p1 = dp[i + 1][r][s];
                    int p2 = 0;
                    if (group[i] <= r) {
                        p2 = dp[i + 1][r - group[i]][Math.max(0, s - profit[i])];
                    }
                    dp[i][r][s] = (p1 + p2) % mod;
                }
            }
        }
        return dp[0][n][minProfit];
    }

    public int profitableSchemes3(int n, int minProfit, int[] group, int[] profit) {
        int[][] dp = new int[n + 1][minProfit + 1];
        for (int r = 0; r <= n; r++) {
            dp[r][0] = 1;
        }
        for (int i = group.length - 1; i >= 0; i--) {
            for (int r = n; r >= 0; r--) {
                for (int s = minProfit; s >= 0; s--) {
                    if (group[i] <= r) {
                        dp[r][s] = (dp[r][s] + dp[r - group[i]][Math.max(0, s - profit[i])]) % mod;
                    }
                }
            }
        }
        return dp[n][minProfit];
    }
}
