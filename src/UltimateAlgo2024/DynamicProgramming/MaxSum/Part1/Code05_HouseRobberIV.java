package UltimateAlgo2024.DynamicProgramming.MaxSum.Part1;

// https://leetcode.com/problems/house-robber-iv/
public class Code05_HouseRobberIV {

    // binary search + dp
    public int minCapability(int[] nums, int k) {
        int n = nums.length, l = Integer.MAX_VALUE, r = Integer.MIN_VALUE;
        for (int i : nums) {
            l = Math.min(l, i);
            r = Math.max(r, i);
        }
        int m, ans = 0;
        while (l <= r) {
            m = (l + r) / 2;
            if (mostRob(nums, n, m) >= k) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    // n = arr.length
    // return how many houses can be robbed
    public static int mostRob(int[] nums, int n, int m) {
        if (n == 1) {
            return nums[0] <= m ? 1 : 0;
        }
        if (n == 2) {
            return (nums[0] <= m || nums[1] <= m) ? 1 : 0;
        }
        int prePre = nums[0] <= m ? 1 : 0;
        int pre = (nums[0] <= m || nums[1] <= m) ? 1 : 0;
        for (int i = 2; i < n; i++) {
            int cur = Math.max(pre, (nums[i] <= m ? 1 : 0) + prePre);
            prePre = pre;
            pre = cur;
        }
        return pre;
    }

    // 贪心优化:能偷就偷，跳两个格子；不能偷跳一个格子
    public static int mostRob2(int[] nums, int n, int ability) {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= ability) {
                ans++;
                i++;
            }
        }
        return ans;
    }
}
