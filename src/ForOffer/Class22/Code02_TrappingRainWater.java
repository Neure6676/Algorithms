package ForOffer.Class22;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 */
public class Code02_TrappingRainWater {

    // water[i] = min(wall[0..i-1].max, wall[i+1..n].max) - wall[i]
    // 左边max和右边max谁小就先结算那边的水量，因为这边的水量可以确定
    // 若左右相等可以一起结算
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int N = height.length;
        int ans = 0;
        int leftMax = height[0];
        int rightMax = height[N - 1];
        int i = 1, j = N - 2;
        while (i <= j) {
            if (leftMax <= rightMax) {
                ans += Math.max(0, leftMax - height[i]);
                leftMax = Math.max(leftMax, height[i++]);
            } else if (leftMax > rightMax) {
                ans += Math.max(0, rightMax - height[j]);
                rightMax = Math.max(rightMax, height[j++]);
            }
        }
        return ans;
    }
}
