package UltimateAlgo2024.TwoPointers;

// https://leetcode.com/problems/trapping-rain-water/
public class Code03_TrappingRainWater {

    public int trap(int[] height) {
        int n = height.length;
        int ans = 0;
        if (n > 2) {
            int left = height[0];
            int right = height[n - 1];
            for (int i = 1, j = n - 2; i <= j;) {
                if (left <= right) {
                    if (height[i] < left) {
                        ans += left - height[i++];
                    } else {
                        left = height[i++];
                    }
                } else {
                    if (height[j] < right) {
                        ans += right - height[j--];
                    }else {
                        right = height[j--];
                    }
                }
            }
        }
        return ans;
    }

}
