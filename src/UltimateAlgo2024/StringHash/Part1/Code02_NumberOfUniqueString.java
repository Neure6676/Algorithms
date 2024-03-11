package UltimateAlgo2024.StringHash.Part1;

import java.util.Arrays;
import java.util.HashSet;

// https://leetcode.com/problems/unique-substrings-with-equal-digit-frequency/
public class Code02_NumberOfUniqueString {

    public int equalDigitFrequency(String str) {
        long base = 499;
        char[] s = str.toCharArray();
        int n = s.length;
        HashSet<Long> set = new HashSet<>();
        int[] cnt = new int[10];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cnt, 0);
            long hashCode = 0;
            // maxCnt最大词频
            // maxCntKinds有几种字符此时词频为最大词频
            // allKinds一共几种字符
            int curVal = 0, maxCnt = 0, maxCntKinds = 0, allKinds = 0;
            for (int j = i; j < n; j++) {
                curVal = s[j] - '0';
                // '0' -> 1
                // '1' -> 2
                // '9' -> 10
                hashCode = hashCode * base + curVal + 1;
                cnt[curVal]++;
                if (cnt[curVal] == 1) {
                    allKinds++;
                }
                if (cnt[curVal] > maxCnt) {
                    maxCnt = cnt[curVal];
                    maxCntKinds = 1;
                } else if (cnt[curVal] == maxCnt) {
                    maxCntKinds++;
                }
                if (maxCntKinds == allKinds) {
                    set.add(hashCode);
                }
            }
        }
        return set.size();
    }
}
