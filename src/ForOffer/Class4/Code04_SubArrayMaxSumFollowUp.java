package ForOffer.Class4;

/**
 * 返回一个数组中所选数字不能相邻的情况下最大子序列累加和
 * 在线测试链接 : https://leetcode.com/problems/house-robber/
 */
public class Code04_SubArrayMaxSumFollowUp {

    // 从左往右
    public static int rob(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);
        for (int i = 2; i < arr.length; i++) {
            int p1 = dp[i - 1];
            //int p2 = dp[i - 2] + arr[i];
            int p2 = Math.max(dp[i - 2], 0) + arr[i]; // 暗含了只要arr[i]的情况
            dp[i] = Math.max(p1, p2);
            // dp[i] = Math.max(Math.max(dp[i - 1], arr[i]), arr[i] + dp[i - 2]);
        }
        return dp[arr.length - 1];
    }


}
