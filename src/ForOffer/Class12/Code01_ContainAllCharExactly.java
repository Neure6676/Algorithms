package ForOffer.Class12;

/**
 * 本题测试链接 : https://leetcode.com/problems/permutation-in-string/
 * 给定长度为m的字符串aim，以及一个长度为n的字符串str，问能否在str中找到一个长度为m的连续子串，
 * 使得这个子串刚好由aim的m个字符组成，顺序无所谓，返回任意满足条件的一个子串的起始位置，未找到返回-1
 */
public class Code01_ContainAllCharExactly {

    public static int containExactly(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < s2.length()) {
            return -1;
        }
        char[] str2 = s2.toCharArray();
        int M = str2.length;
        int[] count = new int[256]; // 欠债表 ASCII 256
        for (int i = 0; i < M; i++) {
            count[str2[i]]++;
        }
        char[] str1 = s1.toCharArray();
        int all = M;
        // 先形成一个M长的窗口
        int i = 0;
        for (; i < M; i++) {
            if (count[str1[i]]-- > 0) {
                all--;
            }
        }
        // 窗口初步形成了，并没有判断有效无效，下一个位置一上来判断
        // 接下来的过程，窗口右进一个，左吐一个
        for (; i < str1.length; i++) {
            if (all == 0) {
                return i - M;
            }
            // 进一个
            if (count[str1[i]]-- > 0) {
                all--;
            }
            // 出一个
            if (count[str1[i - M]]++ >= 0) {
                all++;
            }
        }
        // 最后一个窗口还没判断
         return all == 0 ? i - M : -1;
    }
}
