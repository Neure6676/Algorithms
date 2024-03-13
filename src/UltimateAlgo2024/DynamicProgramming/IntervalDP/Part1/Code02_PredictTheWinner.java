package UltimateAlgo2024.DynamicProgramming.IntervalDP.Part1;

// https://leetcode.com/problems/predict-the-winner/
public class Code02_PredictTheWinner {

    public boolean predictTheWinner1(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        int first = f1(nums, 0, n - 1, dp);
        int second = sum - first;
        return first >= second;
    }

    // nums[l...r]范围上的数字进行游戏，轮到玩家1
    // 返回玩家1最终能获得多少分数
    public static int f1(int[] nums, int l, int r, int[][] dp) {
        if (dp[l][r] != -1) {
            return dp[l][r];
        }
        int ans = 0;
        if (l == r) {
            ans = nums[l];
        } else if (l + 1 == r) {
            ans = Math.max(nums[l], nums[r]);
        } else {
            int p1 = nums[l] + Math.min(f1(nums, l + 2, r, dp), f1(nums, l + 1, r - 1, dp));
            int p2 = nums[r] + Math.min(f1(nums, l, r - 2, dp), f1(nums, l + 1, r - 1, dp));
            ans = Math.max(p1, p2);
        }
        dp[l][r] = ans;
        return ans;
    }


    public boolean predictTheWinner2(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int[][] dp = new int[n][n];
        for (int i = 0; i < n - 1; i++) {
            dp[i][i] = nums[i];
            dp[i][i + 1] = Math.max(nums[i], nums[i + 1]);
        }
        dp[n - 1][n - 1] = nums[n - 1];
        for (int l = n - 3; l >= 0; l--) {
            for (int r = l + 2; r < n; r++) {
                int p1 = nums[l] + Math.min(dp[l + 2][r], dp[l + 1][r - 1]);
                int p2 = nums[r] + Math.min(dp[l][r - 2], dp[l + 1][r - 1]);
                dp[l][r] = Math.max(p1, p2);
            }
        }
        int first = dp[0][n - 1];
        int second = sum - first;
        return first >= second;
    }
}
