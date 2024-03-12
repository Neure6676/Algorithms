package UltimateAlgo2024.DynamicProgramming.Knapsack.part2;

import java.util.List;

// https://leetcode.com/problems/maximum-value-of-k-coins-from-piles/
// 组里的硬币不是“物品”，具体拿走的方案才是
public class Code02_MaximumValueOfKcoinsFromPiles {

    // dp[i][j] : 1~i组上，一共拿走j个硬币的情况下，获得的最大价值
    // 1) 不要i组的硬币 : dp[i-1][j]
    // 2) i组里尝试每一种方案
    // 比如，i组里拿走前k个硬币的方案 : dp[i-1][j-k] + 从顶部开始前k个硬币的价值和
    public int maxValueOfCoins1(List<List<Integer>> piles, int k) {
        int n = piles.size();
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            List<Integer> pile = piles.get(i - 1);
            // get preSum
            int t = Math.min(pile.size(), k);
            int[] preSum = new int[t + 1];
            for (int j = 0, sum = 0; j < t; j++) {
                sum += pile.get(j);
                preSum[j + 1] = sum;
            }
            // update dp
            for (int j = 0; j <= k; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int q = 1; q <= Math.min(t, j); q++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - q] + preSum[q]);
                }
            }
        }
        return dp[n][k];
    }

    public int maxValueOfCoins2(List<List<Integer>> piles, int k) {
        int n = piles.size();
        int[] dp = new int[k + 1];
        for (int i = 1; i <= n; i++) {
            List<Integer> pile = piles.get(i - 1);
            // get preSum
            int t = Math.min(pile.size(), k);
            int[] preSum = new int[t + 1];
            for (int j = 0, sum = 0; j < t; j++) {
                sum += pile.get(j);
                preSum[j + 1] = sum;
            }
            // update dp
            for (int j = k; j >= 0; j--) {
                for (int q = 1; q <= Math.min(t, j); q++) {
                    dp[j] = Math.max(dp[j], dp[j - q] + preSum[q]);
                }
            }
        }
        return dp[k];
    }
}
