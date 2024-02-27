package UltimateAlgo2024.DynamicProgramming.Knapsack;

// https://leetcode.com/problems/last-stone-weight-ii/
public class Code04_LastStoneWeightII {

    public int lastStoneWeightII(int[] stones) {
        int s = 0;
        for (int stone : stones) {
            s += stone;
        }
        // <= s / 2, maxSum
        int near = near(stones, s / 2);
        return s - near - near;
    }

    // 非负数组nums中，子序列累加和不超过t，但是最接近t的累加和是多少
    // 01背包问题(子集累加和尽量接近t) + 空间压缩
    public static int near(int[] arr, int limit) {
        int[] dp = new int[limit + 1];
        for (int num : arr) {
            for (int j = limit; j >= num; j--) {
                dp[j] = Math.max(dp[j], dp[j - num] + num);
            }
        }
        return dp[limit];
    }
}
