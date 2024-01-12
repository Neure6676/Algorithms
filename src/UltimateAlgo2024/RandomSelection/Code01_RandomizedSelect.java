package UltimateAlgo2024.RandomSelection;

// https://leetcode.com/problems/kth-largest-element-in-an-array/
public class Code01_RandomizedSelect {

    public static int first;

    public static int last;

    public int findKthLargest(int[] arr, int k) {
        if (k > arr.length) {
            return -1;
        }
        process(arr, 0, arr.length - 1, k);
        return arr[arr.length - k];
    }

    public static void process(int[] arr, int l, int r, int k) {
        if (l >= r) {
            return;
        }
        int x = arr[l + (int) (Math.random() * (r - l + 1))];
        partition(arr, l, r, x);
        if (last >= arr.length - k && first <= arr.length - k) {
            return;
        } else if (last < arr.length - k) {
            process(arr, last + 1, r, k);
        } else {
            process(arr, l, first - 1, k);
        }
    }

    public static void partition(int[] arr, int l, int r, int x) {
        first = l;
        last = r;
        int i = l;
        while (i <= last) {
            if (arr[i] == x) {
                i++;
            } else if (arr[i] < x) {
                swap(arr, i++, first++);
            } else {
                swap(arr, i, last--);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
