package UltimateAlgo2024.DivideAndConquer;

// https://leetcode.com/problems/count-of-range-sum/
public class Code02_countRangeSum {

    public static int MAX_LENGTH = 100001;

    public static long[] help = new long[MAX_LENGTH];

    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return process(sum, 0, sum.length - 1, lower, upper);
    }

    public int process(long[] arr, int l, int r, int lower, int upper) {
        if (l == r) {
            return arr[l] >= lower && arr[l] <= upper ? 1 : 0;
        }
        int m = (l + r) / 2;
        return process(arr, l, m, lower, upper) +
                process(arr, m + 1, r, lower, upper) +
                merge(arr, l, m, r, lower, upper);
    }

    public int merge(long[] arr, int l, int m, int r, int lower, int upper) {
        int ans = 0;

        int windowL = l;
        int windowR = l;
        // [windowL, windowR)
        for (int i = m + 1; i <= r; i++) {
            long min = arr[i] - upper;
            long max = arr[i] - lower;
            while (windowR <= m && arr[windowR] <= max) {
                windowR++;
            }
            while (windowL <= m && arr[windowL] < min) {
                windowL++;
            }
            ans += windowR - windowL;
        }

        int a = l;
        int b = m + 1;
        int i = l;
        while (a <= m && b <= r) {
            help[i++] = arr[a] <= arr[b] ? arr[a++] : arr[b++];
        }
        while (a <= m) {
            help[i++] = arr[a++];
        }
        while (b <= r) {
            help[i++] = arr[b++];
        }
        for (int k = l; k <= r; k++) {
            arr[k] = help[k];
        }
        return ans;
    }
}
