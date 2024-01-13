package UltimateAlgo2024.BitwiseOperations;

// https://leetcode.com/problems/missing-number/
public class Code02_MissingNumber {

    public int missingNumber(int[] nums) {
        int n = nums.length;
        int allSum = 0;
        for (int num : nums) {
            allSum ^= num;
        }
        for (int i = 1; i <= n; i++) {
            allSum ^= i;
        }
        return allSum;
    }
}
