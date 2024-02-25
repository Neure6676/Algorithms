package UltimateAlgo2024.DynamicProgramming.MaxSum.Part3;

// https://leetcode.com/problems/longest-increasing-subsequence/
public class Code01_LongestIncreasingSubsequence {

    // O(n^2)
    // dp[i]: the length of longest strictly increasing subsequence that end with i
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int ans = 1;
        for (int i = 0; i < n; i++) {
            dp[i] = getDpI(nums, i, dp);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static int getDpI(int[] arr, int i, int[] dp) {
        int ans = 1;
        for (int j = 0; j < i; j++) {
            if (arr[j] < arr[i]) {
                ans = Math.max(ans, dp[j] + 1);
            }
        }
        return ans;
    }

    // O(n*logn)
    // dp[i]: the length of longest strictly increasing subsequence that end with i
    // end[i]: min end of longest strictly increasing subsequence with length of i + 1
    public static int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        int[] ends = new int[n];
        // len表示ends数组目前的有效区长度
        // ends[0...len-1]是有效区，有效区内的数字一定严格升序
        int len = 0;
        for (int i = 0, find; i < n; i++) {
            find = bs1(ends, len, nums[i]);
            if (find == -1) {
                ends[len++] = nums[i];
            } else {
                ends[find] = nums[i];
            }
        }
        return len;
    }

    // "最长递增子序列"使用如下二分搜索 :
    // ends[0...len-1]是严格升序的，找到>=num的最左位置
    // 如果不存在返回-1
    public static int bs1(int[] ends, int len, int num) {
        int l = 0, r = len - 1, m, ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (ends[m] >= num) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }
}
