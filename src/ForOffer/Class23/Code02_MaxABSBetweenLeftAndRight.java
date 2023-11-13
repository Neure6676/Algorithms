package ForOffer.Class23;

import java.util.Arrays;

/**
 * 给定一个数组arr，长度为N > 1，从中间切一刀，保证左部分和右部分都有数字，一共有N-1种切法
 * 如此多的切法中，每一种都有:绝对值(左部分最大值 – 右部分最大值)，返回最大的绝对值是多少
 */
public class Code02_MaxABSBetweenLeftAndRight {

    // O(N^2)
    public static int maxABS1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int ans = 0;
        // divider
        for (int i = 1; i < N; i++) {
            int leftMax = arr[0];
            int rightMax = arr[N - 1];
            // leftMax
            for (int l = 0; l < i; l++) {
                leftMax = Math.max(leftMax, arr[l]);
            }
            // rightMax
            for (int r = i; r < N; r++) {
                rightMax = Math.max(rightMax, arr[r]);
            }
            ans = Math.max(ans, Math.abs(leftMax - rightMax));
        }
        return ans;
    }


    // O(N) LeftMax[] rightMax[]
    public static int maxABS2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int ans = 0;

        int[] leftMax = new int[N];
        int[] rightMax = new int[N];
        leftMax[0] = arr[0];
        rightMax[N - 1] = arr[N - 1];
        for (int i = 1; i < N; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
            rightMax[N - 1 - i] = Math.max(rightMax[N - i], arr[N - 1 - i]);
        }

        for (int i = 1; i < N; i++) {
            ans = Math.max(Math.abs(leftMax[i - 1] - rightMax[i]), ans);
        }
        return ans;
    }

    // O(N) best!
    public static int maxABS3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int max = 0;
        for (int num : arr) {
            max = Math.max(num, max);
        }
        return max - Math.min(arr[0], arr[arr.length - 1]);
    }

    public static int[] generateRandomArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i != arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000) - 499;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = generateRandomArray(200);
        System.out.println(maxABS1(arr));
        System.out.println(maxABS2(arr));
        System.out.println(maxABS3(arr));
    }
}
