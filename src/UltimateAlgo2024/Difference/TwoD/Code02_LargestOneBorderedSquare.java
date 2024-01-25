package UltimateAlgo2024.Difference.TwoD;

// https://leetcode.com/problems/largest-1-bordered-square/
// sum1：某正方形面积 sum2：sum1除去边长的正方形面积
// 如果sum1 - sum2等于周长则满足条件
public class Code02_LargestOneBorderedSquare {
    public int largest1BorderedSquare(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        build(n, m, grid);
        if (sum(grid, 0, 0, n - 1, m - 1) == 0) {
            return 0;
        }
        // 答案的边长
        int ans = 1;
        // traverse all topleft points
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < m; b++) {
                // traverse the length of side
                // c从a+ans开始 -> 剪枝，不需要看比当前ans小的
                for (int c = a + ans, d = b + ans, k = ans + 1; c < n && d < m; c++, d++, k++) {
                    if (sum(grid, a, b, c, d) - sum(grid, a + 1, b + 1, c - 1, d - 1) == (k - 1) * 4) {
                        ans = k;
                    }
                }
            }
        }
        return ans * ans;
    }

    public static void build(int n, int m, int[][] grid) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] += get(i, j - 1, grid) + get(i - 1, j, grid) - get(i - 1, j - 1, grid);
            }
        }
    }

    public static int get(int n, int m, int[][] grid) {
        return (n < 0 || m < 0) ? 0 : grid[n][m];
    }

    public static int sum(int[][] grid, int a, int b, int c, int d) {
        return grid[c][d] - get(c, b - 1, grid) - get(a - 1, d, grid) + get(a - 1, b - 1, grid);
    }
}
