package UltimateAlgo2024.DynamicProgramming.NoIteration.Stock;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
public class Code02_Stock2 {

    public int maxProfit(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i - 1], 0);
        }
        return ans;
    }

}
