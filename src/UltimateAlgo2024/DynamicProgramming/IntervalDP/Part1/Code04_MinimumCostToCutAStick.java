package UltimateAlgo2024.DynamicProgramming.IntervalDP.Part1;

import java.util.Arrays;

// https://leetcode.com/problems/minimum-cost-to-cut-a-stick/
public class Code04_MinimumCostToCutAStick {

    public int minCost1(int n, int[] cuts) {
        int len = cuts.length;
        Arrays.sort(cuts);
        int[] help = new int[len + 2];
        // help[0] = 0
        for (int i = 0; i < len; i++) {
            help[i + 1] = cuts[i];
        }
        help[len + 1] = n;
        int[][] dp = new int[len + 2][len + 2];
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= len; j++) {
                dp[i][j] = -1;
            }
        }
        return f1(help, 1, len,  dp);
    }

    // 分别以每个点作此时第一刀
    // 切点范围是l到r
    public static int f1(int[] help, int l, int r, int[][] dp) {
        if (dp[l][r] != -1) {
            return dp[l][r];
        }
        if (l > r) {  //无切点剩下
            return 0;
        }
        if (l == r) {  //仅剩一个切点
            return help[r + 1] - help[l - 1]; // 该结果就是此时的代价
        }
        int ans = Integer.MAX_VALUE;
        for (int k = l; k <= r; k++) {
            ans = Math.min(ans, f1(help, l, k - 1, dp) + f1(help, k + 1, r, dp) + help[r + 1] - help[l - 1]);
        }
        dp[l][r] = ans;
        return ans;
    }




    public int minCost2(int n, int[] cuts) {
        int len = cuts.length;
        Arrays.sort(cuts);
        int[] help = new int[len + 2];
        // help[0] = 0
        for (int i = 0; i < len; i++) {
            help[i + 1] = cuts[i];
        }
        help[len + 1] = n;
        int[][] dp = new int[len + 2][len + 2];
        for (int l = len; l > 0; l--) {
            for (int r = l, ans = 0; r <= len; r++) {
                if (l == r) {
                    ans = help[r + 1] - help[l - 1];
                } else {
                    for (int k = l; k <= r; k++) {
                        ans = Math.min(ans, dp[l][k - 1] + dp[k + 1][r]);
                    }
                    ans += help[r + 1] - help[l - 1];
                }
                dp[l][r] = ans;
            }
        }
        return dp[1][len];
    }
}
