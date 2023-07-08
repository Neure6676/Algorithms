package SystematicClass.Class20;

//背包问题

/**
 * 背包问题
 * 给定两个长度都为N的数组weights和values，weights[i]和values[i]分别代表 i号物品的重量和价值
 * 给定一个正数bag，表示一个载重bag的袋子，装的物品不能超过这个重量
 * 返回能装下的最大价值
 */
public class Code01_Knapsack {

    public static int maxVal1(int[] w, int[] v, int bag) {
        if (w == null || w.length == 0 || v == null || v.length == 0) {
            return 0;
        }
        return process1(w, v, 0, bag);
    }

    // 当前考虑到index号货物，只考虑他之后的，在不超过rest时的最大value
    private static int process1(int[] w, int[] v, int index, int rest) {
        if (index == w.length) {
            return 0;
        }
        // 不要当前货
        int p1 = process1(w, v, index + 1, rest);
        // 要当前货
        int p2 = v[index] + process1(w, v, index + 1, rest - w[index]);
        return Math.max(p1, p2);
    }



    public static int maxVal2(int[] w, int[] v, int bag) {
        if (w == null || w.length == 0 || v == null || v.length == 0 || w.length != v.length) {
            return 0;
        }
        return process0(w, v, bag, 0, 0);
    }

    public static int process0(int[] w, int[] v, int bagRemains, int curVal, int index) {
        if (bagRemains < 0 || index == w.length) {
            return curVal;
        } else if(bagRemains < w[index]) {
            curVal = process0(w, v, bagRemains, curVal, index + 1);
        } else {
            // 要了当前这个数
            int p1 = process0(w, v, bagRemains - w[index], curVal + v[index], index + 1);
            // 没要当前这个数
            int p2 = process0(w, v, bagRemains, curVal, index + 1);
            curVal = Math.max(p1, p2);
        }
        return curVal;
    }



    // dp
    public static int dp(int[] w, int[] v, int bag) {
        if (w == null || w.length == 0 || v == null || v.length == 0) {
            return 0;
        }
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        //根据base case，dp[N][..] = 0
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = v[index] + rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }




    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
        int[] values = { 5, 6, 3, 19, 12, 4, 2 };
        int bag = 15;
        System.out.println(maxVal1(weights, values, bag));
        System.out.println(maxVal2(weights, values, bag));
        System.out.println(dp(weights, values, bag));

    }
}
