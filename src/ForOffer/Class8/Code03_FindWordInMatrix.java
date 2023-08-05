package ForOffer.Class8;

/**
 * 给定一个char[][] matrix，也就是char类型的二维数组，再给定一个字符串word，
 * 可以从任何一个某个位置出发，可以走上、下、左、右，能不能找到word？
 *  char[][] m = {{ 'a', 'b', 'z' },
 *                { 'c', 'd', 'o' },
 *                { 'f', 'e', 'o' } }
 * 设定1：可以走重复路的情况下，返回能不能找到
 * 比如，word = "zoooz"，是可以找到的，z -> o -> o -> o -> z，因为允许走一条路径中已经走过的字符
 * 设定2：不可以走重复路的情况下，返回能不能找到
 * 比如，word = "zoooz"，是不可以找到的，因为允许走一条路径中已经走过的字符不能重复走
 */
public class Code03_FindWordInMatrix {

    // 可以重复
    // dp[i][j][k]表示从（i，j）出发，能不能走出str[k...]的后缀串
    public static boolean process(char[][] m, int i, int j, char[] str, int k) {
        if (k == str.length) {
            return true;
        }
        // 越界 连第一个都对不上
        if (i == -1 || i == str.length || j == -1 || j ==str.length || m[i][j] != str[k]) {
            return false;
        }
        boolean ans = false;
        // 上下左右只要有一个能搞定
        if (process(m, i + 1, j, str, k + 1) || process(m, i - 1, j, str, k + 1) ||
                process(m, i, j + 1, str, k + 1) || process(m, i, j - 1, str, k + 1)) {
            ans = true;
        }
        return ans;
    }

    // 不可以重复：把走过的节点标0 dfs
    // 不能改dp
    // dp[i][j][k]表示从（i，j）出发，能不能走出str[k...]的后缀串
    public static boolean process2(char[][] m, int i, int j, char[] str, int k) {
        if (k == str.length) {
            return true;
        }
        // 越界\\连第一个都对不上\\回头路
        if (i == -1 || i == str.length || j == -1 || j ==str.length || m[i][j] == 0 || m[i][j] != str[k]) {
            return false;
        }
        char cha = m[i][j];
        m[i][j] = 0; // 标0
        boolean ans = false;
        // 上下左右只要有一个能搞定
        if (process2(m, i + 1, j, str, k + 1) || process2(m, i - 1, j, str, k + 1) ||
                process2(m, i, j + 1, str, k + 1) || process2(m, i, j - 1, str, k + 1)) {
            ans = true;
        }
        m[i][j] = cha; // 恢复
        return ans;
    }

}
