package UltimateAlgo2024.SlidingWindow;

import java.util.Arrays;

// https://leetcode.com/problems/subarrays-with-k-different-integers/
public class Code06_SubarraysWithKDifferentIntegers {

    public int subarraysWithKDistinct(int[] nums, int k) {
        return numsOfMostKinds(nums, k) - numsOfMostKinds(nums, k - 1);
    }

    public static int MAXN = 20001;

    public static int[] cnts = new int[MAXN];

    // arr中有多少子数组，数字种类不超过k -> 原问题没有单调性，转化为有单调性的问题
    // arr的长度是n，arr里的数值1~n之间
    public static int numsOfMostKinds(int[] arr, int k) {
        Arrays.fill(cnts, 1, arr.length + 1, 0);
        int ans = 0;
        for (int l = 0, r = 0, collect = 0; r < arr.length; r++) {
            // 新字符
            if (++cnts[arr[r]] == 1) {
                collect++;
            }
            while (collect > k) {
                if (--cnts[arr[l++]] == 0) {
                    collect--;
                }
            }
            ans += r - l + 1;
        }
        return ans;
    }

}
