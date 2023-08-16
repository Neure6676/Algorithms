package ForOffer.Class15;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
public class Code02_BestTimeToBuyAndSellStockII {

    // 每次上升的量加在一起就是答案
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i-1], 0);
        }
        return ans;
    }

}
