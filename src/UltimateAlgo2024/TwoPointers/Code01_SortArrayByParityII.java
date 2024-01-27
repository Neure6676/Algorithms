package UltimateAlgo2024.TwoPointers;

// https://leetcode.com/problems/sort-array-by-parity-ii/
public class Code01_SortArrayByParityII {
    // 从最后一个位置往当前odd或even位置发货
    public int[] sortArrayByParityII(int[] nums) {
        int odd = 1, even = 0;
        int n = nums.length;
        while (odd < n && even < n) {
            if (nums[n - 1] % 2 == 0) {
                swap(nums, even, n - 1);
                even += 2;
            } else {
                swap(nums, odd, n - 1);
                odd += 2;
            }
        }
        return nums;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
