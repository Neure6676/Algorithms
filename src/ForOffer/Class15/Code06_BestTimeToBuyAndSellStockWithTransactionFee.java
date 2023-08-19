package ForOffer.Class15;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
public class Code06_BestTimeToBuyAndSellStockWithTransactionFee {

    public int maxProfit(int[] prices, int fee) {
        if (prices.length < 2) {
            return 0;
        }
        int N = prices.length;
        int bestBuy = -prices[0] - fee; // i位置必须buy
        int bestSell = 0;               // i位置必须sell
        for (int i = 1; i < N; i++) {
            // 如果此时buy
            int curBuy = bestSell - prices[i] - fee;
            int curSell = bestBuy + prices[i];
            bestBuy = Math.max(bestBuy, curBuy);
            bestSell = Math.max(bestSell, curSell);
        }
        return bestSell;
    }
}
