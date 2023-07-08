package SystematicClass.Class9;

public class RadixSort {
    
    // only for no-negative value
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length, maxbit(arr));
    }

    private static int maxbit(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);  // 找到arr中最大数
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10; // 除10取整
        }
        return res;
    }

    // L到R的范围上排序，最大位数index
    public static void radixSort(int[] arr, int L, int R, int digit) {
        final int radix = 10;
        int i = 0, j = 0;
        //有多少个数就准备多少个辅助空间
        int[] help = new int[R - L + 1];
        for (int d = 0; d <= digit; d++) { // 有多少位就进出几次
            // 10个空间  这个是优化版本 省去了那十个bucket
            // count[0] 当前位(d位)是0的数字有多少个
            // count[1] 当前位(d位)是(0和1)的数字有多少个
            // count[2] 当前位(d位)是(0、1和2)的数字有多少个
            // count[i] 当前位(d位)是(0~i)的数字有多少个
            int[] count = new int[radix]; //count[0~9]
            for (i = L; i <= R; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }
            // 变累加和
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            // 这个步骤相当于出bucket
            // 从右往左遍历
            for (i = R; i >= L; i--) {
                j = getDigit(arr[i], d);
                help[count[j] - 1] = arr[i]; //count上数是几，就去它之前的位置
                count[j]--;
            }
            for (i = L, j = 0; i <= R; i++, j++) {
                arr[i] = help[j];
            }
        }
    }

    private static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }
}
