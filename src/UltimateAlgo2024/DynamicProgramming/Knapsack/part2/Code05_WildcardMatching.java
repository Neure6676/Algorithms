package UltimateAlgo2024.DynamicProgramming.Knapsack.part2;

// https://leetcode.com/problems/wildcard-matching/
public class Code05_WildcardMatching {

    public boolean isMatch(String s1, String p1) {
        char[] s = s1.toCharArray();
        char[] p = p1.toCharArray();
        int n = s.length, m = p.length;
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[n][m] = true;
        for (int j = m - 1; j >= 0; j--) {
            dp[n][j] = p[j] == '*' && dp[n][j + 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (p[j] != '*') {
                    dp[i][j] = (s[i] == p[j] || p[j] == '?') && dp[i + 1][j + 1];
                } else {
                    dp[i][j] = dp[i][j + 1] || dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }

}
