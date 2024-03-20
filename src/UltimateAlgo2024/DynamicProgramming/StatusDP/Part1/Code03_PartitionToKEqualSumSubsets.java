package UltimateAlgo2024.DynamicProgramming.StatusDP.Part1;

// https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
public class Code03_PartitionToKEqualSumSubsets {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        int n = nums.length;
        int[] dp = new int[1 << n];
        return f(nums, sum / k, (1 << n) - 1, 0, k, dp);
    }

    public static boolean f(int[] nums, int sum, int status, int cur, int rest, int[] dp) {
        if (rest == 0) {
            return status == 0;
        }
        if (dp[status] != 0) {
            return dp[status] == 1;
        }
        boolean ans = false;
        for (int i = 0; i < nums.length; i++) {
            if ((status & (1 << i)) != 0 && cur + nums[i] <= sum) {
                if (cur + nums[i] == sum) {
                    ans = f(nums, sum, status ^ (1 << i), 0, rest - 1, dp);
                } else {
                    ans = f(nums, sum, status ^ (1 << i), cur + nums[i], rest, dp);
                }
            }
            if (ans) {
                break;
            }
        }
        dp[status] = ans ? 1 : -1;
        return ans;
    }
}
