package UltimateAlgo2024.DynamicProgramming.MaxSum.Part1;


// https://leetcode.com/problems/maximum-subarray/
public class Code01_MaximumSubarray {

    public static int maxSubArray(int[] arr) {
        int pre = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            pre = Math.max(arr[i], arr[i] + pre);
            max =  Math.max(max, pre);
        }
        return max;
    }
}
