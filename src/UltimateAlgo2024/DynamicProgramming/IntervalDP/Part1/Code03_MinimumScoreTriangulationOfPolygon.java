package UltimateAlgo2024.DynamicProgramming.IntervalDP.Part1;

// https://leetcode.com/problems/minimum-score-triangulation-of-polygon/
public class Code03_MinimumScoreTriangulationOfPolygon {

    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        return f1(values, 0, n - 1, dp);
    }

    public static int f1(int[] v, int l, int r, int[][] dp) {
        if (dp[l][r] != -1) {
            return dp[l][r];
        }
        int ans = Integer.MAX_VALUE;
        if (l == r || l + 1 == r) {
            ans = 0;
        }
        for (int m = l + 1; m < r; m++) {
            ans = Math.min(ans, f1(v, l, m, dp) + f1(v, m, r, dp) + (v[l] * v[m] * v[r]));
        }
        dp[l][r] = ans;
        return ans;
    }

    public int minScoreTriangulation2(int[] v) {
        int n = v.length;
        int[][] dp = new int[n][n];
        for (int l = n - 3; l >= 0; l--) {
            for (int r = l + 2; r < n; r++) {
                int ans = Integer.MAX_VALUE;
                for (int m = l + 1; m < r; m++) {
                    ans = Math.min(ans, dp[l][m] + dp[m][r] + (v[l] * v[m] * v[r]));
                }
                dp[l][r] = ans;
            }
        }
        return dp[0][n - 1];
    }
}
