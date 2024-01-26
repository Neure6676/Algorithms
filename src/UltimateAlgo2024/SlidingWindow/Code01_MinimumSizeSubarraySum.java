package UltimateAlgo2024.SlidingWindow;

// https://leetcode.com/problems/minimum-size-subarray-sum/
public class Code01_MinimumSizeSubarraySum {

    public static int minSubArrayLen(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        for (int l = 0, r = 0, sum = 0; r < nums.length; r++) {
            sum += nums[r];
            while (sum - nums[l] >= target) {
                // sum : nums[l....r]
                // 如果l位置的数从窗口出去，还能继续达标，那就出去
                sum -= nums[l++];
            }
            if (sum >= target) {
                ans = Math.min(ans, r - l + 1);
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
