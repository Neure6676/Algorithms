package ForOffer.Class4;

/**
 * 返回一个数组中子数组最大累加和
 * 以每个位置结尾，可以往前扩多大得到最大sum
 *
 * 本题测试链接 : https://leetcode.com/problems/maximum-subarray/
 */
public class Code02_SubArrayMaxSum {

    public static int maxSubArray1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        int ans = dp[0];
        for (int i = 1; i < arr.length; i++) {
            dp[i] = Math.max(arr[i], dp[i - 1] + arr[i]);
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }

    public static int maxSubArray2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 上一步，dp的值
        // dp[0]
        int pre = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            pre = Math.max(arr[i], arr[i] + pre);
            max =  Math.max(max, pre);
        }
        return max;
    }
}
