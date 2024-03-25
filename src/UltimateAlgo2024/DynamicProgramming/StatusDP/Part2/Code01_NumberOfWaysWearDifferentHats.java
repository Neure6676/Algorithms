package UltimateAlgo2024.DynamicProgramming.StatusDP.Part2;

import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/number-of-ways-to-wear-different-hats-to-each-other
public class Code01_NumberOfWaysWearDifferentHats {

    public static int MOD = 1000000007;

    public static int numberWays(List<List<Integer>> arr) {
        // 帽子颜色的最大值
        int m = 0;
        for (List<Integer> person : arr) {
            for (int hat : person) {
                m = Math.max(m, hat);
            }
        }
        int n = arr.size();
        // 1 ~ m 帽子，能满足哪些人，状态信息 -> int
        int[] hats = new int[m + 1];
        for (int pi = 0; pi < n; pi++) {
            for (int hat : arr.get(pi)) {
                hats[hat] |= 1 << pi;
            }
        }
        int[][] dp = new int[m + 1][1 << n];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], -1);
        }
        return f2(hats, m, n, 1, 0, dp);
    }

    // m : 帽子颜色的最大值, 1 ~ m
    // n : 人的数量，0 ~ n-1
    // i : 来到了什么颜色的帽子
    // s : n个人，谁没满足状态就是0，谁满足了状态就是1
    // dp : 记忆化搜索的表
    // 返回 : 有多少种方法
    public static int f1(int[] hats, int m, int n, int i, int s, int[][] dp) {
        if (s == (1 << n) - 1) {
            return 1;
        }
        // 还有人没满足
        if (i == m + 1) {
            return 0;
        }
        if (dp[i][s] != -1) {
            return dp[i][s];
        }
        // 可能性1 : i颜色的帽子，不分配给任何人
        int ans = f1(hats, m, n, i + 1, s, dp);
        // 可能性2 : i颜色的帽子，分配给可能的每一个人
        int cur = hats[i];
        // 用for循环从0 ~ n-1枚举每个人
        for (int p = 0; p < n; p++) {
            if ((cur & (1 << p)) != 0 && (s & (1 << p)) == 0) {
                ans = (ans + f1(hats, m, n, i + 1, s | (1 << p), dp)) % MOD;
            }
        }
        dp[i][s] = ans;
        return ans;
    }

    public static int f2(int[] hats, int m, int n, int i, int s, int[][] dp) {
        if (s == (1 << n) - 1) {
            return 1;
        }
        if (i == m + 1) {
            return 0;
        }
        if (dp[i][s] != -1) {
            return dp[i][s];
        }
        int ans = f2(hats, m, n, i + 1, s, dp);
        int cur = hats[i];
        // 不用for循环枚举
        // 从当前帽子中依次提取能满足的人
        // 提取出二进制状态中最右侧的1，讲解030-异或运算，题目5，Brian Kernighan算法
        // cur :
        // 0 0 0 1 1 0 1 0
        // -> 0 0 0 0 0 0 1 0
        // -> 0 0 0 0 1 0 0 0
        // -> 0 0 0 1 0 0 0 0
        int rightOne;
        while (cur != 0) {
            rightOne = cur & -cur;
            if ((s & rightOne) == 0) {
                ans = (ans + f2(hats, m, n, i + 1, s | rightOne, dp)) % MOD;
            }
            cur ^= rightOne;
        }
        dp[i][s] = ans;
        return ans;
    }

}
