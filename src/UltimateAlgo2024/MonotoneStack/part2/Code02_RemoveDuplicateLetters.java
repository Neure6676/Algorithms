package UltimateAlgo2024.MonotoneStack.part2;

import java.util.Arrays;

// https://leetcode.com/problems/remove-duplicate-letters/
public class Code02_RemoveDuplicateLetters {

    public static int MAX = 10001;

    public static char[] stack = new char[MAX];

    // 后面各种字符还有几个
    public static int[] map = new int[26];

    // 每种字符目前有没有进栈
    public static boolean[] enter = new boolean[MAX];

    public static int r;

    public String removeDuplicateLetters(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        Arrays.fill(enter, false);
        // get map
        for (int i = 0; i < n; i++) {
            map[c[i] - 'a']++;
        }
        // get stack
        r = 0;
        for (char cur : c) {
            if (!enter[cur - 'a']) {
                while (r > 0 && stack[r - 1] > cur && map[stack[r - 1] - 'a'] > 0) {
                    enter[stack[--r] - 'a'] = false;
                }
                stack[r++] = cur;
                enter[cur - 'a'] = true;
            }
            map[cur - 'a']--;
        }
        return String.valueOf(stack, 0, r);
    }
}
