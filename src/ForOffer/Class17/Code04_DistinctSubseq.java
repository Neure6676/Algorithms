package ForOffer.Class17;

// 测试链接 : https://leetcode.com/problems/distinct-subsequences/
public class Code04_DistinctSubseq {


    // 样本对应模型
    // dp[i][j] 表示从s的[0..i]中自由选择，有多少情况等于t[0..j]
    // 1。不拿i位置的值，即dp[i-1][j]
    // 2。拿i位置的值，要求s[i] = t[j], dp[i-1][j-1]
    public int numDistinct(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int N = s.length;
        int M = t.length;
        int[][] dp = new int[N][M];
        dp[0][0] = s[0] == t[0] ? 1 : 0;
        // 只用左下角 右上角s长度不够
        for (int i = 1; i <= s.length - 1; i++) {
            // 只要s[i] = t[0]就++ 不等就抄下来
            dp[i][0] = s[i] == t[0] ? (dp[i - 1][0] + 1) : dp[i - 1][0];
        }
        for (int i = 1; i <= s.length - 1; i++) {
            for (int j = 1; j <= Math.min(i, t.length - 1); j++) { // 只用左下角 右上角s长度不够
                dp[i][j] = dp[i - 1][j];
                if (s[i] == t[j]) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[s.length - 1][t.length - 1];
    }

}
