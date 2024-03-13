package UltimateAlgo2024.DynamicProgramming.IntervalDP.Part1;

// https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
public class Code01_MinimumInsertionToPalindrome {

    public int minInsertions1(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        return f1(s, 0, n - 1, dp);
    }

    // s[l....r]这个范围上的字符串，整体都变成回文串
    // 返回至少插入几个字符
    public static int f1(char[] s, int l, int r, int[][] dp) {
        if (dp[l][r] != -1) {
            return dp[l][r];
        }
        int ans = 0;
        if (l == r) {
            ans = 0;
        } else if (l + 1 == r) {
            ans = s[l] == s[r] ? 0 : 1;
        } else {
            if (s[l] == s[r]) {
                ans = f1(s, l + 1, r - 1, dp);
            } else {
                int p1 = f1(s, l + 1, r, dp);
                int p2 = f1(s, l, r - 1, dp);
                ans = Math.min(p1, p2) + 1;
            }
        }
        dp[l][r] = ans;
        return ans;
    }


    public int minInsertions2(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n - 1; i++) {
            dp[i][i] = 0;
            dp[i][i + 1] = s[i] == s[i + 1] ? 0 : 1;
        }
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                if (s[i] == s[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[0][n - 1];
    }
}
