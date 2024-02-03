package UltimateAlgo2024.MonotoneQueue.Part2;

// https://leetcode.com/problems/max-value-of-equation/
public class Code02_MaxValueOfEquation {

    public static int MAX = 100001;

    public static int[][] deque = new int[MAX][2];

    public static int h, t;

    public int findMaxValueOfEquation(int[][] points, int k) {
        h = t = 0;
        int n = points.length;
        int ans = Integer.MIN_VALUE;
        for (int i = 0, x, y; i < n; i++) {
            x = points[i][0];
            y = points[i][1];
            while (h < t && x - deque[h][0] > k) {
                h++;
            }
            // 如果还有东西
            if (h < t) {
                ans = Math.max(ans, x + y + deque[h][1] - deque[h][0]);
            }
            while (h < t && y - x >= deque[t - 1][1] - deque[t - 1][0]) {
                t--;
            }
            deque[t][0] = x;
            deque[t++][1] = y;
        }
        return ans;
    }
}
