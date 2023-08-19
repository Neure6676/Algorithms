package ForOffer.Class15;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
public class Code05_BestTimeToBuyAndSellStockWithCooldown {

    // buy[i] : 在0...i范围上，最后一次操作是buy动作，所以要减去某次买的价格
    // 1.i没买 2。买了，但要cooldown一天
    // buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i])
    // sell[i] : 在0...i范围上，最后一次操作是sell动作
    // sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i])
    public static int maxProfit(int[] prices){
        if (prices.length < 2) {
            return 0;
        }
        int N = prices.length;
        int[] buy = new int[N];
        int[] sell = new int[N];
        buy[1] = Math.max(-prices[0], -prices[1]);
        sell[1] = Math.max(0, prices[1] - prices[0]);

        for (int i = 2; i < N; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[N - 1];
    }




}
