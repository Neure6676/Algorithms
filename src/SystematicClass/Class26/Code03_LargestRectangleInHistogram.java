package SystematicClass.Class26;

import java.util.Stack;

/**
 * 给定一个非负数组arr，代表直方图，返回直方图的最大长方形面积
 *
 * 思路：分别求必须以每个位置的数为高所形成的最大长方形面积
 */
public class Code03_LargestRectangleInHistogram {

    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length ==0) {
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int j = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                maxArea = Math.max(maxArea, heights[j] * (i - left - 1));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int right = heights.length;
            int left = stack.isEmpty() ? -1 : stack.peek();
            maxArea = Math.max(maxArea, heights[j] * (right - left - 1));
        }
        return maxArea;
    }
}
