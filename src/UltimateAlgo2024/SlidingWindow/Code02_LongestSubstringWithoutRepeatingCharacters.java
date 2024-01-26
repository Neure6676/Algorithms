package UltimateAlgo2024.SlidingWindow;

import java.util.Arrays;

// https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class Code02_LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        // char -> int -> 0~255
        int[] last = new int[256];
        Arrays.fill(last, -1);
        int ans = 0;
        for (int l = 0, r = 0; r < n; r++) {
            l = Math.max(l, last[c[r]] + 1);
            ans = Math.max(ans, r - l + 1);
            last[c[r]] = r;
        }
        return ans;
    }
}
