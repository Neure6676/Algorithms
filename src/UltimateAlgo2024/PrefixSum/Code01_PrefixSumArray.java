package UltimateAlgo2024.PrefixSum;

// https://leetcode.com/problems/range-sum-query-immutable/
public class Code01_PrefixSumArray {

    class NumArray {

        int[] preSum;

        public NumArray(int[] nums) {
            int N = nums.length;
            preSum = new int[N + 1];
            for (int i = 0; i < N; i++) {
                preSum[i + 1] = preSum[i] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return preSum[right + 1] - preSum[left];
        }
    }
}
