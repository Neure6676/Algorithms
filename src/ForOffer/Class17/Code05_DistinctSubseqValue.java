package ForOffer.Class17;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

// 本题测试链接 : https://leetcode.com/problems/distinct-subsequences-ii/
// 一开始是一个空集，每遍历一个，在之前的基础上都加这一个
// s = "123"
// 0: {}
// 1: {}{1}
// 2: {}{1}{2}{12}
// 3: {}{1}{2}{12}{3}{13}{23}{123}
// 若有重复字符 要去掉重复的（上次出现改字符时以它结尾的集合）
public class Code05_DistinctSubseqValue {

    /**
     * Input: s = "abc"
     * Output: 7
     * Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".
     */
    public int distinctSubseqII(String S) {
        if (S == null || S.length() == 0) {
            return 0;
        }
        int m = 1000000007;
        char[] s = S.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>(); // 上一次出现该字符时以它结尾的字符有几个
        int all = 1; // 空集
        for (char c : s) {
            int add = all; //all中所有集合加个c
            int curAll = all;
            curAll = (curAll + add) % m;
            curAll = (curAll - (map.containsKey(c) ? map.get(c) : 0) + m) % m; // +m防止为负数
            all = curAll;
            map.put(c, add);
        }
        return all - 1; // 不算空集
    }


}
