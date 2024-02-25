package UltimateAlgo2024.DynamicProgramming.MaxSum.Part1;

// https://leetcode.com/problems/house-robber-ii/
public class Code04_HouseRobberII {

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        return Math.max(best(nums, 1, n - 1), nums[0] + best(nums, 2, n - 2));
    }

    public static int best(int[] arr, int l, int r) {
        if (l > r) {
            return 0;
        }
        if (l == r) {
            return arr[l];
        }
        if (r - l == 1) {
            return Math.max(arr[l], arr[r]);
        }
        int prePre = arr[l];
        int pre = Math.max(arr[l], arr[l + 1]);
        for (int i = l + 2; i <= r; i++) {
            int cur = Math.max(pre, prePre + arr[i]);
            prePre = pre;
            pre = cur;
        }
        return pre;
    }
}
