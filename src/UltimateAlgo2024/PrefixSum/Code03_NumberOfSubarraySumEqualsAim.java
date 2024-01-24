package UltimateAlgo2024.PrefixSum;

import java.util.HashMap;

// https://leetcode.com/problems/subarray-sum-equals-k/
public class Code03_NumberOfSubarraySumEqualsAim {

    public int subarraySum(int[] nums, int k) {
        // K : preSum
        // V : how many times
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int ans = 0;
        for (int i = 0, sum = 0; i < nums.length; i++) {
            sum += nums[i];
            ans += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }
}
