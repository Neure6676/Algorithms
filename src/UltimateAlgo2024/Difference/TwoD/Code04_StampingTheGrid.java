package UltimateAlgo2024.Difference.TwoD;

// https://leetcode.com/problems/stamping-the-grid/
// 判断能不能贴：累加和为0
// 贴的过程准备一个差分数组，不要修改原数组的值
public class Code04_StampingTheGrid {

    public boolean possibleToStamp(int[][] g, int stampHeight, int stampWidth) {
        int n = g.length;
        int m = g[0].length;

        // build a preSum array for stamp checking
        int[][] preSum = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                preSum[i + 1][j + 1] = g[i][j];
            }
        }
        build(preSum);

        // build diff arr
        int[][] diff = new int[n + 2][m + 2];
        for (int a = 1, c = a + stampHeight - 1; c <= n; a++, c++) {
            for (int b = 1, d = b + stampWidth - 1; d <= m; b++, d++) {
                if (sumRegion(preSum, a, b, c, d) == 0) {
                    add(diff, a, b, c, d);
                }
            }
        }
        build(diff);

        // check
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (g[i][j] == 0 && diff[i + 1][j + 1] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // build preSum array
    public static void build(int[][] m) {
        for (int i = 1; i < m.length; i++) {
            for (int j = 1; j < m[0].length; j++) {
                m[i][j] += m[i - 1][j] + m[i][j - 1] - m[i - 1][j - 1];
            }
        }
    }

    public static int sumRegion(int[][] preSum, int a, int b, int c, int d) {
        return preSum[c][d] - preSum[c][b - 1] - preSum[a - 1][d] + preSum[a - 1][b - 1];
    }

    public static void add(int[][] diff, int a, int b, int c, int d) {
        diff[a][b] += 1;
        diff[c + 1][d + 1] += 1;
        diff[c + 1][b] -= 1;
        diff[a][d + 1] -= 1;
    }

}
