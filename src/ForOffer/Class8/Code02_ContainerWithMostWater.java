package ForOffer.Class8;

/**
 * 给定n个非负整数a1，a2，...an，每个数代表坐标中的一个点(i,ai)。在坐标内画n条垂直线
 * 垂直线i的两个端点分别为(i,ai)和(i, 0)，找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水
 * Leetcode题目：https://leetcode.com/problems/container-with-most-water/
 */
public class Code02_ContainerWithMostWater {

    public static int maxArea1(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int h = Math.min(height[j], height[i]);
                ans = Math.max((j - i) * h, ans);
            }
        }
        return ans;
    }

    // 同向双指针
    // O(N)
    // 只关注到每个位置是否有找到更大答案的可能性，而不是计算每个位置的最大面积！
    public static int maxArea2(int[] height) {
        int ans = 0;
        int L = 0;
        int R = height.length - 1;
        while (R >= L) {
            if (height[L] <= height[R]) {
                ans = Math.max(ans, height[L] * (R - L));
                L++;
            } else {
                ans = Math.max(ans, height[R] * (R - L));
                R--;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea1(height));
        System.out.println(maxArea2(height));
    }
}
