package UltimateAlgo2024.DynamicProgramming.TwoD.Part1;

// https://leetcode.com/problems/longest-palindromic-subsequence/
public class Code04_LongestPalindromicSubsequence {

    /**
     * note:
     * method 1: longest common subseq with its reversed str
     * method 2: section dp 区间dp
     *           1. s[l] != s[r] a.[l+1, r-1] b.[l+1, r] c.[l, r-1]
     *           2. s[l] == s[r] [l+1, r-1] + 1
     */
    public int longestPalindromeSubseq1(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        return f1(s, 0, n - 1, dp);
    }

    public static int f1(char[] s, int l, int r, int[][] dp) {
        if (l == r) {
            return 1;
        }
        if (l + 1 == r) {
            return s[l] == s[r] ? 2 : 1;
        }
        if (dp[l][r] != -1) {
            return dp[l][r];
        }
        int ans = 0;
        if (s[l] == s[r]) {
            ans = f1(s, l + 1, r - 1, dp) + 2;
        } else {
            ans = Math.max(f1(s, l + 1, r, dp), f1(s, l, r - 1, dp));
        }
        dp[l][r] = ans;
        return ans;
    }


    public int longestPalindromeSubseq2(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
            if (i < n - 1) {
                dp[i][i + 1] = s[i] == s[i + 1] ? 2 : 1;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                int ans = 0;
                if (s[i] == s[j]) {
                    ans = dp[i + 1][j - 1] + 2;
                } else {
                    ans = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
                dp[i][j] = ans;
            }
        }
        return dp[0][n - 1];
    }

    public static int longestPalindromeSubseq(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[] dp = new int[n];
        for (int l = n - 1, leftDown = 0, backup; l >= 0; l--) {
            // dp[l] : 想象中的dp[l][l]
            dp[l] = 1;
            if (l + 1 < n) {
                leftDown = dp[l + 1];
                // dp[l+1] : 想象中的dp[l][l+1]
                dp[l + 1] = s[l] == s[l + 1] ? 2 : 1;
            }
            for (int r = l + 2; r < n; r++) {
                backup = dp[r];
                if (s[l] == s[r]) {
                    dp[r] = 2 + leftDown;
                } else {
                    dp[r] = Math.max(dp[r], dp[r - 1]);
                }
                leftDown = backup;
            }
        }
        return dp[n - 1];
    }

}
