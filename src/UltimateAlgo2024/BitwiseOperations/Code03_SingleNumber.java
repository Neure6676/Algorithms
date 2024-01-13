package UltimateAlgo2024.BitwiseOperations;

// https://leetcode.com/problems/single-number/
public class Code03_SingleNumber {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }
}
