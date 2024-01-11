package UltimateAlgo2024.DivideAndConquer;

// https://leetcode.com/problems/reverse-pairs/
public class Code03_ReversePairs {

    public static int MAX = 50001;

    public static int[] help = new int[MAX];

    public static int reversePairs(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = (l + r) / 2;
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int l, int m, int r) {

        int ans = 0, sum = 0;
        for (int rightStart = m + 1, leftStart = l; leftStart <= m; leftStart++) {
            while (rightStart <= r && (long)arr[leftStart] > 2 * (long)arr[rightStart]) {
                rightStart++;
                sum++;
            }
            ans += sum;
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
        for (int j = l; j <= r; j++) {
            arr[j] = help[j];
        }
        return ans;
    }
}
