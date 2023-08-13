package ForOffer.Class12;

/**
 * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
 * 返回p能否匹配s
 * 测试链接 : https://leetcode.com/problems/regular-expression-matching/
 */
public class Code04_RegularExpressionMatch {

    // 样本对应模型（行列模型）
    // dp[i][j]

    // 先检查有效性
    public static boolean isValid(char[] s, char[] e) {
        // s中不能有'.' or '*'
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '*' || s[i] == '.') {
                return false;
            }
        }
        // 开头的e[0]不能是'*'，没有相邻的'*'
        for (int i = 0; i < e.length; i++) {
            if (e[i] == '*' && (i == 0 || e[i - 1] == '*')) {
                return false;
            }
        }
        return true;
    }

    // 初始尝试版本，不包含斜率优化
    public static boolean isMatch1(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        return isValid(s, e) && process(s, e, 0, 0);
    }

    // str[si.....] 能不能被 exp[ei.....]配出来
    // 按ei是不是*划分
    public static boolean process(char[] s, char[] e, int si, int ei) {
        if (ei == e.length) { // exp 没了 str？ 必须同时没
            return si == s.length;
        }
        // exp[ei]还有字符
        // ei + 1位置的字符，不是*
        if (ei + 1 == e.length || e[ei + 1] != '*') {
            // ei + 1 不是*
            // str[si] 必须和 exp[ei] 能配上！
            return si != s.length && (e[ei] == s[si] || e[ei] == '.') && process(s, e, si + 1, ei + 1);
        }
        // exp[ei]还有字符
        // ei + 1位置的字符，是*!
        while (si != s.length && (e[ei] == s[si] || e[ei] == '.')) {
            if (process(s, e, si, ei + 2)) {
                return true;
            }
            si++;
        }
        return process(s, e, si, ei + 2);  // si已经结尾 或 当前字符对不上 或 4个a的时候
    }

    // 改记忆化搜索+斜率优化
    public static boolean isMatch2(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        if (!isValid(s, e)) {
            return false;
        }
        int[][] dp = new int[s.length + 1][e.length + 1];
        // dp[i][j] = 0, 没算过！
        // dp[i][j] = -1 算过，返回值是false
        // dp[i][j] = 1 算过，返回值是true
        return process2(s, e, 0, 0, dp);
    }

    public static boolean process2(char[] s, char[] e, int si, int ei, int[][] dp) {
        if (dp[si][ei] != 0) {
            return dp[si][ei] == 1;
        }
        boolean ans = false;
        if (ei == e.length) {
            ans = si == s.length;
        } else {
            if (ei + 1 == e.length || e[ei + 1] != '*') {
                ans = si != s.length && (s[si] == e[ei] || e[ei] == '.') && process2(s, e, si + 1, ei + 1, dp);
            } else {
                if (si == s.length) { // ei还有 且ei+1是*
                    ans = process2(s, e, si, ei + 2, dp); //ei和ei+1变0
                } else { // s没结束
                    if (s[si] != e[ei] && e[ei] != '.') {
                        // 配不上 只能变0
                        ans = process2(s, e, si, ei + 2, dp);
                    } else { // 配得上  斜率优化
                        ans = process2(s, e, si, ei + 2, dp) || process2(s, e, si + 1, ei, dp);
                    }
                }
            }
        }
        dp[si][ei] = ans ? 1 : -1;
        return ans;
    }
}
