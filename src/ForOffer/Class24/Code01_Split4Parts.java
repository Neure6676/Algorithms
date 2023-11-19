package ForOffer.Class24;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定一个正数数组arr，长度一定大于6（>=7），一定要选3个数字做分割点，从而分出4个部分，并且每部分都有数
 * 分割点的数字直接删除，不属于任何4个部分中的任何一个。返回有没有可能分出的4个部分累加和一样大
 * 如：{3,2,3,7,4,4,3,1,1,6,7,1,5,2}。可以分成{3,2,3}、{4,4}、{1,1,6}、{1,5,2}。分割点是不算的！
 */
public class Code01_Split4Parts {

    public static boolean canSplits(int[] arr) {
        if (arr == null || arr.length < 7) {
            return false;
        }

        int N = arr.length;
        HashMap<Integer, Integer> preSum = new HashMap<>();    // 不需要遍历
        int sum = arr[0];
        for (int i = 1; i < N; i++) {
            preSum.put(sum, i);
            sum += arr[i];
        }

        int sumOfParts = arr[0];
        for (int first = 1; first < N - 5; first++) {  // 第一刀位置
            int checkSum = 2 * sumOfParts + arr[first];
            if (preSum.containsKey(checkSum)) {
                int second = preSum.get(checkSum);
                checkSum += sumOfParts + arr[second];
                if (preSum.containsKey(checkSum)) {
                    int third = preSum.get(checkSum);
                    if (checkSum + sumOfParts + arr[third] == sum) {
                        return true;
                    }
                }
            }
            sumOfParts += arr[first];
        }
        return false;
    }

    public static boolean canSplits1(int[] arr) {
        if (arr == null || arr.length < 7) {
            return false;
        }
        HashSet<String> set = new HashSet<String>();
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int leftSum = arr[0];
        for (int i = 1; i < arr.length - 1; i++) {
            set.add(String.valueOf(leftSum) + "_" + String.valueOf(sum - leftSum - arr[i]));
            leftSum += arr[i];
        }
        int l = 1;
        int lsum = arr[0];
        int r = arr.length - 2;
        int rsum = arr[arr.length - 1];
        while (l < r - 3) {
            if (lsum == rsum) {
                String lkey = String.valueOf(lsum * 2 + arr[l]);
                String rkey = String.valueOf(rsum * 2 + arr[r]);
                if (set.contains(lkey + "_" + rkey)) {
                    return true;
                }
                lsum += arr[l++];
            } else if (lsum < rsum) {
                lsum += arr[l++];
            } else {
                rsum += arr[r--];
            }
        }
        return false;
    }

    public static int[] generateRandomArray() {
        int[] res = new int[(int) (Math.random() * 10) + 7];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * 10) + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int testTime = 3000000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray();
            if (canSplits1(arr) ^ canSplits(arr)) {
                System.out.println("Error");
            }
        }
    }
}
