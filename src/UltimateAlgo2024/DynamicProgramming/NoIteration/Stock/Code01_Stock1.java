package UltimateAlgo2024.DynamicProgramming.NoIteration.Stock;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
public class Code01_Stock1 {

    public int maxProfit(int[] prices) {
        int ans = 0;
        for (int i = 1, min = prices[0]; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            ans = Math.max(ans, prices[i] - min);
        }
        return ans;
    }
}
