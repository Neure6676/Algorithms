package UltimateAlgo2024.BinarySearch.BSProblems;

// https://leetcode.com/problems/split-array-largest-sum/
public class Code02_SplitArrayLargestSum {

    public int splitArray(int[] nums, int k) {
        long sum = 0;
        for (int i : nums) {
            sum += i;
        }
        long ans = 0;
        // do BS on [0, sum]
        for (long l = 0, r = sum, m, cur; l <= r;) {
            m = l + (r - l) / 2;
            // 必须让数组arr每一部分的累加和 <= limit，请问划分成几个部分才够
            cur = f(nums, m);
            if (cur <= k) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return (int) ans;
    }

    // 必须让数组arr每一部分的累加和 <= limit，请问划分成几个部分才够!
    public static long f(int[] arr, long aim) {
        long sets = 1;
        long sum = 0;
        for (int i : arr) {
            if (i > aim) {
                return Integer.MAX_VALUE;
            }
            if (sum + i > aim) {
                sets++;
                sum = i;
            } else {
                sum += i;
            }
        }
        return sets;
    }
}
