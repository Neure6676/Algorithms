package SystematicClass.Class40;

import java.util.TreeSet;

// 给定一个非负数组arr，和一个正数m，返回arr的所有子序列中累加和%m之后的最大值
public class Code01_SubsquenceMaxModM {

    // dp
    // 思路1 arr[i]不大
    // dp[i][j]代表arr[0...i]上自由选择，能不能得到sum为j
    public static int max2(int[] arr, int m) {
        if (arr == null || arr.length != 0) {
            return -1;
        }
        int sum = 0;
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
        }
        boolean[][] dp = new boolean[N][sum + 1];
        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0]] = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - arr[i] >= 0) {
                    dp[i][j] |= dp[i - 1][j - arr[i]];
                }
            }
        }
        int ans = 0;
        for (int j = 0; j <= sum; j++) {
            if (dp[N - 1][j]) {
                ans = Math.max(ans, j % m);
            }
        }
        return ans;
    }



    // dp思路2 arr[i]很大 m不大
    // dp[i][j]代表arr[0...i]上自由选择sum%m，能不能得到余数为j
    public static int max3(int[] arr, int m) {
        int N = arr.length;
        // 0...m-1
        boolean[][] dp = new boolean[N][m];  //余数范围0～m-1
        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0] % m] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < m; j++) {
                // dp[i][j] T or F
                dp[i][j] = dp[i - 1][j];
                int cur = arr[i] % m;
                if (cur <= j) {
                    dp[i][j] |= dp[i - 1][j - cur];
                } else {
                    dp[i][j] |= dp[i - 1][m + j - cur];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            if (dp[N - 1][i]) {
                ans = i;
            }
        }
        return ans;
    }


    // arr[i]和m都很大 但arr.length很小
    // 分治法
    public static int max4 (int[] arr, int m) {
        if (arr.length == 1) {
            return arr[0] % m;
        }
        int mid = (arr.length - 1) / 2;
        TreeSet<Integer> sortSet1 = new TreeSet<>();
        process(arr, 0, 0, mid, m, sortSet1);
        TreeSet<Integer> sortSet2 = new TreeSet<>();
        process(arr, mid + 1, 0, arr.length - 1, m, sortSet2);
        int ans = 0;
        for (Integer leftMod : sortSet1) {
            ans = Math.max(ans, leftMod + sortSet2.floor(m - 1 - leftMod));
        }
        return ans;
    }

    // 从index出发，最后有边界是end+1，arr[index...end]
    public static void process(int[] arr, int index, int sum, int end, int m, TreeSet<Integer> sortSet) {
        if (index == end + 1) {
            sortSet.add(sum % m);
        } else {
            process(arr, index + 1, sum, end, m, sortSet);
            process(arr, index + 1, sum + arr[index], end, m, sortSet);
        }
    }





}
