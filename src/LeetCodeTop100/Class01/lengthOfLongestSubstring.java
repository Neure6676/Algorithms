package LeetCodeTop100.Class01;

import java.util.HashMap;

public class lengthOfLongestSubstring {

    // Input: s = "abcabcbb"
    // Output: 3
    // 子串问题通用解法：分别以i位置为结尾 答案是什么
    // dp[i]影响因素：1。str[i]上次出现的位置 2。i-1位置的答案
    public static int lengthOfLongestSubstring(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chas = str.toCharArray();
        int[] map = new int[128];  // map[97] = 17 // 'a'上次出现在17位置
        for (int i = 0; i < 128; i++) {
            map[i] = -1;  // 每个位置上次出现在-1位置
        }
        int ans = 0;
        int pre = -1;  // 代替dp[i - 1] 是推到哪个位置推不动的
        int cur = 0;
        for (int i = 0; i != chas.length; i++) {
            pre = Math.max(pre, map[chas[i]]); // 离你更近的
            cur = i - pre;
            ans = Math.max(ans, cur);
            map[chas[i]] = i;
        }
        return ans;
    }



    public static void main(String[] args) {
        String s = "pwwwkew";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
