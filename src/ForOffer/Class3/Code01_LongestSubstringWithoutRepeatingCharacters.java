package ForOffer.Class3;

import java.util.HashMap;

// 本题测试链接 : https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class Code01_LongestSubstringWithoutRepeatingCharacters {

    /**
     * s = "abcabcbb"
     * 分别从0到i位置 看答案是什么 最后取最大的
     * 两个决定因素
     * 1。当前字符上次出现的位置
     * 2。i-1位置往左推的距离
     */
    public int lengthOfLongestSubstring1(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] str = s.toCharArray();
        // map[i] = v 表示i这个ASCII码的字符上次出现在v位置
        int[] map = new int[256]; // ASCII 0-255
        // 初始化
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        int N = str.length;
        int ans = 1;
        int pre = 1; // 上一个位置向左推了多长
        map[str[0]] = 0;
        for (int i = 1; i < N; i++) {
            // 第一种可能：到上次它出现中间的距离
            int p1 = i - map[str[i]];
            // 第二种可能 上一个的答案加一
            int p2 = pre + 1;
            int cur = Math.min(p1, p2);
            ans = Math.max(ans, cur);
            // 维护
            pre = cur;
            map[str[i]] = i;
        }
        return ans;
    }


    // 双指针
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        int ans = 0;
        int left = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int right = 0; right < n; right++) {
            if (!map.containsKey(s.charAt(right)) || map.get(s.charAt(right)) < left) {
                map.put(s.charAt(right), right);
                ans = Math.max(ans, right - left + 1);
            } else {
                left = map.get(s.charAt(right)) + 1;
                map.put(s.charAt(right), right);
            }
        }
        return ans;
    }

}
