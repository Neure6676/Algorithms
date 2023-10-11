package ForOffer.Class20;

/**
 * 给定一个字符串str，当然可以生成很多子序列，返回有多少个子序列是回文子序列，空序列不算回文
 * 比如，str = “aba”，回文子序列：{a}、{a}、 {a,a}、 {b}、{a,b,a}，返回5
 */
public class Code04_PalindromeWays {

    /**
     * dp[L][R]表示str[L...R]上有多少子序列是回文
     * 范围上尝试模型：以开头和结尾字符作为可能性讨论的点,只使用右上三角
     * 可能性：
     * 1. str[L] != str[R]
     * a. L不要 R不要
     * b. L要 R不要
     * c. L不要 R要
     * dp[L+1][R] a+c
     * dp[L][R-1] a+b
     * dp[L+1][R-1] a
     * a+b + a+c - a = dp[L+1][R] + dp[L][R-1] - dp[L+1][R-1]
     *
     * 2. str[L] == str[R]
     * a. L不要 R不要
     * b. L要 R不要
     * c. L不要 R要
     * d. L要 R要
     * dp[L+1][R] + dp[L][R-1] - dp[L+1][R-1] + dp[L+1][R-1]（之前中间有几种 加上L和R又是新的） + 1(中间空)
     */
    public static int palindromeWays(String str) {
        if (str == null || str.length() != 0) {
            return 0;
        }
        char[] s = str.toCharArray();
        int n = s.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = s[i] == s[i + 1] ? 3 : 2;
        }
        for (int L = n - 3; L >= 0; L--) {
            for (int R = L + 2; R < n; R++) {
                dp[L][R] = dp[L + 1][R] + dp[L][R - 1] - dp[L + 1][R - 1];
                if (s[L] == s[R]) {
                    dp[L][R] = dp[L + 1][R - 1] + 1;
                }
            }
        }
        return dp[0][n - 1];
    }

}
