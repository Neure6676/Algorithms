package ForOffer.Class4;

/**
 * 返回一个二维数组中子矩阵最大累加和
 *
 * 数组压缩
 * O(行^2 * 列)
 *
 * 本题测试链接 : https://leetcode-cn.com/problems/max-submatrix-lcci/
 */
public class Code03_SubMatrixMaxSum {


    public static int maxSum(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        // O(N^2 * M)
        int N = m.length;
        int M = m[0].length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            // 必须包含i~j行 且只包含i-j行的情况下 答案是啥
            int[] s = new int[M];
            for (int j = i; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    s[k] += m[j][k];
                }
                max = Math.max(max, maxSubArray(s));
            }
        }
        return max;
    }


    public static int maxSubArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }
}
