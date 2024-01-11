package UltimateAlgo2024.Sort;

// https://leetcode.com/problems/sort-an-array/
public class Code02_MergeSort {

    public static int[] sortArray(int[] arr) {
        if (arr.length > 1) {
            mergeSort(arr, 0, arr.length - 1);
        }
        return arr;
    }

    public static int length = 50001;

    public static int[] help = new int[length];

    public static void mergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int m = (l + r) / 2;
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int leftPointer = l;
        int rightPointer = m + 1;
        int count = l;
        while (leftPointer <= m && rightPointer <= r) {
            help[count++] = arr[leftPointer] <= arr[rightPointer] ? arr[leftPointer++] : arr[rightPointer++];
        }
        while (leftPointer <= m) {
            help[count++] = arr[leftPointer++];
        }
        while (rightPointer <= r) {
            help[count++] = arr[rightPointer++];
        }
        for(int i = l; i <= r; i++) {
            arr[i] = help[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {-1,2,-8,-10};
        arr = sortArray(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

}
