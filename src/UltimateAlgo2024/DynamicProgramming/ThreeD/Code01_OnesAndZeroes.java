package UltimateAlgo2024.DynamicProgramming.ThreeD;

// https://leetcode.com/problems/ones-and-zeroes/
public class Code01_OnesAndZeroes {

    public static int ones;

    public static int zeros;

    public static void count(String str) {
        zeros = 0;
        ones = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                zeros++;
            } else {
                ones++;
            }
        }
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length][m + 1][n + 1];
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j <= m; j++ ) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return f2(strs, 0, m, n, dp);
    }

    // strs[i....] at most m 0's and n 1's
    // how many strs at most
    public static int f2(String[] strs, int i, int z, int o, int[][][] dp) {
        if (i == strs.length) {
            return 0;
        }
        if (dp[i][z][o] != -1) {
            return dp[i][z][o];
        }
        int p1 = f2(strs, i + 1, z, o, dp);
        int p2 = 0;
        // count函数用之前再调用，否则全局变量会被子过程改写！！！
        count(strs[i]);
        if (zeros <= z && ones <= o) {
            p2 = 1 + f2(strs, i + 1, z - zeros, o - ones, dp);
        }
        int ans = Math.max(p1, p2);
        dp[i][z][o] = ans;
        return ans;
    }


    public int findMaxForm2(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
        for (int i = strs.length - 1; i >= 0; i--) {
            count(strs[i]);
            for (int z = 0; z <= m; z++ ) {
                for (int o = 0; o <= n; o++) {
                    int p1 = dp[i + 1][z][o];
                    int p2 = 0;
                    if (zeros <= z && ones <= o) {
                        p2 = 1 + dp[i + 1][z - zeros][o - ones];
                    }
                    int ans = Math.max(p1, p2);
                    dp[i][z][o] = ans;
                }
            }
        }
        return dp[0][m][n];
    }


    public static int findMaxForm3(String[] strs, int m, int n) {
        // 代表i == len
        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {
            // 每个字符串逐渐遍历即可
            // 更新每一层的表
            // 和之前的遍历没有区别
            count(s);
            for (int z = m; z >= zeros; z--) {
                for (int o = n; o >= ones; o--) {
                    dp[z][o] = Math.max(dp[z][o], 1 + dp[z - zeros][o - ones]);
                }
            }
        }
        return dp[m][n];
    }


}
