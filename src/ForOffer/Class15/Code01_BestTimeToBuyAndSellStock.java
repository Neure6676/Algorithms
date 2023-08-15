package ForOffer.Class15;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
public class Code01_BestTimeToBuyAndSellStock {

    public int maxProfit0(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        // [7,1,5,3,6,4]
        int max = Integer.MIN_VALUE;
        for (int buy = 0; buy < arr.length; buy++) {
            for (int sell = buy + 1; sell < arr.length; sell++) {
                int ans = arr[sell] - arr[buy];
                max = Math.max(max, ans);
            }
        }
        return Math.max(max, 0);
    }


    public int maxProfit(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int ans = 0;
        int min = arr[0];
        for (int buy = 1; buy < arr.length; buy++) {
            min = Math.min(arr[buy], min);
            ans = Math.max(ans, arr[buy] - min);
        }
        return Math.max(ans, 0);
    }
}
