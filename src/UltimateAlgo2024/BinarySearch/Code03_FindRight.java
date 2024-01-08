package UltimateAlgo2024.BinarySearch;

import java.util.Arrays;

public class Code03_FindRight {

    public static int rightMost(int[] arr, int tar) {
        int ans = -1;
        int l = 0, r = arr.length - 1, mid = 0;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (arr[mid] <= tar) {
                ans = mid;
                l = mid + 1;
            } else  {
                r = mid - 1;
            }
        }
        return ans;
    }

    // for test
    public static void main(String[] args) {
        int N = 100;
        int V = 1000;
        int testTime = 500000;
        System.out.println("Start");
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * N);
            int[] arr = randomArray(n, V);
            Arrays.sort(arr);
            int num = (int) (Math.random() * N);
            if (eval(arr, num) != rightMost(arr, num)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("Finished");
    }

    // for text
    public static int[] randomArray(int n, int v) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * v) + 1;
        }
        return arr;
    }


    public static int eval(int[] arr, int num) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= num) {
                return i;
            }
        }
        return -1;
    }
}
