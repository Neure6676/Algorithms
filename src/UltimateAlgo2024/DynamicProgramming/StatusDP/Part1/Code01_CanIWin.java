package UltimateAlgo2024.DynamicProgramming.StatusDP.Part1;

// https://leetcode.com/problems/can-i-win/
public class Code01_CanIWin {

    public static boolean canIWin(int n, int m) {
        if (m == 0) {
            // 来自题目规定
            return true;
        }
        if (n * (n + 1) / 2 < m) {
            // 如果1~n数字全加起来
            // 累加和和是n * (n+1) / 2，都小于m
            // 那么不会有赢家，也就意味着先手不会获胜
            return false;
        }
        // dp[status] == 0 代表没算过
        // dp[status] == 1 算过，答案是true
        // dp[status] == -1 算过，答案是false
        int[] dp = new int[1 << (n + 1)];
        return f(n, (1 << (n + 1)) - 1, m, dp);
    }

    // 如果1~7范围的数字都能选，那么status的状态为：
    // 1 1 1 1 1 1 1 1
    // 7 6 5 4 3 2 1 0
    // 0位弃而不用
    // 如果1~7范围的数字，4、2已经选了不能再选，那么status的状态为：
    // 1 1 1 0 1 0 1 1
    // 7 6 5 4 3 2 1 0
    // 0位弃而不用
    // f的含义 :
    // 数字范围1~n，当前的先手，面对status给定的数字状态
    // 在累加和还剩rest的情况下
    // 返回当前的先手能不能赢，能赢返回true，不能赢返回false
    public static boolean f(int n, int status, int rest, int[] dp) {
        if (rest <= 0) {
            return false;
        }
        if (dp[status] != 0) {
            return dp[status] == 1;
        }
        // rest > 0
        boolean ans = false;
        for (int i = 1; i <= n; i++) {
            // 考察所有数字，但是不能选择之前选了的数字
            if ((status & (1 << i)) != 0 && !f(n, (status ^ (1 << i)), rest - i, dp)) {
                ans = true;
                break;
            }
        }
        dp[status] = ans ? 1 : -1;
        return ans;
    }
}
