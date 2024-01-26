package UltimateAlgo2024.SlidingWindow;

// https://leetcode.com/problems/minimum-window-substring/
public class Code03_MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        char[] str = s.toCharArray();
        char[] target = t.toCharArray();
        int[] map = new int[256];
        for (char cha : target) {
            map[cha]--;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0;
        for (int l = 0, r = 0, debt = t.length(); r < str.length; r++) {
            // map[str[r]]+1前如果小于0，说明是一次有效的还款
            if (map[str[r]]++ < 0) {
                debt--;
            }
            if (debt == 0) {
                // r结尾有答案
                // 使ans尽量短
                while (map[str[l]] > 0) {
                    map[str[l++]]--;
                }
                if (r - l + 1 < ans) {
                    ans = r - l + 1;
                    start = l;
                }
            }
        }
        return ans == Integer.MAX_VALUE ? "" : s.substring(start, start + ans);
    }
}
