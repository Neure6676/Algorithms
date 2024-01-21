package UltimateAlgo2024.BitwiseOperations.NQueen;

// https://leetcode.com/problems/n-queens-ii/
// 判断是否在同一对角线上：｜当前行-之前行｜==｜当前列-之前列｜
// path[]: i->row; path[i]->col
public class Code01_NQueens {

    /**
     * 用数组表示路径的方法（经典、常数时间慢，不推荐）
     * 记录之前每一行的皇后放在了什么列
     * 来到第i行的时候，可以根据0..i-1行皇后的位置，判断能放哪些列
     * 把能放的列都尝试一遍，每次尝试修改路径数组表示当前的决策，后续返回的答案都累加返回
     * @param n
     * @return
     */
    public int totalNQueens1(int n) {
        if (n == 0) {
            return 0;
        }
        return process(0, new int[n], n);
    }

    public static int process(int i, int[] path, int n) {
        if (i == n) {
            return 1;
        }
        int ans = 0;
        for (int j = 0; j < n; j++) {
            if (check(i, j, path)) {
                path[i] = j;
                ans += process(i + 1, path, n);
            }
        }
        return ans;
    }

    public static boolean check(int i, int j, int[] path) {
        for (int k = 0; k < i; k++) {
            if (path[k] == j || Math.abs(k - i) == Math.abs(j - path[k])) {
                return false;
            }
        }
        return true;
    }


    /**
     * 用位运算的方法（巧妙、常数时间快，推荐）
     * int col   : 0..i-1行皇后放置的位置因为正下方向延伸的原因，哪些列不能再放皇后
     * int left  : 0..i-1行皇后放置的位置因为左下方向延伸的原因，哪些列不能再放皇后
     * int right : 0..i-1行皇后放置的位置因为右下方向延伸的原因，哪些列不能再放皇后
     * 根据col、left、right，用位运算快速判断能放哪些列
     * 把能放的列都尝试一遍，每次尝试修改3个数字表示当前的决策，后续返回的答案都累加返回
     * @param n
     * @return
     */
    public static int totalNQueens2(int n) {
        if (n < 1) {
            return 0;
        }
        // n = 5
        // 1 << 5 = 0...100000 - 1
        // limit  = 0...011111;
        // n = 7
        // limit  = 0...01111111;
        int limit = (1 << n) - 1;
        return f2(limit, 0, 0, 0);
    }

    // limit : 当前是几皇后问题
    // 之前皇后的列影响：col
    // 之前皇后的右上 -> 左下对角线影响：left
    // 之前皇后的左上 -> 右下对角线影响：right
    public static int f2(int limit, int col, int left, int right) {
        if (col == limit) {
            // 所有皇后放完了！
            return 1;
        }
        // 总限制
        int ban = col | left | right;
        // ~ban : 1可放皇后，0不能放
        int candidate = limit & (~ban);
        // 放置皇后的尝试！
        int place = 0;
        // 一共有多少有效的方法
        int ans = 0;
        while (candidate != 0) {
            // 提取出最右侧的1
            // 0 0 1 1 1 0
            // 5 4 3 2 1 0
            // place :
            // 0 0 0 0 1 0
            // candidate :
            // 0 0 1 1 0 0
            // 5 4 3 2 1 0
            place = candidate & (-candidate);
            candidate ^= place;
            ans += f2(limit, col | place, (left | place) >> 1, (right | place) << 1);
        }
        return ans;
    }

}
