package ForOffer.Class50;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/erect-the-fence/
 */
public class Problem_0587_ErectTheFence {

    /**
     * 凸包算法（ACM经典算法）
     * 叉乘性质：AC * AB 如果AC在AB右侧 结果为正 反之为负
     *
     * 给定一个凸多边形和一个点 判断该点在不在图形内部
     * 逆时针走过所有的边 如果它在每个边的左侧 那么就在内部
     */
    public int[][] outerTrees(int[][] points) {
        int n = points.length;
        int s = 0;
        int[][] stack = new int[n * 2][];
        // x小的排前面，x一样的，y小的排前面
        Arrays.sort(points, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        for (int i = 0; i < n; i++) {
            while (s > 1 && cross(stack[s - 2], stack[s - 1], points[i]) > 0) {
                s--;
            }
            stack[s++] = points[i];
        }
        for (int i = n - 2; i >= 0; i--) {
            while (s > 1 && cross(stack[s - 2], stack[s - 1], points[i]) > 0) {
                s--;
            }
            stack[s++] = points[i];
        }
        // 去重
        Arrays.sort(stack, 0, s, (a, b) -> b[0] == a[0] ? b[1] - a[1] : b[0] - a[0]);
        n = 1;
        for (int i = 1; i < s; i++) {
            // 如果i点，x和y，与i-1点，x和y都一样
            // i点与i-1点，在同一个位置，此时，i点不保留
            if (stack[i][0] != stack[i - 1][0] || stack[i][1] != stack[i - 1][1]) {
                stack[n++] = stack[i];
            }
        }
        return Arrays.copyOf(stack, n);

    }

    public static int cross(int[] a, int[] b, int[] c) {
        return (b[1] - a[1]) * (c[0] - b[0]) - (b[0] - a[0]) * (c[1] - b[1]);
    }

}
