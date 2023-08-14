package ForOffer.Class11;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题一：一个字符串至少要切几刀能让切出来的子串都是回文串
 * 问题二：返回问题一的其中一种划分结果
 * 问题三：返回问题一的所有划分结果
 * 本题测试链接 : https://leetcode.com/problems/palindrome-partitioning-ii/
 */
public class Code02_PalindromePartitioningII {

    // 从左到右尝试模型
    // dp[i]表示把[0...i-1]看作整体，如果他是回文的话，后面切几刀
    public static int minCut(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        // 先创建一个map 能够判断任意一段是不是回文
        boolean[][] checkMap = createCheckMap(str, N);
        int[] dp = new int[N + 1];
        dp[N] = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (checkMap[i][N - 1]) {
                dp[i] = 1;
            } else {
                int next = Integer.MAX_VALUE;
                for (int j = i; j < N; j++) {
                    if (checkMap[i][j]) {
                        next = Math.min(next, dp[j + 1]);
                    }
                }
                dp[i] = 1 + next;
            }
        }
        return dp[0] - 1;  //部分数-1就是刀数
    }

    public static boolean[][] createCheckMap(char[] str, int N) {
        boolean[][] ans = new boolean[N][N];
        for (int i = 0; i < N - 1; i++) {
            ans[i][i] = true;
            ans[i][i + 1] = str[i] == str[i + 1];
        }
        // 从下往上，从左往右
        ans[N - 1][N - 1] = true;
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                ans[i][j] = str[i] == str[j] && ans[i + 1][j - 1];
            }
        }
        return ans;
    }

    // 本题第二问，返回其中一种结果
    // 从左往右回溯dp
    public static List<String> minCutOneWay(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() < 2) {
            ans.add(s);
        } else {
            char[] str = s.toCharArray();
            int N = str.length;
            boolean[][] checkMap = createCheckMap(str, N);
            int[] dp = new int[N + 1];
            dp[N] = 0;
            for (int i = N - 1; i >= 0; i--) {
                if (checkMap[i][N - 1]) {
                    dp[i] = 1;
                } else {
                    int next = Integer.MAX_VALUE;
                    for (int j = i; j < N; j++) {
                        if (checkMap[i][j]) {
                            next = Math.min(next, dp[j + 1]);
                        }
                    }
                    dp[i] = 1 + next;
                }
            }
            // 以上与第一问一致

            // (i...j-1)
            for (int i = 0, j = 1; j < N; j++) {
                if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
                    ans.add(s.substring(i, j)); //[i, j)
                    i = j;
                }
            }
        }
        return ans;
    }

    // 本题第三问，返回所有结果
    public static List<List<String>> minCutAllWays(String s) {
        List<List<String>> ans = new ArrayList<>();
        if (s == null || s.length() < 2) {
            List<String> cur = new ArrayList<>();
            cur.add(s);
            ans.add(cur);
        } else {
            char[] str = s.toCharArray();
            int N = str.length;
            boolean[][] checkMap = createCheckMap(str, N);
            int[] dp = new int[N + 1];
            dp[N] = 0;
            for (int i = N - 1; i >= 0; i--) {
                if (checkMap[i][N - 1]) {
                    dp[i] = 1;
                } else {
                    int next = Integer.MAX_VALUE;
                    for (int j = i; j < N; j++) {
                        if (checkMap[i][j]) {
                            next = Math.min(next, dp[j + 1]);
                        }
                    }
                    dp[i] = 1 + next;
                }
            }
            process(s, 0, 1, checkMap, dp, new ArrayList<>(), ans);
        }
        return ans;
    }

    // s[0....i-1]  存到path里去了
    // s[i..j-1]考察的分出来的第一份
    public static void process(String s, int i, int j, boolean[][] checkMap, int[] dp,
                               List<String> path,
                               List<List<String>> ans) {
        if (j == s.length()) { // s[i...N-1]
            if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
                path.add(s.substring(i, j));
                ans.add(copyStringList(path));
                path.remove(path.size() - 1);
            }
        } else {// s[i...j-1]
            if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
                path.add(s.substring(i, j));
                process(s, j, j + 1, checkMap, dp, path, ans);
                path.remove(path.size() - 1);
            }
            process(s, i, j + 1, checkMap, dp, path, ans);
        }
    }


    public static List<String> copyStringList(List<String> list) {
        List<String> ans = new ArrayList<>();
        for (String str : list) {
            ans.add(str);
        }
        return ans;
    }













}