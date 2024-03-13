package UltimateAlgo2024.DynamicProgramming.IntervalDP.Part1;

// https://leetcode.com/problems/burst-balloons/
public class Code05_BurstBalloons {

    // 记忆化搜索
    public static int maxCoins1(int[] nums) {
        int n = nums.length;
        // a b c d e
        // 1 a b c d e 1
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }
        int[][] dp = new int[n + 2][n + 2];
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                dp[i][j] = -1;
            }
        }
        return f(arr, 1, n, dp);
    }

    // arr[l...r]这些气球决定一个顺序，获得最大得分返回！
    // 一定有 : arr[l-1]一定没爆！
    // 一定有 : arr[r+1]一定没爆！
    // 尝试每个气球最后打爆
    public static int f(int[] arr, int l, int r, int[][] dp) {
        if (dp[l][r] != -1) {
            return dp[l][r];
        }
        int ans;
        if (l == r) {
            ans = arr[l - 1] * arr[l] * arr[r + 1];
        } else {
            // l   ....r
            // l +1 +2 .. r
            ans = Math.max(
                    arr[l - 1] * arr[l] * arr[r + 1] + f(arr, l + 1, r, dp), // l位置的气球最后打爆
                    arr[l - 1] * arr[r] * arr[r + 1] + f(arr, l, r - 1, dp));// r位置的气球最后打爆
            for (int k = l + 1; k < r; k++) {
                // k位置的气球最后打爆
                // l...k-1  k  k+1...r
                ans = Math.max(ans, arr[l - 1] * arr[k] * arr[r + 1] + f(arr, l, k - 1, dp) + f(arr, k + 1, r, dp));
            }
        }
        dp[l][r] = ans;
        return ans;
    }

    // 严格位置依赖的动态规划
    public static int maxCoins2(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }
        int[][] dp = new int[n + 2][n + 2];
        for (int i = 1; i <= n; i++) {
            dp[i][i] = arr[i - 1] * arr[i] * arr[i + 1];
        }
        for (int l = n, ans; l >= 1; l--) {
            for (int r = l + 1; r <= n; r++) {
                ans = Math.max(arr[l - 1] * arr[l] * arr[r + 1] + dp[l + 1][r],
                        arr[l - 1] * arr[r] * arr[r + 1] + dp[l][r - 1]);
                for (int k = l + 1; k < r; k++) {
                    ans = Math.max(ans, arr[l - 1] * arr[k] * arr[r + 1] + dp[l][k - 1] + dp[k + 1][r]);
                }
                dp[l][r] = ans;
            }
        }
        return dp[1][n];
    }
}
