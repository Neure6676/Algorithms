package UltimateAlgo2024.MonotoneStack.part1;

// https://leetcode.com/problems/maximal-rectangle/

import java.util.ArrayList;
import java.util.Arrays;

// 分别以i行做底，找此时最大面积
// 加工高度数组(压缩数组)
public class Code05_MaximalRectangle {

    public static int MAX = 201;

    public static int[] height = new int[MAX];

    public static int[] stack = new int[MAX];

    public static int r;

    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        Arrays.fill(height, 0, m, 0);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            // 来到i行，长方形一定要以i行做底！
            // 加工高度数组(压缩数组) (必须连续)
            for (int j = 0; j < m; j++) {
                height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
            }
            ans = Math.max(ans, f(m));
        }
        return ans;
    }

    public static int f(int m) {
        int ans = 0;
        r = 0;
        for (int i = 0; i < m; i++) {
            while (r > 0 && height[stack[r - 1]] >= height[i]) {
                int cur = stack[--r];
                int left = r == 0 ?  -1 : stack[r - 1];
                ans = Math.max(ans, (i - left - 1) * height[cur]);
            }
            stack[r++] = i;
        }
        while (r > 0) {
            int cur = stack[--r];
            int left = r == 0 ? -1 : stack[r - 1];
            ans = Math.max(ans, (m - left - 1) * height[cur]);
        }
        return ans;
    }
}
