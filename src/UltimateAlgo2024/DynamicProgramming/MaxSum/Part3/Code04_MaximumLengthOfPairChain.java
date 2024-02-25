package UltimateAlgo2024.DynamicProgramming.MaxSum.Part3;

import java.util.Arrays;

// https://leetcode.com/problems/maximum-length-of-pair-chain/
public class Code04_MaximumLengthOfPairChain {

    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int[] ends = new int[pairs.length];
        int len = 0;
        for (int i = 0, find; i < pairs.length; i++) {
            int[] num = pairs[i];
            find = bs(ends, len, num[0]);
            if (find == -1) {
                ends[len++] = num[1];
            } else {
                ends[find] = Math.min(ends[find], num[1]);
            }
        }
        return len;
    }

    public static int bs(int[] ends, int len, int num) {
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
