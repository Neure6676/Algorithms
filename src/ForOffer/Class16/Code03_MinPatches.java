package ForOffer.Class16;

import java.util.Arrays;

// https://leetcode.com/problems/patching-array/
public class Code03_MinPatches {

    // arr请保证有序，且正数  1~n
    public static int minPatches(int[] arr, int aim) {
        int patches = 0; // 缺多少个数字
        long range = 0; // 已经完成了1 ~ range的目标
        Arrays.sort(arr);
        for (int i = 0; i != arr.length; i++) {
            // arr[i]
            // 要求：1 ~ arr[i]-1 范围被搞定！
            while (arr[i] - 1 > range) { // arr[i] 1 ~ arr[i]-1
                range += range + 1; // range + 1 是缺的数字
                patches++;
                if (range >= aim) {
                    return patches;
                }
            }
            // 要求被满足了！
            range += arr[i];
            if (range >= aim) {
                return patches;
            }
        }
        while (aim >= range + 1) {
            range += range + 1;
            patches++;
        }
        return patches;
    }


}
