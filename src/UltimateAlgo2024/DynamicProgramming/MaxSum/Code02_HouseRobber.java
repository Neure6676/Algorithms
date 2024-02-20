package UltimateAlgo2024.DynamicProgramming.MaxSum;

// https://leetcode.com/problems/house-robber/
public class Code02_HouseRobber {

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(Math.max(dp[i - 2] + nums[i], nums[i]), dp[i - 1]);
        }
        return dp[n - 1];
    }

    // 空间压缩
    public static int rob2(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int prepre = nums[0];
        int pre = Math.max(nums[0], nums[1]);
        for (int i = 2, cur; i < n; i++) {
            cur = Math.max(pre, Math.max(nums[i], prepre + nums[i]));
            prepre = pre;
            pre = cur;
        }
        return pre;
    }

}
