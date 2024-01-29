package UltimateAlgo2024.MonotoneStack.part1;

// https://leetcode.com/problems/largest-rectangle-in-histogram
public class Code04_LargestRectangleInHistogram {

    public static int MAX = 100001;

    public static int[] stack = new int[MAX];

    public static int r;
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        r = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            while (r > 0 && heights[stack[r - 1]] >= heights[i]) {
                int cur = stack[--r];
                int left = r == 0 ? -1 : stack[r - 1];
                ans = Math.max(ans, (i - left - 1) * heights[cur]);
            }
            stack[r++] = i;
        }

        // 清算
        while (r > 0) {
            int cur = stack[--r];
            int left = r == 0 ? -1 : stack[r - 1];
            ans = Math.max(ans, (n - left - 1) * heights[cur]);
        }

        return ans;
    }

}
