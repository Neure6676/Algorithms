package UltimateAlgo2024.Tree.Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/word-search-ii/
// 前缀树妙用
// 1. 剪枝
// 2. 方便收集答案，把答案放节点的str里
// 3. 通过pass值进一步剪枝
public class Code03_WordSearchII {

    public List<String> findWords(char[][] board, String[] words) {
        build(words);
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, 1, ans);
            }
        }
        clear();
        return ans;
    }

    // t: tree[t]
    // return: how many str are collected
    public static int dfs(char[][] board, int i, int j, int t, List<String> ans) {
        // i,j out of boundary
        // or has been visited
        if (i < 0 || i == board.length || j < 0 || j < board[0].length || board[i][j] == 0) {
            return 0;
        }
        // if there is a prefix start with board[i][j]
        char temp = board[i][j];
        int road = temp - 'a';
        t = tree[t][road];
        if (pass[t] == 0) {
            return 0;
        }
        int num = 0;
        if (end[t] != null) {
            num++;
            ans.add(end[t]);
            end[t] = null;
        }
        // modify board[i][j] to 0
        board[i][j] = 0;
        num += dfs(board, i + 1, j, t, ans);
        num += dfs(board, i - 1, j, t, ans);
        num += dfs(board, i, j + 1, t, ans);
        num += dfs(board, i, j - 1, t, ans);
        pass[t] -= num;
        // recover the site
        board[i][j] = temp;
        return num;
    }

    public static int MAXN = 10001;
    public static int[][] tree = new int[MAXN][10];
    public static int[] pass = new int[MAXN];
    public static String[] end = new String[MAXN];
    public static int cnt;

    public static void build(String[] words) {
        cnt = 1; // dont use row 0
        for (String s : words) {
            int cur = 1;
            pass[cur]++;
            for (int i = 0; i < s.length(); i++) {
                int path = s.charAt(i) - 'a';
                if (tree[cur][path] == 0) {
                    tree[cur][path] = ++cnt;
                }
                cur = tree[cur][path];
                pass[cur]++;
            }
            end[cur] = s;
        }
    }

    public static void clear() {
        for (int i = 0; i < cnt; i++) {
            Arrays.fill(tree[i], 0);
            pass[i] = 0;
            end[i] = null;
        }
    }


}
