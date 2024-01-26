package UltimateAlgo2024.SlidingWindow;

import java.util.Arrays;

// https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
public class Code07_LongestSubstringWithAtLeastKRepeating {

    // 每次要求子串必须含有require种字符，每种字符都必须>=k次，这样的最长子串是多长
    public int longestSubstring(String s, int k) {
        char[] c = s.toCharArray();
        int n = c.length;
        int[] cnt = new int[256];
        int ans = 0;
        for (int req = 1; req <= 26; req++) {
            Arrays.fill(cnt, 0);
            // collect: 一共收集到几种字符
            // satisfy: 有几种字符满足要求
            for (int l = 0, r = 0, collect = 0, satisfy = 0; r < n; r++) {
                if (++cnt[c[r]] == 1) {
                    collect++;
                }
                if (cnt[c[r]] == k) {
                    satisfy++;
                }
                while (collect > req) {
                    if (cnt[c[l]] == 1) {
                        collect--;
                    }
                    if (cnt[c[l]] == k) {
                        satisfy--;
                    }
                    cnt[c[l++]]--;
                }
                if (satisfy == req) {
                    ans = Math.max(ans, r - l + 1);
                }
            }
        }
        return ans;
    }

}
