package ForOffer.Class23;

import java.util.HashSet;
import java.util.Set;

/**
 * 定义什么是可整合数组：一个数组排完序之后，除了最左侧的数外，有arr[i] = arr[i-1]+1
 * 则称这个数组为可整合数组比如{5,1,2,4,3}、{6,2,3,1,5,4}都是可整合数组，返回arr中最长可整合子数组的长度
 */
public class Code03_LongestIntegratedLength {

    // 可整合：1.无重复 2.max - min = 个数 - 1
    public static int maxLen(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int ans = 1; // 最少是1，只有一个数
        Set<Integer> set = new HashSet<>();
        for (int L = 0; L < N; L++) {
            set.clear(); // clear set
            int min = arr[L];
            int max = arr[L];
            set.add(arr[L]);
            for (int R = L + 1; R < N; R++) {
                // L...R
                if (set.contains(arr[R])) {
                    break;    // 如果有重复值直接break L++
                }
                set.add(arr[R]);
                min = Math.min(min, arr[R]);
                max = Math.max(max, arr[R]);
                if (max - min == R - L) {
                    ans = Math.max(ans, R - L + 1);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 5, 5, 3, 2, 6, 4, 3};
        System.out.println(maxLen(arr));
    }
}
