package UltimateAlgo2024.DynamicProgramming.TwoD.Part2;

// https://leetcode.com/problems/interleaving-string/
public class Code03_InterleavingString {

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        char[] c3 = s3.toCharArray();
        int n = c1.length;
        int m = c2.length;
        // dp[i][j]: can I concat c1[0~i-1] and c2[0~j-1] to get c3[0~i+j-1]
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = (dp[i - 1][0] && c1[i - 1] == c3[i - 1]);
        }
        for (int j = 1; j <= m; j++) {
            dp[0][j] = (dp[0][j - 1] && c2[j - 1] == c3[j - 1]);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = (c1[i - 1] == c3[i + j - 1] && dp[i - 1][j]) || (c2[j - 1] == c3[i + j - 1] && dp[i][j - 1]);
            }
        }
        return dp[n][m];
    }


    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        char[] c3 = s3.toCharArray();
        int n = c1.length;
        int m = c2.length;
        boolean[] dp = new boolean[m + 1];
        dp[0] = true;
        for (int j = 1; j <= m; j++) {
            dp[j] = (dp[j - 1] && c2[j - 1] == c3[j - 1]);
        }
        for (int i = 1; i <= n; i++) {
            dp[0] = (dp[0] && c1[i - 1] == c3[i - 1]);
            for (int j = 1; j <= m; j++) {
                dp[j] = (c1[i - 1] == c3[i + j - 1] && dp[j]) || (c2[j - 1] == c3[i + j - 1] && dp[j - 1]);
            }
        }
        return dp[m];
    }

}
