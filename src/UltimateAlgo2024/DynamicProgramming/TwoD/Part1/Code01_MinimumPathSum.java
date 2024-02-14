package UltimateAlgo2024.DynamicProgramming.TwoD.Part1;

// https://leetcode.com/problems/minimum-path-sum/
public class Code01_MinimumPathSum {

    public int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        return f2(grid, m - 1, n - 1, dp);
    }

    public static int f2(int[][] grid, int m, int n, int[][] dp) {
        if (m == 0 && n == 0) {
            return grid[0][0];
        }
        if (dp[m][n] != -1) {
            return dp[m][n];
        }
        int up = Integer.MAX_VALUE;
        int left = Integer.MAX_VALUE;
        if (m - 1 >= 0) {
            up = f2(grid, m - 1, n, dp);
        }
        if (n - 1 >= 0) {
            left = f2(grid, m, n - 1, dp);
        }
        dp[m][n] = Math.min(up, left) + grid[m][n];
        return dp[m][n];
    }


    public int minPathSum3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i  = 1; i < m; i ++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }


    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + grid[0][i];
        }
        for (int i  = 1; i < m; i ++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < n; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
            }
        }
        return dp[n - 1];
    }



}
