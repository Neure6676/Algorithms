package ForOffer.Class10;

/**
 * 给定一个布尔表达式和一个期望的布尔结果 result，布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。
 * Leetcode题目：https://leetcode-cn.com/problems/boolean-evaluation-lcci/
 */
public class Code05_BooleanEvaluation {

    public static class Info {
        public int t;
        public int f;

        public Info(int tr, int fa) {
            t = tr;
            f = fa;
        }
    }

    public static Info func(char[] str, int L, int R, Info[][] dp) {
        if (dp[L][R] != null) {
            return dp[L][R];
        }
        int t = 0;
        int f = 0;
        if (L == R) {
            t = str[0] == '1' ? 1 : 0;
            f = str[0] == '0' ? 1 : 0;
        } else {
            // 按运算符位置划分
            for (int split = L + 1; split < R; split += 2) {
                Info leftInfo = func(str, L, split - 1, dp);
                Info rightInfo = func(str, split + 1, R, dp);
                int a = leftInfo.t;
                int b = leftInfo.f;
                int c = rightInfo.t;
                int d = rightInfo.f;
                switch(str[split]) {
                    case '&':
                        t += a * c;
                        f += a * d + b * c + b * d;
                        break;
                    case '|':
                        t += a * c + a * d + b * c;
                        f += b * d;
                        break;
                    case '^':
                        t += a * d + b * c;
                        f += a * c + b * d;
                        break;
                }
            }
        }
        dp[L][R] = new Info(t, f);
        return new Info(t, f);
    }

    public static int countEval0(String express, int desired) {
        if (express == null || express.equals("")) {
            return 0;
        }
        char[] exp = express.toCharArray();
        int N = exp.length;
        Info[][] dp = new Info[N][N];
        Info allInfo = func(exp, 0, exp.length - 1, dp);
        return desired == 1 ? allInfo.t : allInfo.f;
    }

}
