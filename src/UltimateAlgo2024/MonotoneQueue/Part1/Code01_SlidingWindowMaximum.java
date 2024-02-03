package UltimateAlgo2024.MonotoneQueue.Part1;

// https://leetcode.com/problems/sliding-window-maximum/
public class Code01_SlidingWindowMaximum {

    public static int MAX = 100001;

    public static int[] queue = new int[MAX];

    public static int h, t;

    public int[] maxSlidingWindow(int[] nums, int k) {
        h = t = 0;
        int n = nums.length;
        // 0 ~ k-1
        for (int i = 0; i < k; i++) {
            while (h < t && nums[i] >= nums[queue[t - 1]]) {
                t--;
            }
            queue[t++] = i;
        }

        int m = n - k + 1;
        int[] ans = new int[m];
        for (int l = 0, r = k - 1; l < m; l++, r++) {
            while (h < t && nums[r] >= nums[queue[t - 1]]) {
                t--;
            }
            queue[t++] = r;
            ans[l] = nums[queue[h]];
            if (queue[h] == l) {
                h++;
            }
        }
        return ans;
    }
}
