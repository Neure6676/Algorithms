package UltimateAlgo2024.TwoPointers;

// https://leetcode.com/problems/container-with-most-water/
public class Code05_ContainerWithMostWater {

    public int maxArea(int[] height) {
        int ans = 0, l = 0, r = height.length - 1;
        while (l < r) {
            if (height[l] <= height[r]) {
                ans = Math.max(ans, height[l] * (r - l));
                l++;
            } else {
                ans = Math.max(ans, height[r] * (r - l));
                r--;
            }
        }
        return ans;
    }
}
