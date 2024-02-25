package UltimateAlgo2024.DynamicProgramming.MaxSum.Part3;

// https://leetcode.com/problems/minimum-operations-to-make-the-array-k-increasing/
public class Code03_MinimumOperationsToMakeArraykIncreasing {

    public static int MAXN = 100001;

    public static int[] nums = new int[MAXN];

    public static int[] ends = new int[MAXN];

    public int kIncreasing(int[] arr, int k) {
        int n = arr.length;
        int ans = 0;
        for (int i = 0, size; i < k; i++) {
            size = 0;
            // 把每一组的数字放入容器
            for (int j = i; j < n; j += k) {
                nums[size++] = arr[j];
            }
            // 当前组长度 - 当前组最长不下降子序列长度 = 当前组至少需要修改的数字个数
            ans += size - lengthOfNoDecreasing(size);
        }
        return ans;
    }

    public static int lengthOfNoDecreasing(int size) {
        int len = 0;
        for (int i = 0, find; i < size; i++) {
            find = bs(len, nums[i]);
            if (find == -1) {
                ends[len++] = nums[i];
            } else {
                ends[find] = nums[i];
            }
        }
        return len;
    }

    public static int bs(int len, int num) {
        int l = 0, r = len - 1, m, ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (num < ends[m]) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }
}
