package ForOffer.Class24;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/minimum-window-substring/
 * 给定两个字符串str1和str2，在str1中寻找一个最短子串，能包含str2的所有字符，字符顺序无所谓，str1的这个最短子串也可以包含多余的字符，返回这个最短包含子串
 */
public class Code05_MinWindowLength {

    // 有单调性就有滑动窗口的方法
    // 本题单调性：当str1范围变大时，一定会包含下更多str2的字符
    // str2: bcca
    // 欠债map：b:1, c:2, a:1, all:4
    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        char[] str = s.toCharArray();
        char[] target = t.toCharArray();
        int[] map = new int[256];
        for (char cha : target) {
            map[cha]++;
        }
        int all = target.length;
        int L = 0;
        int R = 0;
        int minLen = Integer.MAX_VALUE;
        int ansl = -1;
        int ansr = -1;
        while (R != str.length) {
            map[str[R]]--;
            if (map[str[R]] >= 0) {
                all--;
            }
            if (all == 0) {
                while (map[str[L]] < 0) {
                    map[str[L++]]++;
                }
                if (minLen > R - L + 1) {
                    minLen = R - L + 1;
                    ansl = L;
                    ansr = R;
                }
                map[str[L++]]++;
                all++;
            }
            R++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(ansl, ansr + 1);
    }

}
