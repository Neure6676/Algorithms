package UltimateAlgo2024.MonotoneStack.part2;

// https://leetcode.com/problems/maximum-width-ramp/
public class Code01_MaximumWidthRamp {

    public static int MAX = 50001;

    public static int[] stack = new int[MAX];

    public static int r;

    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        r = 1;
        for (int i = 1; i < n; i++) {
            // 小压大
            if (nums[stack[r - 1]] >= nums[i]) {
                stack[r++] = i;
            }
        }
        int ans = 0;
        for (int j = n - 1; j >= 0; j--) {
            while (r > 0 && nums[stack[r - 1]] <= nums[j]) {
                ans = Math.max(ans, j - stack[--r]);
            }
        }
        return ans;
    }

}
