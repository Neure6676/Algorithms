package ForOffer.Class7;

/**
 * 给定一个非负数组成的数组，长度一定大于1，想知道数组中哪两个数&的结果最大，
 * 返回这个最大结果。要求时间复杂度O(N)，额外空间复杂度O(1)
 */
public class Code01_MaxAndValue {

    // 1010 0101 1100 0010 0001
    // 先紧高位
    public static int maxAndValue2(int[] arr) {
        // arr[0..M-1]    arr[M..]是垃圾区域
        int M = arr.length;
        int ans = 0;
        for (int bit = 30; bit >= 0; bit--) {
            int i = 0;
            int tmp = M;
            while (i < M) { // arr[0...M-1]
                if ((arr[i] & (1 << bit)) == 0) {
                    swap(arr, i, --M);
                } else {
                    i++;
                }
            }
            if (M == 2) { // arr[0,1] 正好有两个
                return arr[0] & arr[1];
            }
            if (M < 2) {
                M = tmp;  // 这一轮都没淘汰
            } else { // > 2个数  bit位上有1
                ans |= (1 << bit);
            }
        }
        return ans;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    // O(N^2)的暴力解
    public static int maxAndValue1(int[] arr) {
        int N = arr.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                max = Math.max(max, arr[i] & arr[j]);
            }
        }
        return max;
    }

    public static int[] randomArray(int size, int range) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * range) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxSize = 50;
        int range = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int size = (int) (Math.random() * maxSize) + 2;
            int[] arr = randomArray(size, range);
            int ans1 = maxAndValue1(arr);
            int ans2 = maxAndValue2(arr);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }
}
