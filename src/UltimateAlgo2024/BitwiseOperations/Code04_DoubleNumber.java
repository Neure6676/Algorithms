package UltimateAlgo2024.BitwiseOperations;

// https://leetcode.com/problems/single-number-iii/
public class Code04_DoubleNumber {

    public int getRightMostOne(int num) {
        return num & (~num + 1);
    }

    public int[] singleNumber(int[] nums) {
        int eorAll = 0;
        for (int num : nums) {
            eorAll ^= num;
        }
        int theOne = getRightMostOne(eorAll);
        int eorT = 0;
        for (int num : nums) {
            if ((num & theOne) == theOne) {
                eorT ^= num;
            }
        }
        int[] ans = {eorT, eorAll ^ eorT};
        return ans;
    }

}
