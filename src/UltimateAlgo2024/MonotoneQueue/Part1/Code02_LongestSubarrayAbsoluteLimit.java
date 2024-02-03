package UltimateAlgo2024.MonotoneQueue.Part1;

// https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
public class Code02_LongestSubarrayAbsoluteLimit {

    public static int MAX = 100001;

    public static int[] maxQue = new int[MAX];

    public static int[] minQue = new int[MAX];

    public static int minh, mint, maxh, maxt;

    public static int[] arr;

    public int longestSubarray(int[] nums, int limit) {
        minh = mint = maxh = maxt = 0;
        arr = nums;
        int n = arr.length;
        int ans = 0;
        for (int l = 0, r = 0; l < n; l++) {
            // [l,r)，r永远是没有进入窗口的、下一个数所在的位置
            while (r < n && ok(limit, nums[r])) {
                push(r++);
            }
            ans = Math.max(ans, r - l);
            pop(l);
        }
        return ans;
    }

    public static boolean ok(int limit, int x) {
        int max = maxt > maxh ? Math.max(arr[maxQue[maxh]], x) : x;
        int min = mint > minh ? Math.min(arr[minQue[minh]], x) : x;
        return max - min <= limit;
    }

    public static void pop(int l) {
        if (maxt > maxh && maxQue[maxh] == l) {
            maxh++;
        }
        if (mint > minh && minQue[minh] == l) {
            minh++;
        }
    }


    public static void push(int r) {
        while (maxt > maxh && arr[r] >= arr[maxQue[maxt - 1]]) {
            maxt--;
        }
        maxQue[maxt++] = r;
        while (mint > minh && arr[r] <= arr[minQue[mint - 1]]) {
            mint--;
        }
        minQue[mint++] = r;
    }
}
