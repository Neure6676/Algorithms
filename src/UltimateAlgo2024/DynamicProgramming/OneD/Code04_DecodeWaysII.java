package UltimateAlgo2024.DynamicProgramming.OneD;

import java.util.Arrays;

// https://leetcode.com/problems/decode-ways-ii/

/**
 * Note
 * use 1 char -> num or *
 * use 2 char
 * 1. 2 nums: must <= 26
 * 2. 2 *'s: 11-19, 21-26 = 15
 * 3. num and *: a. 1* -> 9 2* -> 6
 * 4. * and num: a. *to1 -> 1 b. *to2 -> 1 (must <= 6)
 */
public class Code04_DecodeWaysII {

    // top down
    public static long mod = 1000000007;

    public int numDecodings(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        long[] dp = new long[n + 1];
        Arrays.fill(dp, -1);
        return (int) f2(s, 0, dp);
    }

    public static long f2(char[] s, int i, long[] dp) {
        if (i == s.length) {
            return 1;
        }
        if (s[i] == '0') {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        long ans = f2(s, i + 1, dp) * (s[i] == '*' ? 9 : 1);
        if (i + 1 < s.length) {
            if (s[i] == '*' && s[i + 1] == '*') {
                ans += 15 * f2(s, i + 2, dp);
            } else if (s[i + 1] == '*') {
                ans += s[i] == '1' ? 9 * f2(s, i + 2, dp) : (s[i] == '2' ? 6 * f2(s, i + 2, dp) : 0);
            } else if (s[i] == '*') {
                ans += s[i + 1] <= '6' ? 2 * f2(s, i + 2, dp) : f2(s, i + 2, dp);
            } else if ((s[i] - '0') * 10 + s[i + 1] - '0' <= 26) {
                ans += f2(s, i + 2, dp);
            }
        }
        ans %= mod;
        dp[i] = ans;
        return ans;
    }



    // bottom up
    public int numDecodings2(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        long nextNext = 0;
        long next = 1;
        long ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s[i] != '0') {
                ans = next * (s[i] == '*' ? 9 : 1);
                if (i + 1 < n) {
                    if (s[i] == '*' && s[i + 1] == '*') {
                        ans += 15 * nextNext;
                    } else if (s[i + 1] == '*') {
                        ans += s[i] == '1' ? 9 * nextNext : (s[i] == '2' ? 6 * nextNext : 0);
                    } else if (s[i] == '*') {
                        ans += s[i + 1] <= '6' ? 2 * nextNext : nextNext;
                    } else if ((s[i] - '0') * 10 + s[i + 1] - '0' <= 26) {
                        ans += nextNext;
                    }
                }
                ans %= mod;
            }
            nextNext = next;
            next = ans;
            ans = 0;
        }
        return (int) next;
    }
}
