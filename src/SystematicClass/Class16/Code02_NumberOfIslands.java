package SystematicClass.Class16;

/**
 * 本题为leetcode原题
 * 测试链接：https://leetcode.com/problems/number-of-islands/
 * 所有方法都可以直接通过
 */
public class Code02_NumberOfIslands {

    public static class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int sets;
        private int col;

        public UnionFind(char[][] board) {
            col = board[0].length;
            sets = 0;
            int row = board.length;
            int len = row * col;
            parent = new int[len]; // (i,j) -> i*col+row
            size = new int[len];
            help = new int[len];
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (board[r][c] == '1') {
                        int i = index(r, c);
                        parent[i] = i;
                        size[i] = 1;
                        sets++;
                    }
                }
            }
        }

        private int index(int r, int c) {
            return r * col + c;  // (r,c) -> i
        }

        // i是下标，原始二维位置已被一维下标替代
        private int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                i = parent[i];
                help[hi++] = i;
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        // (r1, c1) -> 1    (r2, c2) -> 1
        //      i1             i2
        private void union(int r1, int c1, int r2, int c2) {
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            int f1 = find(i1);
            int f2 = find(i2);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }

        private int sets() {
            return sets;
        }

    }

    //只需要看左边和上边
    public static int numIslands1(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        UnionFind uf = new UnionFind(board);
        for (int j = 1; j < col; j++) {
            if (board[0][j - 1] == '1' && board[0][j] == '1') {
                uf.union(0, j - 1, 0, j);
            }
        }
        for (int i = 1; i < row; i++) {
            if (board[i - 1][0] == '1' && board[i][0] == '1') {
                uf.union(i - 1, 0, i, 0);
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j] == '1') {
                    if (board[i][j - 1] == '1') {
                        uf.union(i, j - 1, i, j);
                    }
                    if (board[i - 1][j] == '1') {
                        uf.union(i - 1, j, i, j);
                    }
                }
            }
        }
        return uf.sets();
    }



    //递归感染方法，不使用并查集. O(m*n)
    public static int numIslands2(char[][] board) {
        int island = 0;
        for (int i = 0; i < board.length; i++) {           //行遍历
            for (int j = 0; j < board[0].length; j++) {    //列遍历
                if (board[i][j] == '1') {                  //单引号
                    island++;
                    infect(board,i,j);
                }
            }
        }
        return island;
    }

    private static void infect(char[][] board, int i, int j) {
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != '1') {
            return;
        }
        board[i][j] = '2';
        infect(board, i - 1, j);
        infect(board, i + 1, j);
        infect(board, i, j - 1);
        infect(board, i, j + 1);
    }


}
