package UltimateAlgo2024.DynamicProgramming.NoIteration.Stock;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
public class Code06_Stack6 {

    public static int maxProfit1(int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0;
        }
        // prepare[i] : 0...i范围上，可以做无限次交易，获得收益的同时一定要扣掉一次购买，所有情况中的最好情况
        int[] prepare = new int[n];
        // done[i] : 0...i范围上，可以做无限次交易，能获得的最大收益
        int[] done = new int[n];
        prepare[1] = Math.max(-prices[0], -prices[1]);
        done[1] = Math.max(0, prices[1] - prices[0]);
        for (int i = 2; i < n; i++) {
            done[i] = Math.max(done[i - 1], prepare[i - 1] + prices[i]);
            prepare[i] = Math.max(prepare[i - 1], done[i - 2] - prices[i]);
        }
        return done[n - 1];
    }

    // 只是把方法1做了变量滚动更新(空间压缩)
    // 并没有新的东西
    public static int maxProfit2(int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0;
        }
        // prepare : prepare[i-1]
        int prepare = Math.max(-prices[0], -prices[1]);
        // done2 : done[i-2]
        int done2 = 0;
        // done1 : done[i-1]
        int done1 = Math.max(0, prices[1] - prices[0]);
        for (int i = 2, curDone; i < n; i++) {
            // curDone : done[i]
            curDone = Math.max(done1, prepare + prices[i]);
            // prepare : prepare[i-1] -> prepare[i]
            prepare = Math.max(prepare, done2 - prices[i]);
            done2 = done1;
            done1 = curDone;
        }
        return done1;
    }
}
