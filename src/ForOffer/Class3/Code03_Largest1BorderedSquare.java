package ForOffer.Class3;

/**
 * 给定一个只有0和1组成的二维数组，返回边框全是1（内部无所谓）的最大正方形面积
 *
 * 本题测试链接 : https://leetcode.com/problems/largest-1-bordered-square/
 */
public class Code03_Largest1BorderedSquare {

    // N*N矩阵中围成长方形数量O(n^4) 围成正方形数量O(n^3)
    public static int test(int[][] m) {
        int N = m.length;
        int M = m[0].length;

        // 求任意一点右边和下边有多少连续的1
        int right[][] = new int[N][M];
        int down[][] = new int[N][M];
        setBorderMap(m, right, down);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int border = 1; border < Math.min(N - i, M - j); border++) {

                    // 判断该正方形是否符合要求 优化到O(1)


                }
            }
        }
        return 0;
    }


    public static int largest1BorderedSquare(int[][] m) {
        int[][] right = new int[m.length][m[0].length];
        int[][] down = new int[m.length][m[0].length];
        setBorderMap(m, right, down);
        for (int size = Math.min(m.length, m[0].length); size != 0; size--) {
            if (hasSizeOfBorder(size, right, down)) {
                return size * size;
            }
        }
        return 0;
    }

    public static void setBorderMap(int[][] m, int[][] right, int[][] down) {
        int r = m.length;
        int c = m[0].length;
        if (m[r - 1][c - 1] == 1) {
            right[r - 1][c - 1] = 1;
            down[r - 1][c - 1] = 1;
        }
        for (int i = r - 2; i != -1; i--) {
            if (m[i][c - 1] == 1) {
                right[i][c - 1] = 1;
                down[i][c - 1] = down[i + 1][c - 1] + 1;
            }
        }
        for (int i = c - 2; i != -1; i--) {
            if (m[r - 1][i] == 1) {
                right[r - 1][i] = right[r - 1][i + 1] + 1;
                down[r - 1][i] = 1;
            }
        }
        for (int i = r - 2; i != -1; i--) {
            for (int j = c - 2; j != -1; j--) {
                if (m[i][j] == 1) {
                    right[i][j] = right[i][j + 1] + 1;
                    down[i][j] = down[i + 1][j] + 1;
                }
            }
        }
    }

    public static boolean hasSizeOfBorder(int size, int[][] right, int[][] down) {
        for (int i = 0; i != right.length - size + 1; i++) {
            for (int j = 0; j != right[0].length - size + 1; j++) {
                if (right[i][j] >= size && down[i][j] >= size && right[i + size - 1][j] >= size
                        && down[i][j + size - 1] >= size) {
                    return true;
                }
            }
        }
        return false;
    }
}
