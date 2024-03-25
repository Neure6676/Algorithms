package UltimateAlgo2024.DynamicProgramming.NoIteration.Stock;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
public class Code04_Stock4 {

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][] dp = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            int best = -prices[0];
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], best + prices[j]);
                best = Math.max(best, dp[i - 1][j] - prices[j]);
            }
        }
        return dp[k][n - 1];
    }

    // 就是股票问题2
    public static int free(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i - 1], 0);
        }
        return ans;
    }

    public static int maxProfit1(int k, int[] prices) {
        int n = prices.length;
        if (k >= n / 2) {
            // 这是一个剪枝
            // 如果k >= n / 2，那么代表所有上坡都可以抓到
            // 所有上坡都可以抓到 == 交易次数无限，所以回到股票问题2
            return free(prices);
        }
        int[][] dp = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1];
                for (int p = 0; p < j; p++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][p] + prices[j] - prices[p]);
                }
            }
        }
        return dp[k][n - 1];
    }

    public static int maxProfit2(int k, int[] prices) {
        int n = prices.length;
        if (k >= n / 2) {
            // 这是一个剪枝
            // 如果k >= n / 2，那么代表所有上坡都可以抓到
            // 所有上坡都可以抓到 == 交易次数无限，所以回到股票问题2
            return free(prices);
        }
        int[][] dp = new int[k + 1][n];
        for (int i = 1, best; i <= k; i++) {
            best = dp[i - 1][0] - prices[0];
            for (int j = 1; j < n; j++) {
                // 用best变量替代了枚举行为
                dp[i][j] = Math.max(dp[i][j - 1], best + prices[j]);
                best = Math.max(best, dp[i - 1][j] - prices[j]);
            }
        }
        return dp[k][n - 1];
    }

    // 对方法2进行空间压缩的版本
    public static int maxProfit3(int k, int[] prices) {
        int n = prices.length;
        if (k >= n / 2) {
            // 这是一个剪枝
            // 如果k >= n / 2，那么代表所有上坡都可以抓到
            // 所有上坡都可以抓到 == 交易次数无限，所以回到股票问题2
            return free(prices);
        }
        int[] dp = new int[n];
        for (int i = 1, best, tmp; i <= k; i++) {
            best = dp[0] - prices[0];
            for (int j = 1; j < n; j++) {
                tmp = dp[j];
                dp[j] = Math.max(dp[j - 1], best + prices[j]);
                best = Math.max(best, tmp - prices[j]);
            }
        }
        return dp[n - 1];
    }
}
