package UltimateAlgo2024.DynamicProgramming.Knapsack.part2;

// https://leetcode.com/problems/regular-expression-matching/
public class Code04_RegularExpressionMatching {
    public static boolean isMatch1(String str, String pat) {
        char[] s = str.toCharArray();
        char[] p = pat.toCharArray();
        return f1(s, p, 0, 0);
    }

    // s[i....]能不能被p[j....]完全匹配出来
    // p[j]这个字符，一定不是'*'
    public static boolean f1(char[] s, char[] p, int i, int j) {
        if (i == s.length) {
            // s没了
            if (j == p.length) {
                // 如果p也没了，返回true
                return true;
            } else {
                // p还剩下一些后缀
                // 如果p[j+1]是*，那么p[j..j+1]可以消掉，然后看看p[j+2....]是不是都能消掉
                return j + 1 < p.length && p[j + 1] == '*' && f1(s, p, i, j + 2);
            }
        } else if (j == p.length) {
            // s有后缀
            // p没后缀了
            return false;
        } else {
            // s有后缀
            // p有后缀
            if (j + 1 == p.length || p[j + 1] != '*') {
                // s[i....]
                // p[j....]
                // 如果p[j+1]不是*，那么当前的字符必须能匹配：(s[i] == p[j] || p[j] == '?')
                // 同时，后续也必须匹配上：process1(s, p, i + 1, j + 1);
                return (s[i] == p[j] || p[j] == '.') && f1(s, p, i + 1, j + 1);
            } else {
                // 如果p[j+1]是*
                // 完全背包！
                // s[i....]
                // p[j....]
                // 选择1: 当前p[j..j+1]是x*，就是不让它搞定s[i]，那么继续 : process1(s, p, i, j + 2)
                boolean p1 = f1(s, p, i, j + 2);
                // 选择2: 当前p[j..j+1]是x*，如果可以搞定s[i]，那么继续 : process1(s, p, i + 1, j)
                // 如果可以搞定s[i] : (s[i] == p[j] || p[j] == '.')
                // 继续匹配 : process1(s, p, i + 1, j)
                boolean p2 = (s[i] == p[j] || p[j] == '.') && f1(s, p, i + 1, j);
                // 两个选择，有一个可以搞定就返回true，都无法搞定返回false
                return p1 || p2;
            }
        }
    }


    public boolean isMatch2(String s1, String p1) {
        char[] s = s1.toCharArray();
        char[] p = p1.toCharArray();
        int n = s.length, m = p.length;
        int[][] dp = new int[n + 1][m + 1];
        return f2(s, p, 0, 0, dp);
    }

    // s[i....]能不能被p[j....]完全匹配出来
    // p[j]这个字符，一定不是'*'
    public static boolean f2(char[] s, char[] p, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j] == 1;
        }
        boolean ans;
        if (i == s.length) {
            if (j == p.length) {
                ans = true;
            } else {
                // p[j+1]一定是*
                ans = j + 1 < p.length && p[j + 1] == '*' && f2(s, p, i, j + 2, dp);
            }
        } else if (j == p.length) {
            ans = false;
        } else {
            // j + 1不是*
            if (j + 1 == p.length || p[j + 1] != '*') {
                ans = (s[i] == p[j] || p[j] == '.') && f2(s, p, i + 1, j + 1, dp);
            } else {
                // 完全背包
                boolean p1 = f2(s, p, i, j + 2, dp); // 0个p[j]
                boolean p2 = (s[i] == p[j] || p[j] == '.') && f2(s, p, i + 1, j, dp);
                ans = p1 || p2;
            }
        }
        dp[i][j] = ans ? 1 : -1;
        return ans;
    }


    public boolean isMatch3(String s1, String p1) {
        char[] s = s1.toCharArray();
        char[] p = p1.toCharArray();
        int n = s.length, m = p.length;
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[n][m] = true;
        for (int j = m - 1; j >= 0; j--) {
            dp[n][j] = j + 1 < m && p[j + 1] == '*' && dp[n][j + 2];
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (j + 1 == p.length || p[j + 1] != '*') {
                    dp[i][j] = (s[i] == p[j] || p[j] == '.') && dp[i + 1][j + 1];
                } else {
                    dp[i][j] = dp[i][j + 2] || ((s[i] == p[j] || p[j] == '.') && dp[i + 1][j]);
                }
            }
        }
        return dp[0][0];
    }



}
