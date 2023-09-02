package ForOffer.Class16;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 给定一个正数数组arr，
 * 返回arr的子集不能累加出的最小正数
 * 1）正常怎么做？
 * 2）如果arr中肯定有1这个值，怎么做？
 */
public class Code02_SmallestUnFormedSum {

    public static int unformedSum1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        HashSet<Integer> set = new HashSet<>();
        process(arr, 0, 0, set);
        // 找能搞定的min, 就是arr的min
        int min = Integer.MAX_VALUE;
        for (int j : arr) {
            min = Math.min(min, j);
        }
        for (int i = min; i < Integer.MIN_VALUE; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return 0;
    }

    public static void process(int[] arr, int i, int pre, HashSet<Integer> set) {
        if (i == arr.length) {
            set.add(pre);
            return;
        }
        process(arr, i + 1, pre, set);
        process(arr, i + 1, pre + arr[i], set);
    }



    public static int unformedSum2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 1;
        }
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            min = Math.min(min, arr[i]);
        }
        // boolean[][] dp ...
        int N = arr.length;
        boolean[][] dp = new boolean[N][sum + 1];
        for (int i = 0; i < N; i++) {// arr[0..i] 0
            dp[i][0] = true;
        }
        dp[0][arr[0]] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j] || (j - arr[i] >= 0 && dp[i - 1][j - arr[i]]);
            }
        }
        for (int j = min; j <= sum; j++) {
            if (!dp[N - 1][j]) {
                return j;
            }
        }
        return sum + 1;
    }


    // 如果arr中肯定有1这个值，怎么做？
    // range = i 从1到i都能搞定
    // 假设来到i位置值是17，之前range = 100，现在range等于117
    public static int unformedSum3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr); // O (N * logN)
        int range = 1;
        // arr[0] == 1
        for (int i : arr) {
            if (i > range + 1) {  // 如果range是100，当前来到102，那101搞定不了
                return range + 1;
            } else {
                range += i;
            }
        }
        return range + 1;
    }


}
