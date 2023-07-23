package ForOffer.Class4;

/**
 * 给定三个字符串s1、s2、s3，请你帮忙验证s3是否是由s1和s2交错组成的
 * Leetcode题目：https://leetcode.com/problems/interleaving-string/
 */
public class Code07_InterleavingString {

    // dp样本对应模型
    // dp[i][j]表示str1只拿前i个字符和str2只拿前j个字符 能否组成str总 表的右下角就是答案
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] str3 = s3.toCharArray();
        if (str1.length + str2.length != str3.length) {
            return false;
        }
        boolean[][] dp = new boolean[str1.length + 1][str2.length + 1];
        dp[0][0] = true;
        for (int i = 1; i <= str1.length; i++) {
            if (str1[i - 1] != str3[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }
        for (int j = 1; j <= str2.length; j++) {
            if (str2[j - 1] != str3[j - 1]) {
                break;
            }
            dp[0][j] = true;
        }
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                boolean p1 = str1[i - 1] == str3[i + j - 1] && dp[i - 1][j];
                boolean p2 = str2[j - 1] == str3[i + j - 1] && dp[i][j - 1];
                if (p1 || p2) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[str1.length][str2.length];
    }
}
