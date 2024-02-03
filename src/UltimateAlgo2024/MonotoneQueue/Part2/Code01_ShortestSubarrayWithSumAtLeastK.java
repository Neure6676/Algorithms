package UltimateAlgo2024.MonotoneQueue.Part2;

// https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
public class Code01_ShortestSubarrayWithSumAtLeastK {

    public static int MAX = 100001;

    public static int[] deque = new int[MAX];

    // sum[3]: 前三个数的preSum
    public static long[] sum = new long[MAX];

    public static int h, t;

    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        h = t = 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            while (h != t && sum[i] - sum[deque[h]] >= k) {
                ans = Math.min(ans, i - deque[h++]);
            }
            while (t != h && sum[deque[t - 1]] >= sum[i]) {
                t--;
            }
            deque[t++] = i;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
