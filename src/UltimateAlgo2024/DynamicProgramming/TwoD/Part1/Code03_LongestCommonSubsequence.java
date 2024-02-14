package UltimateAlgo2024.DynamicProgramming.TwoD.Part1;

// https://leetcode.com/problems/longest-common-subsequence/
public class Code03_LongestCommonSubsequence {

    /**
     * Note:
     * 1. s1[i] == s2[j] ans + 1
     * 2. s1[i] and s2[j - 1]
     * 3. s1[i - 1] and s2[j]
     */

    // top down
    public int longestCommonSubsequence2(String text1, String text2) {
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        int n = s1.length;
        int m = s2.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = -1;
            }
        }
        return f2(s1, s2, n, m, dp);
    }

    public static int f2(char[] s1, char[] s2, int len1, int len2, int[][] dp) {
        if (len1 == 0 || len2 == 0) {
            return 0;
        }
        if (dp[len1][len2] != -1) {
            return dp[len1][len2];
        }
        int ans = 0;
        if (s1[len1 - 1] == s2[len2 - 1]) {
            ans = f2(s1, s2, len1 - 1, len2 - 1, dp) + 1;
        } else {
            int p1 = f2(s1, s2, len1, len2 - 1, dp);
            int p2 = f2(s1, s2, len1 - 1, len2, dp);
            ans = Math.max(p1, p2);
        }
        dp[len1][len2] = ans;
        return ans;
    }


    public int longestCommonSubsequence3(String text1, String text2) {
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        int n = s1.length;
        int m = s2.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int len1 = 1; len1 <= n; len1++) {
            for (int len2 = 1; len2 <= m; len2++) {
                int ans = 0;
                if (s1[len1 - 1] == s2[len2 - 1]) {
                    ans = dp[len1 - 1][len2 - 1] + 1;
                } else {
                    ans = Math.max(dp[len1][len2 - 1], dp[len1 - 1][len2]);
                }
                dp[len1][len2] = ans;
            }
        }
        return dp[n][m];
    }


}
