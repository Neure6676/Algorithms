package UltimateAlgo2024.DynamicProgramming.OneD;

// https://leetcode.com/problems/longest-valid-parentheses/

/**
 * for i in n
 * the substring have be ended with i, what's the longest ans?
 * 1. str[i] == "(" -> dp[i] = 0
 * 2. str[i] == ")" -> int p = last invalid pos according to dp[i - 1]
 *                     a. s[p] = ')' dp[i] = 0
 *                     b. s[p] = '(' dp[i] = dp[i - 1] + 2; + dp[p - 1]
 *                     c. p out of boundary
 */
public class Code06_LongestValidParentheses {

    public int longestValidParentheses(String str) {
        char[] s = str.toCharArray();
        int[] dp = new int[s.length];
        int ans = 0;
        for (int i = 1, p; i < s.length; i++) {
            if (s[i] == ')') {
                p = i - dp[i - 1] - 1;
                if (p >= 0 && s[p] == '(') {
                    dp[i] = dp[i - 1] + 2 + (p > 0 ? dp[p - 1] : 0);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

}
