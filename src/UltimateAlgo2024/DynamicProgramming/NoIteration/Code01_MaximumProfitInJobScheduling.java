package UltimateAlgo2024.DynamicProgramming.NoIteration;

import java.util.Arrays;

// https://leetcode.com/problems/maximum-profit-in-job-scheduling/
public class Code01_MaximumProfitInJobScheduling {

    public static int MAXN = 50001;

    public static int[][] jobs = new int[MAXN][3];

    public static int[] dp = new int[MAXN];

    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        for (int i = 0; i < n; i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }
        // 工作按照结束时间从小到大排序
        Arrays.sort(jobs, 0, n, (a, b) -> a[1] - b[1]);
        dp[0] = jobs[0][2];
        for (int i = 1, start; i < n; i++) {
            start = jobs[i][0];
            dp[i] = jobs[i][2];
            if (jobs[0][1] <= start) {
                dp[i] += dp[find(i - 1, start)];
            }
            dp[i] = Math.max(dp[i], dp[i - 1]);
        }
        return dp[n - 1];
    }

    // job[0...i]范围上，找到结束时间 <= start，最右的下标
    public static int find(int i, int start) {
        int ans = 0;
        int l = 0;
        int r = i;
        int m;
        // 答案可能就是r，所以l要能够=r
        while (l <= r) {
            m = (l + r) / 2;
            if (jobs[m][1] <= start) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }
}
