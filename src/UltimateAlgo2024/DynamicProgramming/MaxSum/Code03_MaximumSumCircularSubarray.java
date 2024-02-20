package UltimateAlgo2024.DynamicProgramming.MaxSum;

// https://leetcode.com/problems/maximum-sum-circular-subarray/
public class Code03_MaximumSumCircularSubarray {

    /**
     * note
     * p1. continuous
     * p2. not continuous 一定是开头一部分结尾一部分 maxSum = all-minSum
     */
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int all = nums[0], maxSum = nums[0], minSum = nums[0];
        for (int i = 1, maxPre = nums[0], minPre = nums[0]; i < n; i++) {
            all += nums[i];
            maxPre = Math.max(maxPre + nums[i], nums[i]);
            maxSum = Math.max(maxPre, maxSum);
            minPre = Math.min(minPre + nums[i], nums[i]);
            minSum = Math.min(minPre, minSum);
        }
        return all == minSum ? maxSum : Math.max(maxSum, all - minSum);
    }

}
