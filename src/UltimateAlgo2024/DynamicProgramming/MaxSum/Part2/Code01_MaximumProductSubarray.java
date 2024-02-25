package UltimateAlgo2024.DynamicProgramming.MaxSum.Part2;

// https://leetcode.com/problems/maximum-product-subarray/
public class Code01_MaximumProductSubarray {

    // p1: [i]
    // p2: [i - 1]max * [i]
    // p3: [i - 1]min * [i] ([i] < 0)
    public int maxProduct(int[] nums) {
        int ans = nums[0];
        for (int i = 1, max = nums[0], min = nums[0], curMax, curMin; i < nums.length; i++) {
            curMax = Math.max(nums[i], Math.max(max * nums[i], min * nums[i]));
            curMin = Math.min(nums[i], Math.min(max * nums[i], min * nums[i]));
            max = curMax;
            min = curMin;
            ans = Math.max(ans, max);
        }
        return ans;
    }
}
