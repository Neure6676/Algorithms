package UltimateAlgo2024.MonotoneStack.part1;

// https://leetcode.com/problems/sum-of-subarray-minimums/
public class Code03_SumOfSubarrayMinimums {

    public static int mod = 1000000007;

    public static int MAX = 30001;

    public static int[] stack = new int[MAX];

    public static int r;

    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            while (r > 0 && arr[stack[r - 1]] > arr[i]) {
                int cur = stack[--r];
                int left = r == 0 ? -1 : stack[r - 1];
                ans = (ans + (i - cur) * (long) (cur - left) * arr[cur]) % mod;
            }
            stack[r++] = i;
        }
        while (r > 0) {
            int cur = stack[--r];
            int left = r == 0 ? -1 : stack[r - 1];
            ans = (ans + (long) (cur - left) * (arr.length - cur) * arr[cur]) % mod;
        }
        return (int) ans;
    }
}
