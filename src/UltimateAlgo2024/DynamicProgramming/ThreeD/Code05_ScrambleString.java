package UltimateAlgo2024.DynamicProgramming.ThreeD;

// https://leetcode.com/problems/scramble-string/
public class Code05_ScrambleString {

    public boolean isScramble1(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int n = s1.length;
        // dp[l1][l2][len] : int 0 -> 没展开过
        // dp[l1][l2][len] : int -1 -> 展开过，返回的结果是false
        // dp[l1][l2][len] : int 1 -> 展开过，返回的结果是true
        int[][][] dp = new int[n][n][n + 1];
        return f1(s1, s2, 0, 0, n, dp);
    }

    public static boolean f1(char[] s1, char[] s2, int l1, int l2, int len, int[][][] dp) {
        if (len == 1) {
            return s1[l1] == s2[l2];
        }
        if (dp[l1][l2][len] != 0) {
            return dp[l1][l2][len] == 1;
        }
        boolean ans = false;
        for (int k = 1; k < len; k++) {
            if (f1(s1, s2, l1, l2, k, dp) && f1(s1, s2, l1 + k, l2 + k, len - k, dp)) {
                ans = true;
                break;
            }
        }
        if (!ans) {
            for (int k = 1; k < len; k++) {
                if (f1(s1, s2, l1, l2 + len - k, k, dp) && f1(s1, s2, l1 + k, l2, len - k, dp)) {
                    ans = true;
                    break;
                }
            }
        }
        dp[l1][l2][len] = ans == true ? 1 : -1;
        return ans;
    }




    public boolean isScramble2(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int n = s1.length;
        boolean[][][] dp = new boolean[n][n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = s1[i] == s2[j];
            }
        }
        for (int len = 2; len <= n; len++) {
            // 注意如下的边界条件 : l1 <= n - len l2 <= n - len
            for (int l1 = 0; l1 <= n - len; l1++) {
                for (int l2 = 0; l2 <= n - len; l2++) {
                    for (int k = 1; k < len; k++) {
                        if (dp[l1][l2][k] && dp[l1 + k][l2 + k][len - k]) {
                            dp[l1][l2][len] = true;
                            break;
                        }
                    }
                    if (!dp[l1][l2][len]) {
                        for (int i = l1 + 1, j = l2 + len - 1, k = 1; k < len; i++, j--, k++) {
                            if (dp[l1][j][k] && dp[i][l2][len - k]) {
                                dp[l1][l2][len] = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return dp[0][0][n];
    }
}
