package ForOffer.Class13;

/**
 * 旋变字符串
 * 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
 * 如果字符串的长度为 1 ，算法停止
 * 如果字符串的长度 > 1 ，执行下述步骤：
 * 在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。
 * 随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。
 * 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
 * 给你两个 长度相等 的字符串 s1 和s2，判断s2是否是s1的扰乱字符串。如果是，返回 true ；否则，返回 false 。
 * Leetcode题目：https://leetcode.com/problems/scramble-string/
 */
public class Code03_ScrambleString {

    // 两个字符串 -> 样本对应模型
    // f(s1,L1,R1,s2,L2,R2)
    public static boolean isScramble0(String s1, String s2) {
        if ((s1 == null && s2 != null) || (s1 != null && s2 == null)) {
            return false;
        }
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1.equals(s2)) {
            return true;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        if (!sameTypeSameNumber(str1, str2)) {
            return false;
        }
        return process(str1, 0, str1.length - 1, str2, 0, str2.length - 1);
    }


    // str1[L1...R1] str2[L2...R2] 是否互为玄变串
    // 一定保证这两段是等长的！
    public static boolean process(char[] str1, int L1, int R1, char[] str2, int L2, int R2) {
        if (L1 == R1) {
            return str1[L1] == str2[L2];
        }
        // 以如何切str1划分，L1为一部分，L1到L1+1为一部分，L1到L1+2为一部分。。
        for (int leftEnd = L1; leftEnd < R1; leftEnd++) {
            boolean p1 = process(str1, L1, leftEnd, str2, L2, L2 + leftEnd - L1)
                    && process(str1, leftEnd + 1, R1, str2, L2 + leftEnd - L1 + 1, R2);
            boolean p2 = process(str1, L1, leftEnd, str2, R2 - (leftEnd - L1), R2)
                    && process(str1, leftEnd + 1, R1, str2, L2, R2 - (leftEnd - L1) - 1);
            if (p1 || p2) {
                return true;
            }
        }
        return false;
    }

    public static boolean sameTypeSameNumber(char[] str1, char[] str2) {
        if (str1.length != str2.length) {
            return false;
        }
        int[] map = new int[256];
        for (int i = 0; i < str1.length; i++) {
            map[str1[i]]++;
        }
        for (int i = 0; i < str2.length; i++) {
            if (--map[str2[i]] < 0) {
                return false;
            }
        }
        return true;
    }

    // 如果直接改dp将是4纬表 改成3参数版本 L1，L2，N
    public static boolean process2(char[] str1, char[] str2, int L1, int L2, int size, int[][][] dp) {
        if (dp[L1][L2][size] != 0) {
            return dp[L1][L2][size] == 1;
        }
        boolean ans = false;
        if (size == 1) {
            ans = str1[L1] == str2[L2];
        } else {
            for (int leftPart = 1; leftPart < size; leftPart++) {
                if ((process2(str1, str2, L1, L2, leftPart, dp)
                        && process2(str1, str2, L1 + leftPart, L2 + leftPart, size - leftPart, dp))
                        || (process2(str1, str2, L1, L2 + size - leftPart, leftPart, dp)
                        && process2(str1, str2, L1 + leftPart, L2, size - leftPart, dp))) {
                    ans = true;
                    break;
                }
            }
        }
        dp[L1][L2][size] = ans ? 1 : -1;
        return ans;
    }

    public static boolean isScramble2(String s1, String s2) {
        if ((s1 == null && s2 != null) || (s1 != null && s2 == null)) {
            return false;
        }
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1.equals(s2)) {
            return true;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        if (!sameTypeSameNumber(str1, str2)) {
            return false;
        }
        int N = s1.length();
        int[][][] dp = new int[N][N][N + 1];
        // dp[i][j][k] = 0 processDP(i,j,k)状态之前没有算过的
        // dp[i][j][k] = -1 processDP(i,j,k)状态之前算过的,返回值是false
        // dp[i][j][k] = 1 processDP(i,j,k)状态之前算过的,返回值是true
        return process2(str1, str2, 0, 0, N, dp);
    }


}
