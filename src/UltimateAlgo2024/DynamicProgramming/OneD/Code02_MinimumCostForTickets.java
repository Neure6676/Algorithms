package UltimateAlgo2024.DynamicProgramming.OneD;

import java.util.Arrays;

// https://leetcode.com/problems/minimum-cost-for-tickets/
public class Code02_MinimumCostForTickets {

    public static int[] duration = {1, 7, 30};

    // top-down
    public int mincostTickets2(int[] days, int[] costs) {
        int[] dp = new int[days.length];
        Arrays.fill(dp, -1);
        return f2(days, costs, 0, dp);
    }

    public static int f2(int[] days, int[] costs, int i, int[] dp) {
        if (i == days.length) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int ans = Integer.MAX_VALUE;
        for (int k = 0, j = i; k < 3; k++) {
            while (j < days.length && days[j] - days[i] < duration[k]) {
                j++;
            }
            ans = Math.min(ans, costs[k] + f2(days, costs, j, dp));
        }
        dp[i] = ans;
        return ans;
    }


    // bottom-up
    public static int MAXN = 366;

    public static int[] dp = new int[MAXN];

    public int mincostTickets3(int[] days, int[] costs) {
        int n = days.length;
        Arrays.fill(dp, 0, n + 1, Integer.MAX_VALUE);
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int k = 0, j = i; k < 3; k++) {
                while (j < days.length && days[j] - days[i] < duration[k]) {
                    j++;
                }
                dp[i] = Math.min(dp[i], costs[k] + dp[j]);
            }
        }
        return dp[0];
    }

}
