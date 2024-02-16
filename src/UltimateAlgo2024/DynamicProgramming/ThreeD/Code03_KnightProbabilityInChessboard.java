package UltimateAlgo2024.DynamicProgramming.ThreeD;

// https://leetcode.com/problems/ksnight-probability-in-chessboard/
public class Code03_KnightProbabilityInChessboard {

    public static int[][] move = {{-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}};

    public double knightProbability(int n, int k, int row, int column) {
        double[][][] dp = new double[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int s = 0; s <= k; s++) {
                    dp[i][j][s] = -1;
                }
            }
        }
        return f(n, row, column, k, dp);
    }

    public static double f(int n, int i, int j, int k, double[][][] dp) {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            return 0;
        }
        if (dp[i][j][k] != -1) {
            return dp[i][j][k];
        }
        double ans = 0;
        if (k == 0) {
            ans = 1;
        } else {
            for (int m = 0; m < 8; m++) {
                ans += (f(n, i + move[m][0], j + move[m][1], k - 1, dp) / 8);
            }
        }
        dp[i][j][k] = ans;
        return ans;
    }
}
