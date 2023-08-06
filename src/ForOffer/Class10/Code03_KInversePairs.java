package ForOffer.Class10;

/**
 * 给出两个整数n和k，找出所有包含从1到n的数字，且恰好拥有k个逆序对的不同的数组的个数
 * 逆序对的定义如下：对于数组的第i个和第j个元素，如果满i<j且a[i]>a[j]，则其为一个逆序对，否则不是
 * 由于答案可能很大，只需要返回 答案 mod 10^9+ 7 的值
 * Leetcode题目：https://leetcode.com/problems/k-inverse-pairs-array/
 */
public class Code03_KInversePairs {

    /**
     * Constraints:
     *
     * 1 <= n <= 1000
     * 0 <= k <= 1000
     *
     * 猜测O(n * k) -> dp[n][k] 样本对应模型：以最后一个字符作为可能性讨论的点
     * dp[i][j]：使用1～i，正好有j个逆序对的排列有几种
     * 具体举个例子分析：dp[5][3] =
     * 1) dp[4][3] + 最后放个5
     * 2) dp[4][2] + 最后一个前面加个5
     * 3) dp[4][1] + 倒数第二个前面加个5
     * 4) dp[4][0] + 倒数第三个前面加个5
     *
     * 结论：j<i时 dp[i][j] = dp[i-1][j。。。0]累加
     *
     * 斜率优化：dp[5][3] = dp[4][3。。。0]
     *         dp[5][4] = dp[4][4。。。0] = dp[5][3] + dp[4][4]
     *
     * j>=i时可能累加不到0   dp[5][7] = dp[4][7。。。3]
     *                    dp[5][8] = dp[4][8。。。4] = dp[5][7] + dp[4][8] - dp[4][3]
     *
     * 注意mod
     */
    public int kInversePairs(int n, int k) {
        int mod = 1000000007;
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % mod;
                if (j >= i) {
                    dp[i][j] = (dp[i][j] - dp[i - 1][j - i] + mod) % mod;
                }
            }
        }
        return dp[n][k];
    }
}
