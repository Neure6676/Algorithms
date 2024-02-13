package UltimateAlgo2024.DynamicProgramming.TwoD;

// https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
public class Code06_LongestIncreasingPath {

    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, f(matrix, i, j, dp));
            }
        }
        return ans;
    }

    public static int f(int[][] m, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int next = 0;
        if (i + 1 < m.length && m[i + 1][j] > m[i][j]) {
            next = Math.max(next, f(m, i + 1, j, dp));
        }
        if (i - 1 >= 0 && m[i - 1][j] > m[i][j]) {
            next = Math.max(next, f(m, i - 1, j, dp));
        }
        if (j + 1 < m[0].length && m[i][j + 1] > m[i][j]) {
            next = Math.max(next, f(m, i, j + 1, dp));
        }
        if (j - 1 >= 0 && m[i][j - 1] > m[i][j]) {
            next = Math.max(next, f(m, i, j - 1, dp));
        }
        dp[i][j] = ++next;
        return next;
    }
}
