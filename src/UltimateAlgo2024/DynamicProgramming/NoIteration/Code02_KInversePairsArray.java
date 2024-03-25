package UltimateAlgo2024.DynamicProgramming.NoIteration;

// https://leetcode.com/problems/k-inverse-pairs-array/description/
public class Code02_KInversePairsArray {

    // dp[i][j] : 1、2、3...i这些数字，形成的排列一定要有j个逆序对，请问这样的排列有几种
    // dp[5][3] = ?    a b c d      e
    // p1 = dp[4][3] e插最后
    // p2 = dp[4][2] e插cd中间 + dp[4][1] e插bc中间 + dp[4][0] e插ab中间
    // dp[5][8] = ?
    // dp[5][8] = dp[4][8] + dp[4][7] + dp[4][6] + dp[4][5] + dp[4][4]
    public static int mod = 1000000007;

    public int kInversePairs1(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        // dp[i][j] : 1、2、3...i这些数字，形成的排列一定要有j个逆序对，请问这样的排列有几种
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= k; j++) {
                if (i > j) {
                    for (int q = 0; q <= j; q++) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][q]) % mod;
                    }
                } else {
                    for (int q = j - i + 1; q <= j; q++) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][q]) % mod;
                    }
                }
            }
        }
        return dp[n][k];
    }

    public int kInversePairs2(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        // dp[i][j] : 1、2、3...i这些数字，形成的排列一定要有j个逆序对，请问这样的排列有几种
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= k; j++) {
                if (i > j) {
                    dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % mod;
                } else {
                    dp[i][j] = ((dp[i][j - 1] + dp[i - 1][j])% mod - dp[i - 1][j - i] + mod) % mod;
                }
            }
        }
        return dp[n][k];
    }

    public int kInversePairs(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;
        // window : 窗口的累加和
        for (int i = 1, window; i <= n; i++) {
            dp[i][0] = 1;
            window = 1;
            for (int j = 1; j <= k; j++) {
                if (i > j) {
                    window = (window + dp[i - 1][j]) % mod;
                } else {
                    // i <= j
                    window = ((window + dp[i - 1][j]) % mod - dp[i - 1][j - i] + mod) % mod;
                }
                dp[i][j] = window;
            }
        }
        return dp[n][k];
    }
}
