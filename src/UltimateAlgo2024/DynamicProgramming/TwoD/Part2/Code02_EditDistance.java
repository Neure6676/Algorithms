package UltimateAlgo2024.DynamicProgramming.TwoD.Part2;

// https://leetcode.com/problems/edit-distance/
public class Code02_EditDistance {

    /**
     * Note
     * 1. w1[i] == w2[j] i++, j++
     * 2. w1[i] != w2[j] a.delete i-1   b.replace i-1, j-1  c.insert j-1
     */
    public int minDistance(String word1, String word2) {
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        int n = w1.length;
        int m = w2.length;
        // the minimum number of operations required to convert w1 (len = i) to w2 (len = j)
        int[][] dp = new int[n + 1][m + 1];
        // col 1 - all delete
        for (int i = 1; i <= n; i++) {
            dp[i][0] = i;
        }
        // row 1 - all insert
        for (int i = 1; i <= m; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (w1[i - 1] == w2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // replace
                    int replace = dp[i - 1][j - 1];
                    int insert = dp[i][j - 1];
                    int delete = dp[i - 1][j];
                    dp[i][j] = Math.min(replace, Math.min(insert, delete)) + 1;
                }
            }
        }
        return dp[n][m];
    }
}
