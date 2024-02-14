package UltimateAlgo2024.DynamicProgramming.TwoD.Part1;

// https://leetcode.com/problems/word-search/
public class Code02_WordSearch {

    public boolean exist(char[][] board, String word) {
        char[] s = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (f(board, i, j, s, 0)) {
                    return true;
                }
            }
        }
        return false;
    }


    // 因为board会改其中的字符
    // 用来标记哪些字符无法再用
    // 带路径的递归无法改成动态规划或者说没必要
    // 从(i,j)出发，来到w[k]，请问后续能不能把word走出来w[k...]
    public static boolean f(char[][] board, int i, int j, char[] w, int k) {
        if (k == w.length) {
            return true;
        }
        if (i < 0 || j < 0 || i == board.length || j == board[0].length || board[i][j] != w[k]) {
            return false;
        }
        char temp = board[i][j];
        board[i][j] = 0;
        boolean ans = f(board, i - 1, j, w, k + 1) || f(board, i + 1, j, w, k + 1) || f(board, i, j - 1, w, k + 1) || f(board, i, j + 1, w, k + 1);
        board[i][j] = temp;
        return ans;
    }
}
