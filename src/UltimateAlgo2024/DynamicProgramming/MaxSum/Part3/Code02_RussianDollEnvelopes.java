package UltimateAlgo2024.DynamicProgramming.MaxSum.Part3;

import java.util.Arrays;

// https://leetcode.com/problems/russian-doll-envelopes/
public class Code02_RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        int len = 0;
        Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int[] ends = new int[n];
        for (int i = 0, find; i < n; i++) {
            int num = envelopes[i][1];
            find = getFind(ends, len, num);
            if (find == -1) {
                ends[len++] = num;
            } else {
                ends[find] = num;
            }
        }
        return len;
    }

    public static int getFind(int[] ends, int len, int num) {
        int l = 0, r = len - 1, m, ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (ends[m] >= num) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }
}
