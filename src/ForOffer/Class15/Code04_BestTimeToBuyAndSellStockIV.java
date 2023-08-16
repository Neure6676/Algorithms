package ForOffer.Class15;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/
 */
public class Code04_BestTimeToBuyAndSellStockIV {

    // 如果有N个数，最多有N/2个坡
    // 如果k大于N/2，就相当于不限制次数

    //从左往右的模型加一点业务限制
    //dp[i][j]表示：只使用arr[0, i]不超过j次校验，求最大值

    //有枚举行为 想观察位置法把它省掉
    public int maxProfit(int k, int[] arr) {
        if (arr == null || arr.length == 0 || k < 1) {
            return 0;
        }
        int N = arr.length;
        if (k >= N / 2) {
            return allTrans(arr);
        }
        int[][] dp = new int[N][k + 1];
        // 第一行第一列全是0
        for (int j = 1; j <= k; j++) {
            int p1 = dp[0][j];
            int best = Math.max(dp[1][j - 1] - arr[1], dp[0][j - 1] - arr[0]);
            dp[1][j] = Math.max(p1, best + arr[1]);
            // 一列一列地填，业务每个格是依赖下面的格子的
            for (int i = 2; i < N; i++) {
                p1 = dp[i - 1][j];
                int newP = dp[i][j - 1] - arr[i];
                best = Math.max(newP, best);
                dp[i][j] = Math.max(p1, best + arr[i]);
            }
        }
        return dp[N - 1][k];
    }


    public static int allTrans(int[] arr) {
        int ans = 0;
        for (int i = 1; i < arr.length; i++) {
            ans += Math.max(arr[i] - arr[i -1], 0);
        }
        return ans;
    }
}
