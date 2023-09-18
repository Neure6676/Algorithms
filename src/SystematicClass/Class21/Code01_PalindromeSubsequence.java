package SystematicClass.Class21;


// 测试链接：https://leetcode.com/problems/longest-palindromic-subsequence/
/**
 * 给定一个字符串str，返回这个字符串的最长回文子序列长度
 * 比如 ： str = “a12b3c43def2ghi1kpm”
 * 最长回文子序列是“1234321”或者“123c321”，返回长度7
 *
 * 子序列（subsequence）是可以不连续的，子串（substring）必须连续
 */
public class Code01_PalindromeSubsequence {
    // 方法1：一个串和他逆序串的最长公共子序列就是原始串的最长回文子序列
    // 方法2：f(str, L, R)：在L到R范围上，最长回文子序列
    public static int longestPalindromeSubseq1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        return f(str, 0, s.length() - 1);
    }

    public static int f(char[] str, int L, int R) {
        //base case
        if (L == R) {
            return 1;
        }
        if (L == R - 1) { //只有俩
            return str[L] == str[R] ? 2 : 1;
        }
        /**
         * 分类
         * 1。既不以L开头，又不以R结尾 a12321b
         * 2。以L开头，不以R结尾 12a321b
         * 3。不以L开头，以R结尾 a123b21
         * 4。以L开头，以R结尾 12321   str[L] == str[R]
         */
        int p1 = f(str, L + 1, R - 1);
        int p2 = f(str, L, R - 1);
        int p3 = f(str, L + 1, R);
        int p4 = str[L] != str[R] ? 0 : (2 + f(str, L + 1, R - 1));  //不相等直接等于0：如果不相等肯定是前面3种之一
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }


    // 动态规划
    public static int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        // 只要L和R定了，f（）就确定
        // L和R范围都是0～N-1
        int N = s.length();
        int[][] dp = new int[N][N];
        dp[N-1][N-1] = 1; // 先填最后一个，剩下的两个两个填
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int L = N - 3; L >= 0; L--) { //从下往上填
            for (int R = L + 2; R < N; R++) {
                // 改递归
                int p1 = dp[L + 1][R - 1];
                int p2 = dp[L][R - 1];
                int p3 = dp[L + 1][R];
                int p4 = (str[L] != str[R] || dp[L + 1][R - 1] != R -L -1) ? 0 : (2 + dp[L + 1][R - 1]);
                dp[L][R] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }
        return dp[0][N - 1];
    }






    // 继续优化
    public static int longestPalindromeSubseq3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        // 只要L和R定了，f（）就确定
        // L和R范围都是0～N-1
        int N = s.length();
        int[][] dp = new int[N][N];
        dp[N-1][N-1] = 1; // 先填最后一个，剩下的两个两个填
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int L = N - 3; L >= 0; L--) { //从下往上填
            for (int R = L + 2; R < N; R++) {
                // 优化 把第一种可能性省去
                // 为什么能省：每个位置都是由p1,p2,p3的最大值得到，而p2,p3必然大于p1（p1曾是p2的p3）
                dp[L][R] = Math.max(dp[L][R - 1], dp[L + 1][R]);
                if (str[L] == str[R]) {
                    dp[L][R] = Math.max(dp[L][R], 2 + dp[L + 1][R - 1]);
                }
            }
        }
        return dp[0][N - 1];
    }

    public static void main(String[] args) {
        String s = "acabdkaca";
        System.out.println(longestPalindromeSubseq2(s));
    }

}
