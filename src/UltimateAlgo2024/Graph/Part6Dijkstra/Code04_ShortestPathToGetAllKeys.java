package UltimateAlgo2024.Graph.Part6Dijkstra;

// https://leetcode.com/problems/shortest-path-to-get-all-keys
public class Code04_ShortestPathToGetAllKeys {

    public static int MAXN = 31;

    public static int MAXM = 31;

    public static int MAXK = 6;

    public static int[] move = {-1, 0, 1, 0, -1};

    public static char[][] grid = new char[MAXN][];

    public static boolean[][][] visited = new boolean[MAXN][MAXM][1 << MAXK];

    public static int[][] queue = new int[MAXN * MAXM * 1 << MAXK][3];

    public static int l, r, n, m, key;

    public static void build(String[] g) {
        l = r = key = 0;
        n = g.length;
        m = g[0].length();
        for (int i = 0; i < n; i++) {
            grid[i] = g[i].toCharArray();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '@') {
                    queue[r][0] = i;
                    queue[r][1] = j;
                    // 0 : 000000
                    queue[r++][2] = 0;
                }
                if (grid[i][j] >= 'a' && grid[i][j] <= 'f') {
                    // key: 目标状态 0001111
                    key |= 1 << (grid[i][j] - 'a');
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int s = 0; s <= key; s++) {
                    visited[i][j][s] = false;
                }
            }
        }
    }

    public static int shortestPathAllKeys(String[] g) {
        build(g);
        int level = 1;
        while (l < r) {
            for (int k = 0, size = r - l, x, y, s; k < size; k++) {
                x = queue[l][0];
                y = queue[l][1];
                s = queue[l++][2];
                for (int i = 0, nx, ny, ns; i < 4; i++) {
                    nx = x + move[i];
                    ny = y + move[i + 1];
                    ns = s;
                    if (nx < 0 || nx == n || ny < 0 || ny == m || grid[nx][ny] == '#') {
                        // 越界或者障碍
                        continue;
                    }
                    if (grid[nx][ny] >= 'A' && grid[nx][ny] <= 'F' && ((ns & (1 << (grid[nx][ny] - 'A'))) == 0)) {
                        // 是锁，又没有对应的钥匙
                        continue;
                    }
                    if (grid[nx][ny] >= 'a' && grid[nx][ny] <= 'f') {
                        // 是某一把钥匙
                        ns |= (1 << (grid[nx][ny] - 'a'));
                    }
                    if (ns == key) {
                        // 常见剪枝
                        // 发现终点直接返回
                        // 不用等都结束
                        return level;
                    }
                    if (!visited[nx][ny][ns]) {
                        visited[nx][ny][ns] = true;
                        queue[r][0] = nx;
                        queue[r][1] = ny;
                        queue[r++][2] = ns;
                    }
                }
            }
            level++;
        }
        return -1;
    }
}
