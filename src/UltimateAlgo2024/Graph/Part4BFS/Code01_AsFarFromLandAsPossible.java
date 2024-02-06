package UltimateAlgo2024.Graph.Part4BFS;

// https://leetcode.com/problems/as-far-from-land-as-possible/
public class Code01_AsFarFromLandAsPossible {
    public static int MAXN = 101;

    public static int MAXM = 101;

    public static int[][] queue = new int[MAXN * MAXM][2];

    public static boolean[][] visited = new boolean[MAXN][MAXM];

    public static int l, r;

    // 0:上，1:右，2:下，3:左
    public static int[] move = new int[] { -1, 0, 1, 0, -1 };
    //                                      0  1  2  3   4
    //                                               i
    // (x,y)  i来到0位置 : x + move[i], y + move[i+1] -> x - 1, y
    // (x,y)  i来到1位置 : x + move[i], y + move[i+1] -> x, y + 1
    // (x,y)  i来到2位置 : x + move[i], y + move[i+1] -> x + 1, y
    // (x,y)  i来到3位置 : x + move[i], y + move[i+1] -> x, y - 1

    public int maxDistance(int[][] grid) {
        l = r = 0;
        int n = grid.length;
        int m = grid[0].length;
        // how many vals are seas
        int seas = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    // land
                    visited[i][j] = true;
                    queue[r][0] = i;
                    queue[r++][1] = j;
                } else {
                    // clear visited
                    visited[i][j] = false;
                    seas++;
                }
            }
        }
        if (seas == 0 || seas == n * m) {
            return -1;
        }
        // BFS
        int level = 0;
        while (l < r) {
            level++;
            int size = r - l;
            for (int k = 0, x, y, nx, ny; k < size; k++) {
                x = queue[l][0];
                y = queue[l++][1];
                for (int i = 0; i < 4; i++) {
                    // top, right, down, left
                    nx = x + move[i];
                    ny = y + move[i + 1];
                    if (nx >= 0 && nx < n && ny >=0 && ny < m && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue[r][0] = nx;
                        queue[r++][1] = ny;
                    }
                }
            }
        }
        return level - 1;
    }

}
