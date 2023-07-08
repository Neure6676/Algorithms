package SystematicClass.Class26;

import java.util.Stack;

// https://leetcode.com/problems/maximal-rectangle/
public class Code04_MaximalRectangle {

    public static int maximalRectangle(char[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxArea = Integer.MIN_VALUE;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                height[j] = map[i][j] == '0' ? 0 : 1 + height[j];
            }
            maxArea = Math.max(maxRecFromBottom(height), maxArea);
        }
        return maxArea;
    }


    public static int maxRecFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                int j = stack.pop();
                int right = i;
                int left = stack.isEmpty() ? -1 : stack.peek();
                int curArea = (right - left - 1) * height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int right = height.length;
            int left = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (right - left - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }
}
