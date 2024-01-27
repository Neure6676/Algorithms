package UltimateAlgo2024.TwoPointers;

// https://leetcode.com/problems/first-missing-positive/

// l的左边，都是做到i位置上放着i+1的区域
// 永远盯着l位置的数字看，看能不能扩充(l++)
// [r....]垃圾区
// 最好的状况下，认为1~r是可以收集全的，每个数字收集1个，不能有垃圾
// 有垃圾呢？预期就会变差(r--)
public class Code07_FirstMissingPositive {

    public int firstMissingPositive(int[] arr) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            if (arr[l] == l + 1) {
                l++;
            } else if (arr[l] <= l || arr[l] > r || arr[l] == arr[arr[l] - 1]) {
                swap(arr, l, --r);
            } else {
                swap(arr, l, arr[l] - 1);
            }
        }
        return l + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
